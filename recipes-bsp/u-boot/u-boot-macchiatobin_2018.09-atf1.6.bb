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
    git://github.com/MarvellEmbeddedProcessors/ble-marvell.git;protocol=https;branch=atf-mainline;rev=${MV_BLE_REV};destsuffix=ble-marvell \
    file://macchiatobin-rules \
    "
SRC_URI[u-boot.sha256sum] = "839bf23cfe8ce613a77e583a60375179d0ad324e92c82fbdd07bebf0fd142268"

BUILD_DEPENDS =. "libssl-dev:native, "

MV_DDR_REV="779e860c3b81eda192dd40270f46c0ff44e52113"
MV_BIN_BRANCH="binaries-marvell-armada-18.06"
MV_BIN_REV="14481806e699dcc6f7025dbe3e46cf26bb787791"
MV_BLE_REV="61d305e3869dec8c8c5b8c2fd985548f9a424688"

U_BOOT_CONFIG="mvebu_mcbin-88f8040_defconfig"
U_BOOT_BIN="u-boot.bin"

S = "${WORKDIR}/u-boot-${U_BOOT_PV}"

do_prepare_build_append() {
    cp ${WORKDIR}/macchiatobin-rules ${S}/debian/rules

    echo "../arm-trusted-firmware-${ATF_PV}/build/a80x0_mcbin/release/flash-image.bin /usr/lib/u-boot/macchiatobin" > \
        ${S}/debian/u-boot-macchiatobin.install
}
