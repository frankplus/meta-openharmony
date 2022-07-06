# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# OpenHarmony relies on Android's ashmem, which is in staging and therefore
# the header is not installed by default
do_install:append:df-openharmony() {
    install ${S}/drivers/staging/android/uapi/ashmem.h ${D}${includedir}/linux
}
