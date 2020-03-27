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
SRCREV = "00daf85ffa373ecce7836df7543c6ebe4cf43639"
SRC_URI[sha256sum] = "26e6e4aace9c9d1e9b9b1447f57ebd743dc15f3a337deac9e77d964885fcb3b3"

do_install() {
    cd ${WORKDIR}/firmware-nonfree-${SRCREV}/brcm
    install -v -d ${D}/lib/firmware/brcm/
    install -v -m 644 brcmfmac43430-sdio.txt ${D}/lib/firmware/brcm/
    install -v -m 644 brcmfmac43455-sdio.clm_blob ${D}/lib/firmware/brcm/
    install -v -m 644 brcmfmac43455-sdio.txt ${D}/lib/firmware/brcm/
}
