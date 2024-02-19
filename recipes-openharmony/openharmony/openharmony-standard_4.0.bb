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

include ${PN}-sources-${OPENHARMONY_VERSION}.inc

python prebuilts_download() {
    # echo "prebuilts_download start"
    # if [ -d "${S}/prebuilts/build-tools/common/nodejs" ]; then
    #     rm -rf "${S}/prebuilts/build-tools/common/nodejs"
    # fi
    # python3 ${S}/build/prebuilts_download.py --host-cpu=x86_64 --host-platform=linux # TODO: avoid hardcoded values
    # echo "prebuilts_download end"
    pass
}

python_is_python3() { 
    PYTHON3_DIR=$(dirname $(which python3))
    if [ ! -f "${PYTHON3_DIR}/python" ]; then
        ln -sf "${PYTHON3_DIR}/python3" "${PYTHON3_DIR}/python"
    else
        echo "not creating symlink, python already exists in ${PYTHON3_DIR}"
    fi
}

do_configure[prefuncs] += "prebuilts_download python_is_python3"

# Allow network connectivity from do_configure() task. This is needed forprebuilts_download task
# TODO: this is ugly but works for now
do_configure[network] = "1"

do_compile:append() {
    python3 ${S}/build/hb/main.py build --product-name rk3568 -ccache
}
