# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DEPENDS += "oniro-openharmony-bundle oniro-openharmony-toolchain-integration-native oniro-openharmony-thirdparty-integration-native"
DEPENDS += "gn-native ninja-native"
DEPENDS += "hc-gen-native"
DEPENDS += "clang-native compiler-rt-native libcxx-native"

do_configure_oniro_ohos[dirs] = "${S}"
do_configure_oniro_ohos() {
    # Merge /usr/share/oniro-ohos from recipe-sysroot and
    # recipe-sysroot-native into //build/oniro
    rm -rf oniro
    mkdir oniro
    if [ -d ${RECIPE_SYSROOT}${datadir}/oniro-openharmony ] ; then
	      cp -rv ${RECIPE_SYSROOT}${datadir}/oniro-openharmony/* oniro/
    fi
    if [ -d ${RECIPE_SYSROOT_NATIVE}${datadir_native}/oniro-openharmony ] ; then
	      cp -rv ${RECIPE_SYSROOT_NATIVE}${datadir_native}/oniro-openharmony/* oniro/
    fi
    mkdir oniro/sysroots
    ln -s ${RECIPE_SYSROOT} oniro/sysroots/target
    ln -s ${RECIPE_SYSROOT_NATIVE} oniro/sysroots/host
    ./oniro/setup.sh -f
}
do_configure[prefuncs] += "do_configure_oniro_ohos"
