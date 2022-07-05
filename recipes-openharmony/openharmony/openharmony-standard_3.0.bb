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

require sanity-check.inc

DEPENDS += "nodejs-native"
DEPENDS += "bison-native"
DEPENDS += "ruby-native"
DEPENDS += "packing-tool-native"

# Note: Using include instead of require to avoid parser error skipping recipe
include ${PN}-sources-${OPENHARMONY_VERSION}.inc

require java-tools.inc
require musl-ldso-paths-sanity-check.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-standard-${OPENHARMONY_VERSION}:"

SRC_URI += "${@bb.utils.contains('PTEST_ENABLED', '1', 'file://run-ptest', '', d)}"

# TODO: we probably want these
SRC_URI += "file://hilog-Add-tests.patch"

SRC_URI += "file://bison_parser.patch;patchdir=${S}/third_party/libxkbcommon"
SRC_URI += "file://flexlexer.patch;patchdir=${S}/base/update/updater"

# Native node hacks
SRC_URI += "file://jsframwork-use-yocto-node.patch;patchdir=${S}/third_party/jsframework"
SRC_URI += "file://ts2abc-don-t-set-node_path-for-Linux-host-toolchain.patch;patchdir=${S}/ark/ts2abc"

SRC_URI += "file://hdc-build-system-files.patch;patchdir=${S}/developtools/hdc_standard"
SRC_URI += "file://build_packing-tool-path.patch;patchdir=${S}/build"
SRC_URI += "file://build_node-path.patch;patchdir=${S}/build"

SRC_URI += "file://vendor-qemu-uhdf-files.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "git://gitlab.eclipse.org/eclipse/oniro-core/openharmony-vendor-oniro.git;protocol=https;branch=main;rev=c7f69115d7af1a37f81bd4fc0462100d0aa87c2d;destsuffix=${S}/vendor/oniro"

SRC_URI += "file://peripherals-Limit-drivers-list-to-supported-by-the-qemuarm.patch;patchdir=${S}/drivers/adapter"
SRC_URI += "file://display_device.c;subdir=${S}/drivers/peripheral/display/hal/default/standard_system"
SRC_URI += "file://display-Use-temporary-qemuarm-implementation.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "file://display_gralloc_gbm.c-Use-card-drm-node.patch;patchdir=${S}/device/hihope"
SRC_URI += "file://hihope-gralloc-Backport-to-3.0.patch;patchdir=${S}/device/hihope"
SRC_URI += "file://ivi-input-controller.c-Fix-g_ctx-declaration-causing-segfault.patch;patchdir=${S}/third_party/wayland-ivi-extension"
SRC_URI += "file://xf86drm.c-Add-drmWaitVBlank-hack.patch;patchdir=${S}/third_party/libdrm"

SRC_URI += "file://graphic-standard-Add-missing-entry-for-libwms_client.patch;patchdir=${S}/foundation/graphic/standard"

SRC_URI += "file://appspawn-procps.patch;patchdir=${S}/base/startup/appspawn_standard"
SRC_URI += "file://base_startup_appspawn_standard-disable-longProcName-resetting.patch;patchdir=${S}/base/startup/appspawn_standard"
SRC_URI += "file://test_xts_acts-Align-tests-list-with-mandatory-set.patch;patchdir=${S}/test/xts/acts"

SRC_URI += "file://param_service_main.c;subdir=${S}/base/startup/init_lite/services/param/src"
SRC_URI += "file://param_service-Add-to-build-system.patch;patchdir=${S}/base/startup/init_lite"
SRC_URI += "file://param_service.c-Fix-stopping-param_service.patch;patchdir=${S}/base/startup/init_lite"
SRC_URI += "file://param_service-Add-to-startup-l2-part.patch;patchdir=${S}/base/startup/appspawn_standard"

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

# OpenHarmony build system needs a bit of help to be able to find the right
# ld-musl-*.so path
inherit linuxloader
MUSL_LDSO_ARCH = "${@get_musl_loader_arch(d)}"
GN_ARGS += 'musl_arch="${MUSL_LDSO_ARCH}"'

