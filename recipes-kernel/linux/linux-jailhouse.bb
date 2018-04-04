#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: GPL-2.0
#

require recipes-kernel/linux/linux-custom.inc

SRC_URI += " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz \
    file://${MACHINE}_defconfig"
SRC_URI[sha256sum] = "38a98baa8cbdeb6b06554e5ea724ee8d08bfd32645f6b1d2d4af41f7ada6ea70"
SRCREV = "c2cb6bc17681b44eeeb66ca40bd8051053105317"
PV = "4.14.32"

S = "linux-${SRCREV}"

KERNEL_DEFCONFIG = "${MACHINE}_defconfig"

KBUILD_DEPENDS += "liblz4-tool"
