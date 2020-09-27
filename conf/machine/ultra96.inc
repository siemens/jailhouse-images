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

DISTRO_ARCH = "arm64"

IMAGE_TYPE = "wic-img"
WKS_FILE = "ultra96.wks"
IMAGER_INSTALL += "u-boot-ultra96-v${ULTRA96_VERSION}"
IMAGER_BUILD_DEPS += "u-boot-ultra96-v${ULTRA96_VERSION}"
IMAGE_INSTALL_append = " u-boot-script"

IMAGE_BOOT_FILES = "/usr/lib/u-boot/ultra96-v${ULTRA96_VERSION}/boot.bin;boot.bin"

IMAGE_PREINSTALL_append = " firmware-ti-connectivity"
