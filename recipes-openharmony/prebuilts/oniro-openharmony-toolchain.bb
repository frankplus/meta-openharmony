# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Oniro toolchain for OpenHarmony source repository"
LICENSE = "Apache-2.0"

DESCRIPTION = "This recipe provides a toolchain based on Clang and musl libc \
that can be used to build the OpenHarmony codebase. An SDK image is built and \
is for installing on top of an OpenHarmony source repository, and used for \
building OpenHarmony using Oniro toolchain without the need to get involved \
with bitbake. Recipes building OpenHarmony with bitbake should depend on this \
recipe, and will need to hook in the recipe-sysroot and recipe-sysroot-native \
folders before building."

inherit populate_sdk

TOOLCHAIN_OUTPUTNAME = "${PN}-${TUNE_PKGARCH}-${SDK_VERSION}"
SDK_VERSION ?= "${DISTRO_VERSION}"

TOOLCHAIN_HOST_TASK += "nativesdk-oniro-openharmony-toolchain-integration"
TOOLCHAIN_HOST_TASK += "nativesdk-gn nativesdk-ninja"
TOOLCHAIN_HOST_TASK += "nativesdk-hc-gen"
TOOLCHAIN_HOST_TASK += "nativesdk-clang nativesdk-compiler-rt-staticdev nativesdk-libcxx-staticdev"
# Note: Adding -native recipes to DEPENDS will not help us here, as they are
# being ignored when depending on this (target) recipe. So we will have to add
# these dependencies in the OpenHarmony recipes.

TOOLCHAIN_TARGET_TASK += "oniro-openharmony-target-integration"
DEPENDS += "oniro-openharmony-target-integration"

move_oniro_dir() {
    mv ${SDK_OUTPUT}${SDKPATHNATIVE}${datadir_native}/oniro-openharmony/* ${SDK_OUTPUT}${SDKPATH}/
}
POPULATE_SDK_POST_HOST_COMMAND += "move_oniro_dir; "

create_sysroot_symlinks() {
    (
	      cd ${SDK_OUTPUT}${SDKPATH}/sysroots
	      ln -s ${REAL_MULTIMACH_TARGET_SYS} target
	      ln -s ${SDK_SYS} host
    )
}
POPULATE_SDK_POST_TARGET_COMMAND += "create_sysroot_symlinks; "

DEPENDS += "musl"
TOOLCHAIN_TARGET_TASK += "musl-dev"
# OpenHarmony patched musl to include sys/capability.h header file, but we will
# be using the one from libcap instead
DEPENDS += "libcap"
TOOLCHAIN_TARGET_TASK += "libcap-dev"
