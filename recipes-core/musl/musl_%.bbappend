# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

BASEVER:oniro-openharmony-linux = "1.2.0"
SRCREV:oniro-openharmony-linux = "040c1d16b468c50c04fc94edff521f1637708328"
LIC_FILES_CHKSUM:oniro-openharmony-linux = "file://COPYRIGHT;md5=f95ee848a08ad253c04723da00cedb01"
FILESEXTRAPATHS:prepend:oniro-openharmony-linux := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
SRC_URI:append:oniro-openharmony-linux = " file://openharmony-common.patch"
SRC_URI:append:oniro-openharmony-linux = " file://openharmony-linux-user.patch"
# This conflicts with libcap, so we have to go with libcap instead
#SRC_URI:append:oniro-openharmony-linux = " file://openharmony-linux-user-capability_h.patch"

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
#CFLAGS:append:oniro-openharmony-linux = "-DHOOK_ENABLE"

do_install:append:oniro-openharmony-linux () {
    echo "${libdir}/module" >> ${D}${sysconfdir}/ld-musl-${MUSL_LDSO_ARCH}.path
}
