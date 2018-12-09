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

require recipes-kernel/linux/linux-custom.inc

python() {
    machine = d.getVar('MACHINE', True)
    if machine in ['qemu-amd64', 'nuc6cay']:
        machine = 'x86-64'
    if machine in ['qemu-arm64', 'espressobin', 'macchiatobin', 'hikey620']:
        machine = 'arm64'
    d.setVar('KERNEL_DEFCONFIG', machine + '_defconfig_4.14')
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
    file://0008-serial-mvebu-uart-Fix-reporting-of-effective-CSIZE-t.patch \
    file://${KERNEL_DEFCONFIG}"
SRC_URI[sha256sum] = "9eda42c32f046eefe98ce97cf343068896054e16daa84a4ca9f1a576ab58507f"
SRCREV = "1dd68658b3a8308a160b0786fc4e1e04d8ff5216"

S = "${WORKDIR}/linux-${SRCREV}"

KBUILD_DEPENDS += "liblz4-tool"
