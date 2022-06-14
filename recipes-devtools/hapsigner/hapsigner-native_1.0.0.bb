# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "HAP signer tool"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit native

S = "${WORKDIR}/src"

SRC_URI = "git://gitee.com/openharmony/developtools_hapsigner.git;protocol=http;branch=OpenHarmony-3.1-Release;rev=ff2cf76f14f7d6c6fa55c9d0f02c4c1eb9c795a9;destsuffix=${S}"

DEPENDS:append = " gradle-native"
require recipes-openharmony/openharmony/java-tools.inc

do_configure[noexec] = "1"

# Network connectivity needed for gradle daemon
do_compile[network] = "1"

do_compile() {
	cd hapsigntool
	gradle build
}

DSTDIR = "${libdir}/hap-sign-tool"

do_install() {
	install -d ${D}/${DSTDIR}
	install -m 0644 hapsigntool/hap_sign_tool/build/libs/hap-sign-tool.jar ${D}/${DSTDIR}/
	install -m 0644 dist/OpenHarmony.p12 ${D}/${DSTDIR}/
	install -m 0644 dist/OpenHarmonyApplication.pem ${D}/${DSTDIR}/
}
