<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: CC-BY-4.0
-->

# OpenHarmony prebuilts

There are two different recipes providing a prebuilts for the OpenHarmony build system.

The `oniro-openharmony-toolchain` recipe builds an image containing the Oniro
Clang version and a musl libc version. The musl libc version is same upstream
version as currently used in OpenHarmony, and is patched to be mostly identical
to the musl libc version included in OpenHarmony build system.

The `oniro-openharmony-bundle` recipe is a superset of
`oniro-openharmony-toolchain`, adding Oniro third-party components on top.

Using one of these images will replace OpenHarmony versions of the included
OpenSource toolchain and third-party components with Oniro versions.

## Usage

To use one of the images produced with these recipes, you need to install it to
an OpenHarmony source repository.

Warning! It is recommended to only install OpenHarmony prebuilts to clean
upstream OpenHarmony source repsitories, as the installation will remove files
and entire git repositories!

To install the `oniro-openharmony-bundle` to a clean OpenHarmony 3.0-LTS
repository, you should do something like this:

    tar xfz $DOWNLOADS/code-v3.1-Release.tar.gz
    cd code-v3.1-Relase/OpenHarmony
    $DOWNLOADS/oniro-openharmony-bundle-3.1-cortexa7-neon-vfpv4-1.99.99.sh -y -d oniro
    ./oniro/setup.sh

After this, you can use normal OpenHarmony build system procedures to build as
usual.  To build image for HiSilicon Hi3516DV300 (taurus) board:

    ./build.sh --product-name Hi3516DV300


## Use inside BitBake

**meta-openharmony** is a bitbake layer, containing recipes for building
OpenHarmony software components.

The meta-openharmony layer enables building of prebuilts for use with the
OpenHarmony build system. A toolchain-only image, making it possible to use the
Oniro Clang version instead of the default Clang version included, and a bundle
image which contains both the Oniro Clang compiler and Oniro versions of various
third-party components, replacing the corresponding default third-party
versions.
