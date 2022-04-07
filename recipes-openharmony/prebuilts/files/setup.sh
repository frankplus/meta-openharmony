#!/bin/bash
#
# Copyright [2022] [Huawei Inc.]
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

#
# Installation script for ohos-thirdparty bundle.
#
# Use this script to install the Oniro OpenHarmony toolchain to an OpenHarmony
# code base. This should be done only on top of a clean checkout of the
# supported OpenHarmony version.
#
# In order to do this, you should unpack the tar-ball in the top of the
# OpenHarmony source dir (the dir with `build.py`, `build.sh` and `.gn` files),
# and then execute the `oniro/setup.sh` script.
# Note: it is advisable to do this on top of a clean OpenHarmony repository.
#
# The following example shows how to build a standard system demo image for
# HiSpark Taurus board, using the full OpenHarmony 3.0-LTS source distribution.
#
#     tar xfz $DOWNLOADS/code-v3.0-LTS.tar.gz
#     cd code-v3.0-LTS/OpenHarmony
#     /bin/sh $DOWNLOADS/oniro-openharmony-bundle-cortexa7-neon-vfpv4-3.0.sh -d oniro/ -y
#     ./oniro/setup.sh
#     ./build.sh --product-name Hi3516DV300
#

# Simple sanity check on the current directory being right
if ! test \
     -d third_party -a \
     -f build/toolchain/gcc_toolchain.gni -a \
     -f oniro/setup.sh -a \
     "$0" = "./oniro/setup.sh"
then
    echo >&2 "Error: This script must be called from top of OpenHarmony repository"
    exit 1
fi

# Script arguments
force=false
if [ "$1" = "-f" ]; then
    force=true
fi

# Apply patches
for patch_file in oniro/patches/* ; do
    patch -p1 -i $patch_file
done

# Bail out if this setup.sh was run before and '-f' is not used
dirty_components=(third_party/*~openharmony)
if [ -e "${dirty_components[0]}" ] ; then
    if [ $force = false ] ; then
	echo >&2 "Error: OpenHarmony repository already setup for third party prebuilts"
	exit 1
    fi
fi

# Backup existing component directory and wipe it if running with '-f'
for component in oniro/overlay/third_party/* ; do
    component=$(basename $component)
    if ! [ -e "third_party/${component}~openharmony" ] ; then
	echo "Backing up //third_party/${component}"
	mv "third_party/${component}" "third_party/${component}~openharmony"
    elif ! [ -e "third_party/${component}" ] ; then
	: # Strange, but no worries.  Either the component was never checked
	  # out, or a previous run of this script was exited prematurely.
	  # It should be safe to continue...
    elif [ $force = true ] ; then
	echo "Wiping //third_party/${component}"
	rm -rf "third_party/${component}"
    else
	echo >&2 "Error: //third_party/${component} exists (consider using '-f')"
	exit 1
    fi
done

echo "Applying //oniro/overlay"
cp -rv oniro/overlay/* ./

# Use prebuilt hc-gen
mkdir -p drivers/framework/tools/hc-gen/build
ln -sfT ../../../../../oniro/sysroots/host/usr/bin/hc-gen drivers/framework/tools/hc-gen/build/hc-gen
ln -sfT ../../../../oniro/sysroots/host/usr/bin/hc-gen drivers/framework/tools/hc-gen/build_hcs.py
