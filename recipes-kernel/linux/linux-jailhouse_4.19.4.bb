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

require recipes-kernel/linux/linux-custom.inc

python() {
    machine = d.getVar('MACHINE', True)
    if machine in ['qemu-amd64', 'nuc6cay']:
        machine = 'x86-64'
    if machine in ['qemu-arm64', 'espressobin', 'macchiatobin', 'hikey']:
        machine = 'arm64'
    d.setVar('KERNEL_DEFCONFIG', machine + '_defconfig_4.19')
}

SRC_URI += " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz \
    file://0001-ARM-dts-orange-pi-zero-Adjust-wifi-settings.patch \
    file://${KERNEL_DEFCONFIG}"
SRC_URI[sha256sum] = "a277fe6a0656e52c370fdb23115c960f77e7760d91e5a81c66e8d7286048bd06"
SRCREV = "4e0310ab768840f16ac4fa455be66e75c9d68fcc"

S = "${WORKDIR}/linux-${SRCREV}"

KBUILD_DEPENDS += "liblz4-tool"