# OpenHarmony unit tests are statically linked and therefore not stripped
# binaries sum up to almost 80GB which makes it difficult to build OpenHarmony
# with tests on a normal desktop, let alone the CI runner
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'remove_unstripped_execs=true', '', d)}"
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'remove_unstripped_so=true', '', d)}"

# Build ACTS only when acts DISTRO_FEATURE is set
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'acts', 'build_xts=true', '', d)}"

#BUILD_CXXFLAGS:prepend = "-Wno-error=pedantic -Uunix "
#TARGET_CXXFLAGS:prepend = "-D__MUSL__ -Wno-unused-but-set-variable "
#TARGET_CFLAGS:prepend = "-D__MUSL__ -DHAVE_VERSIONSORT -Wno-unused-but-set-variable "

# OpenHarmony build system generates all possible targets, but only `packages`
# target is build with ninja in the end
NINJA_ARGS = "packages"
NINJA_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'make_test', '', d)}"

# Build ACTS only when acts DISTRO_FEATURE is set
NINJA_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'acts', 'acts deploy_testtools', '', d)}"

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

do_configure[prefuncs] += "generate_build_config_json_file"
do_configure[prefuncs] += "generate_platforms_build_file"
do_configure[prefuncs] += "generate_parts_json"
do_configure[prefuncs] += "copy_subsystem_config_json_file"
do_configure[prefuncs] += "symlink_python3"

