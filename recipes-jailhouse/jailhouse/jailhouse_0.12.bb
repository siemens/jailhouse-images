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
    file://0001-configs-arm64-Add-support-for-RPi4-with-more-than-1G.patch \
    file://0002-arm-common-gicv2-Fix-byte-access-to-ITARGETR.patch \
    "

SRCREV = "92db71f257fabd3c08fa4b99498fa61a41ea831d"

EXTRA_JAILHOUSE_CONFIGS_amd64 += " \
    nuc6cay_0.12.c \
    ipc127e_0.12.c \
    "

do_prepare_build_append_amd64() {
    mv ${S}/configs/x86/nuc6cay_0.12.c ${S}/configs/x86/nuc6cay.c
    mv ${S}/configs/x86/ipc127e_0.12.c ${S}/configs/x86/ipc127e.c
}
