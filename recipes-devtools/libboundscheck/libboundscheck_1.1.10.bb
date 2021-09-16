# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Bounds checking functions (C11 Annex K)"
LICENSE = "MulanPSL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc479462e2e349c42ba9e4167df0f7aa"

SRC_URI = "git://gitee.com/openeuler/libboundscheck.git;protocol=https;branch=master"
SRCREV = "1a59e54390a23176d7f09ea4d20706fc991e0b00"

S = "${WORKDIR}/git"

# The project provided makefile builds a shared library, but want a static one instead
SRC_URI += "file://static-library.patch"

EXTRA_OEMAKE += "CC='${CC}'"

do_install () {
    install -D -t ${D}${includedir} ${S}/include/*.h
    install -D -t ${D}${libdir} ${S}/*.a
}

BBCLASSEXTEND = "native nativesdk"
