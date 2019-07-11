#!/bin/sh
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

usage()
{
	echo "Usage: $0 [OPTIONS]"
	echo -e "\nOptions:"
	echo -e "--latest\tBuild latest Jailhouse version from next branch."
	echo -e "--all\t\tBuild all available images (may take hours...)."
	echo -e "--shell\t\tDrop into a shell to issue bitbake commands" \
		"manually."
	echo -e "--docker-args\tAdditional arguments to pass to docker for" \
		"running the build."
	exit 1
}

KAS_FILES="/jailhouse-images/kas.yml"
CMD="build"
DOCKER_ARGS=""

while [ $# -gt 0 ]; do
	case "$1" in
	--latest)
		KAS_FILES="${KAS_FILES}:/jailhouse-images/opt-latest.yml"
		shift 1
		;;
	--rt)
		KAS_FILES="${KAS_FILES}:/jailhouse-images/opt-rt.yml"
		shift 1
		;;
	--all)
		KAS_TARGET=
		while read MACHINE DESCRIPTION; do
			KAS_TARGET="${KAS_TARGET} multiconfig:${MACHINE}-jailhouse-demo:demo-image"
		done < images.list
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

if [ -z "${KAS_TARGET}" ]; then
	echo "Available images demo images:"
	IFS="	"
	MACHINES=
	NUM_MACHINES=0
	while read MACHINE DESCRIPTION; do
		MACHINES="${MACHINES} ${MACHINE}"
		NUM_MACHINES=$((NUM_MACHINES + 1))
		echo " ${NUM_MACHINES}: ${DESCRIPTION}"
	done < images.list
	echo " 0: all (may take hours...)"
	echo ""

	echo -n "Select images to build (space-separated index list): "
	read SELECTION
	[ -z "${SELECTION}" ] && exit 0

	IFS=" "
	KAS_TARGET=
	for IDX in ${SELECTION}; do
		if [ ${IDX} -eq 0 ] 2>/dev/null; then
			KAS_TARGET=
			for MACHINE in ${MACHINES}; do
				KAS_TARGET="${KAS_TARGET} multiconfig:${MACHINE}-jailhouse-demo:demo-image"
			done
			break
		fi

		N=1
		for MACHINE in ${MACHINES}; do
			if [ ${N} -eq ${IDX} ] 2>/dev/null; then
				KAS_TARGET="${KAS_TARGET} multiconfig:${MACHINE}-jailhouse-demo:demo-image"
				break
			fi
			N=$((N + 1))
		done
		if [ ${N} -gt ${NUM_MACHINES} ]; then
			echo "Invalid index: ${IDX}"
			exit 1
		fi
	done
fi

if [ -t 1 ]; then
	INTERACTIVE="-t -i"
fi

docker run -v $(pwd):/jailhouse-images:ro -v $(pwd):/work:rw --workdir=/work \
	   -e USER_ID=$(id -u) -e SHELL=${SHELL} \
	   -e KAS_TARGET="${KAS_TARGET}" -e KAS_TASK="${KAS_TASK}" \
	   --rm ${INTERACTIVE} \
	   --cap-add=SYS_ADMIN --cap-add=MKNOD --privileged \
	   -e http_proxy=$http_proxy -e https_proxy=$https_proxy \
	   -e ftp_proxy=$ftp_proxy -e no_proxy=$no_proxy \
	   -e NO_PROXY=$NO_PROXY ${DOCKER_ARGS} \
	   kasproject/kas-isar ${CMD} ${KAS_FILES}