symlink_python3() {
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
    # undo the default installation of ptest done by ptest.bbclass
    rm -f ${D}${PTEST_PATH}/run-ptest
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

OPENHARMONY_PARTS += "aafwk:aafwk_standard"
OPENHARMONY_PARTS += "account:os_account_standard"
OPENHARMONY_PARTS += "ace:ace_engine_standard"
OPENHARMONY_PARTS += "ace:napi"
OPENHARMONY_PARTS += "appexecfwk:appexecfwk_standard"
OPENHARMONY_PARTS += "ark:ark_js_runtime"
OPENHARMONY_PARTS += "ark:ark"
OPENHARMONY_PARTS += "ccruntime:jsapi_worker"
OPENHARMONY_PARTS += "common:common"
OPENHARMONY_PARTS += "communication:dsoftbus_standard"
OPENHARMONY_PARTS += "communication:ipc"
OPENHARMONY_PARTS += "communication:ipc_js"
OPENHARMONY_PARTS += "developtools:bytrace_standard"
OPENHARMONY_PARTS += "developtools:hdc_standard"
OPENHARMONY_PARTS += "distributeddatamgr:distributeddatamgr"
OPENHARMONY_PARTS += "distributeddatamgr:native_appdatamgr"
OPENHARMONY_PARTS += "distributedhardware:device_manager_base"
OPENHARMONY_PARTS += "distributedschedule:dmsfwk_standard"
OPENHARMONY_PARTS += "distributedschedule:safwk"
OPENHARMONY_PARTS += "distributedschedule:samgr_L2"
OPENHARMONY_PARTS += "global:i18n_standard"
OPENHARMONY_PARTS += "global:resmgr_standard"
OPENHARMONY_PARTS += "graphic:graphic_standard"
OPENHARMONY_PARTS += "hdf:hdf"
OPENHARMONY_PARTS += "hdf:display_device_driver"
OPENHARMONY_PARTS += "hiviewdfx:faultloggerd"
OPENHARMONY_PARTS += "hiviewdfx:hilog"
OPENHARMONY_PARTS += "hiviewdfx:hilog_native"
OPENHARMONY_PARTS += "hiviewdfx:hilog_service"
OPENHARMONY_PARTS += "hiviewdfx:hisysevent_native"
OPENHARMONY_PARTS += "hiviewdfx:hiviewdfx_hilog_native"
OPENHARMONY_PARTS += "miscservices:inputmethod_native"
OPENHARMONY_PARTS += "miscservices:time_native"
OPENHARMONY_PARTS += "multimedia:multimedia_audio_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_camera_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_histreamer"
OPENHARMONY_PARTS += "multimedia:multimedia_image_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_media_standard"
OPENHARMONY_PARTS += "multimodalinput:multimodalinput_base"
OPENHARMONY_PARTS += "notification:ans_standard"
OPENHARMONY_PARTS += "notification:ces_standard"
OPENHARMONY_PARTS += "powermgr:battery_manager_native"
OPENHARMONY_PARTS += "powermgr:display_manager_native"
OPENHARMONY_PARTS += "powermgr:power_manager_native"
OPENHARMONY_PARTS += "security:appverify"
OPENHARMONY_PARTS += "security:dataclassification"
OPENHARMONY_PARTS += "security:deviceauth_standard"
OPENHARMONY_PARTS += "security:huks_standard"
OPENHARMONY_PARTS += "security:permission_standard"
OPENHARMONY_PARTS += "startup:startup_l2"
OPENHARMONY_PARTS += "telephony:core_service"
OPENHARMONY_PARTS += "telephony:ril_adapter"
OPENHARMONY_PARTS += "utils:utils_base"

OPENHARMONY_PARTS += "${@bb.utils.contains('DISTRO_FEATURES', 'acts', 'xts:phone_tests', '', d)}"
export XTS_SUITENAME = "${@bb.utils.contains('DISTRO_FEATURES', 'acts', 'acts', '', d)}"

python generate_parts_json() {
    # parts.json file is used by the loader.py tool to generate BUILD.gn files
    # for all required "parts" basing on ohos.build files that accompany
    # OpenHarmony components
    #
    # NOTE: In normal OpenHarmony build process, this file is generated by the
    # preloader.py tool

    import json
    import os

    json_parts = {}
    json_parts['parts'] = []

    for part in d.getVar("OPENHARMONY_PARTS").split():
        json_parts['parts'].append(part)

    config_dir = d.getVar("OHOS_PRELOADER_BUILD_CONFIG_DIR")

    os.makedirs(config_dir, exist_ok=True)

    with open(os.path.join(config_dir, 'parts.json'), 'w') as outfile:
        outfile.write(json.dumps(json_parts, indent=2, sort_keys=True))
}

copy_subsystem_config_json_file() {
    cp "${S}/build/subsystem_config.json" "${OHOS_BUILD_CONFIGS_DIR}/"
}

# //utils/native component
PACKAGES =+ "${PN}-libutilsecurec ${PN}-libutils"
FILES:${PN}-libutilsecurec = "${libdir}/libutilsecurec*${SOLIBS}"
FILES:${PN}-libutils = "${libdir}/libutils*${SOLIBS}"
RDEPENDS:${PN}-libutilsecurec += "musl libcxx"
RDEPENDS:${PN}-libutils += "musl libcxx ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-libutilsecurec ${PN}-libutils"

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"

# //base/hiviewdfx/hilog component
PACKAGES =+ "${PN}-hilog ${PN}-hilog-ptest"
SYSTEMD_PACKAGES = "${PN}-hilog"
SYSTEMD_SERVICE:${PN}-hilog = "hilogd.service"
SRC_URI += "file://hilogd.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/hilogd.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/hilogd.cfg
    install -d ${D}${sysconfdir}/sysctl.d
    echo "net.unix.max_dgram_qlen=600" > ${D}${sysconfdir}/sysctl.d/hilogd.conf
}
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hilog/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hilog/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/hiviewdfx/hilog ${D}${libdir}/${BPN}-hilog/ptest/moduletest
    rmdir ${D}${PTEST_PATH}/moduletest/hiviewdfx
    echo "hilogd.service" > ${D}${libdir}/${BPN}-hilog/ptest/systemd-units
}
FILES:${PN}-hilog = "\
    ${bindir}/hilog* \
    ${libdir}/libhilog* \
    ${sysconfdir}/openharmony/hilog*.conf \
    ${systemd_unitdir}/hilogd.service \
"
FILES:${PN}-hilog-ptest = "${libdir}/${BPN}-hilog/ptest"
RDEPENDS:${PN} += "${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-hilog-ptest ${PN}-hilog"
RDEPENDS:${PN}-hilog-ptest += "${PN}-hilog"
RDEPENDS:${PN}-hilog       += "musl libcxx"
RDEPENDS:${PN}-hilog-ptest += "musl libcxx"
RDEPENDS:${PN}-hilog       += "${PN}-libutilsecurec"

