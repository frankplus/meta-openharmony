# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenHarmony Components PoC"

LICENSE = "0BSD & Apache-2.0 & BSD-2-Clause & BSD-3-Clause & BSL-1.0 & \
GPL-2.0-only & GPL-2.0-or-later & GPL-2-with-bison-exception & GPL-3.0-only & \
LGPL-2.1-only & LGPL-2.1-or-later & LGPL-3.0-only & CPL-1.0 & MIT & MIT-0 & \
MIT-Modern-Variant & Zlib & CC-BY-3.0 & CC-BY-SA-3.0 & CC-BY-NC-SA-3.0 & X11 & \
PD & OFL-1.1 & OpenSSL & MulanPSL-2.0 & bzip2-1.0.6 & ISC & ICU & IJG & Libpng & \
MPL-1.1 & MPL-2.0 & FTL"
LIC_FILES_CHKSUM = "file://build/LICENSE;md5=cfba563cea4ce607306f8a392f19bf6c"

DEPENDS += "nodejs-native"
DEPENDS += "bison-native"
DEPENDS += "ruby-native"

require ${PN}-sources-${OPENHARMONY_VERSION}.inc
require musl-ldso-paths-sanity-check.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-standard-${OPENHARMONY_VERSION}:"

# TODO: we probably want these
SRC_URI += "file://hilog-Add-tests.patch"

SRC_URI += "file://bison_parser.patch;patchdir=${S}/third_party/libxkbcommon"
SRC_URI += "file://flexlexer.patch;patchdir=${S}/base/update/updater"

# Native node hacks
SRC_URI += "file://jsframwork-use-yocto-node.patch;patchdir=${S}/third_party/jsframework"
SRC_URI += "file://ts2abc-don-t-set-node_path-for-Linux-host-toolchain.patch;patchdir=${S}/ark/ts2abc"

# Should be covered by Oniro prebuilts
# TODO: Cleanup prebuilts recipe with these component specific patches instead
#       of adding arguments in toolchain definition.
#SRC_URI += "file://toolchain-compiler-path.patch;patchdir=${S}/build"
#SRC_URI += "file://ark-runtime_core-compiler-option.patch;patchdir=${S}/ark/runtime_core"
#SRC_URI += "file://ark-runtime_core-libpandabase.patch;patchdir=${S}/ark/runtime_core"
#SRC_URI += "file://libweston_config-Add-Wno-unused-but-set-variable-com.patch;patchdir=${S}/third_party/weston"
#SRC_URI += "file://libunwind-compiler-option.patch;patchdir=${S}/third_party/libunwind"
#SRC_URI += "file://protobuf-compiler-option.patch;patchdir=${S}/third_party/protobuf"
#SRC_URI += "file://multimedia-audio-compiler-option.patch;patchdir=${S}/foundation/multimedia/audio_standard"
#SRC_URI += "file://quickjs-compiler-option.patch;patchdir=${S}/third_party/quickjs"
#SRC_URI += "file://ace_engine-clang-config.patch;patchdir=${S}/foundation/ace/ace_engine"
#SRC_URI += "file://icu-compile-option.patch;patchdir=${S}/third_party/icu"
#SRC_URI += "file://ark-js_runtime-compile-option.patch;patchdir=${S}/ark/js_runtime"
#SRC_URI += "file://ts2abc_host-toolchain.patch;patchdir=${S}/ark/ts2abc"
#SRC_URI += "file://hc-gen-compiler.patch;patchdir=${S}/drivers/framework"

SRC_URI += "file://hdc-build-system-files.patch;patchdir=${S}/developtools/hdc_standard"

SRC_URI += "file://vendor-qemu-uhdf-files.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "git://gitlab.eclipse.org/eclipse/oniro-core/openharmony-vendor-oniro.git;protocol=https;branch=main;rev=c7f69115d7af1a37f81bd4fc0462100d0aa87c2d;destsuffix=${S}/vendor/oniro"

SRC_URI += "file://peripherals-Limit-drivers-list-to-supported-by-the-qemuarm.patch;patchdir=${S}/drivers/adapter"
SRC_URI += "file://display_device.c;subdir=${S}/drivers/peripheral/display/hal/default/standard_system"
SRC_URI += "file://display-Use-temporary-qemuarm-implementation.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "file://display_gralloc_gbm.c-Use-card-drm-node.patch;patchdir=${S}/device/hihope"
SRC_URI += "file://hihope-gralloc-Backport-to-3.0.patch;patchdir=${S}/device/hihope"


