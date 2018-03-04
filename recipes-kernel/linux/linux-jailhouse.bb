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
    file://x86_64_defconfig"
SRC_URI[sha256sum] = "9a99dd2d3028d3e40beef325f4a59f0f39b8a699f0247fb98df815f0d92106a0"
SRCREV = "3c91d2686cbaaf19e0f5f440895626441bc68182"
PV = "4.14.24"

S = "linux-${SRCREV}"

KERNEL_DEFCONFIG_qemuamd64 = "x86_64_defconfig"

KBUILD_DEPENDS += "liblz4-tool"