# //base/startup/appspawn_standard component
PACKAGES =+ "${PN}-appspawn ${PN}-appspawn-ptest"
SYSTEMD_PACKAGES += "${PN}-appspawn"
SYSTEMD_SERVICE:${PN}-appspawn = "appspawn.service"
SRC_URI += "file://appspawn.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/appspawn.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/appspawn.cfg
}
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-appspawn/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-appspawn/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/startup_l2/appspawn_l2 ${D}${libdir}/${BPN}-appspawn/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/startup_l2/appspawn_l2 ${D}${libdir}/${BPN}-appspawn/ptest/unittest
    rmdir ${D}${PTEST_PATH}/*/startup_l2
    echo "appspawn.service" > ${D}${libdir}/${BPN}-appspawn/ptest/systemd-units
}
OPENHARMONY_PTEST_IS_BROKEN += "appspawn"
FILES:${PN}-appspawn = "\
    ${bindir}/appspawn* \
    ${libdir}/libappspawn* \
    ${systemd_unitdir}/appspawnd.service \
"
FILES:${PN}-appspawn-ptest = "${libdir}/${BPN}-appspawn/ptest"
RDEPENDS:${PN} += "${PN}-appspawn"
RDEPENDS:${PN}-ptest += "${PN}-appspawn-ptest ${PN}-appspawn"
RDEPENDS:${PN}-appspawn-ptest += "${PN}-appspawn"
RDEPENDS:${PN}-appspawn       += "musl libcxx"
RDEPENDS:${PN}-appspawn-ptest += "musl libcxx"
RDEPENDS:${PN}-appspawn       += "${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-appspawn-ptest += "${PN}-libutils ${PN}-hilog"
# TODO: remove when needed parts are split out
RDEPENDS:${PN}-appspawn       += "${PN}"
RDEPENDS:${PN}-appspawn-ptest += "${PN}"

# //foundation/appexecfwk/standard component
PACKAGES =+ "${PN}-appexecfwk ${PN}-appexecfwk-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-appexecfwk/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-appexecfwk/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/appexecfwk_standard ${D}${libdir}/${BPN}-appexecfwk/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/appexecfwk_standard ${D}${libdir}/${BPN}-appexecfwk/ptest/unittest
    mv ${D}${PTEST_PATH}/systemtest/appexecfwk_standard ${D}${libdir}/${BPN}-appexecfwk/ptest/systemtest
}
OPENHARMONY_PTEST_IS_BROKEN += "appexecfwk"
FILES:${PN}-appexecfwk = "\
    ${libdir}/libappexecfwk*${SOLIBS} \
"
FILES:${PN}-appexecfwk-ptest = "${libdir}/${BPN}-appexecfwk/ptest"
RDEPENDS:${PN} += "${PN}-appexecfwk"
RDEPENDS:${PN}-ptest += "${PN}-appexecfwk-ptest ${PN}-appexecfwk"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-appexecfwk"
RDEPENDS:${PN}-appexecfwk       += "musl libcxx"
RDEPENDS:${PN}-appexecfwk-ptest += "musl libcxx"
#RDEPENDS:${PN}-appexecfwk       += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc"
RDEPENDS:${PN}-appexecfwk       += "${PN}-libutils ${PN}-hilog"
#RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc ${PN}-libeventhandler ${PN}-hichecker ${PN}-hitrace"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-libutils ${PN}-hilog ${PN}-appspawn"
# TODO: remove when needed parts are split out
RDEPENDS:${PN}-appexecfwk += "${PN}"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}"

# Disable all ptest suites that are know to not work for now. When the x-bit is
# not set, the ptest is visible (using `ptest-runner -l`), but no test cases
# will be run when executing it.
# TODO: Fix all components and tests and remove all of this
do_install_ptest:append() {
    for component in ${OPENHARMONY_PTEST_IS_BROKEN} ; do
        chmod -x ${D}${libdir}/${BPN}-$component/ptest/run-ptest
    done
}

EXCLUDE_FROM_SHLIBS = "1"

# To avoid excessive diskspace blowup, we are stripping our executables
INSANE_SKIP:${PN} += "already-stripped"
