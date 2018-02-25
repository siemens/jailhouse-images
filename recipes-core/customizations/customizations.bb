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

inherit dpkg-raw

DESCRIPTION = "demo image customizations"

SRC_URI = " \
    file://postinst \
    file://.bash_history \
    file://e1000e-intx.conf \
    file://e1000e"

do_install() {
	install -v -d ${D}/etc/modprobe.d
	install -v -m 644 ${WORKDIR}/e1000e-intx.conf ${D}/etc/modprobe.d/

	install -v -d ${D}/etc/network/interfaces.d
	install -v -m 644 ${WORKDIR}/e1000e ${D}/etc/network/interfaces.d/

	install -v -d ${D}/root
	install -v -m 600 ${WORKDIR}/.bash_history ${D}/root/

	install -v -d -m 700 ${D}/root/.ssh
	install -v -m 644 ${WORKDIR}/known_hosts ${D}/root/.ssh/
}
