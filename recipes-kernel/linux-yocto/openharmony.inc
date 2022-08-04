# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony:"

SRC_URI:append:df-openharmony = " file://ashmem.cfg"
SRC_URI:append:df-openharmony = " file://driver-add-hilog-and-hievent-buffer-management-drive.patch"
SRC_URI:append:df-openharmony = " file://driver-add-hilog-and-hievent-buffer-management-drive.cfg"
SRC_URI:append:df-openharmony = " file://android_binder_ipc.cfg"

#
# QEMU ARM Cortex-A7
#
COMPATIBLE_MACHINE:qemuarma7 = "qemuarma7"
KBUILD_DEFCONFIG:qemuarma7 = "multi_v7_defconfig"
KCONFIG_MODE:qemuarma7 = "--alldefconfig"
SRC_URI:append:qemuarma7 = " file://qemufb.cfg"