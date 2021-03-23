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

require recipes-kernel/linux-module/module.inc

FIRMWARE_REV = "a67c351f9028c7a559330365c649a07721965cd4"

SRC_URI += " \
    git://github.com/Avnet/u96v2-wilc-driver;protocol=https;branch=v15_2 \
    file://0001-Make-compatible-with-5.8-kernels.patch;striplevel=2 \
    https://github.com/linux4wilc/firmware/raw/${FIRMWARE_REV}/wilc3000_wifi_firmware.bin;name=firmware \
    file://debian/wilc.install"
SRCREV = "01ab7484e0e6b2191c69d7ec7c6e89da5ca51f0f"
SRC_URI[firmware.sha256sum] = "8cc0328e80d8e0e96b295777b58e7f48fea25d29808df6dd0fabd3ea283b3da1"

S = "${WORKDIR}/git/wilc"

AUTOLOAD = "wilc-sdio"

do_prepare_build_append() {
    mv ${S}/debian/wilc.install ${S}/debian/wilc-${KERNEL_NAME}.install
}

dpkg_runbuild_prepend() {
    export CONFIG_WILC_SDIO=m
}
