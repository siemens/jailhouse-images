#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-jailhouse_5.4.inc

SRC_URI[sha256sum] = "d84c78d0931d2e5c784d30d73aa63cd6b1a849f5d957d87e1a660aa1f5a4c070"
SRCREV = "45e6cf9c5dfd149cccadaf960907bd99408a2e70"
