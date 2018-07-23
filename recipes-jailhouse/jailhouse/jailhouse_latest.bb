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

SRCREV = "next"
PV = "0.9999-next"

SRC_URI += " \
    file://nuc6cay.c \
    file://linux-nuc6cay-demo.c"

dpkg_runbuild_prepend() {
    bbplain $(printf "jailhouse-latest: Building revision %.12s\n" \
                     $(cat ${S}/.git/refs/heads/next))
    cp ${WORKDIR}/nuc6cay.c ${S}/configs/x86/
    cp ${WORKDIR}/linux-nuc6cay-demo.c ${S}/configs/x86/
}
