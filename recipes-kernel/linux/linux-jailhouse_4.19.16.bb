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

SRC_URI[sha256sum] = "a83f14e2570a7e992b2424bdc9a110ce2d910a2595906bd409372e208a955f92"
SRCREV = "830af0cc30813504d6b3f49ac7e3228a98a3ab8e"
