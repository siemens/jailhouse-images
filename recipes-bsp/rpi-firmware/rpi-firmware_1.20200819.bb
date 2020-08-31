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
SRC_URI[sha256sum] = "661e4c2c33ef9354da50ff2f679b697ea94d2d820166cde749692133e3461c34"

S = "${WORKDIR}/firmware-${PV}"

DEBIAN_BUILD_DEPENDS = "device-tree-compiler"

do_prepare_build[cleandirs] += "${S}/debian"
do_prepare_build() {
    cp -r ${WORKDIR}/debian ${S}

    deb_debianize
}
