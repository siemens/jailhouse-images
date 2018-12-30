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
    if machine in ['qemu-arm64', 'espressobin', 'macchiatobin', 'hikey620']:
        machine = 'arm64'
    d.setVar('KERNEL_DEFCONFIG', machine + '_defconfig_4.19')
}

SRC_URI += " \
    https://github.com/siemens/linux/archive/${SRCREV}.tar.gz \
    file://0001-ARM-dts-orange-pi-zero-Adjust-wifi-settings.patch \
    file://0001-pwrseq_simple-Workaround-for-missing-device-tree-ent.patch \
    file://0001-arm64-dts-zcu100-revC-Give-wifi-some-time-after-powe.patch \
    file://${KERNEL_DEFCONFIG}"

S = "${WORKDIR}/linux-${SRCREV}"

KBUILD_DEPENDS += "liblz4-tool"
