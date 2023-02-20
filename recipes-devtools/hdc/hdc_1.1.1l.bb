# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "hdc tool"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a832eda17114b48ae16cda6a500941c2"

inherit pkgconfig cmake

S = "${WORKDIR}/git"

SRC_URI += "git://gitee.com/openharmony/developtools_hdc_standard.git;protocol=https;branch=OpenHarmony-3.1-Release;rev=5304e6ff48d783362d577b8cf1fb1b34e3e451d4;lfs=0"
SRC_URI += "file://CMakeLists.txt;subdir=${S}"
SRC_URI += "file://libusb-include-path.patch"

DEPENDS += "libusb1 libuv openssl lz4 libboundscheck"

do_install:append() {
    ln -sfT hdc ${D}${bindir}/hdc_std
}

BBCLASSEXTEND = "native nativesdk"
