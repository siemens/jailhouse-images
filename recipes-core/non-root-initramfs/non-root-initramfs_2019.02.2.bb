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
    https://raw.githubusercontent.com/siemens/jailhouse/${SRC_REV_IVSHMEM_DEMO}/tools/ivshmem-demo.c;name=ivshmem-demo \
    file://debian/ \
    file://${DISTRO_ARCH}-config \
    file://build-ivshmem-demo.sh \
    file://overlay/"
SRC_URI[sha256sum] = "0250b4e11d4aeba7cb5ac956b56e3069d3136c1e7fd741a658b0ea96c6b35181"

SRC_REV_IVSHMEM_DEMO = "87cc49f944ed756e930b70991726ca5595a30717"
SRC_URI[ivshmem-demo.sha256sum] = "e7cc426bb009c3c7d99c042ae45760494d8454647fbb042359655ea37ad1dcfc"

S = "${WORKDIR}/buildroot-${PV}"

do_prepare_build() {
	cd ${WORKDIR}

	cp -r debian ${S}
	deb_add_changelog

	ln -sf ${DISTRO_ARCH}-config .config
}
