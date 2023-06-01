# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "A console-only image with OpenHarmony first party components"

LICENSE = "Apache-2.0"

inherit core-image

# install OpenHarmony components and ptests
IMAGE_INSTALL += "openharmony-standard"

# Let's be friendly enough to provide a fully working interactive shell
IMAGE_INSTALL += "bash"

# Provide avahi and a DNS-SD service file to discover the HDC service
IMAGE_INSTALL += "avahi-daemon"
