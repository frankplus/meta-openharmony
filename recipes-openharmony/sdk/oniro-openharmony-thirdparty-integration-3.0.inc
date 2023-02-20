# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SRC_URI += "file://third_party/libunwind/BUILD.gn;subdir=src/overlay"
SRC_URI += "file://patches/no-processdump.patch;apply=no;subdir=src"
SRC_URI += "file://patches/hiviewdfx_faultloggerd_local_unwind_unused_variables.patch;apply=no;subdir=src"
