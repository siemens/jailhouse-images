#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

U_BOOT_PV="${@d.getVar('PV').split('-atf')[0]}"
ATF_PV="${@d.getVar('PV').split('-atf')[1]}"

require recipes-bsp/u-boot/u-boot-custom.inc
require recipes-bsp/arm-trusted-firmware/arm-trusted-firmware_${ATF_PV}.inc

SRC_URI += " \
    ftp://ftp.denx.de/pub/u-boot/u-boot-${U_BOOT_PV}.tar.bz2;name=u-boot \
    file://0001-zynqmp-Downgrade-to-PMUFW-0.3.patch \
    https://download.opensuse.org/repositories/devel:/ARM:/Factory:/Contrib:/Zynq:/ZCU100/standard/noarch/zynqmp-pmufw-2017.1-7.2.noarch.rpm;name=pmufw \
    https://download.opensuse.org/repositories/devel:/ARM:/Factory:/Contrib:/Zynq:/ZCU100/standard/aarch64/zynqmp-fsbl-2017.1-7.2.aarch64.rpm;name=fsbl \
    https://download.opensuse.org/repositories/devel:/ARM:/Factory:/Contrib:/Zynq:/ZCU100/standard/noarch/zynqmp-hdf-20180326-4.2.noarch.rpm;name=hdf \
    file://ultra96.bif \
    file://ultra96-rules \
    "
SRC_URI[u-boot.sha256sum] = "e82ad58e1f83a39ba361b42203eaa2b70b30a43a64614b2b6853ffd68d0fd1f7"
SRC_URI[pmufw.sha256sum] = "c87fc36d08361399aa98f1c06a20f447c07f0061c781a015c1d7b0b866b135c9"
SRC_URI[fsbl.sha256sum] = "6f420f4cb049eb4ddd981fb9a1c964db9771e359dc51385a886bbcb27a9a616e"
SRC_URI[hdf.sha256sum] = "30e34942e3a33a5d9fdfa395e5a9cd28d21317eb6200353640e973591206ec3d"

U_BOOT_CONFIG="avnet_ultra96_rev1_defconfig"
U_BOOT_BIN="u-boot.elf"

S = "${WORKDIR}/u-boot-${U_BOOT_PV}"

do_prepare_build_append() {
    cp ${WORKDIR}/ultra96-rules ${S}/debian/rules

    unzip -u -d ${WORKDIR} ${WORKDIR}/usr/share/zynqmp/system.hdf \
        design_1_wrapper.bit

    sed -i "s/@ATF_PV@/${ATF_PV}/g" ${WORKDIR}/ultra96.bif

    echo "boot.bin /usr/lib/u-boot/ultra96" > ${S}/debian/u-boot-ultra96.install
}
