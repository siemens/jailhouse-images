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

require recipes-kernel/linux/linux-custom.inc

python() {
    machine = d.getVar('MACHINE', True)
    if machine in ['qemuamd64', 'nuc6cay']:
        machine = 'x86-64'
    if machine in ['qemuarm64']:
        machine = 'arm64'
    d.setVar('KERNEL_DEFCONFIG', machine + '_defconfig')
}

SRC_URI += " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz \
    file://0001-net-stmmac-snps-dwmac-mdio-MDIOs-are-automatically-r.patch \
    file://0002-net-stmmac-dwmac-sun8i-Handle-integrated-external-MD.patch \
    file://0003-net-stmmac-sun8i-Restore-the-compatibles.patch \
    file://0004-arm-dts-sunxi-h3-h5-Restore-EMAC-changes.patch \
    file://0005-ARM-dts-sunxi-h3-h5-represent-the-mdio-switch-used-b.patch \
    file://0006-ARM-dts-sunxi-Restore-EMAC-changes-boards.patch \
    file://0007-ARM-dts-orange-pi-zero-Adjust-wifi-settings.patch \
    file://${KERNEL_DEFCONFIG}"
SRC_URI[sha256sum] = "27dbdeee1e6f2f4aea6bcfc6d552f371c6ccc2e63e12d692516d05e3daa9cf1f"
SRCREV = "fb67a109309134bee65d97e29076128d603227b3"
PV = "4.14.70"

S = "${WORKDIR}/linux-${SRCREV}"

KBUILD_DEPENDS += "liblz4-tool"
