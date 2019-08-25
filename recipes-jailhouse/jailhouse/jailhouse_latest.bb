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

require jailhouse.inc

SRC_URI += " \
    file://nuc6cay.c \
    file://linux-nuc6cay-demo.c"

SRCREV = "next"
PV = "0.9999-next"

do_prepare_build_append() {
    cp ${WORKDIR}/nuc6cay.c ${S}/configs/x86/
    cp ${WORKDIR}/linux-nuc6cay-demo.c ${S}/configs/x86/
}

dpkg_runbuild_prepend() {
    bbplain $(printf "jailhouse-latest: Building revision %.12s\n" \
                     $(cat ${S}/.git/refs/heads/next))
}
