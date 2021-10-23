#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

inherit dpkg

DESCRIPTION = "Microblaze toolchain built by crosstool-ng"

SRC_URI = " \
    https://github.com/crosstool-ng/crosstool-ng/archive/crosstool-ng-${PV}.tar.gz \
    file://debian/ \
    file://defconfig"
SRC_URI[sha256sum] = "36c0067a2da265aa88f8d91c1647d152c98a100b8e2ce416cf47dedf08c069e9"

TEMPLATE_FILES = "debian/control.tmpl"

S = "${WORKDIR}/crosstool-ng-crosstool-ng-${PV}"

do_prepare_build[dirs] = "${DL_DIR}/crosstool-ng"
do_prepare_build() {
    rm -rf ${S}/debian
    cp -r ${WORKDIR}/debian ${S}
    deb_add_changelog

    cp ${WORKDIR}/defconfig ${S}
}

do_cleanall[cleandirs] += "${DL_DIR}/crosstool-ng"
