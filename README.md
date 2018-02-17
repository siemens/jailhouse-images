Jailhouse Image
===============

The goal of this project is to generate ready-to-use reference images for the
[Jailhouse hypervisor](https://github.com/siemens/jailhouse) to support
demonstration and testing. The images are generated from Debian packages using
the [Isar build system](https://github.com/ilbers/isar).


Quickstart
----------

The host-side requirements are:

- Docker (tested with 17.09.1-ce)
- QEMU 2.7 (for x86 image)
- Kernel >= 4.4 with KVM support (for x86 image)

To build all images, just run ```build-image.sh```. A QEMU image can then be
started using ```start-qemu.sh ARCHITECTURE```. Currently supported is "x86" as
architecture.


Community Resources
-------------------

See [Jailhouse project](https://github.com/siemens/jailhouse).
