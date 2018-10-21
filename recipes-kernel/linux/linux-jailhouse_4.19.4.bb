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

SRC_URI[sha256sum] = "a277fe6a0656e52c370fdb23115c960f77e7760d91e5a81c66e8d7286048bd06"
SRCREV = "4e0310ab768840f16ac4fa455be66e75c9d68fcc"
