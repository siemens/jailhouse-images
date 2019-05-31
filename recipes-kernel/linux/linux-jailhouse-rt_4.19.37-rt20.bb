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

SRC_URI[sha256sum] = "ca6da44c47571ad3ce911eb92a0174422dd9138f8c70ff2fd35c2a0b7613adfd"
SRCREV = "64415ba5b24165b1be91bd0533fc9e8ca35f1aa0"

do_prepare_build_prepend() {
    cat ${WORKDIR}/preempt-rt.cfg >> ${WORKDIR}/${KERNEL_DEFCONFIG}
}
