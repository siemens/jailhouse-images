#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

inherit dpkg

DESCRIPTION = "ZynqMP PMU Firmware"

SRC_URI = " \
    https://github.com/Xilinx/embeddedsw/archive/xilinx-v${PV}.tar.gz \
    file://debian/"
SRC_URI[sha256sum] = "0b36721d62f970b1873fd337e94ee13304500ecec1dd5dbfc4f0ed952e55cf5f"

DEPENDS = "crosstool-ng-microblaze"

TEMPLATE_FILES = "debian/control.tmpl"

S = "${WORKDIR}/embeddedsw-xilinx-v${PV}"

do_prepare_build() {
    cp -r ${WORKDIR}/debian ${S}
    deb_add_changelog
}
