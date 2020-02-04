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

SRC_URI[sha256sum] = "49ee37658114f123390e5c2b00bf177aa110e77ebfd62325a3267e29f34ec873"
SRCREV = "83e85c7a8e18f1d72e5c78952a853b656def48b6"
