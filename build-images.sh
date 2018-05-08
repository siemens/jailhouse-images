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
	echo -e "--latest\tBuild latest Jailhouse version from next branch."
	echo -e "--shell\t\tDrop into a shell to issue bitbake commands" \
		"manually."
	echo -e "--docker-args\tAdditional arguments to pass to docker for" \
		"running the build."
	exit 1
}

LATEST=""
CMD="build"
DOCKER_ARGS=""

while [ $# -gt 0 ]; do
	case "$1" in
	--latest)
		LATEST="-latest"
		shift 1
		;;
	--shell)
		CMD="shell"
		shift 1
		;;
	--docker-args)
		[ $# -gt 0 ] || usage
		DOCKER_ARGS=$2
		shift 2
		;;
	*)
		usage
		;;
	esac
done

mkdir -p out
docker run -v $(pwd):/jailhouse-images:ro -v $(pwd)/out:/out:rw \
	   -e USER_ID=$(id -u) -e SHELL=${SHELL} -e KAS_TARGET="${KAS_TARGET}" \
	   --rm -t -i --cap-add=SYS_ADMIN --cap-add=MKNOD --privileged \
	   --device $(/sbin/losetup -f) \
	   -e http_proxy=$http_proxy -e https_proxy=$https_proxy \
	   -e no_proxy=$no_proxy ${DOCKER_ARGS} \
	   kasproject/kas-isar sh -c "
		cd /out;
		kas ${CMD} /jailhouse-images/kas${LATEST}.yml"
