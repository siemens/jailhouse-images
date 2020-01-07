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

SRC_URI += "file://preempt-rt.cfg"

SRC_URI[sha256sum] = "5714bd79562a43c17aa869a6dac40a19ac3965a3c1151aa4d0f66993eb69ae9d"
SRCREV = "f8a16c29b76d12da917f14bbbbf97b862346e91e"
