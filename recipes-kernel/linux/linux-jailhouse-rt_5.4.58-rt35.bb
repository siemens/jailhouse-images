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

SRC_URI[sha256sum] = "7b58715af2468353b966dc57ec41737f41cde8729f1e2e209dc1d10e758464a3"
SRCREV = "951c9e56a32b9ef1f9c1906b5cf8b47a8d953d64"
