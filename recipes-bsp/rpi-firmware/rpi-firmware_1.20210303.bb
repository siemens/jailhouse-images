#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

inherit dpkg

SRC_URI = " \
    https://github.com/raspberrypi/firmware/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz \
    file://debian \
    file://rules"
SRC_URI[sha256sum] = "9b57137c602ecb0d05de6c530c5e5e77d0ad2cb3ffe43e98db81cd44b046de85"

S = "${WORKDIR}/firmware-${PV}"

DEBIAN_BUILD_DEPENDS = "device-tree-compiler"

do_prepare_build[cleandirs] += "${S}/debian"
do_prepare_build() {
    cp -r ${WORKDIR}/debian ${S}

    deb_debianize
}
