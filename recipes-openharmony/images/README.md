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


You may encounter error `RPC failed` during fetching files from gitee.com. In order to fix it, run following commands:

    git config --global http.postBuffer 1048576000
    git config --global https.postBuffer 1048576000
    
and rebuild the image.

To run the image, run this command after successfully completing the above build command:

    runqemu serialstdio nographic slirp

# QEMU test image

The `openharmony-standard-image-tests` recipe provides an image with test suites
included. As with the example image shown above, you need the following in
`build/conf/local.conf`:

    DISTRO = "oniro-openharmony-linux"
    MACHINE = "qemuarma7"

To build the image and run the tests:

    bitbake openharmony-standard-image-tests

This will build the image, start the image with QEMU, and execute the tests in
one go. If you want to run the tests manually, you can use the following command
afterwards:

    runqemu serialstdio nographic slirp

To run all the tests, simply do:

    ptest-runner
