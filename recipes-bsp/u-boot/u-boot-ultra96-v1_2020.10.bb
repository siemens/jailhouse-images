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

require u-boot-${PV}.inc

ULTRA96_VERSION ?= "1"

SRC_URI += " \
    file://ultra96-v${ULTRA96_VERSION}.bit.xz \
    file://ultra96-v${ULTRA96_VERSION}-fsbl.elf.xz \
    file://ultra96.bif.tmpl \
    file://ultra96-rules \
    "

TEMPLATE_FILES += "ultra96.bif.tmpl"
TEMPLATE_VARS += "ULTRA96_VERSION"

DEPENDS += "zynqmp-pmufw trusted-firmware-a-ultra96"
DEBIAN_BUILD_DEPENDS += ", zynqmp-pmufw:native, trusted-firmware-a-ultra96"

U_BOOT_CONFIG = "xilinx_zynqmp_virt_defconfig"
U_BOOT_BIN = "u-boot.elf"

do_prepare_build_append() {
    cp ${WORKDIR}/ultra96-rules ${S}/debian/rules

    echo "boot.bin /usr/lib/u-boot/ultra96-v${ULTRA96_VERSION}" > ${S}/debian/u-boot-ultra96-v${ULTRA96_VERSION}.install
}

dpkg_runbuild_prepend() {
    export ULTRA96_VERSION=${ULTRA96_VERSION}
}
