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

require trusted-firmware-a_${PV}.inc

SRC_URI += " \
    git://github.com/MarvellEmbeddedProcessors/mv-ddr-marvell;protocol=https;rev=${MV_DDR_REV};destsuffix=mv-ddr-marvell \
    git://github.com/MarvellEmbeddedProcessors/binaries-marvell;protocol=https;branch=${MV_BIN_BRANCH};rev=${MV_BIN_REV};destsuffix=binaries-marvell \
    file://0001-plat-marvell-armada-Add-missing-dependency-of-mrvl_f.patch \
    "

MV_DDR_REV="7c351731d19645f64d2826a47e8f4d9cd1c74db3"
MV_BIN_BRANCH="binaries-marvell-armada-18.12"
MV_BIN_REV="c6c529ea3d905a28cc77331964c466c3e2dc852e"

DEPENDS += "u-boot-macchiatobin"
DEBIAN_BUILD_DEPENDS = "u-boot-macchiatobin, libssl-dev:native"

TF_A_PLATFORM = "a80x0_mcbin"
TF_A_EXTRA_BUILDARGS = " \
    USE_COHERENT_MEM=0 \
    MV_DDR_PATH=../mv-ddr-marvell \
    SCP_BL2=../binaries-marvell/mrvl_scp_bl2.img \
    BL33=/usr/lib/u-boot/macchiatobin/u-boot.bin \
    mrvl_flash"
TF_A_BINARIES = "release/flash-image.bin"
