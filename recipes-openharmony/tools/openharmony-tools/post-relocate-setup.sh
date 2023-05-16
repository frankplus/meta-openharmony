#! /bin/sh
# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

echo -n "Fixing qemuboot.conf files..."

sdkpath="${1:?}"
sdkpath_build="${2:?}"

for f in $sdkpath/images/*/*qemuboot.conf ; do
    sed -i $f -e "s|@SDKPATH@|$sdkpath|"
done

echo "done"
