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

SRC_URI[sha256sum] = "e63412cea4350b038634a870b229abfb9fc64bf82201bb5afc1cfcabb87bea0b"
SRCREV = "ef906c3f35f5b53cc6cb3b9420a862d9f16e79b5"
