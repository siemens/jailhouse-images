#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019-2020
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

ULTRA96_VERSION ?= "1"

SRC_URI += " \
    https://ftp.denx.de/pub/u-boot/u-boot-${U_BOOT_PV}.tar.bz2;name=u-boot \
    file://0001-Revert-tools-Makefile-fix-HOSTCFLAGS-with-CROSS_BUIL.patch \
    file://ultra96-v${ULTRA96_VERSION}.bit.xz \
    file://ultra96-v${ULTRA96_VERSION}-fsbl.elf.xz \
    file://ultra96.bif.tmpl \
    file://ultra96-rules \
    "
SRC_URI[u-boot.sha256sum] = "8d6d6070739522dd236cba7055b8736bfe92b4fac0ea18ad809829ca79667014"

TEMPLATE_FILES += "ultra96.bif.tmpl"
TEMPLATE_VARS += "ATF_PV ULTRA96_VERSION"

DEPENDS += "zynqmp-pmufw"
BUILD_DEPENDS += ", zynqmp-pmufw:native"

U_BOOT_CONFIG="avnet_ultra96_rev1_defconfig"
U_BOOT_BIN="u-boot.elf"

S = "${WORKDIR}/u-boot-${U_BOOT_PV}"

do_prepare_build_append() {
    cp ${WORKDIR}/ultra96-rules ${S}/debian/rules

    echo "boot.bin /usr/lib/u-boot/ultra96-v${ULTRA96_VERSION}" > ${S}/debian/u-boot-ultra96-v${ULTRA96_VERSION}.install
}

dpkg_runbuild_prepend() {
    export ULTRA96_VERSION=${ULTRA96_VERSION}
}
