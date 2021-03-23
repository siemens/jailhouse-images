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

SRC_URI += "file://preempt-rt.cfg"

SRC_URI[sha256sum] = "8206051628eac35ded4f691ed683cd08a22174a2e05f9f1837cef4e39443e5e7"
SRCREV = "5cf8cf58d799b9d16bf522c31d92476dc9e22736"
