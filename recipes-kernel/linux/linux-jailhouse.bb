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
    file://0001-builddeb-Fix-header-package-regarding-dtc-source-lin.patch \
    file://${MACHINE}_defconfig"
SRC_URI[sha256sum] = "3f4bca35a5a525710ae592f1640f0f009fefee08a896ceb561c5086ea7d08711"
SRCREV = "104cee7fcc0b21ca80d0eaedadf310e22ab69bdb"
PV = "4.14.39"

S = "linux-${SRCREV}"

KERNEL_DEFCONFIG = "${MACHINE}_defconfig"

KBUILD_DEPENDS += "liblz4-tool"
