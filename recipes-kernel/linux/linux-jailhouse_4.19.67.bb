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

SRC_URI[sha256sum] = "e70f225edee1dfe90cbc7b5bfcbb8c39c0a8ab34d0be379a4be417952420a7ad"
SRCREV = "b8f584b391bc90920008708d77d6bc880f61965e"
