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

ROOTFS_EXTRA = "1024"

require recipes-core/images/isar-image-base.bb

IMAGE_PREINSTALL += " \
    bash-completion less vim nano \
    ifupdown isc-dhcp-client net-tools iputils-ping ssh \
    pciutils ethtool"

IMAGE_INSTALL += "jailhouse customizations non-root-initramfs"
