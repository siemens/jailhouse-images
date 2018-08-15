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

SRC_URI += " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz \
    file://0001-net-stmmac-snps-dwmac-mdio-MDIOs-are-automatically-r.patch \
    file://0002-net-stmmac-dwmac-sun8i-Handle-integrated-external-MD.patch \
    file://0003-net-stmmac-sun8i-Restore-the-compatibles.patch \
    file://0004-arm-dts-sunxi-h3-h5-Restore-EMAC-changes.patch \
    file://0005-ARM-dts-sunxi-h3-h5-represent-the-mdio-switch-used-b.patch \
    file://0006-ARM-dts-sunxi-Restore-EMAC-changes-boards.patch \
    file://0007-ARM-dts-orange-pi-zero-Adjust-wifi-settings.patch \
    file://${MACHINE}_defconfig"
SRC_URI[sha256sum] = "2cf3accdbf9396141169f5906c77069f61554bc6a2f88c6c1c1983030e4e3ed1"
SRCREV = "c4dc6dcc96ee8cda3311988ab9339a1201d50280"
PV = "4.14.62"

S = "${WORKDIR}/linux-${SRCREV}"

KERNEL_DEFCONFIG = "${MACHINE}_defconfig"

KBUILD_DEPENDS += "liblz4-tool"
