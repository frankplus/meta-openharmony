.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _OpenHarmony SDK:

OpenHarmony SDK
###############

Oniro Project provides support for building an alternative open-source toolchain
for use with the OpenHarmony reference implementation. This allows using an
updated Clang/LLVM compiler built from the meta-clang Yocto layer instead of the
default prebuilt Clang/LLVM compiler provided by OpenHarmony project.

There are two different Yocto recipes providing prebuilt SDK images for the
OpenHarmony build system.

``oniro-openharmony-toolchain``
  The `oniro-openharmony-toolchain` recipe builds an image containing the Oniro
  Clang version and a musl libc version. The musl libc version is same upstream
  version as currently used in OpenHarmony, and is patched to exhibit the same
  runtime behavior to the musl libc version included in OpenHarmony build
  system.

``oniro-openharmony-bundle``
  The `oniro-openharmony-bundle` recipe is a superset of
  `oniro-openharmony-toolchain`, adding third-party components on top using
  Oniro (Yocto) recipes, replacing the corresponding third-party components
  included in OpenHarmony repository.

Using one of these images replaces then OpenHarmony versions of the included
OpenSource toolchain and third-party components with newer Oniro versions.


Building
********

To build `oniro-openharmony-bundle` for OpenHarmony 3.0.1, the `bitbake` command
is simply::

    DISTRO=oniro-openharmony-linux MACHINE=qemuarma7 bitbake oniro-openharmony-bundle

In order to build for OpenHarmony 3.1.1 instead, you need to add the following
line to the `build/conf/local.conf` file::

    OPENHARMONY_VERSION = "3.1"

And then use the same command as shown above for 3.0.1.

To build `oniro-openharmony-toolchain` instead, simply use::

    DISTRO=oniro-openharmony-linux MACHINE=qemuarma7 bitbake oniro-openharmony-toolchain


Usage
*****

To use one of the images produced with these recipes, you need to install it to
an OpenHarmony source repository.

Warning! It is recommended to only install OpenHarmony prebuilts to clean
upstream OpenHarmony source repsitories, as the installation will remove files
and entire git repositories!

To install the `oniro-openharmony-bundle` to a clean OpenHarmony 3.0.1
repository, you should do something like this::

    tar xfz $DOWNLOADS/code-v3.0.1-LTS.tar.gz
    cd code-v3.0.1-LTS/OpenHarmony
    $DOWNLOADS/oniro-openharmony-bundle-3.0-cortexa7-neon-vfpv4-1.99.99.sh -y -d oniro
    ./oniro/setup.sh

After this, you can use normal OpenHarmony build system procedures to build as
usual.  To build image for HiSilicon Hi3516DV300 (taurus) board::

    ./build.sh --product-name Hi3516DV300


Use inside BitBake
******************

**meta-openharmony** is a bitbake layer, containing recipes for building
OpenHarmony software components.

The meta-openharmony layer enables building of prebuilts for use with the
OpenHarmony build system. A toolchain-only image, making it possible to use the
Oniro Clang version instead of the default Clang version included, and a bundle
image which contains both the Oniro Clang compiler and Oniro versions of various
third-party components, replacing the corresponding default third-party
versions.
