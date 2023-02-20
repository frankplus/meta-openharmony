# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "XDevice, a core module of the OpenHarmony test framework, provides services on which test case execution depends."
SUMMARY = "Tool for remote test execution in OpenHarmony test framework"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI += "git://gitee.com/openharmony/test_xdevice.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b0845abc4da7c5deef3335f7052ec9fa29ef4c34;lfs=0"
S = "${WORKDIR}/git/extension"

inherit setuptools3

RDEPENDS:${PN} += "python3-setuptools"

BBCLASSEXTEND = "native nativesdk"
