#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2019
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
    file://debian/ \
    file://${DISTRO_ARCH}-config \
    file://overlay/"
SRC_URI[sha256sum] = "0250b4e11d4aeba7cb5ac956b56e3069d3136c1e7fd741a658b0ea96c6b35181"

S = "${WORKDIR}/buildroot-${PV}"

do_prepare_build() {
	cd ${WORKDIR}

	cp -r debian ${S}
	deb_add_changelog

	ln -sf ${DISTRO_ARCH}-config .config
}
