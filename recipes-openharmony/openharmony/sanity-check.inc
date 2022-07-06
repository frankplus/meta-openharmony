# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Skip recipe if openharmony is not in DISTRO_FEATURES
inherit features_check
REQUIRED_DISTRO_FEATURES = "openharmony"

# Skip recipe if TOOLCHAIN != "clang" or RUNTIME != "llvm"
python () {
    toolchain = d.getVar('TOOLCHAIN')
    runtime = d.getVar('RUNTIME')
    tclibc = d.getVar('TCLIBC')
    if toolchain != "clang":
        raise bb.parse.SkipRecipe("TOOLCHAIN is not clang")
    if runtime != "llvm":
        raise bb.parse.SkipRecipe("RUNTIME is not llvm")
    if tclibc != "musl":
        raise bb.parse.SkipRecipe("TCLIBC is not musl")
}
