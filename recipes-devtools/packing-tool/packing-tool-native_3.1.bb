# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenHarmony Packing Tool"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a832eda17114b48ae16cda6a500941c2"

inherit cmake

require recipes-openharmony/openharmony/java-tools.inc

S = "${WORKDIR}/git"

SRC_URI += "git://gitee.com/openharmony/developtools_packing_tool.git;protocol=http;branch=OpenHarmony-3.1-Release;rev=f6eb2e99858479915c34b9c2e5984403dc733247"
SRC_URI += "file://CMakeLists.txt;subdir=${S}"
SRC_URI += "file://fastjson-pkg-name.patch"

# Fetch fastjson-2.0.7.jar package from maven repository. Licensed under Apache 2.0
SRC_URI += "https://search.maven.org/remote_content?g=com.alibaba&a=fastjson&v=2.0.7;unpack=0;subdir=${S}/lib;downloadfilename=fastjson.jar;name=fastjson"
SRC_URI[fastjson.sha256sum] = "902de86ba19b188efca83f3138b1004578882de4306d6e70463dbc89595bf6b7"

# native class must be inherited last
inherit native
