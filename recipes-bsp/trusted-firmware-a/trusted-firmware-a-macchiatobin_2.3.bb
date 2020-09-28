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
    git://github.com/MarvellEmbeddedProcessors/mv-ddr-marvell;protocol=https;branch=mv_ddr-armada-atf-mainline;rev=${MV_DDR_REV};destsuffix=mv-ddr-marvell \
    git://github.com/MarvellEmbeddedProcessors/binaries-marvell;protocol=https;branch=${MV_BIN_BRANCH};rev=${MV_BIN_REV};destsuffix=binaries-marvell \
    file://0001-mv_ddr4_training-fix-gcc-warning-about-uninitialized.patch;patchdir=${WORKDIR}/mv-ddr-marvell \
    "

MV_DDR_REV="a881467ef0f0185e6570dd0483023fde93cbb5f5"
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
    all fip"
TF_A_BINARIES = "flash-image.bin"
