#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require trusted-firmware-a_${PV}.inc

SRC_URI += " \
    file://0001-rpi-Use-common-memreserve-pattern-for-rpi3-and-rpi4.patch"

TF_A_PLATFORM = "rpi4"
