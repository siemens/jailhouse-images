#!/bin/sh
#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2019
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

usage()
{
	printf "%b" "Usage: $0 [OPTIONS]\n"
	printf "%b" "\nOptions:\n"
	printf "%b" "--latest\tBuild latest Jailhouse version from next" \
		    "branch.\n"
	printf "%b" "--all\t\tBuild all available images (may take hours...).\n"
	printf "%b" "--shell\t\tDrop into a shell to issue bitbake commands" \
		    "manually.\n"
	exit 1
}

JAILHOUSE_IMAGES=$(dirname "$0")
KAS_CONTAINER=${KAS_CONTAINER:-${JAILHOUSE_IMAGES}/kas-container}
KAS_FILES="${JAILHOUSE_IMAGES}/kas.yml"
CMD="build"

while [ $# -gt 0 ]; do
	case "$1" in
	--latest)
		KAS_FILES="${KAS_FILES}:${JAILHOUSE_IMAGES}/opt-latest.yml"
		shift 1
		;;
	--rt)
		KAS_FILES="${KAS_FILES}:${JAILHOUSE_IMAGES}/opt-rt.yml"
		shift 1
		;;
	--all)
		KAS_TARGET=
		while read -r MACHINE DESCRIPTION; do
			KAS_TARGET="${KAS_TARGET} mc:${MACHINE}-jailhouse-demo:demo-image"
		done < "${JAILHOUSE_IMAGES}/images.list"
		shift 1
		;;
	--shell)
		CMD="shell"
		shift 1
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
	while read -r MACHINE DESCRIPTION; do
		MACHINES="${MACHINES} ${MACHINE}"
		NUM_MACHINES=$((NUM_MACHINES + 1))
		echo " ${NUM_MACHINES}: ${DESCRIPTION}"
	done < "${JAILHOUSE_IMAGES}/images.list"
	echo " 0: all (may take hours...)"
	echo ""

	printf "Select images to build (space-separated index list): "
	read -r SELECTION
	[ -z "${SELECTION}" ] && exit 0

	IFS=" "
	KAS_TARGET=
	for IDX in ${SELECTION}; do
		if [ "${IDX}" -eq 0 ] 2>/dev/null; then
			KAS_TARGET=
			for MACHINE in ${MACHINES}; do
				KAS_TARGET="${KAS_TARGET} mc:${MACHINE}-jailhouse-demo:demo-image"
			done
			break
		fi

		N=1
		for MACHINE in ${MACHINES}; do
			if [ ${N} -eq "${IDX}" ] 2>/dev/null; then
				KAS_TARGET="${KAS_TARGET} mc:${MACHINE}-jailhouse-demo:demo-image"
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
export KAS_TARGET

${KAS_CONTAINER} ${CMD} "${KAS_FILES}"
