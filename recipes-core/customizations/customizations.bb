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

PN = "customizations-${MACHINE}"

inherit dpkg-raw

DESCRIPTION = "demo image customizations"

SRC_URI = " \
    file://postinst-common \
    file://postinst-${MACHINE} \
    file://.bash_history-${MACHINE} \
    file://e1000e-intx.conf \
    file://ethernet \
    file://ivshmem-net \
    file://known_hosts \
    file://99-silent-printk.conf \
    file://20-jailhouse-motd"

DEBIAN_DEPENDS = "openssh-server, passwd"

DEBIAN_DEPENDS_append_orangepi-zero = ", u-boot-script"
DEPENDS_append_orangepi-zero = "u-boot-script"

DEBIAN_DEPENDS_append_espressobin = ", u-boot-script"
DEPENDS_append_espressobin = "u-boot-script"

do_install() {
	sudo rm -rf ${D}/etc ${D}/root

	install -v -d ${D}/etc/modprobe.d
	install -v -m 644 ${WORKDIR}/e1000e-intx.conf ${D}/etc/modprobe.d/

	install -v -d ${D}/etc/network/interfaces.d
	install -v -m 644 ${WORKDIR}/ethernet ${D}/etc/network/interfaces.d/
	install -v -m 644 ${WORKDIR}/ivshmem-net ${D}/etc/network/interfaces.d/

	install -v -d ${D}/etc/sysctl.d
	install -v -m 644 ${WORKDIR}/99-silent-printk.conf ${D}/etc/sysctl.d/

	install -v -d ${D}/etc/update-motd.d
	install -v -m 755 ${WORKDIR}/20-jailhouse-motd ${D}/etc/update-motd.d/

	install -v -d ${D}/root
	install -v -m 600 ${WORKDIR}/.bash_history-${MACHINE} ${D}/root/.bash_history

	install -v -d -m 700 ${D}/root/.ssh
	install -v -m 644 ${WORKDIR}/known_hosts ${D}/root/.ssh/

	cat ${WORKDIR}/postinst-common ${WORKDIR}/postinst-${MACHINE} > ${WORKDIR}/postinst

	sudo chown -R root:root ${D}/etc ${D}/root
}
