# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Linux kernel for OpenHarmony"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# TODO: check correct version
LINUX_VERSION ?= "5.10.97"
KMETA_BRANCH ?= "yocto-5.10"

PV = "${LINUX_VERSION}+git${SRCPV}"

require recipes-kernel/linux/linux-yocto.inc

ORIGIN_URL = "git://github.com/eclipse-oniro-mirrors"

# Specify the source URI for the kernel
SRC_URI = "${ORIGIN_URL}/kernel_linux_5.10.git;name=linux-openharmony;protocol=https;branch=OpenHarmony-3.2-Release"
SRCREV_linux-openharmony = "6345b3a7466c544260483bb41060511f4039907e"

KMETA = "kernel-meta"
SRC_URI += "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"
SRCREV_meta = "e1979ceb171bc91ef2cb71cfcde548a101dab687"

SRC_URI += "${ORIGIN_URL}/third_party_bounds_checking_function.git;protocol=https;branch=OpenHarmony-3.2-Release;name=third_party_bounds_checking_function;destsuffix=third_party/bounds_checking_function"
SRCREV_third_party_bounds_checking_function = "f15960f6888556f671925f8b3d0d3072956a3678"

SRC_URI += "${ORIGIN_URL}/third_party_FreeBSD.git;protocol=https;branch=OpenHarmony-3.2-Release;name=third_party_FreeBSD;destsuffix=third_party/FreeBSD"
SRCREV_third_party_FreeBSD = "ca57c902d175ebcdda33707764e9cbc82fc3024c"

SRC_URI += "${ORIGIN_URL}/drivers_hdf_core.git;protocol=https;branch=OpenHarmony-3.2-Release;name=drivers_hdf_core;destsuffix=drivers/hdf_core"
SRCREV_drivers_hdf_core = "213c680542b0f9916c317f4d77507529b166c582"

SRC_URI += "git://gitee.com/openharmony-sig/vendor_iscas.git;protocol=https;branch=OpenHarmony-3.2-Release;name=vendor_iscas;destsuffix=vendor/iscas"
SRCREV_vendor_iscas = "16a4d8efba39c03e46342dc75f1b400ee8258136"

SRC_URI += "file://defconfig"
SRC_URI += "file://hdf.patch"
SRC_URI += "file://hdf_patch.sh"
SRC_URI += "file://rpi4.patch"
SRC_URI += "file://kbuild-flags.patch"

# Specify the paths for patches and additional files
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-openharmony:"

# Define the compatibility of the kernel
COMPATIBLE_MACHINE = "qemuarma7|raspberrypi4-64"

# use file://defconfig
KBUILD_DEFCONFIG:qemuarma7 = ""
KBUILD_DEFCONFIG:raspberrypi4-64 = ""

do_patch:append(){
    KERNEL_BUILD_ROOT=${S}
    bash ${WORKDIR}/hdf_patch.sh ${WORKDIR} ${KERNEL_BUILD_ROOT}
}

do_compile:prepend(){
    export PRODUCT_PATH=vendor/iscas/rpi4
}

do_compile_kernelmodules:prepend(){
    export PRODUCT_PATH=vendor/iscas/rpi4
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
