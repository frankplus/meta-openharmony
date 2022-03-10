# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"

SRC_URI:append:oniro-openharmony-linux = " file://ashmem.cfg"
SRC_URI:append:oniro-openharmony-linux = " file://driver-add-hilog-and-hievent-buffer-management-drive.patch"
SRC_URI:append:oniro-openharmony-linux = " file://driver-add-hilog-and-hievent-buffer-management-drive.cfg"
SRC_URI:append:oniro-openharmony-linux = " file://android_binder_ipc.cfg"
