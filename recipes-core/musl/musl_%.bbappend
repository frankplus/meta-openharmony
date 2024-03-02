# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend:df-openharmony := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"

SRC_URI:append:df-openharmony = " file://openharmony-common.patch"
SRC_URI:append:df-openharmony = " file://openharmony-linux-user.patch"
SRC_URI:append:df-openharmony = " file://fix-pointer-integer-conversion-errors.patch"

# As musl links with -nostdlib, we need to add linking with
# libclang_rt.builtins.a manually as needed
LDFLAGS_CLANG_COMPILER_RT = "-L${RECIPE_SYSROOT}/lib/clang/*/lib/${OPENHARMONY_LLVM_BINARY_TARGET_ARCH}/${OPENHARMONY_LLVM_BINARY_TARGET_ABI} -lclang_rt.builtins"
LDFLAGS:append:toolchain-clang = " ${@bb.utils.contains('COMPILER_RT', '-rtlib=compiler-rt', d.getVar('LDFLAGS_CLANG_COMPILER_RT'), '', d)}"

# Enable this to (try to) build the malloc/free hooks and support needed for
# OpenHarmony //developtools/profiler component. It doesn't build for now as the
# code uses stdatomic.h header, which is not available as musl is being built
# with `-nostdinc` argument.
# Possible fix is to rewrite the hooks implementation to use the musl internal
# atomic.h functions instead.
#CFLAGS:append:df-openharmony = "-DHOOK_ENABLE"

do_install:append:df-openharmony () {
    for directory in ${MUSL_LDSO_PATHS}; do
        echo "${directory}" >> ${D}${sysconfdir}/ld-musl-${MUSL_LDSO_ARCH}.path
    done
}
