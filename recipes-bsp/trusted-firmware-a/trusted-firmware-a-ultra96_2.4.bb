#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2019-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

require trusted-firmware-a_${PV}.inc

SRC_URI += " \
    file://0001-zynqmp-pm-Filter-errors-related-to-clock-gate-permis.patch \
    file://0002-zynqmp-pm-update-error-codes-to-match-Linux-and-PMU-.patch \
    "

TF_A_NAME = "ultra96"
TF_A_PLATFORM = "zynqmp"
TF_A_EXTRA_BUILDARGS = "RESET_TO_BL31=1 ZYNQMP_CONSOLE=cadence1"
TF_A_BINARIES = "release/bl31/bl31.elf"
