#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: GPL-2.0
#

inherit dpkg

DESCRIPTION = "Linux initramfs for Jailhouse non-root cells"

SRC_URI = " \
    https://buildroot.org/downloads/buildroot-${PV}.tar.bz2 \
    file://debian/ \
    file://${DISTRO_ARCH}-config \
    file://overlay/"
SRC_URI[sha256sum] = "e75b1a8cb0adb6ef6db9a809d4f4a8bb6494206ed0c4145e773b69251df92a01"

S = "${WORKDIR}/buildroot-${PV}"

dpkg_runbuild_prepend() {
	cd ${WORKDIR}

	cp -r debian ${S}
	sed -i 's/@PV@/${PV}/' ${S}/debian/changelog

	sed -i 's|^BR2_DL_DIR=.*|BR2_DL_DIR="/downloads/buildroot"|' ${DISTRO_ARCH}-config
	ln -sf ${DISTRO_ARCH}-config .config
}
