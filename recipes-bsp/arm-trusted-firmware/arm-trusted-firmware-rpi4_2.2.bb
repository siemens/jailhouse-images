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

ATF_PV = "${PV}"

require arm-trusted-firmware_${PV}.inc

SRC_URI += "file://rpi-rules"

S = "${WORKDIR}/arm-trusted-firmware-${PV}"

do_prepare_build[cleandirs] += "${S}/debian"
do_prepare_build() {
    cp ${WORKDIR}/rpi-rules ${WORKDIR}/rules
    deb_debianize

    echo "build/rpi4/release/bl31.bin /usr/lib/arm-trusted-firmware/rpi4/" > ${S}/debian/install
}
