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
    git://github.com/siemens/jailhouse \
    file://debian/"

S = "git"

inherit dpkg

DEPENDS = "virtual/kernel"

dpkg_runbuild_prepend() {
    cp -r ${WORKDIR}/debian ${WORKDIR}/${S}/
    sed -i 's/@PV@/${PV}/' ${WORKDIR}/${S}/debian/changelog
}
