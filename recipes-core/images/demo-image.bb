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

require recipes-core/images/isar-image-base.bb

IMAGE_PREINSTALL += "bash-completion less vim nano"

IMAGE_INSTALL += "jailhouse customizations"
