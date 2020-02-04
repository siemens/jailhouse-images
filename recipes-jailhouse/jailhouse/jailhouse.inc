#
# Jailhouse, a Linux-based partitioning hypervisor
#
# Copyright (c) Siemens AG, 2018-2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# SPDX-License-Identifier: MIT
#

PN .= "-${KERNEL_NAME}"

DESCRIPTION = "Jailhouse partitioning hypervisor"

LICENSE = "GPL-2.0 & BSD-2-clause"

SRC_URI = " \
    git://github.com/siemens/jailhouse;branch=next \
    file://debian/ \
    file://nuc6cay.c \
    file://linux-nuc6cay-demo.c"

TEMPLATE_FILES = "debian/control.tmpl"
TEMPLATE_VARS += "KERNEL_NAME"

S = "${WORKDIR}/git"

inherit dpkg

DEPENDS = "linux-image-${KERNEL_NAME}"

do_prepare_build() {
    cp -r ${WORKDIR}/debian ${S}/
    deb_add_changelog
    if [ "${DISTRO_ARCH}" != "amd64" ]; then
        # Install device trees only on non-x86 archs as they only exist there
        echo "configs{,/*}/dts/*.dtb etc/jailhouse/dts" >> ${S}/debian/jailhouse.install
    fi
    mv ${S}/debian/jailhouse.install ${S}/debian/jailhouse-${KERNEL_NAME}.install

    cp ${WORKDIR}/nuc6cay.c ${S}/configs/x86/
    cp ${WORKDIR}/linux-nuc6cay-demo.c ${S}/configs/x86/
}

dpkg_runbuild_prepend() {
    export KDIR=$(dpkg -L --root=${BUILDCHROOT_DIR} linux-headers-${KERNEL_NAME} | \
                  grep "/lib/modules/.*/build")
}
