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

usage()
{
	echo "Usage: $0 [OPTIONS]"
	echo -e "\nOptions:"
	echo -e "--shell\t\tDrop into a shell to issue bitbake commands" \
		"manually."
	exit 1
}

CMD="build"

while [ $# -gt 0 ]; do
	case "$1" in
	--shell)
		CMD="shell"
		shift 1
		;;
	*)
		usage
		;;
	esac
done

mkdir -p out
docker run -v $(pwd):/jailhouse-images:ro -v $(pwd)/out:/out:rw \
	   -e USER_ID=$(id -u) -e SHELL=${SHELL} --rm -t -i \
	   --cap-add=SYS_ADMIN --cap-add=MKNOD --privileged \
	   --device $(/sbin/losetup -f) \
	   -e http_proxy=$http_proxy -e https_proxy=$https_proxy \
	   -e no_proxy=$no_proxy \
	   kasproject/kas-isar sh -c "
		cd /out;
		kas ${CMD} /jailhouse-images/kas.yml"
