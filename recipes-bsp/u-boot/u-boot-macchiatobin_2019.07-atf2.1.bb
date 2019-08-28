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

U_BOOT_PV="${@d.getVar('PV').split('-atf')[0]}"
ATF_PV="${@d.getVar('PV').split('-atf')[1]}"

require recipes-bsp/u-boot/u-boot-custom.inc
require recipes-bsp/arm-trusted-firmware/arm-trusted-firmware_${ATF_PV}.inc

SRC_URI += " \
    ftp://ftp.denx.de/pub/u-boot/u-boot-${U_BOOT_PV}.tar.bz2;name=u-boot \
    git://github.com/MarvellEmbeddedProcessors/mv-ddr-marvell;protocol=https;branch=mv_ddr-armada-atf-mainline;rev=${MV_DDR_REV};destsuffix=mv-ddr-marvell \
    git://github.com/MarvellEmbeddedProcessors/binaries-marvell;protocol=https;branch=${MV_BIN_BRANCH};rev=${MV_BIN_REV};destsuffix=binaries-marvell \
    file://macchiatobin-rules \
    "
SRC_URI[u-boot.sha256sum] = "bff4fa77e8da17521c030ca4c5b947a056c1b1be4d3e6ee8637020b8d50251d0"

BUILD_DEPENDS =. "libssl-dev:native, "

MV_DDR_REV="a881467ef0f0185e6570dd0483023fde93cbb5f5"
MV_BIN_BRANCH="binaries-marvell-armada-18.12"
MV_BIN_REV="c6c529ea3d905a28cc77331964c466c3e2dc852e"

U_BOOT_CONFIG="mvebu_mcbin-88f8040_defconfig"
U_BOOT_BIN="u-boot.bin"

S = "${WORKDIR}/u-boot-${U_BOOT_PV}"

do_prepare_build_append() {
    cp ${WORKDIR}/macchiatobin-rules ${S}/debian/rules

    echo "../arm-trusted-firmware-${ATF_PV}/build/a80x0_mcbin/release/flash-image.bin /usr/lib/u-boot/macchiatobin" > \
        ${S}/debian/u-boot-macchiatobin.install
}
