#!/bin/sh

eval $(grep ^BR2_ARCH= ${BR2_CONFIG})
if [ ${BR2_ARCH} = arm ]; then
    ABI="gnueabihf"
fi

${BR2_ARCH}-buildroot-linux-uclibc${ABI}-gcc -o $1/usr/bin/ivshmem-demo ../ivshmem-demo.c
