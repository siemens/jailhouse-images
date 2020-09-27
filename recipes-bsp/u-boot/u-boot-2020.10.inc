#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-bsp/u-boot/u-boot-custom.inc

SRC_URI += "https://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2"
SRC_URI[sha256sum] = "0d481bbdc05c0ee74908ec2f56a6daa53166cc6a78a0e4fac2ac5d025770a622"

S = "${WORKDIR}/u-boot-${PV}"
