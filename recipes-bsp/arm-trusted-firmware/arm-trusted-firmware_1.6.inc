#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018, 2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

FILESPATH =. "${LAYERDIR_jailhouse}/recipes-bsp/arm-trusted-firmware/files:"

SRC_URI += " \
    https://github.com/ARM-software/arm-trusted-firmware/archive/v${ATF_PV}.tar.gz;name=atf \
    file://0001-tools-Fix-broken-object-compilation-rules.patch;apply=no \
    "
SRC_URI[atf.sha256sum] = "62120368f2196d3e126296c8116f3399568e100960a5122e52017d22766b7009"

do_prepare_build_append() {
    patch -d ${WORKDIR}/arm-trusted-firmware-${ATF_PV} -p1 \
        -i ${WORKDIR}/0001-tools-Fix-broken-object-compilation-rules.patch
}
