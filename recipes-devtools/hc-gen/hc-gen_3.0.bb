# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Meta-build system that generates build files for Ninja"
LICENSE = "GPL-2.0-or-later | BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=e97719e0c951ddc23a7d37facffdf32a"

BBCLASSEXTEND = "native nativesdk"

DEPENDS += "bison-native flex-native"

SRC_URI = "git://gitee.com/openharmony/drivers_framework.git;protocol=https;branch=OpenHarmony-3.0-LTS"
SRCREV = "9a26a7542281dcab90e7c0e9aa33c921171117cb"

SRC_URI:append = " file://cxx-and-flags.patch"

S = "${WORKDIR}/git/tools/hc-gen"

EXTRA_OEMAKE += "CC='${CC}' CFLAGS='${CFLAGS}' LDFLAGS='${LDFLAGS}'"

# Include debug symbols in build (bitbake will strip place them in -dbg package)
EXTRA_OEMAKE += "BUILD_TYPE=debug"

do_install () {
    install -D -t ${D}${bindir} -m 755 ${S}/build/hc-gen
}
