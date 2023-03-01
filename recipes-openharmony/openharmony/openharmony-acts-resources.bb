# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

SUMMARY = "ACTS Resources For OpenHarmony Test Suite"
DESCRIPTION = "This recipe installs the necessary resource files for running the OpenHarmony ACTS test suite."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "zip-native"

SRC_URI = "file://ceshi.txt \
           file://ceshitwo.txt"

S = "${WORKDIR}"

# install missing resources of ACTS tests
do_install() {
    appexecfwk_dir="${D}${libexecdir}/openharmony-standard/acts/resource/appexecfwk/"
    install -d ${appexecfwk_dir}
    install -m 0644 ${WORKDIR}/ceshi.txt ${appexecfwk_dir}
    install -m 0644 ${WORKDIR}/ceshitwo.txt ${appexecfwk_dir}
    cd ${appexecfwk_dir}
    # generate the zip file from ceshitwo.txt
    zip ceshitwo.zip ceshitwo.txt
}

PACKAGES = "${PN}"
FILES:${PN} = "/"
