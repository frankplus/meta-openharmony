# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony:"

SRC_URI:append:df-openharmony = " file://ashmem.cfg"
SRC_URI:append:df-openharmony = " file://driver-add-hilog-and-hievent-buffer-management-drive.patch"
SRC_URI:append:df-openharmony = " file://driver-add-hilog-and-hievent-buffer-management-drive.cfg"
SRC_URI:append:df-openharmony = " file://android_binder_ipc.cfg"
