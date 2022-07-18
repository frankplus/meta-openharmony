#! /bin/sh
# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

echo -n "Fixing ld scripts..."

for d in "$OECORE_NATIVE_SYSROOT/lib" "$OECORE_NATIVE_SYSROOT/usr/lib" ; do
    for f in $d/*.so ; do
	# The assumption ELF files have x-bit set and linker scripts does not
	if [ ! -h $f -a ! -x $f ] ; then
	    sed -i -e "s|$OECORE_NATIVE_SYSROOT||g" $f
	fi
    done
done

echo "done"
