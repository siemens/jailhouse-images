#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Vijai Kumar K, 2020
#
# Authors:
#  Vijai Kumar K <vijaikumar.kanagarajan@gmail.com>
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require u-boot-${PV}.inc

SRC_URI += "file://pine64-plus-rules"

U_BOOT_CONFIG = "pine64_plus_defconfig"
U_BOOT_BIN = "u-boot-sunxi-with-spl.bin"

DEBIAN_BUILD_DEPENDS += ", libssl-dev, swig:native, python3-dev:native, trusted-firmware-a-pine64-plus"
DEPENDS += "trusted-firmware-a-pine64-plus"

do_prepare_build_append() {
    cp ${WORKDIR}/pine64-plus-rules ${S}/debian/rules
}
