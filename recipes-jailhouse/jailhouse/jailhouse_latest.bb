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

SRCREV = "next"
PV = "0.9999-next"

EXTRA_JAILHOUSE_CONFIGS_amd64 += " \
    nuc6cay.c \
    ipc127e.c \
    "

dpkg_runbuild_prepend() {
    bbplain $(printf "jailhouse-latest: Building revision %.12s\n" \
                     $(cat ${S}/.git/refs/heads/next))
}
