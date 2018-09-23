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

inherit dpkg

DESCRIPTION = "Linux initramfs for Jailhouse non-root cells"

SRC_URI = " \
    https://buildroot.org/downloads/buildroot-${PV}.tar.bz2 \
    file://debian/ \
    file://${DISTRO_ARCH}-config \
    file://overlay/"
SRC_URI[sha256sum] = "8dc4b9bd22a165a4df0a1737f01de3dd0a6c15d9f8b16989426af062471a0abb"

S = "${WORKDIR}/buildroot-${PV}"

do_prepare_build() {
	cd ${WORKDIR}

	cp -r debian ${S}
	sed -i 's/@PV@/${PV}/' ${S}/debian/changelog

	ln -sf ${DISTRO_ARCH}-config .config
}

dpkg_runbuild_append() {
	sudo chown -R $(stat -c "%U" ${DL_DIR}) ${DL_DIR}/buildroot
}
