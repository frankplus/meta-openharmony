# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Linux kernel for OpenHarmony"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# TODO: check correct version
LINUX_VERSION ?= "5.10.165"
KMETA_BRANCH ?= "yocto-5.10"

PV = "${LINUX_VERSION}+git${SRCPV}"

require recipes-kernel/linux/linux-yocto.inc

ORIGIN_URL = "git://github.com/eclipse-oniro-mirrors"

# Specify the source URI for the kernel
SRC_URI = "${ORIGIN_URL}/kernel_linux_5.10.git;name=linux-openharmony;protocol=https;branch=OpenHarmony-4.0-Release"
SRCREV_linux-openharmony = "65fc16cf207366cc21ba04ae652c6314917ce089"

KMETA = "kernel-meta"
SRC_URI += "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"
SRCREV_meta = "e1979ceb171bc91ef2cb71cfcde548a101dab687"

SRC_URI += "${ORIGIN_URL}/kernel_linux_common_modules.git;protocol=https;branch=OpenHarmony-4.0-Release;type=kmeta;name=kernel_linux_common_modules;destsuffix=kernel/linux/common_modules"
SRCREV_kernel_linux_common_modules = "0ce4109b8e33aa12e0181957c9921a3ec962bfee"

SRC_URI += "${ORIGIN_URL}/third_party_bounds_checking_function.git;protocol=https;branch=OpenHarmony-4.0-Release;name=third_party_bounds_checking_function;destsuffix=third_party/bounds_checking_function"
SRCREV_third_party_bounds_checking_function = "cbfb477f4197a899eca3cfeb5e791d4a66c71ac1"

SRC_URI += "${ORIGIN_URL}/third_party_FreeBSD.git;protocol=https;branch=OpenHarmony-4.0-Release;name=third_party_FreeBSD;destsuffix=third_party/FreeBSD"
SRCREV_third_party_FreeBSD = "870b4df7834b4cfec174e71d65b8258828ace33c"

SRC_URI += "${ORIGIN_URL}/drivers_hdf_core.git;protocol=https;branch=OpenHarmony-4.0-Release;name=drivers_hdf_core;destsuffix=drivers/hdf_core"
SRCREV_drivers_hdf_core = "0217b83f3f07ba7b3b5a9a7524a20f363cb330db"

SRC_URI += "file://defconfig"
SRC_URI += "file://hdf.patch"
SRC_URI += "file://hdf_patch.sh"
SRC_URI += "file://remove-vendor-kconfig.patch"
SRC_URI += "file://net-sched-sch_qfq-fix.patch"
# SRC_URI += "file://qemu-arm-linux.patch"
SRC_URI += "file://rpi4.patch"

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

do_configure:prepend() {
    KERNEL_BUILD_ROOT=${S}
    ROOT_DIR=${WORKDIR}
    
    bash ${ROOT_DIR}/kernel/linux/common_modules/newip/apply_newip.sh ${ROOT_DIR} ${KERNEL_BUILD_ROOT} ${MACHINE} ${LINUX_VERSION}
    bash ${ROOT_DIR}/kernel/linux/common_modules/qos_auth/apply_qos_auth.sh ${ROOT_DIR} ${KERNEL_BUILD_ROOT} ${MACHINE} ${LINUX_VERSION}
    if [ ! -d "$KERNEL_BUILD_ROOT/security/xpm" ]; then
        bash ${ROOT_DIR}/kernel/linux/common_modules/xpm/apply_xpm.sh ${ROOT_DIR} ${KERNEL_BUILD_ROOT} ${MACHINE} ${LINUX_VERSION}
    fi
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
