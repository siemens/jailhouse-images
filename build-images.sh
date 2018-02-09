#!/bin/sh
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

mkdir -p out
docker run -v $(pwd):/isar-jailhouse:ro -v $(pwd)/out:/out:rw \
	   -e USER_ID=$(id -u) --rm -t -i \
	   --cap-add=SYS_ADMIN --cap-add=MKNOD --privileged \
	   --device $(/sbin/losetup -f) \
	   -e http_proxy=$http_proxy -e https_proxy=$https_proxy \
	   -e no_proxy=$no_proxy \
	   kasproject/kas-isar sh -c "
		cd /out;
		kas build /isar-jailhouse/kas.yml"
