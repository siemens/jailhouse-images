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

SRC_URI[sha256sum] = "02e7eb3ea8366785ae516ca3bb9cdd2d3b83672b543b8be12a4853b02f2a6279"
SRCREV = "643fa6fcbe61f5db2120aa8757bc02faa56988cb"

do_prepare_build_prepend() {
    cat ${WORKDIR}/preempt-rt.cfg >> ${WORKDIR}/${KERNEL_DEFCONFIG}
}
