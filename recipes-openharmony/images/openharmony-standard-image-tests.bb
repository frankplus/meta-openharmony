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

# make target see working graphics device
TEST_RUNQEMUPARAMS = "publicvnc"

# debug-tweaks provides password-less root account required by testimage
EXTRA_IMAGE_FEATURES += "debug-tweaks"

# ptest requires ptest-runner and sshd to be present in the image
EXTRA_IMAGE_FEATURES += "ssh-server-dropbear"
IMAGE_INSTALL += "ptest-runner"

# install OpenHarmony components and ptests
IMAGE_INSTALL += "openharmony-standard-ptest"

# Pre-generate SSH host keys for quick first time SSH connection
ROOTFS_POSTPROCESS_COMMAND += "ssh_host_key_gen;"
SSH_HOST_KEY_TYPES="rsa ecdsa ed25519"
ssh_host_key_gen() {
    mkdir -p "${IMAGE_ROOTFS}${sysconfdir}/ssh/"
    for type in ${SSH_HOST_KEY_TYPES} ; do
        ssh-keygen -f "${IMAGE_ROOTFS}${sysconfdir}/ssh/ssh_host_"$type"_key" -N '' -t $type
    done
}
