#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2023
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-jailhouse_5.15.inc

SRC_URI += "file://preempt-rt.cfg"

SRC_URI[sha256sum] = "5115b47c8a9fd1edb076f209e765098e29185a8ac7a9f9d8dd81b1f2d73ec1da"
SRCREV = "8306c186100abd709bd3e5708bf05d7892f31ed5"
