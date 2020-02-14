Jailhouse Image
===============

The goal of this project is to generate ready-to-use reference images for the
[Jailhouse hypervisor](https://github.com/siemens/jailhouse) to support
demonstration and testing. The images are generated from Debian packages using
the [Isar build system](https://github.com/ilbers/isar).


Quickstart for Virtual Targets
------------------------------

The host-side requirements are:

- Docker (tested with 19.03.5-ce)
- QEMU >= 3.1
- Kernel >= 4.4 with KVM support (for qemu-x86 image)
- kvm_intel module loaded with parameter `nested=1` (for qemu-x86 image on
  kernel < 4.20)

To build a target image, just run `build-images.sh` and select one (or
both) of the QEMU targets. The generated image can then be executed using
`start-qemu.sh ARCHITECTURE`. Currently supported are `x86` (only works on
Intel CPUs so far) and `arm64` as architectures. On x86, make sure the
kvm-intel module was loaded with `nested=1` to enable nested VMX support.


Quickstart for Physical Targets
-------------------------------

Call `build-images.sh` and select the desired target. Afterwards, flash the
image on an empty SD card, e.g.:

    dd if=build/tmp/deploy/images/orangepi-zero/demo-image-jailhouse-demo-orangepi-zero.wic.img \
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

Note that XHCI is no longer working with the combination of pre-built vendor
U-Boot 2017.03-armada-17.10 and kernel 5.4. The kernel suggests to update the
firmware. This involves manual building and flashing a more recent version.

### MACCHIATObin

The [MACCHIATObin](http://macchiatobin.net/compare/) is supported in both
variants. Same story as with the ESPRESSObin regarding the pre-installed
U-Boot, but we are able to replace it with a recent upstream version:
Follow the [instructions](http://wiki.macchiatobin.net/tiki-index.php?page=MACCHIATObin+Interface+list#Boot_Selection)
to switch the board to SD card booting, then flash the image on an empty
card and plug that into the board.

Note that the generated image is not yet directly usable for booting from the
eMMC.

### HiKey

The [LeMaker HiKey](http://www.lemaker.org/product-hikey-specification.html)
with Kirin 620 SoC is supported with its 2 GB edition. The generated image can
be used to boot from SD card. This requires a recent version of the UEFI-based
bootloader (tested with version 85, December 20 2018).

You may also use the content of the boot and the root partition to fill the
corresponding partitions on the eMMC, but do no flash the complete image
directly to the eMMC because it does not contain any firmware.

## Ultra96

The [Avnet Ultra96](https://www.96boards.org/product/ultra96/) is supported.
You can boot the board directly from the generated SD card image.

## Raspberry Pi 4

The [Raspberry Pi 4 Model B](https://www.raspberrypi.org/products/raspberry-pi-4-model-b/)
is support. You can boot the board directly from the generated SD card image.
The mini UART on the GPIO header (pin 6/8/10: Ground/TXD/RXD) is used as serial
console.

## Pine64+

The [Pine64+](https://www.pine64.org/devices/single-board-computers/pine-a64/)
with Allwinner A64 is supported with its 2GB edition. You can boot the board
directly from the generated SD card image. UART0 available via EXP 10 connector
(pin 7/8/9: TXD/RXD/GND) is used as serial console. For details refer
[here](https://linux-sunxi.org/Pine64#Serial_port_.2F_UART).

Community Resources
-------------------

See [Jailhouse project](https://github.com/siemens/jailhouse).


License
-------

Unless otherwise stated in the respective file, files in this layer are
provided under the MIT license, see COPYING file. Patches (files ending with
.patch) are licensed according to their target project and file, typically
GPLv2.
