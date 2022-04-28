# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "A console-only image with OpenHarmony first party components and unit tests"

require openharmony-standard-image.bb

inherit testimage
TESTIMAGE_AUTO = "1"
REQUIRED_DISTRO_FEATURES = "ptest"

# ping and ssh are the minimum required test suites dependencies of ptest
TEST_SUITES = "ping ssh ptest"

# slirp provides networking without the need for sudo to setup TUN/TAP
TEST_QEMUBOOT_TIMEOUT = "60"
TEST_SERVER_IP = "127.0.0.1"
QEMU_USE_SLIRP = "1"

# debug-tweaks provides password-less root account required by testimage
EXTRA_IMAGE_FEATURES += "debug-tweaks"

# ptest requires ptest-runner and sshd to be present in the image
IMAGE_INSTALL += "sshd ptest-runner"

# install OpenHarmony components and ptests
IMAGE_INSTALL += "openharmony-standard-exes"
IMAGE_INSTALL += "openharmony-standard-ptest"
