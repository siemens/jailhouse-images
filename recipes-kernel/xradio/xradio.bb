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

require recipes-kernel/linux-module/module.inc

FW_URL = "https://github.com/armbian/firmware/blob/7172b4b6608b131cbd6c60253c78a231993beed8/xr819"

SRC_URI += " \
    git://github.com/fifteenhex/xradio \
    file://0001-Adjust-wrapping-of-ieee80211_nullfunc_get.patch \
    file://0002-Makefile-Add-proper-build-targets.patch \
    ${FW_URL}/boot_xr819.bin?raw=true;downloadfilename=boot_xr819.bin;sha256sum=6583350b3eb12f70fc6d6081426717bd0019b55c6558ffe820c1548f0702bb8c \
    ${FW_URL}/fw_xr819.bin?raw=true;downloadfilename=fw_xr819.bin;sha256sum=4954ceb85807959c42e82c432109455bd9eabe95971402299a16d77ddd7d79f5 \
    ${FW_URL}/sdd_xr819.bin?raw=true;downloadfilename=sdd_xr819.bin;sha256sum=84d3fb3ca8e5d25a0c113a5063bccbeb5b53da230a0afa236b5b625f37db5161 \
    file://debian/xradio.install"
SRCREV = "3ffc0f729454a5d118d6118974ae925beeb521c1"

S = "${WORKDIR}/git"
