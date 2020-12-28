#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux/linux-custom.inc

KERNEL_DEFCONFIG = "${DISTRO_ARCH}_defconfig_5.4"
KERNEL_DEFCONFIG_rpi4 = "rpi4_defconfig_5.4"

SRC_URI += " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz;downloadfilename=linux-${SRCREV}.tar.gz \
    file://0001-ARM-dts-orange-pi-zero-Adjust-wifi-settings.patch \
    file://0001-pwrseq_simple-Workaround-for-missing-device-tree-ent.patch \
    file://0001-mmc-sdhci-of-arasan-Separate-out-clk-related-data-to.patch \
    file://0002-mmc-sdhci-of-arasan-Add-sampling-clock-for-a-phy-to-.patch \
    file://0003-arm64-zynqmp-Add-firmware-DT-node.patch \
    file://0004-firmware-zynqmp-Add-Node-IDs-in-xlnx-zynqmp.h.patch \
    file://0005-mmc-sdhci-of-arasan-Add-support-to-set-clock-phase-d.patch \
    file://0006-firmware-xilinx-Add-SDIO-Tap-Delay-nodes.patch \
    file://0007-mmc-sdhci-of-arasan-Add-support-for-ZynqMP-Platform-.patch \
    file://0008-firmware-xilinx-Add-DLL-reset-support.patch \
    file://0009-mmc-sdhci-of-arasan-Add-support-for-DLL-reset-for-Zy.patch \
    file://0010-arm64-zynqmp-Add-ZynqMP-SDHCI-compatible-string.patch \
    file://0011-mmc-sdhci-of-arasan-Modified-SD-default-speed-to-19M.patch \
    file://0012-arm64-dts-Add-support-for-Avnet-Ultra96-rev2-board.patch \
    file://${KERNEL_DEFCONFIG}"

S = "${WORKDIR}/linux-${SRCREV}"

KBUILD_DEPENDS += "liblz4-tool"
