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

require recipes-bsp/u-boot/u-boot-custom.inc

SRC_URI += " \
    ftp://ftp.denx.de/pub/u-boot/u-boot-${U_BOOT_PV}.tar.bz2;name=u-boot \
    https://github.com/ARM-software/arm-trusted-firmware/archive/v${ATF_PV}.tar.gz;name=atf \
    git://github.com/MarvellEmbeddedProcessors/mv-ddr-marvell;protocol=https;branch=mv_ddr-armada-atf-mainline;rev=${MV_DDR_REV};destsuffix=mv-ddr-marvell \
    git://github.com/MarvellEmbeddedProcessors/binaries-marvell;protocol=https;branch=${MV_BIN_BRANCH};rev=${MV_BIN_REV};destsuffix=binaries-marvell \
    git://github.com/MarvellEmbeddedProcessors/ble-marvell.git;protocol=https;branch=atf-mainline;rev=${MV_BLE_REV};destsuffix=ble-marvell \
    file://0001-tools-Fix-broken-object-compilation-rules.patch;apply=no \
    file://macchiatobin-rules \
    "
SRC_URI[u-boot.sha256sum] = "839bf23cfe8ce613a77e583a60375179d0ad324e92c82fbdd07bebf0fd142268"
SRC_URI[atf.sha256sum] = "62120368f2196d3e126296c8116f3399568e100960a5122e52017d22766b7009"

U_BOOT_PV="2018.09"
ATF_PV="1.6"
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

    patch -d ${WORKDIR}/arm-trusted-firmware-${ATF_PV} -p1 \
        -i ${WORKDIR}/0001-tools-Fix-broken-object-compilation-rules.patch
}
