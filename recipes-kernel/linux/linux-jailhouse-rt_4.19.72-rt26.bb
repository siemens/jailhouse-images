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

SRC_URI += "file://preempt-rt.cfg"

SRC_URI[sha256sum] = "2d4640f928e237254ed4242f75c6133853859d38aa08a6b91fc3a919caa0dc7e"
SRCREV = "90c6f75204884080b23d30424a4eff313dfeaa7d"
