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
SRC_URI[sha256sum] = "39c6c6d2834bdb367778f7ccde9112b0817aff0b1883b57ff1780544f4bc92b8"
SRCREV = "715d109f184a1f68a87307557870ab40311993c7"
PV = "4.14.50"

S = "${WORKDIR}/linux-${SRCREV}"

KERNEL_DEFCONFIG = "${MACHINE}_defconfig"

KBUILD_DEPENDS += "liblz4-tool"
