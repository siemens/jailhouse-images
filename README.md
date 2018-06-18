Jailhouse Image
===============

The goal of this project is to generate ready-to-use reference images for the
[Jailhouse hypervisor](https://github.com/siemens/jailhouse) to support
demonstration and testing. The images are generated from Debian packages using
the [Isar build system](https://github.com/ilbers/isar).


Quickstart for Virtual Targets
------------------------------

The host-side requirements are:

- Docker (tested with 17.09.1-ce)
- QEMU >= 2.8 for x86 image, >= 2.12 for ARM64 image
- Kernel >= 4.4 with KVM support (for x86 image)
- On Intel, kvm_intel module loaded with parameter "nested=1"

To build all images, just run ```build-images.sh```. A QEMU image can then be
started using ```start-qemu.sh ARCHITECTURE```. Currently supported are "x86"
and "arm64" as architectures.

Note that the ARM64 image is not built by default as it currently takes several
hours to complete. You can request the build this way:

    KAS_TARGET="multiconfig:qemuarm64-jailhouse:demo-image" ./build-images.sh


Quickstart for Physical Targets
-------------------------------

Currently supported is the [Orange Pi Zero](http://www.orangepi.org/orangepizero)
(256 MB edition). Run the following command to build this target:

    KAS_TARGET="multiconfig:orangepi-zero-jailhouse:demo-image" ./build-images.sh

Afterwards, flash the image on an empty SD card:

    dd if=out/build/tmp/deploy/images/demo-image-debian-stretch-orangepi-zero.wic.img \
       of=/dev/mmcblk0 bs=4M status=progress

To configure the WLAN interface on this board, create
/etc/network/interfaces.d/wlan0 with the following content:

    allow-hotplug wlan0

    iface wlan0 inet dhcp
        wpa-ssid <your wlan ssid>
        wpa-psk <your wlan key>

Note that the driver and the WLAN firmware are of experimental quality and have
significant reception latency problems. In contrast, the LAN interface works
smoothly.


Community Resources
-------------------

See [Jailhouse project](https://github.com/siemens/jailhouse).
