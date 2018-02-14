inherit dpkg-raw

DESCRIPTION = "demo image customizations"
DEBIAN_DEPENDS = "passwd"

SRC_URI = " \
    file://postinst \
    file://issue"

do_install() {
	install -v -d ${D}/etc
	install -v -m 644 ${WORKDIR}/issue ${D}/etc/issue
}
