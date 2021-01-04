#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

inherit dpkg

DESCRIPTION = "Linux initramfs for Jailhouse non-root cells"

SRC_URI = " \
    https://buildroot.org/downloads/buildroot-${PV}.tar.bz2 \
    https://raw.githubusercontent.com/siemens/jailhouse/${SRC_REV_IVSHMEM_DEMO}/tools/demos/ivshmem-demo.c;name=ivshmem-demo \
    file://debian/ \
    file://${DISTRO_ARCH}-config \
    file://build-ivshmem-demo.sh \
    file://overlay/"
SRC_URI[sha256sum] = "2d86279c26a2745b044e0b1cfa9bee17bd3b211e889f9a493defeed0ffaad588"

SRC_REV_IVSHMEM_DEMO = "9325b765d1ef16af3ad6e84f6dee872cd9e553c9"
SRC_URI[ivshmem-demo.sha256sum] = "7053e9f6c05e3c73b2b095f02c55b1c1d8a7f9760c9bd0b006c09d53a2acc024"

S = "${WORKDIR}/buildroot-${PV}"

do_prepare_build() {
	cd ${WORKDIR}

	cp -r debian ${S}
	deb_add_changelog

	ln -sf ${DISTRO_ARCH}-config .config
}

do_cleanall[cleandirs] += "${DL_DIR}/buildroot"
