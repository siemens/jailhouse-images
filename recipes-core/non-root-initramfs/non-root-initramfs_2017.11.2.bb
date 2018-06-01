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
SRC_URI[sha256sum] = "736d38933b93cc2365950f58dbabe8803b3c2b029457b8fe7e5323df39a39019"

S = "${WORKDIR}/buildroot-${PV}"

dpkg_runbuild_prepend() {
	cd ${WORKDIR}

	cp -r debian ${S}
	sed -i 's/@PV@/${PV}/' ${S}/debian/changelog

	sed -i 's|^BR2_DL_DIR=.*|BR2_DL_DIR="/downloads/buildroot"|' ${DISTRO_ARCH}-config
	ln -sf ${DISTRO_ARCH}-config .config
}
