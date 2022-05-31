# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Target specific integration of Oniro toolchain into OpenHarmony"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM += "file://BUILD_target.gn;beginline=1;endline=3;md5=a1537856660cf2c8e36079c007b35bec"

S = "${WORKDIR}"
B = "${WORKDIR}/build"

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"

SRC_URI = "file://BUILD_target.gn"

TARGET_GN_CPU = "${TRANSLATED_TARGET_ARCH}"
TARGET_GN_CPU:x86-64 = "x64"
TARGET_GN_CPU:aarch64 = "arm64"

do_compile() {
    sed -e "s|@TARGET_SYS@|${TARGET_SYS}|g" \
        -e "s|@TARGET_GN_CPU@|${TARGET_GN_CPU}|g" \
        -e "s|@TUNE_CCARGS@|${TUNE_CCARGS}|g" \
        -e "s|@bindir@|${bindir}|g" \
        < ${WORKDIR}/BUILD_target.gn > ${B}/BUILD.gn
}

# The files installed into ${D} will end in the target subdir of SDK
# sysroots/ dir.
do_install() {
    install -m 0644 ${B}/BUILD.gn ${D}/
}

PACKAGES = "${PN}"
FILES:${PN} = "/BUILD.gn"
# SYSROOT_DIRS does not support files, so we have to declare
# everything.  Luckily, we only have this one file, and we want all of
# that :)
SYSROOT_DIRS = "/"
