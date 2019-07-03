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

require recipes-kernel/linux/linux-jailhouse_4.19.inc

SRC_URI += "file://preempt-rt.cfg"

SRC_URI[sha256sum] = "f68559963019307950e3bbc9ee2cb6d997debca1cfa6435ed79c0bb15682ad57"
SRCREV = "372c95a9bdd80b5bdbb2ceff2ea741193a187f1f"

do_prepare_build_prepend() {
    cat ${WORKDIR}/preempt-rt.cfg >> ${WORKDIR}/${KERNEL_DEFCONFIG}
}
