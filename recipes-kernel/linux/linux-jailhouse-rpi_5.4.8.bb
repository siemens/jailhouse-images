#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-jailhouse_5.4.inc

SRC_URI[sha256sum] = "cbec40dc889127df018fa41c7c39db8fab7728dd82cc04fe8336b2ed6c0e2bed"
SRCREV = "b4a6c797cb8f2e60dea711dc1bd12acdd88e6eeb"
