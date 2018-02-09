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

SRC_URI = " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz \
    file://defconfig"
SRC_URI[sha256sum] = "e779cb4297b7bec7397d23984c9f611c5d88f5ca1600b9346bfa9e44dc1bc4f4"
SRCREV = "26a81a994feef8d54f53ca15fbd2a527b285d56f"
PV = "4.14.18"

S = "linux-${SRCREV}"
