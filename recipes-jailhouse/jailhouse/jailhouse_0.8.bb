#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: GPL-2.0
#

require jailhouse.inc

SRC_URI += " \
    file://0001-tools-cell-linux-Use-minimal-decompression-space-for.patch \
    file://0002-tools-cell-linux-Make-kernel-decompression-factor-co.patch \
    file://0003-tools-cell-linux-Tune-x86-decompression-factor-based.patch \
    file://0004-configs-x86-Expand-inmate-reservation.patch"

SRCREV = "07341fa315a7fabb38f07cd3c0b3fe880cffaa65"
