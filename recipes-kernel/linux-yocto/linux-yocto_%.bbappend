# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"

SRC_URI:append:oniro-openharmony-linux = " file://ashmem.cfg"
