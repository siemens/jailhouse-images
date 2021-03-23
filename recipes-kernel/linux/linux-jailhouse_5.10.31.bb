#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2021
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-jailhouse_5.10.inc

SRC_URI[sha256sum] = "73ea481798bffb0378c7eba6d10f7d009c0d6dc6937d3760248c2cc143d8b70b"
SRCREV = "eb6927f7eea77f823b96c0c22ad9d4a2d7ffdfce"
