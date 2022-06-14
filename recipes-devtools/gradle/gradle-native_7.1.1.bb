# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "gradle build tool"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=68357e3ca76cd18c7d868c3d2d0fa377"

SRC_URI = "https://services.gradle.org/distributions/gradle-7.1.1-bin.zip"
SRC_URI[sha256sum] = "bf8b869948901d422e9bb7d1fa61da6a6e19411baa7ad6ee929073df85d6365d"

inherit native

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${libdir}/plugins
        install -m 0755 bin/gradle ${D}${bindir}/
        install -m 0755 lib/*.jar ${D}${libdir}/
        install -m 0755 lib/plugins/*.jar ${D}${libdir}/plugins
}
