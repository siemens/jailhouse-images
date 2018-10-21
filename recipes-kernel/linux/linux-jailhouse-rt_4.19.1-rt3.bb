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

SRC_URI[sha256sum] = "0bc709622e663363272442b2b153fd4f177c1ef8e7275bff16e47cca9749491a"
SRCREV = "7573a462a541b494b245f5e7e10cb0c23c2819c1"

do_prepare_build_prepend() {
    cat ${WORKDIR}/preempt-rt.cfg >> ${WORKDIR}/${KERNEL_DEFCONFIG}
}
