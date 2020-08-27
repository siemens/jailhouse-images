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

SRC_URI[sha256sum] = "e6769cfb9fe666372a5baf52e116c6022ee8a0a2de48248a5897c6af54812a8e"
SRCREV = "eb3ca54a7882348374d7ae32a749459c8bb4decd"
