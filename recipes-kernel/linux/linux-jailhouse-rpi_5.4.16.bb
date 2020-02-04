#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-jailhouse_5.4.inc

SRC_URI[sha256sum] = "53de4d966d6072302fdc87ddce3ec94a22132638bcb8bf2c0944e0159d8db611"
SRCREV = "e569bd2d6d2d7b958973bb8c6e9db9cfc05c790b"
