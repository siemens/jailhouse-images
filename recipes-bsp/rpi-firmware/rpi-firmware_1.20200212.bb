inherit dpkg-raw

SRC_URI = " \
    https://github.com/raspberrypi/firmware/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz \
    file://cmdline.txt \
    file://config.txt"
SRC_URI[sha256sum] = "e3b0577beb62d886a45016447bdf8c2d57b794d3d71b502868b16ba82ff8fe43"

do_install() {
    install -v -d ${D}/usr/lib/rpi-firmware/overlays
    install -v -m 644 ${WORKDIR}/firmware-${PV}/boot/bootcode.bin ${D}/usr/lib/rpi-firmware/
    install -v -m 644 ${WORKDIR}/firmware-${PV}/boot/LICENCE.broadcom ${D}/usr/lib/rpi-firmware/
    install -v -m 644 ${WORKDIR}/firmware-${PV}/boot/*.dat ${D}/usr/lib/rpi-firmware/
    install -v -m 644 ${WORKDIR}/firmware-${PV}/boot/*.dtb ${D}/usr/lib/rpi-firmware/
    install -v -m 644 ${WORKDIR}/firmware-${PV}/boot/*.elf ${D}/usr/lib/rpi-firmware/
    install -v -m 644 ${WORKDIR}/firmware-${PV}/boot/overlays/* ${D}/usr/lib/rpi-firmware/overlays/
    install -v -m 644 ${WORKDIR}/*.txt ${D}/usr/lib/rpi-firmware/
}
