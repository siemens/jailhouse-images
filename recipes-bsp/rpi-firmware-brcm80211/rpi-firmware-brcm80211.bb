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

inherit dpkg-raw

SRC_URI = "https://github.com/RPi-Distro/firmware-nonfree/archive/${SRCREV}.tar.gz;downloadfilename=firmware-${SRCREV}.tar.gz"
SRCREV = "b66ab26cebff689d0d3257f56912b9bb03c20567"
SRC_URI[sha256sum] = "033a21d19fbdc7617b8c5b58d4be5951e29be5be787a45875b615f4d4dcf3f5b"

do_install() {
    cd ${WORKDIR}/firmware-nonfree-${SRCREV}/brcm
    install -v -d ${D}/lib/firmware/brcm/
    install -v -m 644 brcmfmac43430-sdio.txt ${D}/lib/firmware/brcm/
    install -v -m 644 brcmfmac43455-sdio.clm_blob ${D}/lib/firmware/brcm/
    install -v -m 644 brcmfmac43455-sdio.txt ${D}/lib/firmware/brcm/
}
