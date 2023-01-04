#!/bin/sh
#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2023
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

usage()
{
	printf "%b" "Usage: $0 ARCHITECTURE [QEMU_OPTIONS]\n"
	printf "%b" "\nSet QEMU_PATH environment variable to use a locally " \
		    "built QEMU version\n"
	exit 1
}

if [ -n "${QEMU_PATH}" ]; then
	QEMU_PATH="${QEMU_PATH}/"
fi

case "$1" in
	x86|x86_64|amd64)
		DISTRO_ARCH=amd64
		QEMU=qemu-system-x86_64
		CPU_FEATURES="-kvm-pv-eoi,-kvm-pv-ipi,-kvm-asyncpf,-kvm-steal-time,-kvmclock"

		# qemu >= 5.2 has kvm-asyncpf-int which needs to be disabled
		if ${QEMU} -cpu help | grep kvm-asyncpf-int > /dev/null; then
			CPU_FEATURES="${CPU_FEATURES},-kvm-asyncpf-int"
		fi

		QEMU_EXTRA_ARGS=" \
			-cpu host,${CPU_FEATURES} \
			-smp 4 \
			-enable-kvm -machine q35,kernel_irqchip=split \
			-serial vc \
			-device ide-hd,drive=disk \
			-device intel-iommu,intremap=on,x-buggy-eim=on \
			-device intel-hda,addr=1b.0 -device hda-duplex \
			-device e1000e,addr=2.0,netdev=net \
			-device pcie-pci-bridge"
		KERNEL_SUFFIX=vmlinuz
		KERNEL_CMDLINE=" \
			root=/dev/sda intel_iommu=off memmap=82M\$0x3a000000 \
			vga=0x305"
		;;
	arm64|aarch64)
		DISTRO_ARCH=arm64
		QEMU=qemu-system-aarch64
		QEMU_EXTRA_ARGS=" \
			-cpu cortex-a57 \
			-smp 16 \
			-machine virt,gic-version=3,virtualization=on,its=off \
			-device virtio-serial-device \
			-device virtconsole,chardev=con -chardev vc,id=con \
			-device virtio-blk-device,drive=disk \
			-device virtio-net-device,netdev=net"
		KERNEL_SUFFIX=vmlinux
		KERNEL_CMDLINE=" \
			root=/dev/vda mem=768M"
		;;
	arm)
		DISTRO_ARCH=arm
		QEMU=qemu-system-arm
		QEMU_EXTRA_ARGS=" \
			-cpu cortex-a15 \
			-smp 8 \
			-machine virt,virtualization=on,highmem=off \
			-device virtio-serial-device \
			-device virtconsole,chardev=con -chardev vc,id=con \
			-device virtio-blk-device,drive=disk \
			-device virtio-net-device,netdev=net"
		KERNEL_SUFFIX=vmlinuz
		KERNEL_CMDLINE=" \
			root=/dev/vda mem=768M vmalloc=768M"
		;;
	""|--help)
		usage
		;;
	*)
		echo "Unsupported architecture: $1"
		exit 1
		;;
esac

IMAGE_PREFIX="$(dirname "$0")/build/tmp/deploy/images/qemu-${DISTRO_ARCH}/demo-image-jailhouse-demo-qemu-${DISTRO_ARCH}"
IMAGE_FILE=$(ls "${IMAGE_PREFIX}.ext4.img")

shift 1

# SC2086: Double quote to prevent globbing and word splitting.
# shellcheck disable=2086
"${QEMU_PATH}${QEMU}" \
	-drive file="${IMAGE_FILE}",discard=unmap,if=none,id=disk,format=raw \
	-m 1G -serial mon:stdio -netdev user,id=net \
	-kernel "${IMAGE_PREFIX}-${KERNEL_SUFFIX}" -append "${KERNEL_CMDLINE}" \
	-initrd "${IMAGE_PREFIX}-initrd.img" ${QEMU_EXTRA_ARGS} "$@"
