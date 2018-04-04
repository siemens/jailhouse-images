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

DESCRIPTION = "Jailhouse partitioning hypervisor"

LICENSE = "GPL-2.0 & BSD-2-clause"

SRC_URI = " \
    git://github.com/siemens/jailhouse;branch=next \
    file://debian/ \
    file://${MACHINE}_config.h"

S = "git"

inherit dpkg

DEPENDS = "linux-image-${KERNEL_NAME}"

dpkg_runbuild_prepend() {
    cp -r ${WORKDIR}/debian ${WORKDIR}/${S}/
    cp ${WORKDIR}/${MACHINE}_config.h ${WORKDIR}/${S}/include/jailhouse/config.h
    sed -i 's/@PV@/${PV}/' ${WORKDIR}/${S}/debian/changelog
    sed -i 's/@KERNEL_NAME@/${KERNEL_NAME}/' ${WORKDIR}/${S}/debian/control
}
