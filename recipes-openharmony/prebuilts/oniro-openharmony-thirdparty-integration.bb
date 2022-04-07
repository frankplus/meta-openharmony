# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Integration of Oniro 3rd party components into OpenHarmony"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://overlay/third_party/openssl/BUILD.gn;beginline=1;endline=3;md5=a1537856660cf2c8e36079c007b35bec"

BBCLASSEXTEND = "native nativesdk"

INHIBIT_DEFAULT_DEPS = "1"

DEPENDS += "oniro-openharmony-toolchain-integration"

S = "${WORKDIR}/src"
B = "${WORKDIR}/build"

SRC_URI += "file://patches/build_third_party.patch;apply=no;subdir=src"

SRC_URI += "file://third_party/openssl/BUILD.gn;subdir=src/overlay"

do_install() {
    mkdir -p ${D}${datadir}/oniro-openharmony
    cp -rv ${S}/* ${D}${datadir}/oniro-openharmony/
}

PACKAGES = "${PN}"
FILES:${PN} = "${datadir}/oniro-openharmony"
