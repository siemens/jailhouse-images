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

require recipes-kernel/linux/linux-jailhouse_4.19.inc

SRC_URI[sha256sum] = "fe74c652761e9530e5387a3f835c781e99e751174a13cc35cf5d2ca868443b06"
SRCREV = "e64475bfe9071b9603a59c51875c5503ccc3f785"
