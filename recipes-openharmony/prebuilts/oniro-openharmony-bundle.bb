# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Oniro toolchain and third-party prebuilts bundle for OpenHarmony source repository"
LICENSE = "Apache-2.0"

# This recipe is a supserset of the toolchain recipe, adding third-party
# components on top of it
require oniro-openharmony-toolchain.bb

TOOLCHAIN_HOST_TASK += "nativesdk-oniro-openharmony-thirdparty-integration"

# OpenSSL for target
DEPENDS += "openssl"
RDEPENDS:${PN} = "libcrypto libssl openssl-conf openssl-engines openssl-staticdev"
TOOLCHAIN_TARGET_TASK += "openssl-dev"

# OpenSSL for build host
TOOLCHAIN_HOST_TASK += "nativesdk-openssl-dev"
