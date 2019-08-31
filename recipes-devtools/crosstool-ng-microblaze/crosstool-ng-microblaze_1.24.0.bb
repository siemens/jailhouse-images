#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

inherit dpkg

DESCRIPTION = "Microblaze toolchain built by crosstool-ng"

SRC_URI = " \
    http://crosstool-ng.org/download/crosstool-ng/crosstool-ng-${PV}.tar.xz \
    file://debian/ \
    file://defconfig"
SRC_URI[sha256sum] = "804ced838ea7fe3fac1e82f0061269de940c82b05d0de672e7d424af98f22d2d"

TEMPLATE_FILES = "debian/control.tmpl"

S = "${WORKDIR}/crosstool-ng-${PV}"

do_prepare_build[dirs] = "${DL_DIR}/crosstool-ng"
do_prepare_build() {
    cp -r ${WORKDIR}/debian ${S}
    deb_add_changelog

    cp ${WORKDIR}/defconfig ${S}
}
