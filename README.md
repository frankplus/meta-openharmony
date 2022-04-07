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


## General usage

In order to build with this layer, you need to use a compatible host OS. See
https://docs.yoctoproject.org/3.1.8/brief-yoctoprojectqs/brief-yoctoprojectqs.html
for details on this. Sections
https://docs.yoctoproject.org/3.1.8/brief-yoctoprojectqs/brief-yoctoprojectqs.html#compatible-linux-distribution
and
https://docs.yoctoproject.org/3.1.8/brief-yoctoprojectqs/brief-yoctoprojectqs.html#build-host-packages
in particular.

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


## Repo manifests

The meta-openharmony repository includes a number of different repo manifest
files, which can be used fetch all repositories needed for building OpenHarmony.

### manifests/branch.xml

This manifest checks out all repositories with the latest revision from
their development branch, including upstream projects.

### manifests/pin.xml

This manifest checks out all repositories at known good versions, which are
locked down by commit ids in the manifest file. This is the default manifest.
