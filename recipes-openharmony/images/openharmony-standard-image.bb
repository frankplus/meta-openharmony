# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "A console-only image with OpenHarmony first party components"

IMAGE_FEATURES += "splash"

LICENSE = "Apache-2.0"

inherit core-image

QEMU_USE_SLIRP = "1"

# debug-tweaks provides password-less root account required by testimage
EXTRA_IMAGE_FEATURES += "debug-tweaks"

# ptest requires ptest-runner and sshd to be present in the image
IMAGE_INSTALL += "sshd"

# install OpenHarmony components and ptests
IMAGE_INSTALL += "openharmony-standard-exes"