inherit python3native gn_base ptest

B = "${S}/out/ohos-arm-release"

COMPATIBLE_MACHINE = "qemuarm"

OHOS_DEVICE_CPU_ARCH = "arm"
OHOS_DEVICE_NAME = "qemuarm"
OHOS_DEVICE_COMPANY = "oniro"
OHOS_PRODUCT_NAME = "yocto-ohos-${OHOS_DEVICE_NAME}"
OHOS_PRODUCT_COMPANY = "oniro"
# For some reason platform type has to be phone
OHOS_PRODUCT_PLATFORM_TYPE = "phone"

OHOS_BUILD_CONFIGS_DIR = "${B}/../build_configs"
OHOS_PRELOADER_BUILD_CONFIG_DIR = "${OHOS_BUILD_CONFIGS_DIR}/${OHOS_PRODUCT_NAME}/preloader"
OHOS_STANDARD_SYSTEM_CONFIG_DIR = "${OHOS_BUILD_CONFIGS_DIR}/standard_system"

OHOS_PROJECT_BUILD_CONFIG_DIR = "${B}/build_configs"

GN_ARGS += 'target_os="ohos"'
GN_ARGS += 'target_cpu="${OHOS_DEVICE_CPU_ARCH}"'
GN_ARGS += 'product_name="${OHOS_PRODUCT_NAME}"'
GN_ARGS += 'is_standard_system=true'
GN_ARGS += 'is_debug=false'
GN_ARGS += 'is_component_build=true'
GN_ARGS += 'release_test_suite=false'
GN_ARGS += 'treat_warnings_as_errors=false'
GN_ARGS += 'node_path="${RECIPE_SYSROOT_NATIVE}/usr/bin"'
GN_ARGS += 'host_toolchain="//oniro:host_toolchain"'
GN_ARGS += 'install_oniro_third_party=false'

# OpenHarmony unit tests are statically linked and therefore not stripped
# binaries sum up to almost 80GB which makes it difficult to build OpenHarmony
# with tests on a normal desktop, let alone the CI runner
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'remove_unstripped_execs=true', '', d)}"
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'remove_unstripped_so=true', '', d)}"

#BUILD_CXXFLAGS:prepend = "-Wno-error=pedantic -Uunix "
#TARGET_CXXFLAGS:prepend = "-D__MUSL__ -Wno-unused-but-set-variable "
#TARGET_CFLAGS:prepend = "-D__MUSL__ -DHAVE_VERSIONSORT -Wno-unused-but-set-variable "

# OpenHarmony build system generates all possible targets, but only `packages`
# target is build with ninja in the end
NINJA_ARGS = "packages"
NINJA_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'make_test', '', d)}"

# Copy FlexLexer.h from recipe sysroot
do_copy_to_srcdir() {
    cp ${RECIPE_SYSROOT_NATIVE}/usr/include/FlexLexer.h \
       ${S}/base/update/updater/services/script/yacc

    # Create a fake camera config file for gn based on the rpi3 one.
    # This file is referenced as "camera.${product_name}.gni"
    cp ${S}/drivers/peripheral/camera/hal/adapter/chipset/gni/camera.rpi3.gni \
       ${S}/drivers/peripheral/camera/hal/adapter/chipset/gni/camera.${OHOS_PRODUCT_NAME}.gni
}

addtask do_copy_to_srcdir after do_prepare_recipe_sysroot do_unpack before do_configure

do_configure:prepend() {
    bbnote "Generating OpenHarmony Standard System build config files..."

    generate_build_config_json_file
    generate_platforms_build_file
    generate_parts_json
    copy_subsystem_config_json_file

    # OpenHarmony build system relies on python 3 being available as `python`
    ln -sf $(which python3) ${STAGING_BINDIR_NATIVE}/python
}

