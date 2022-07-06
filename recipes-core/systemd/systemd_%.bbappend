# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/systemd:"

# This patch was dropped in oe-core as musl 1.2.3 implements qsort_r, but as
# long as we are pinning on 1.2.0 for OpenHarmony builds, we need it.
SRC_URI:append:df-openharmony = " file://0002-don-t-use-glibc-specific-qsort_r.patch"
