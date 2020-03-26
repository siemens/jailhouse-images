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

SRC_URI[sha256sum] = "807290fdb1f17aa20201b9814d78e9256645765654bc2befb8100110a4af5f47"
SRCREV = "e24c5d5d42ceea850df147cbe71d77600941524a"
