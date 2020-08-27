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

SRC_URI[sha256sum] = "dc9719a70743bf98057eb1a4d1e40a2cbed9ea08fc752a1a1ddb0285d23a2ede"
SRCREV = "108bab72978d4ec29ef6ac32506eb1783af599a2"
