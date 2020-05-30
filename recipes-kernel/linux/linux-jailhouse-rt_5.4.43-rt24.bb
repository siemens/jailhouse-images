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

SRC_URI[sha256sum] = "299f777d0b253a4bbafd40ceade93f967d2bb637ed7bb4a6ed8064474fc08600"
SRCREV = "db818a378db60497d2e14a697a486826772f5024"
