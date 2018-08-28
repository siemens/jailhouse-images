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
- On Intel, kvm_intel module loaded with parameter `nested=1`

To build a target image, just run `build-images.sh` and select one (or
both) of the QEMU targets. The generated image can then be executed using
`start-qemu.sh ARCHITECTURE`. Currently supported are `x86` and `arm64` as
architectures.


Quickstart for Physical Targets
-------------------------------

Call `build-images.sh` and select the desired target. Afterwards, flash the
image on an empty SD card, e.g.:

    dd if=out/build/tmp/deploy/images/demo-image-debian-stretch-orangepi-zero.wic.img \
       of=/dev/mmcblk0 bs=4M status=progress

### Orange Pi Zero

The [Orange Pi Zero](http://www.orangepi.org/orangepizero) is supported with
its 256 MB edition. Ethernet is supported out of the box with the generated
image. To configure the WLAN interface on this board, create
`/etc/network/interfaces.d/wlan0` with the following content:

    allow-hotplug wlan0

    iface wlan0 inet dhcp
        wpa-ssid <your wlan ssid>
        wpa-psk <your wlan key>

Note that the driver and the WLAN firmware are of experimental quality and have
significant reception latency problems. In contrast, the LAN interface works
smoothly.

### NUC6CAY

The [NUC6CAY](https://www.intel.com/content/www/us/en/products/boards-kits/nuc/mini-pcs/nuc6cays.html)
is supported with 8 GB of RAM. It can boot from an SD card, or you can flash
the generated on a built-in storage device. The device has to boot in EFI mode.

As the device comes without a UART connector, the output of Jailhouse can only
be seen via the EFI framebuffer on a monitor or on the virtual Jailhouse
console (`jailhouse console`).

### ESPRESSObin

The [ESPRESSObin](http://espressobin.net/tech-spec/) 1 GB edition is supported.
Before being able to boot the SD card image, the pre-installed U-Boot needs
further manual tuning (because the old vendor U-Boot lacks distro support).
Attach to the serial port of the board and type the following on the U-Boot
command line:

    setenv bootcmd "load mmc 0:1 0x4d00000 /boot/boot.scr; source 0x4d00000"
    saveenv
    reset

After that, the board will automatically start from the generated SD card
image.


Community Resources
-------------------

See [Jailhouse project](https://github.com/siemens/jailhouse).
