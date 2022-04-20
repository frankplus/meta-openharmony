<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: CC-BY-4.0
-->

# QEMU example image

The `openharmony-standard-image` recipe provides an easy way to build and run
OpenHarmony components. You can build the image for QEMU and run it using the
following in your `build/conf/local.conf` file:

    DISTRO = "oniro-openharmony-linux"
    MACHINE = "qemuarma7"

To build the image, run:

    bitbake openharmony-standard-image

To run it, run the this commands after successfully completing the above build command:

    runqemu serialstdio nographic
