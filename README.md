<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: CC-BY-4.0
-->

# meta-openharmony

**meta-openharmony** is a bitbake layer, containing recipes for building
OpenHarmony software components.


## Contributing

See the `CONTRIBUTING.md` file.


## License

See the `LICENSES` subdirectory.


## Dependencies

	URI: https://gitlab.eclipse.org/eclipse/oniro-core/oniro.git
	layers: meta-oniro-staging
	branch: kirkstone

	URI: git://git.openembedded.org/bitbake
	branch: 2.0

	URI: https://git.openembedded.org/openembedded-core
	layers: meta
	branch: kirkstone

	URI: https://git.openembedded.org/meta-openembedded
	layers: meta-oe meta-python
	branch: master

	URI: https://github.com/kraj/meta-clang.git
	branch: master


## Introduction

OpenHarmony is an open-source project launched by the OpenAtom Foundation, and
serves as an open-source, distributed operating system (OS) that is intended to
address all conceivable usage scearios. OpenHarmony uses a multi-kernel design,
supporting both Linux and LiteOS.

OpenHarmony project provides a specification, a certification program and a
reference implementation.

This meta-openharmony repository is a Yocto meta-data layer, providing Yocto
recipes for integrating parts of the OpenHarmony reference (Linux)
implementation with Yocto based projects. The layer is developed and maintained
by the Oniro Project under Eclipse Foundation, but should be usable both when
using Oniro and for non-Oniro Yocto projects.

Aside from enabling the use of OpenHarmony components in Yocto based projects,
meta-openharmony allows building an updated Clang/LLVM toolchain which can be
used instead of the default toolchain Clang/LLVM provided by OpenHarmony
projects for building OpenHarmony reference implementation.

## General usage

In order to build with this layer, you need to use a compatible host OS. See
https://docs.yoctoproject.org/3.1.8/brief-yoctoprojectqs/brief-yoctoprojectqs.html
for details on this. Sections
https://docs.yoctoproject.org/3.1.8/brief-yoctoprojectqs/brief-yoctoprojectqs.html#compatible-linux-distribution
and
https://docs.yoctoproject.org/3.1.8/brief-yoctoprojectqs/brief-yoctoprojectqs.html#build-host-packages
in particular.

Make sure to update as well as install git-lfs which wasn't required in yocto docs:

	sudo apt-get update && sudo apt-get install git-lfs

To use the meta-openharmony layer, you need a build setup with BitBake and all
needed Yocto layers.

If you are adding OpenHarmony features to an existing project, you have to add
meta-openharmony and its layer dependencies to your existing project setup. You
can use the `bitbake-layers add-layer` command to do this.

If you don't have an existing project, and simply want to quickly try out
OpenHarmony features, you could use the repo manifest included in the
meta-openharmony layer.

You can run these commands to do this:

	mkdir ohoe && cd ohoe
	repo init -u https://gitlab.eclipse.org/eclipse/oniro-core/meta-openharmony.git -b kirkstone
	repo sync --no-clone-bundle

After this is done, you need to initialize the build environment. This modifies
the shell environment, so need to be done again after rebooting or starting
another shell/terminal. Run this in the ohoe directory:

    TEMPLATECONF=../meta-openharmony/conf source oe-core/oe-init-build-env

The current directory will be changed to the build directory, from where you
should be running your `bitbake` build commands and so on.

## OpenHarmony version

You can choose which version of OpenHarmony to be built by uncommenting the
variable `OPENHARMONY_VERSION` in the `local.conf` file and setting it to the
desired value.

Supported versions are `3.0` and `3.1`.

## QEMU example image

The meta-openharmony provides an example image recipe which can be used for
quickly building and running OpenHarmony code in QEMU ARM simulator.

See [recipes-openharmony/images/README.md](recipes-openharmony/images/README.md)
for more information.

## OpenHarmony prebuilts

The meta-openharmony layer enables building of prebuilts for use with the
OpenHarmony build system. A toolchain-only image, making it possible to use the
Oniro Clang version instead of the default Clang version included, and a bundle
image which contains both the Oniro Clang compiler and Oniro versions of various
third-party components, replacing the corresponding default third-party
versions.

See
[recipes-openharmony/prebuilts/README.md](recipes-openharmony/prebuilts/README.md)
for more information.

## Repo manifests

The meta-openharmony repository includes a number of different repo manifest
files, which can be used fetch all repositories needed for building OpenHarmony.

### manifests/branch.xml

This manifest checks out all repositories with the latest revision from
their development branch, including upstream projects.

### manifests/pin.xml

This manifest checks out all repositories at known good versions, which are
locked down by commit ids in the manifest file. This is the default manifest.
