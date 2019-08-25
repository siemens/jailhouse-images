#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require jailhouse.inc

SRC_URI += " \
    file://nuc6cay_0.11.c \
    file://linux-nuc6cay-demo_0.11.c"

SRCREV = "58052a7a9d1f5904d72b1637282c877172ee69f6"

do_prepare_build_append() {
    cp ${WORKDIR}/nuc6cay_0.11.c ${S}/configs/x86/nuc6cay.c
    cp ${WORKDIR}/linux-nuc6cay-demo_0.11.c ${S}/configs/x86/linux-nuc6cay-demo.c
}