do_install () {
    OHOS_PACKAGE_OUT_DIR="${B}/packages/${OHOS_PRODUCT_PLATFORM_TYPE}"

    # We install library files to ${libdir} and executables into ${bindir}, and
    # then setup /system/lib and /system/bin symlinks to avoid breaking use of
    # hard-coded paths.
    mkdir -p ${D}/system ${D}${libdir} ${D}${bindir}
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/lib/* ${D}${libdir}/
    install -m 755 -t ${D}${bindir}/ ${OHOS_PACKAGE_OUT_DIR}/system/bin/*
    ln -sfT ..${libdir} ${D}/system/lib
    ln -sfT ..${bindir} ${D}/system/bin

    # system ability configurations
    mkdir -p ${D}${libdir}/openharmony/profile
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/profile/* ${D}${libdir}/openharmony/profile
    ln -sfT ..${libdir}/openharmony/profile ${D}/system/profile

    # OpenHarmony etc (configuration) files
    mkdir -p ${D}${sysconfdir}/openharmony
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/etc/* ${D}${sysconfdir}/openharmony
    ln -sfT ..${sysconfdir}/openharmony ${D}/system/etc

    # OpenHarmony font files
    mkdir -p ${D}${datadir}/fonts/openharmony
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/fonts/* ${D}${datadir}/fonts/openharmony
    ln -sfT ..${datadir}/fonts/openharmony ${D}/system/fonts

    # Avoid file-conflict on /usr/bin/udevadm with //third_party/eudev and udev
    # recipe
    rm ${D}${bindir}/udevadm
}

PACKAGES =+ "${PN}-configs ${PN}-fonts"

RDEPENDS:${PN} += "${PN}-configs ${PN}-fonts"

RDEPENDS:${PN} += "musl libcxx libcrypto libssl libatomic"
RDEPENDS:${PN}-ptest += "musl libcxx libcrypto libssl ${PN}-libutils"

# OpenHarmony libraries are not versioned properly.
# Move the unversioned .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/media ${libdir}/module ${libdir}/ark ${libdir}/openharmony ${libdir}/*${SOLIBS}"
FILES:${PN}-configs = "${sysconfdir}"
FILES:${PN}-fonts = "${datadir}/fonts"

FILES:${PN} += "/system/bin /system/lib /system/profile"
FILES:${PN}-configs += "/system/etc"
FILES:${PN}-fonts += "/system/fonts"

generate_build_config_json_file() {

    mkdir -p "${OHOS_PRELOADER_BUILD_CONFIG_DIR}"
    cat > "${OHOS_PRELOADER_BUILD_CONFIG_DIR}/build_config.json" << EOF
    {
      "system_type": "standard",
      "product_name": "${OHOS_PRODUCT_NAME}",
      "product_company": "${OHOS_PRODUCT_COMPANY}",
      "device_name": "${OHOS_DEVICE_NAME}",
      "device_company": "${OHOS_DEVICE_COMPANY}",
      "target_os": "ohos",
      "target_cpu": "arm",
      "kernel_version": ""
  }
EOF
}

do_install_ptest () {
    for f in $(cd "${B}/tests" && find . -type f)
    do
        install -D -m 755 "${B}/tests/$f" "${D}${PTEST_PATH}/$f"
    done
}

generate_platforms_build_file() {
    # platforms.build file is used by the loader.py tool as a top-level
    # platform configuration entry point
    #
    # NOTE: In normal OpenHarmony build process, this file is generated by the
    # preloader.py tool

    mkdir -p "${OHOS_STANDARD_SYSTEM_CONFIG_DIR}"
    cat > "${OHOS_STANDARD_SYSTEM_CONFIG_DIR}/platforms.build" << EOF
    {
      "platforms": {
        "${OHOS_PRODUCT_PLATFORM_TYPE}": [
          {
            "target_os": "ohos",
            "target_cpu": "${OHOS_DEVICE_CPU_ARCH}",
            "toolchain": "//oniro/sysroots/target:target_clang",
            "parts_config": "../${OHOS_PRODUCT_NAME}/preloader/parts.json"
          }
        ]
      }
    }
EOF
}

generate_parts_json() {
    # parts.json file is used by the loader.py tool to generate BUILD.gn files
    # for all required "parts" basing on ohos.build files that accompany
    # OpenHarmony components
    #
    # NOTE: In normal OpenHarmony build process, this file is generated by the
    # preloader.py tool

    mkdir -p "${OHOS_PRELOADER_BUILD_CONFIG_DIR}"
    cat > "${OHOS_PRELOADER_BUILD_CONFIG_DIR}/parts.json" << EOF
    {
      "parts": [
        "aafwk:aafwk_standard",
        "account:os_account_standard",
        "ace:ace_engine_standard",
        "ace:napi",
        "appexecfwk:appexecfwk_standard",
        "ark:ark_js_runtime",
        "ark:ark",
        "ccruntime:jsapi_worker",
        "common:common",
        "communication:dsoftbus_standard",
        "communication:ipc",
        "communication:ipc_js",
        "developtools:bytrace_standard",
        "developtools:hdc_standard",
        "distributeddatamgr:distributeddatamgr",
        "distributeddatamgr:native_appdatamgr",
        "distributedhardware:device_manager_base",
        "distributedschedule:dmsfwk_standard",
        "distributedschedule:safwk",
        "distributedschedule:samgr_L2",
        "global:i18n_standard",
        "global:resmgr_standard",
        "graphic:graphic_standard",
        "hdf:hdf",
        "hdf:display_device_driver",
        "hiviewdfx:faultloggerd",
        "hiviewdfx:hilog",
        "hiviewdfx:hilog_native",
        "hiviewdfx:hilog_service",
        "hiviewdfx:hisysevent_native",
        "hiviewdfx:hiviewdfx_hilog_native",
        "miscservices:inputmethod_native",
        "miscservices:time_native",
        "multimedia:multimedia_audio_standard",
        "multimedia:multimedia_camera_standard",
        "multimedia:multimedia_histreamer",
        "multimedia:multimedia_image_standard",
        "multimedia:multimedia_media_standard",
        "multimodalinput:multimodalinput_base",
        "notification:ans_standard",
        "notification:ces_standard",
        "powermgr:battery_manager_native",
        "powermgr:display_manager_native",
        "powermgr:power_manager_native",
        "security:appverify",
        "security:dataclassification",
        "security:deviceauth_standard",
        "security:huks_standard",
        "security:permission_standard",
        "startup:startup_l2",
        "telephony:core_service",
        "telephony:ril_adapter",
        "utils:utils_base"
      ]
    }
EOF
}

copy_subsystem_config_json_file() {
    cp "${S}/build/subsystem_config.json" "${OHOS_BUILD_CONFIGS_DIR}/"
}

PACKAGES =+ "${PN}-libutilsecurec ${PN}-libutils"
FILES:${PN}-libutilsecurec = "${libdir}/libutilsecurec*${SOLIBS}"
FILES:${PN}-libutils = "${libdir}/libutils*${SOLIBS}"
RDEPENDS:${PN}-libutilsecurec += "musl libcxx"
RDEPENDS:${PN}-libutils += "musl libcxx ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-libutilsecurec ${PN}-libutils"

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"

PACKAGES =+ "${PN}-hilog ${PN}-hilog-ptest"
SYSTEMD_PACKAGES = "${PN}-hilog"
SYSTEMD_SERVICE:${PN}-hilog = "hilogd.service"
SRC_URI += "file://hilogd.service"
SRC_URI += "${@bb.utils.contains('PTEST_ENABLED', '1', 'file://hilog.run-ptest', '', d)}"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/hilogd.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/init/hilogd.cfg
    install -d ${D}${sysconfdir}/sysctl.d
    echo "net.unix.max_dgram_qlen=600" > ${D}${sysconfdir}/sysctl.d/hilogd.conf
}
do_install_ptest:append() {
    install -D ${WORKDIR}/hilog.run-ptest ${D}${libdir}/${BPN}-hilog/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/hiviewdfx/hilog/* ${D}${libdir}/${BPN}-hilog/ptest/
    rmdir ${D}${PTEST_PATH}/moduletest/hiviewdfx/hilog
}
FILES:${PN}-hilog = "\
    ${bindir}/hilog* \
    ${libdir}/libhilog* \
    ${sysconfdir}/openharmony/hilog*.conf \
    ${systemd_unitdir}/hilogd.service \
"
FILES:${PN}-hilog-ptest = "${libdir}/${BPN}-hilog/ptest"
RDEPENDS:${PN}-hilog += "musl libcxx ${PN}-libutilsecurec"
RDEPENDS:${PN}-hilog-ptest += "${PN}-hilog musl libcxx"
RDEPENDS:${PN} += "${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-hilog-ptest ${PN}-hilog"

INSANE_SKIP:${PN} = "already-stripped"
EXCLUDE_FROM_SHLIBS = "1"

# We have the following problem:
# ERROR: openharmony-standard-3.0-r0 do_package_qa: QA Issue: /usr/lib/module/multimedia/libcamera_napi.z.so contained in package openharmony-standard requires libwms_client.z.so, but no providers found in RDEPENDS:openharmony-standard? [file-rdeps]
# and seems to be a bug in OpenHarmony 3.0
INSANE_SKIP:${PN} = "file-rdeps"

# To avoid excessive diskspace blowup, we are stripping our executables
INSANE_SKIP:${PN} += "already-stripped"
