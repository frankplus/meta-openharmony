# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "A console-only image with OpenHarmony first party components"

LICENSE = "Apache-2.0"

inherit core-image

# install OpenHarmony components and ptests
IMAGE_INSTALL += "openharmony-standard-exes"
