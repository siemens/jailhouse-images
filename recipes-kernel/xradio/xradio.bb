#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2022
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require recipes-kernel/linux-module/module.inc

FW_URL = "https://github.com/armbian/firmware/blob/aff348fa9eef0fcc97d4f2bb7304f0862baffc20/xr819"

SRC_URI += " \
    git://github.com/fifteenhex/xradio;protocol=https \
    ${FW_URL}/boot_xr819.bin?raw=true;downloadfilename=boot_xr819.bin;sha256sum=6583350b3eb12f70fc6d6081426717bd0019b55c6558ffe820c1548f0702bb8c \
    ${FW_URL}/fw_xr819.bin?raw=true;downloadfilename=fw_xr819.bin;sha256sum=fb81436ad7cc0876614a2a9c2a54c5a93a75315aee164e3a3afe3db80842a9e1 \
    ${FW_URL}/sdd_xr819.bin?raw=true;downloadfilename=sdd_xr819.bin;sha256sum=84d3fb3ca8e5d25a0c113a5063bccbeb5b53da230a0afa236b5b625f37db5161 \
    file://debian/xradio.install"
SRCREV = "16180b6308e3c5dc42a92a663adf669028087ff7"

S = "${WORKDIR}/git"

do_prepare_build_append() {
    mv ${S}/debian/xradio.install ${S}/debian/xradio-${KERNEL_NAME}.install
}
