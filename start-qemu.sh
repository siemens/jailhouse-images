#!/bin/sh
#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

usage()
{
	echo "Usage: $0 ARCHITECTURE [QEMU_OPTIONS]"
	echo -e "\nSet QEMU_PATH environment variable to use a locally " \
		"built QEMU version"
	exit 1
}

if [ -n "${QEMU_PATH}" ]; then
	QEMU_PATH="${QEMU_PATH}/"
fi

case "$1" in
	x86|x86_64|amd64)
		DISTRO_ARCH=amd64
		QEMU=qemu-system-x86_64
		QEMU_EXTRA_ARGS=" \
			-cpu kvm64,-kvm_pv_eoi,-kvm_steal_time,-kvm_asyncpf,-kvmclock,+vmx,+arat \
			-smp 4 \
			-enable-kvm -machine q35,kernel_irqchip=split \
			-serial vc \
			-device ide-hd,drive=disk \
			-device intel-iommu,intremap=on,x-buggy-eim=on \
			-device intel-hda,addr=1b.0 -device hda-duplex \
			-device e1000e,addr=2.0,netdev=net"
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
			-machine virt,gic-version=3,virtualization=on \
			-device virtio-serial-device \
			-device virtconsole,chardev=con -chardev vc,id=con \
			-device virtio-blk-device,drive=disk \
			-device virtio-net-device,netdev=net"
		KERNEL_CMDLINE=" \
			root=/dev/vda mem=768M"
		;;
	""|--help)
		usage
		;;
	*)
		echo "Unsupported architecture: $1"
		exit 1
		;;
esac

IMAGE_PREFIX="$(dirname $0)/build/tmp/deploy/images/qemu-${DISTRO_ARCH}/demo-image-jailhouse-demo-qemu-${DISTRO_ARCH}"
IMAGE_FILE=$(ls ${IMAGE_PREFIX}.ext4.img)

shift 1

${QEMU_PATH}${QEMU} \
	-drive file=${IMAGE_FILE},discard=unmap,if=none,id=disk,format=raw \
	-m 1G -serial mon:stdio -netdev user,id=net \
	-kernel ${IMAGE_PREFIX}-vmlinuz -append "${KERNEL_CMDLINE}" \
	-initrd ${IMAGE_PREFIX}-initrd.img ${QEMU_EXTRA_ARGS} "$@"
