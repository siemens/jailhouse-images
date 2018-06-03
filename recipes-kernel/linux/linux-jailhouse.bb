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
SRC_URI[sha256sum] = "945505f36cb2ea98f84934e375da58ede991c911f02fe84c5da082a4907c1e36"
SRCREV = "f3dd5be06d4d24cb2a55ddd0a5b0d2d5b084e459"
PV = "4.14.47"

S = "${WORKDIR}/linux-${SRCREV}"

KERNEL_DEFCONFIG = "${MACHINE}_defconfig"

KBUILD_DEPENDS += "liblz4-tool"
