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

PN = "demo-image-${MACHINE}"

ROOTFS_EXTRA = "1024"

require recipes-core/images/isar-image-base.bb

IMAGE_PREINSTALL += " \
    bash-completion less vim nano man \
    ifupdown isc-dhcp-client net-tools iputils-ping ssh \
    iw wireless-tools wpasupplicant \
    pciutils ethtool iperf3"

IMAGE_INSTALL += "jailhouse customizations-${MACHINE} non-root-initramfs"
