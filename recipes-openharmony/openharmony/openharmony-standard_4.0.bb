# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenHarmony 4.0"


LICENSE = "0BSD & Apache-2.0 & BSD-2-Clause & BSD-3-Clause & BSL-1.0 & \
GPL-2.0-only & GPL-2.0-or-later & GPL-2-with-bison-exception & GPL-3.0-only & \
LGPL-2.1-only & LGPL-2.1-or-later & LGPL-3.0-only & CPL-1.0 & MIT & MIT-0 & \
MIT-Modern-Variant & Zlib & CC-BY-3.0 & CC-BY-SA-3.0 & CC-BY-NC-SA-3.0 & X11 & \
PD & OFL-1.1 & OpenSSL & MulanPSL-2.0 & bzip2-1.0.6 & ISC & ICU & IJG & Libpng & \
MPL-1.1 & MPL-2.0 & FTL"
LIC_FILES_CHKSUM = "file://build/LICENSE;md5=cfba563cea4ce607306f8a392f19bf6c"

DEPENDS += "bison-native"
DEPENDS += "perl-native"
DEPENDS += "ruby-native"
DEPENDS += "ncurses-native"
DEPENDS += "ccache-native"
DEPENDS += "clang-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-standard-${OPENHARMONY_VERSION}:"

include ${PN}-sources-${OPENHARMONY_VERSION}.inc

require java-tools.inc

inherit ccache
inherit logging

B = "${S}/out/rk3568"

SRC_URI += "file://prebuilts_download.sh"
SRC_URI += "file://prebuilts_download.py"

# The task is usually done by //build/prebuilts_download.sh but we are 
# skipping the download part which is done by do_fetch,
# we are extracting here only the main installation part of the prebuilts
prebuilts_download() {
    python3 ${WORKDIR}/prebuilts_download.py --host-cpu x86_64 --host-platform linux --code-dir ${S}
    chmod +x ${WORKDIR}/prebuilts_download.sh
    ${WORKDIR}/prebuilts_download.sh ${S}
}

python_is_python3() { 
    PYTHON3_DIR=$(dirname $(which python3))
    if [ ! -f "${PYTHON3_DIR}/python" ]; then
        ln -sf "${PYTHON3_DIR}/python3" "${PYTHON3_DIR}/python"
    else
        bbnote "not creating symlink, python already exists in ${PYTHON3_DIR}"
    fi
}

do_configure[prefuncs] += "prebuilts_download python_is_python3"

do_compile:append() {
    # Find the path of the g++ compiler
    GPP_PATH=$(which g++)
    if [ -z "$GPP_PATH" ]; then
        bberr "g++ not found."
        exit 1
    fi

    # Get the directory where g++ is located
    GPP_DIR=$(dirname "$GPP_PATH")

    # Check if c++ exists in the same directory
    if [ ! -f "$GPP_DIR/c++" ]; then
        # If c++ does not exist, create a symlink to g++
        ln -s "$GPP_PATH" "$GPP_DIR/c++"
        bbnote "Symbolic link for c++ created."
    fi
    
    CCACHE_LOGFILE="${CCACHE_DIR}/logfile.log"

    cd ${S}
    python3 ${S}/build/hb/main.py build --product-name rpi4 --ccache
}

do_install () {
    OHOS_PACKAGE_OUT_DIR="${B}/packages/${OHOS_PRODUCT_PLATFORM_TYPE}"

    # We install library files to ${libdir} and executables into ${bindir}, and
    # then setup /system/lib and /system/bin symlinks to avoid breaking use of
    # hard-coded paths.
    mkdir -p ${D}/system ${D}${libdir} ${D}${bindir}
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/lib/* ${D}${libdir}/
    install -m 755 -t ${D}${bindir}/ ${OHOS_PACKAGE_OUT_DIR}/system/bin/*
    ln -sfT ..${libdir} ${D}/system/lib
    ln -sfT ..${bindir} ${D}/system/bin
    # same for /vendor/lib and /vendor/bin
    mkdir -p ${D}/vendor
    cp -r ${OHOS_PACKAGE_OUT_DIR}/vendor/lib/* ${D}${libdir}/
    install -m 755 -t ${D}${bindir}/ ${OHOS_PACKAGE_OUT_DIR}/vendor/bin/*
    ln -sfT ..${libdir} ${D}/vendor/lib
    ln -sfT ..${bindir} ${D}/vendor/bin

    # system ability configurations
    mkdir -p ${D}${libdir}/openharmony/profile
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/profile/* ${D}${libdir}/openharmony/profile
    ln -sfT ..${libdir}/openharmony/profile ${D}/system/profile

    # odd files in /system/usr
    mkdir -p ${D}${libdir}/openharmony/usr
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/usr/* ${D}${libdir}/openharmony/usr
    ln -sfT ..${libdir}/openharmony/usr ${D}/system/usr

    # OpenHarmony etc (configuration) files
    mkdir -p ${D}${sysconfdir}/openharmony
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/etc/* ${D}${sysconfdir}/openharmony
    ln -sfT ..${sysconfdir}/openharmony ${D}/system/etc
    # same for /vendor/etc
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/vendor/etc/* ${D}${sysconfdir}/openharmony
    ln -sfT ..${sysconfdir}/openharmony ${D}/vendor/etc

    # OpenHarmony font files
    mkdir -p ${D}${datadir}/fonts/openharmony
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/fonts/* ${D}${datadir}/fonts/openharmony
    ln -sfT ..${datadir}/fonts/openharmony ${D}/system/fonts

    # OpenHarmony app files
    mkdir -p ${D}/system/app
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/app/* ${D}/system/app
}

inherit native
