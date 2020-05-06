#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Vijai Kumar K, 2020
#
# Authors:
#  Vijai Kumar K <vijaikumar.kanagarajan@gmail.com>
#
# SPDX-License-Identifier: MIT
#

U_BOOT_PV="${@d.getVar('PV').split('-atf')[0]}"
ATF_PV="${@d.getVar('PV').split('-atf')[1]}"

require recipes-bsp/u-boot/u-boot-custom.inc
require recipes-bsp/arm-trusted-firmware/arm-trusted-firmware_${ATF_PV}.inc

SRC_URI += " \
    https://ftp.denx.de/pub/u-boot/u-boot-${U_BOOT_PV}.tar.bz2;name=u-boot \
    file://pine64-plus-rules \
    "
SRC_URI[u-boot.sha256sum] = "8d6d6070739522dd236cba7055b8736bfe92b4fac0ea18ad809829ca79667014"

U_BOOT_CONFIG="pine64_plus_defconfig"
U_BOOT_BIN="u-boot-sunxi-with-spl.bin"

BUILD_DEPENDS += ", libssl-dev, swig:native, python-dev:native"

S = "${WORKDIR}/u-boot-${U_BOOT_PV}"

do_prepare_build_append() {
    cp ${WORKDIR}/pine64-plus-rules ${S}/debian/rules
}
