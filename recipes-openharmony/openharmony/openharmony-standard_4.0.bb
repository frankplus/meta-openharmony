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
DEPENDS += "hc-gen-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-standard-${OPENHARMONY_VERSION}:"

include ${PN}-sources-${OPENHARMONY_VERSION}.inc

require java-tools.inc

inherit ccache
inherit logging

OHOS_PRODUCT_NAME="rk3568"
B = "${S}/out/${OHOS_PRODUCT_NAME}"

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

    cd ${S}
    python3 ${S}/build/hb/main.py build --product-name ${OHOS_PRODUCT_NAME} --ccache
}

do_install () {
    OHOS_PACKAGE_OUT_DIR="${B}/packages/phone"
    bbnote "installing contents from ${OHOS_PACKAGE_OUT_DIR} to ${D}"

    # We install library files to /lib and executables into /bin, and
    # then setup /system/lib and /system/bin symlinks to avoid breaking use of
    # hard-coded paths.
    mkdir -p ${D}/system ${D}/lib ${D}/bin
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/lib/* ${D}/lib/
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/bin/* ${D}/bin/
    find ${D}/bin/ -type f -exec chmod 755 {} \;
    ln -sfT ../lib ${D}/system/lib
    ln -sfT ../bin ${D}/system/bin

    # OpenHarmony etc (configuration) files
    mkdir -p ${D}/etc
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/etc/* ${D}/etc
    ln -sfT ../etc ${D}/system/etc

    # system ability configurations
    mkdir -p ${D}/system/profile
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/profile/* ${D}/system/profile

    # copy /system/usr
    mkdir -p ${D}/system/usr
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/usr/* ${D}/system/usr

    # OpenHarmony font files
    mkdir -p ${D}/system/fonts
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/fonts/* ${D}/system/fonts

    # OpenHarmony app files
    mkdir -p ${D}/system/app
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/app/* ${D}/system/app

    # copy /vendor
    mkdir -p ${D}/vendor
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/vendor/* ${D}/vendor
}

inherit native
