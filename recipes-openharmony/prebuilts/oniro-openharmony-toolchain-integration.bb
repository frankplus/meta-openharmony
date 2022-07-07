# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Integration of Oniro toolchain into OpenHarmony"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://setup.sh;beginline=3;endline=15;md5=306e15e328d14d4055327f82b55064a0"

require sanity-check.inc

BBCLASSEXTEND = "native nativesdk"

INHIBIT_DEFAULT_DEPS = "1"

S = "${WORKDIR}/src"
B = "${WORKDIR}/build"

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"

SRC_URI += "file://setup.sh;subdir=src"
SRC_URI += "file://relocate-ld-scripts.sh"
SRC_URI += "file://BUILD.gn;subdir=src"
SRC_URI += "file://toolchain.gni;subdir=src"
SRC_URI += "file://third_party.gni;subdir=src"

SRC_URI += "file://patches/build.patch;apply=no;subdir=src"
SRC_URI += "file://patches/build_gcc-toolchain-gni.patch;apply=no;subdir=src"
SRC_URI += "file://patches/drivers_peripheral.patch;apply=no;subdir=src"
SRC_URI += "file://patches/drivers_framework.patch;apply=no;subdir=src"
SRC_URI += "file://patches/drivers_adapter_khdf_linux.patch;apply=no;subdir=src"
SRC_URI += "file://patches/developtools_profiler.patch;apply=no;subdir=src"
SRC_URI += "file://patches/ace_ace_engine.patch;apply=no;subdir=src"
SRC_URI += "file://patches/ark_runtime_core.patch;apply=no;subdir=src"
SRC_URI += "file://patches/ark_js_runtime.patch;apply=no;subdir=src"
SRC_URI += "file://patches/ark_ts2abc.patch;apply=no;subdir=src"
SRC_URI += "file://patches/kernel_linux_build.patch;apply=no;subdir=src"
SRC_URI += "file://patches/third_party_e2fsprogs.patch;apply=no;subdir=src"
SRC_URI += "file://patches/third_party_flutter.patch;apply=no;subdir=src"
SRC_URI += "file://patches/third_party_icu.patch;apply=no;subdir=src"
SRC_URI += "file://patches/third_party_grpc.patch;apply=no;subdir=src"

SRC_URI += "file://third_party/musl/BUILD.gn;subdir=src/overlay"
SRC_URI += "file://third_party/musl/musl_config.gni;subdir=src/overlay"
SRC_URI += "file://build/common/musl/BUILD.gn;subdir=src/overlay"

SRC_URI += "file://BUILD_host.gn"

# Note: Using include instead of require to avoid parser error skipping recipe
include oniro-openharmony-toolchain-integration-${OPENHARMONY_VERSION}.inc

SDK_DYNAMIC_LINKER:x86-64  = "ld-linux-x86-64.so.2"
SDK_DYNAMIC_LINKER:x86     = "ld-linux.so.2"
SDK_DYNAMIC_LINKER:aarch64 = "ld-linux-aarch64.so.1"

SDK_GN_CPU = "${TRANSLATED_TARGET_ARCH}"
SDK_GN_CPU:x86-64 = "x64"
SDK_GN_CPU:aarch64 = "arm64"

do_compile() {
    sed -e "s|@SDK_SYS@|${SDK_SYS}|g" \
	      -e "s|@SDK_GN_CPU@|${SDK_GN_CPU}|g" \
	      -e "s|@SDK_DYNAMIC_LINKER@|${SDK_DYNAMIC_LINKER}|g" \
	      -e "s|@bindir_nativesdk@|${bindir_nativesdk}|g" \
	      -e "s|@base_libdir_nativesdk@|${base_libdir_nativesdk}|g" \
	      -e "s|@libdir_nativesdk@|${libdir_nativesdk}|g" \
	      < ${WORKDIR}/BUILD_host.gn > ${B}/BUILD.gn
}

# The files installed into ${D} will end in the host subdir of SDK
# sysroots/ dir.  The oniro subdir of that will then be moved to
# top-level of SDK when building the SDK image.
do_install() {
    install -d ${D}${SDKPATHNATIVE}/post-relocate-setup.d/
    install -m 0755 ${WORKDIR}/relocate-ld-scripts.sh ${D}${SDKPATHNATIVE}/post-relocate-setup.d/

    # SDK sysroot BUILD.gn file
    mkdir -p ${D}${base_prefix}
    install -m 0644 ${B}/BUILD.gn ${D}${base_prefix}

    # Files for //oniro directory
    mkdir -p ${D}${datadir}/oniro-openharmony
    cp -rv ${S}/* ${D}${datadir}/oniro-openharmony
    chmod +x ${D}${datadir}/oniro-openharmony/setup.sh
}

PACKAGES = "${PN}"
FILES:${PN} = "${base_prefix}"
# SYSROOT_DIRS does not support files, so we have to declare
# everything.  Luckily, we actually do want everything :)
SYSROOT_DIRS:append = "/"
