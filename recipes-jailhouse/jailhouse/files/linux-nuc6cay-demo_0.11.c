/*
 * Jailhouse, a Linux-based partitioning hypervisor
 *
 * Configuration for Linux inmate, 2 CPUs, 74 MB RAM, ~1MB shmem, serial ports
 *
 * Copyright (c) Siemens AG, 2013-2018
 *
 * Authors:
 *  Jan Kiszka <jan.kiszka@siemens.com>
 *
 * This work is licensed under the terms of the GNU GPL, version 2.  See
 * the COPYING file in the top-level directory.
 */

#include <jailhouse/types.h>
#include <jailhouse/cell-config.h>

#define ARRAY_SIZE(a) sizeof(a) / sizeof(a[0])

struct {
	struct jailhouse_cell_desc cell;
	__u64 cpus[1];
	struct jailhouse_memory mem_regions[4];
	__u8 pio_bitmap[0x2000];
	struct jailhouse_pci_device pci_devices[1];
} __attribute__((packed)) config = {
	.cell = {
		.signature = JAILHOUSE_CELL_DESC_SIGNATURE,
		.revision = JAILHOUSE_CONFIG_REVISION,
		.name = "linux-nuc6cay-demo",
		.flags = JAILHOUSE_CELL_PASSIVE_COMMREG |
			 JAILHOUSE_CELL_VIRTUAL_CONSOLE_PERMITTED,

		.cpu_set_size = sizeof(config.cpus),
		.num_memory_regions = ARRAY_SIZE(config.mem_regions),
		.pio_bitmap_size = ARRAY_SIZE(config.pio_bitmap),
		.num_pci_devices = ARRAY_SIZE(config.pci_devices),
	},

	.cpus = {
		0xc,
	},

	.mem_regions = {
		/* low RAM */ {
			.phys_start = 0x3a600000,
			.virt_start = 0,
			.size = 0x00100000,
			.flags = JAILHOUSE_MEM_READ | JAILHOUSE_MEM_WRITE |
				JAILHOUSE_MEM_EXECUTE | JAILHOUSE_MEM_DMA |
				JAILHOUSE_MEM_LOADABLE,
		},
		/* communication region */ {
			.virt_start = 0x00100000,
			.size = 0x00001000,
			.flags = JAILHOUSE_MEM_READ | JAILHOUSE_MEM_COMM_REGION,
		},
		/* high RAM */ {
			.phys_start = 0x3a700000,
			.virt_start = 0x00200000,
			.size = 0x4a00000,
			.flags = JAILHOUSE_MEM_READ | JAILHOUSE_MEM_WRITE |
				JAILHOUSE_MEM_EXECUTE | JAILHOUSE_MEM_DMA |
				JAILHOUSE_MEM_LOADABLE,
		},
		/* IVSHMEM shared memory region */
		{
			.phys_start = 0x3f100000,
			.virt_start = 0x3f100000,
			.size = 0xff000,
			.flags = JAILHOUSE_MEM_READ | JAILHOUSE_MEM_WRITE |
				JAILHOUSE_MEM_ROOTSHARED,
		},
	},

	.pio_bitmap = {
		[     0/8 ... 0xffff/8] = -1,
	},

	.pci_devices = {
		{
			.type = JAILHOUSE_PCI_TYPE_IVSHMEM,
			.domain = 0x0,
			.bdf = 0x10 << 3,
			.bar_mask = {
				0xffffff00, 0xffffffff, 0x00000000,
				0x00000000, 0xffffffe0, 0xffffffff,
			},
			.num_msix_vectors = 1,
			.shmem_region = 3,
			.shmem_protocol = JAILHOUSE_SHMEM_PROTO_VETH,
		},
	},
};
