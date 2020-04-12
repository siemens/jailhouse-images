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

require jailhouse.inc

SRC_URI += " \
    file://0001-configs-arm-arm64-Fix-vPCI-IRQ-assignment-of-Linux-c.patch \
    file://0001-configs-arm64-Add-support-for-pine64-plus-board.patch \
    file://0002-configs-arm64-Add-inmate-demo-for-pine64-plus-board.patch \
    file://0003-configs-arm64-Add-Linux-demo-for-pine64-plus.patch \
    file://0001-inmates-x86-Add-LED-blinking-support-to-apic-demo.patch \
    "

SRCREV = "92db71f257fabd3c08fa4b99498fa61a41ea831d"
