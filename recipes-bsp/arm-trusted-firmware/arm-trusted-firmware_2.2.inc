#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

FILESPATH =. "${LAYERDIR_jailhouse}/recipes-bsp/arm-trusted-firmware/files:"

SRC_URI += " \
    https://github.com/ARM-software/arm-trusted-firmware/archive/v${ATF_PV}.tar.gz;name=atf \
    file://0001-rpi3-4-Add-support-for-offlining-CPUs.patch;patchdir=${WORKDIR}/arm-trusted-firmware-${ATF_PV} \
    "
SRC_URI[atf.sha256sum] = "07e3c058ae2d95c7d516a46fc93565b797e912c3271ddbf29df523b1ab1ee911"
