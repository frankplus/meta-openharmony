# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require openharmony.inc

#
# QEMU ARM Cortex-A7
#
COMPATIBLE_MACHINE:qemuarma7 = "qemuarma7"
KBUILD_DEFCONFIG:qemuarma7 = "multi_v7_defconfig"
KCONFIG_MODE:qemuarma7 = "--alldefconfig"
SRC_URI:append:qemuarma7 = " file://qemufb.cfg"
