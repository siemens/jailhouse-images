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

IMAGE_TYPE = "ext4-img"

IMAGE_INSTALL_remove += "expand-on-first-boot"
ROOTFS_EXTRA = "1024"
