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
SRC_URI += "file://hilog-Add-tests.patch;patchdir=${S}/base/hiviewdfx/hilog"

SRC_URI += "file://bison_parser.patch;patchdir=${S}/third_party/libxkbcommon"
SRC_URI += "file://flexlexer.patch;patchdir=${S}/base/update/updater"

# Native node hacks
SRC_URI += "file://jsframwork-use-yocto-node.patch;patchdir=${S}/third_party/jsframework"
SRC_URI += "file://ts2abc-don-t-set-node_path-for-Linux-host-toolchain.patch;patchdir=${S}/ark/ts2abc"

SRC_URI += "file://hdc-build-system-files.patch;patchdir=${S}/developtools/hdc_standard"
SRC_URI += "file://build_packing-tool-path.patch;patchdir=${S}/build"
SRC_URI += "file://build_node-path.patch;patchdir=${S}/build"
SRC_URI += "file://build_js_assets.patch;patchdir=${S}/build"

SRC_URI += "file://vendor-qemu-uhdf-files.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "git://gitlab.eclipse.org/eclipse/oniro-core/openharmony-vendor-oniro.git;protocol=https;branch=main;rev=c7f69115d7af1a37f81bd4fc0462100d0aa87c2d;destsuffix=${S}/vendor/oniro"

SRC_URI += "file://peripherals-Limit-drivers-list-to-supported-by-the-qemuarm.patch;patchdir=${S}/drivers/adapter"
SRC_URI += "file://display_device.c;subdir=src/drivers/peripheral/display/hal/default/standard_system"
SRC_URI += "file://display-Use-temporary-qemuarm-implementation.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "file://display_gralloc_gbm.c-Use-card-drm-node.patch;patchdir=${S}/device/hihope"
SRC_URI += "file://hihope-gralloc-Backport-to-3.0.patch;patchdir=${S}/device/hihope"
SRC_URI += "file://ivi-input-controller.c-Fix-g_ctx-declaration-causing-segfault.patch;patchdir=${S}/third_party/wayland-ivi-extension"
SRC_URI += "file://xf86drm.c-Add-drmWaitVBlank-hack.patch;patchdir=${S}/third_party/libdrm"

SRC_URI += "file://graphic-standard-Add-missing-entry-for-libwms_client.patch;patchdir=${S}/foundation/graphic/standard"

SRC_URI += "file://appspawn-procps.patch;patchdir=${S}/base/startup/appspawn_standard"
SRC_URI += "file://base_startup_appspawn_standard-disable-longProcName-resetting.patch;patchdir=${S}/base/startup/appspawn_standard"
SRC_URI += "file://test_xts_acts-Align-tests-list-with-mandatory-set.patch;patchdir=${S}/test/xts/acts"

SRC_URI += "file://init_lite-silence-GetControlFromEnv-spam.patch;patchdir=${S}/base/startup/init_lite"
SRC_URI += "file://param_service_standalone.patch;patchdir=${S}/base/startup/init_lite"
SRC_URI += "file://param_service-Add-to-startup-l2-part.patch;patchdir=${S}/base/startup/appspawn_standard"

SRC_URI += "file://base_hiviewdfx_hiview-libfaultlogger-static.patch;patchdir=${S}/base/hiviewdfx/hiview"

# Patch to allow /system/profile and /system/usr to be symlinks to /usr/lib/openharmony
SRC_URI += "file://foundation_distributedschedule_safwk-slash-system-symlink.patch;patchdir=${S}/foundation/distributedschedule/safwk"

SRC_URI += "file://RenderText-PerformLayout-remove-sigsegv-code.patch;patchdir=${S}/foundation/ace/ace_engine"
SRC_URI += "file://test-xts-acts-timeout-increment.patch;patchdir=${S}/test/xts/acts"
SRC_URI += "file://start-ability-timeout-increment.patch;patchdir=${S}/test/xts/acts"
SRC_URI += "file://test-xts-acts-fix-Defpermission-typo.patch;patchdir=${S}/test/xts/acts"
SRC_URI += "file://test-xts-acts-fix-faultloggertest.patch;patchdir=${S}/test/xts/acts"

inherit python3native gn_base ptest

B = "${S}/out/ohos-arm-release"

COMPATIBLE_MACHINE = "(qemuarm|qemuarm64|qemuarm-efi|qemuarm64-efi|raspberrypi4-64)"

def get_ohos_arch(d):
    arch = get_musl_loader_arch(d)
    if arch.startswith("aarch64"):
        return "arm64"
    elif arch.startswith("arm"):
        return "arm"
    return arch

def get_ohos_libdir(d):
    if get_ohos_arch(d).endswith("64"):
        return "lib64"
    else:
        return "lib"

def get_ohos_libdirs(d):
    if get_ohos_arch(d).endswith("64"):
        return "/system/lib64 /system/lib"
    else:
        return "/system/lib"

OHOS_DEVICE_CPU_ARCH = "${@get_ohos_arch(d)}"
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

# Workaround for problem with nodejs 17:
# error:0308010C:digital envelope routines::unsupported
export NODE_OPTIONS = "--openssl-legacy-provider"
export OPENSSL_CONF = "${RECIPE_SYSROOT_NATIVE}/usr/lib/ssl-3/openssl.cnf"
export SSL_CERT_DIR = "${RECIPE_SYSROOT_NATIVE}/usr/lib/ssl-3/certs"
export OPENSSL_ENGINES = "${RECIPE_SYSROOT_NATIVE}/usr/lib/engines-3"
export OPENSSL_MODULES = "${RECIPE_SYSROOT_NATIVE}/usr/lib/ossl-modules"

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
# Needed rdeps for acts
RDEPENDS:${PN}-ptest += "${@bb.utils.contains('DISTRO_FEATURES', 'acts', '${PN}-hisysevent ${PN}-hiview ${PN}-hicollie ${PN}-hitrace ${PN}-hilog', '', d)}"

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
    cp -r ${OHOS_PACKAGE_OUT_DIR}/system/${@get_ohos_libdir(d)}/* ${D}${libdir}/
    install -m 755 -t ${D}${bindir}/ ${OHOS_PACKAGE_OUT_DIR}/system/bin/*
    ln -sfT ..${libdir} ${D}/system/${@get_ohos_libdir(d)}
    ln -sfT ..${bindir} ${D}/system/bin
    # FIXME this is not really the right thing to do, but OpenHarmony hardcodes
    # /system/lib in some places and uses /system/lib64 in a few others.
    # For now, this fix is sufficient.
    [ "${@get_ohos_libdir(d)}" != "lib" ] && ln -s ${@get_ohos_libdir(d)} ${D}/system/lib

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
RDEPENDS:${PN} += "coreutils"

RDEPENDS:${PN} += "musl libcxx libcrypto libssl libatomic"
RDEPENDS:${PN}-ptest += "musl libcxx libcrypto libssl ${PN}-libutils"

# OpenHarmony libraries are not versioned properly.
# Move the unversioned .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# Remove default file patterns from FILES:${PN} and avoid non-packaged
# files to be included in the ${PN} package
FILES:${PN}:remove = "${libdir}/lib*.so"
FILES:${PN}:remove = "${bindir}/*"
FILES:${PN}:remove = "${libdir}/${BPN}/*"

FILES:${PN}-configs = "${sysconfdir}"
FILES:${PN}-fonts = "${datadir}/fonts"

FILES:${PN} += "/system/bin ${@get_ohos_libdirs(d)} /system/profile"
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
OPENHARMONY_PARTS += "distributeddatamgr:appdatamgr_jskits"
OPENHARMONY_PARTS += "distributeddatamgr:distributeddatamgr"
OPENHARMONY_PARTS += "distributeddatamgr:distributedfilejs"
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
OPENHARMONY_PARTS += "hiviewdfx:hiview"
OPENHARMONY_PARTS += "hiviewdfx:hiview_L2"
OPENHARMONY_PARTS += "hiviewdfx:hicollie_native"
OPENHARMONY_PARTS += "hiviewdfx:hitrace_native"
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

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"

# OpenHarmony pre-init package and its systemd service
# Used to create folders needed by OH services and components
PACKAGES =+ "${PN}-openharmony-preinit"
SYSTEMD_PACKAGES = "${PN}-openharmony-preinit"
SYSTEMD_SERVICE:${PN}-openharmony-preinit = "openharmony-preinit.service"
FILES:${PN}-openharmony-preinit = " \
    ${libdir}/openharmony-preinit \
"
SRC_URI += "file://openharmony-preinit file://openharmony-preinit.service"
do_install:append() {
    install -d ${D}/${sbindir}
    install -m 755 ${WORKDIR}/openharmony-preinit ${D}/${sbindir}

    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/openharmony-preinit.service ${D}${systemd_unitdir}/system/
}
RDEPENDS:${PN} += "${PN}-openharmony-preinit"

# //utils/native component
PACKAGES =+ "${PN}-libutilsecurec ${PN}-libutils"
FILES:${PN}-libutilsecurec = "${libdir}/libutilsecurec*${SOLIBS}"
FILES:${PN}-libutils = "${libdir}/libutils*${SOLIBS}"
RDEPENDS:${PN}-libutilsecurec += "musl libcxx"
RDEPENDS:${PN}-libutils += "musl libcxx ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-libutilsecurec ${PN}-libutils"

PACKAGES =+ "${PN}-libutils-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "libutils"
FILES:${PN}-libutils-ptest = "${libdir}/${BPN}-libutils/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-libutils/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-libutils/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/utils/base ${D}${libdir}/${BPN}-libutils/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/utils
}
RDEPENDS:${PN}-libutils-ptest += "musl libcxx"
RDEPENDS:${PN}-libutils-ptest += "${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-libutils-ptest"

# //base/hiviewdfx/hilog component
PACKAGES =+ "${PN}-hilog"
SYSTEMD_PACKAGES += "${PN}-hilog"
SYSTEMD_SERVICE:${PN}-hilog = "hilogd.service"
SRC_URI += "file://hilogd.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/hilogd.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/hilogd.cfg
    install -d ${D}${sysconfdir}/sysctl.d
    echo "net.unix.max_dgram_qlen=600" > ${D}${sysconfdir}/sysctl.d/hilogd.conf
}
FILES:${PN}-hilog = " \
    ${bindir}/hilog* \
    ${libdir}/libhilog*${SOLIBS} \
    ${sysconfdir}/openharmony/hilog*.conf \
    ${systemd_unitdir}/hilogd.service \
"
RDEPENDS:${PN}-hilog += "musl libcxx"
RDEPENDS:${PN}-hilog += "${PN}-libutilsecurec"
RDEPENDS:${PN} += "${PN}-hilog"

PACKAGES =+ "${PN}-hilog-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hilog/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hilog/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/hiviewdfx/hilog ${D}${libdir}/${BPN}-hilog/ptest/moduletest
    echo "hilogd.service" > ${D}${libdir}/${BPN}-hilog/ptest/systemd-units
}
FILES:${PN}-hilog-ptest = "${libdir}/${BPN}-hilog/ptest"
RDEPENDS:${PN}-hilog-ptest += "musl libcxx"
RDEPENDS:${PN}-hilog-ptest += "${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-hilog-ptest"

# //base/startup/appspawn_standard component
PACKAGES =+ "${PN}-appspawn"
SYSTEMD_PACKAGES += "${PN}-appspawn"
SYSTEMD_SERVICE:${PN}-appspawn = "appspawn.service"
SRC_URI += "file://appspawn.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/appspawn.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/appspawn.cfg
}
FILES:${PN}-appspawn = " \
    ${bindir}/appspawn \
    ${libdir}/libappspawn*${SOLIBS} \
    ${systemd_unitdir}/appspawnd.service \
"
RDEPENDS:${PN}-appspawn += "musl libcxx"
RDEPENDS:${PN}-appspawn += "${PN}-libutils ${PN}-hilog ${PN}-appexecfwk"
RDEPENDS:${PN} += "${PN}-appspawn"

PACKAGES =+ "${PN}-appspawn-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "appspawn"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-appspawn/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-appspawn/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/startup_l2/appspawn_l2 ${D}${libdir}/${BPN}-appspawn/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/startup_l2/appspawn_l2 ${D}${libdir}/${BPN}-appspawn/ptest/unittest
    rmdir ${D}${PTEST_PATH}/*/startup_l2
    echo "appspawn.service" > ${D}${libdir}/${BPN}-appspawn/ptest/systemd-units
}
FILES:${PN}-appspawn-ptest = "${libdir}/${BPN}-appspawn/ptest"
RDEPENDS:${PN}-appspawn-ptest += "${PN}-appspawn"
RDEPENDS:${PN}-appspawn-ptest += "musl libcxx"
RDEPENDS:${PN}-appspawn-ptest += "${PN}-libutils ${PN}-hilog ${PN}-appexecfwk"
RDEPENDS:${PN}-ptest += "${PN}-appspawn-ptest"

# //foundation/appexecfwk/standard component
PACKAGES =+ "${PN}-appexecfwk"
SYSTEMD_PACKAGES += "${PN}-appexecfwk"
SYSTEMD_SERVICE:${PN}-appexecfwk = "installs.service foundation.service"
SRC_URI += "file://installs.service"
SRC_URI += "file://foundation.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/installs.service ${D}${systemd_unitdir}/system/
    install -m 644 ${WORKDIR}/foundation.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/installs.cfg
}
FILES:${PN}-appexecfwk = "\
    ${bindir}/appexec \
    ${bindir}/bm \
    ${bindir}/fm \
    ${bindir}/installs \
    ${bindir}/lmks \
    ${libdir}/libappexecfwk*${SOLIBS} \
    ${libdir}/libappkit_*${SOLIBS} \
    ${libdir}/libeventhandler*${SOLIBS} \
    ${libdir}/libams*${SOLIBS} \
    ${libdir}/libbms*${SOLIBS} \
    ${libdir}/libfms*${SOLIBS} \
    ${libdir}/module/libbundle*${SOLIBS} \
    ${libdir}/module/libnapi_app_mgr*${SOLIBS} \
    ${libdir}/openharmony/profile/foundation.xml \
"
RDEPENDS:${PN}-appexecfwk += "musl libcxx"
RDEPENDS:${PN}-appexecfwk += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc ${PN}-appverify ${PN}-distributeddatamgr ${PN}-notification-ces"
RDEPENDS:${PN}-appexecfwk += "${PN}-security-permission ${PN}-appspawn ${PN}-safwk ${PN}-timeservice ${PN}-powermgr ${PN}-dmsfwk ${PN}-resmgr"
RDEPENDS:${PN}-appexecfwk += "${PN}-aafwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-appexecfwk"

PACKAGES =+ "${PN}-appexecfwk-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "appexecfwk"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-appexecfwk/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-appexecfwk/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/appexecfwk_standard ${D}${libdir}/${BPN}-appexecfwk/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/appexecfwk_standard ${D}${libdir}/${BPN}-appexecfwk/ptest/unittest
    mv ${D}${PTEST_PATH}/systemtest/appexecfwk_standard ${D}${libdir}/${BPN}-appexecfwk/ptest/systemtest
}
FILES:${PN}-appexecfwk-ptest = "${libdir}/${BPN}-appexecfwk/ptest"
RDEPENDS:${PN}-appexecfwk-ptest += "musl libcxx"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-appexecfwk ${PN}-libutils ${PN}-hilog ${PN}-appspawn ${PN}-appverify ${PN}-distributeddatamgr"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-thirdparty-jsoncpp ${PN}-samgr ${PN}-ipc ${PN}-safwk ${PN}-aafwk"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-samgr ${PN}-notification-ces ${PN}-dmsfwk ${PN}-security-permission"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-thirdparty-libxml2"
RDEPENDS:${PN}-ptest += "${PN}-appexecfwk-ptest"

# //base/security/appverify
PACKAGES =+ "${PN}-appverify"
FILES:${PN}-appverify = "${libdir}/libhapverify*${SOLIBS}"
RDEPENDS:${PN}-appverify += "musl libcxx libcrypto ${PN}-libutils ${PN}-hilog ${PN}-syspara"
RDEPENDS:${PN} += "${PN}-appverify"

PACKAGES =+ "${PN}-appverify-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-appverify/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-appverify/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/appverify ${D}${libdir}/${BPN}-appverify/ptest/unittest
}
FILES:${PN}-appverify-ptest = "${libdir}/${BPN}-appverify/ptest"
RDEPENDS:${PN}-appverify-ptest += "musl libcxx libcrypto"
RDEPENDS:${PN}-appverify-ptest += "${PN}-appverify ${PN}-libutils ${PN}-hilog ${PN}-syspara"
RDEPENDS:${PN}-ptest += "${PN}-appverify-ptest"

# //base/startup/syspara_lite
PACKAGES =+ "${PN}-syspara"
FILES:${PN}-syspara = " \
    ${libdir}/libsyspara*${SOLIBS} \
    ${libdir}/module/libdeviceinfo*${SOLIBS} \
    ${libdir}/module/libsystemparameter*${SOLIBS} \
"
RDEPENDS:${PN}-syspara += "musl libcxx libcrypto ${PN}-libutils ${PN}-hilog ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-syspara"

# //foundation/aafwk
PACKAGES =+ "${PN}-aafwk"
FILES:${PN}-aafwk = " \
    ${bindir}/aa \
    ${libdir}/libability*${SOLIBS} \
    ${libdir}/libbase*${SOLIBS} \
    ${libdir}/libdataobs*${SOLIBS} \
    ${libdir}/libdummy_classes*${SOLIBS} \
    ${libdir}/libintent*${SOLIBS} \
    ${libdir}/libnapi_common*${SOLIBS} \
    ${libdir}/libwant.z${SOLIBS} \
    ${libdir}/module/ability/*${SOLIBS} \
    ${libdir}/module/app/libabilitymanager*${SOLIBS} \
    ${libdir}/module/libzlib*${SOLIBS} \
"
RDEPENDS:${PN}-aafwk += "musl libcxx"
RDEPENDS:${PN}-aafwk += "${PN}-appexecfwk ${PN}-samgr ${PN}-libutils ${PN}-ipc ${PN}-appdatamgr ${PN}-dmsfwk ${PN}-resmgr ${PN}-security-permission"
RDEPENDS:${PN}-aafwk += "${PN}-safwk ${PN}-notification-ces ${PN}-multimodalinput ${PN}-thirdparty-jsoncpp ${PN}-graphic ${PN}-hilog ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-aafwk"

PACKAGES =+ "${PN}-aafwk-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "aafwk"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-aafwk/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-aafwk/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/aafwk_standard ${D}${libdir}/${BPN}-aafwk/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/aafwk_standard ${D}${libdir}/${BPN}-aafwk/ptest/unittest
    mv ${D}${PTEST_PATH}/systemtest/aafwk_standard ${D}${libdir}/${BPN}-aafwk/ptest/systemtest
}
FILES:${PN}-aafwk-ptest = "${libdir}/${BPN}-aafwk/ptest"
RDEPENDS:${PN}-aafwk-ptest += "musl libcxx"
RDEPENDS:${PN}-aafwk-ptest += "${PN}-aafwk ${PN}-appexecfwk ${PN}-samgr ${PN}-thirdparty-jsoncpp ${PN}-libutils ${PN}-hilog ${PN}-ipc"
RDEPENDS:${PN}-aafwk-ptest += "${PN}-appdatamgr ${PN}-dmsfwk ${PN}-notification-ces ${PN}-multimodalinput ${PN}-resmgr ${PN}-ace-napi"
RDEPENDS:${PN}-aafwk-ptest += "${PN}-security-permission"
RDEPENDS:${PN}-ptest += "${PN}-aafwk-ptest"

# //base/notification/ans_standard - Advanced Notification Service
PACKAGES =+ "${PN}-notification-ans"
FILES:${PN}-notification-ans = " \
    ${bindir}/anm \
    ${libdir}/libans*${SOLIBS} \
    ${libdir}/libwantagent_innerkits*${SOLIBS} \
    ${libdir}/module/libnotification*${SOLIBS} \
    ${libdir}/module/libwantagent*${SOLIBS} \
"
RDEPENDS:${PN}-notification-ans += "musl libcxx"
RDEPENDS:${PN}-notification-ans += "${PN}-dmsfwk ${PN}-libutils ${PN}-hilog ${PN}-ipc ${PN}-samgr ${PN}-distributeddatamgr ${PN}-appexecfwk"
RDEPENDS:${PN}-notification-ans += "${PN}-notification-ces ${PN}-safwk ${PN}-aafwk ${PN}-dmsfwk ${PN}-multimedia-image ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-notification-ans"

PACKAGES =+ "${PN}-notification-ans-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "notification-ans"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-notification-ans/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-notification-ans/ptest/run-ptest

    mv ${D}${PTEST_PATH}/moduletest/ans_standard/moduletest ${D}${libdir}/${BPN}-notification-ans/ptest/moduletest
    rmdir ${D}${PTEST_PATH}/moduletest/ans_standard

    mv ${D}${PTEST_PATH}/unittest/ans_standard/unittest ${D}${libdir}/${BPN}-notification-ans/ptest/unittest
    mv ${D}${PTEST_PATH}/unittest/ans_standard/wantagent ${D}${libdir}/${BPN}-notification-ans/ptest/unittest/
    rmdir ${D}${PTEST_PATH}/unittest/ans_standard
}
FILES:${PN}-notification-ans-ptest = "${libdir}/${BPN}-notification-ans/ptest"
RDEPENDS:${PN}-notification-ans-ptest += "musl libcxx"
RDEPENDS:${PN}-notification-ans-ptest += "${PN}-notification-ans ${PN}-appexecfwk ${PN}-dmsfwk ${PN}-samgr ${PN}-multimedia-image ${PN}-libutils"
RDEPENDS:${PN}-notification-ans-ptest += "${PN}-hilog ${PN}-thirdparty-jsoncpp ${PN}-aafwk ${PN}-ipc ${PN}-safwk ${PN}-notification-ces"
RDEPENDS:${PN}-notification-ans-ptest += "${PN}-resmgr ${PN}-multimodalinput ${PN}-appdatamgr"
RDEPENDS:${PN}-ptest += "${PN}-notification-ans-ptest"

# //base/notification/ces_standard - Common Event Service
PACKAGES =+ "${PN}-notification-ces"
FILES:${PN}-notification-ces = " \
    ${bindir}/cem \
    ${libdir}/libcesfwk_*${SOLIBS} \
    ${libdir}/libevent_common*${SOLIBS} \
    ${libdir}/module/libcommonevent*${SOLIBS} \
"
RDEPENDS:${PN}-notification-ces += "musl libcxx"
RDEPENDS:${PN}-notification-ces += "${PN}-ipc ${PN}-libutils ${PN}-hilog ${PN}-thirdparty-libxml2 ${PN}-ipc"
RDEPENDS:${PN}-notification-ces += "${PN}-samgr ${PN}-appexecfwk ${PN}-safwk ${PN}-aafwk ${PN}-dmsfwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-notification-ces"

PACKAGES =+ "${PN}-notification-ces-ptest"
# Sometimes passes, sometimes fails...
OPENHARMONY_PTEST_IS_BROKEN += "notification-ces"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-notification-ces/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-notification-ces/ptest/run-ptest

    mv ${D}${PTEST_PATH}/moduletest/ces_standard/mouduletest ${D}${libdir}/${BPN}-notification-ces/ptest/moduletest
    mv ${D}${PTEST_PATH}/moduletest/ces_standard/tools ${D}${libdir}/${BPN}-notification-ces/ptest/moduletest/
    rmdir ${D}${PTEST_PATH}/moduletest/ces_standard

    mv ${D}${PTEST_PATH}/unittest/ces_standard ${D}${libdir}/${BPN}-notification-ces/ptest/unittest

    mv ${D}${PTEST_PATH}/systemtest/ces_standard/systemtest ${D}${libdir}/${BPN}-notification-ans/ptest/systemtest
    mv ${D}${PTEST_PATH}/systemtest/ces_standard/tools ${D}${libdir}/${BPN}-notification-ans/ptest/systemtest/
    rmdir ${D}${PTEST_PATH}/systemtest/ces_standard
}
FILES:${PN}-notification-ces-ptest = "${libdir}/${BPN}-notification-ces/ptest"
RDEPENDS:${PN}-notification-ces-ptest += "musl libcxx"
RDEPENDS:${PN}-notification-ces-ptest += "${PN}-notification-ces ${PN}-appexecfwk ${PN}-libutils ${PN}-aafwk ${PN}-hilog ${PN}-ipc ${PN}-resmgr"
RDEPENDS:${PN}-notification-ces-ptest += "${PN}-multimodalinput ${PN}-distributeddatamgr"
RDEPENDS:${PN}-ptest += "${PN}-notification-ces-ptest"

# //foundation/communication/ipc
PACKAGES =+ "${PN}-ipc"
FILES:${PN}-ipc = "\
    ${libdir}/libipc*${SOLIBS} \
    ${libdir}/module/librpc*${SOLIBS} \
    ${libdir}/libdbinder*${SOLIBS} \
"
RDEPENDS:${PN}-ipc += "musl libcxx"
RDEPENDS:${PN}-ipc += "${PN}-libutils ${PN}-hilog ${PN}-dsoftbus ${PN}-samgr ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-ipc"

PACKAGES =+ "${PN}-ipc-ptest"
# Missing binary and library paths to openharmony-standard-ipc/ptest/*/resource/communication/ipc
# for the tests to be executed
OPENHARMONY_PTEST_IS_BROKEN += "ipc"
FILES:${PN}-ipc-ptest = "${libdir}/${BPN}-ipc/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-ipc/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-ipc/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/ipc ${D}${libdir}/${BPN}-ipc/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/ipc ${D}${libdir}/${BPN}-ipc/ptest/unittest
}
RDEPENDS:${PN}-ipc-ptest += "musl libcxx"
RDEPENDS:${PN}-ipc-ptest += "${PN}-libutils ${PN}-hilog ${PN}-dsoftbus ${PN}-ipc ${PN}-samgr"
RDEPENDS:${PN}-ptest += "${PN}-ipc-ptest"

# //foundation/communication/dsoftbus
PACKAGES =+ "${PN}-dsoftbus"
SYSTEMD_PACKAGES += "${PN}-dsoftbus"
SYSTEMD_SERVICE:${PN}-dsoftbus = "dsoftbus.service"
SRC_URI += "file://dsoftbus.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/dsoftbus.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/softbus_server.cfg
}
FILES:${PN}-dsoftbus = " \
    ${libdir}/libsoftbus*${SOLIBS} \
    ${libdir}/libnstackx*${SOLIBS} \
    ${libdir}/libmbedtls*${SOLIBS} \
    ${libdir}/openharmony/profile/softbus_server.xml \
"
RDEPENDS:${PN} += "${PN}-dsoftbus"
RDEPENDS:${PN}-dsoftbus += "musl libcxx"
RDEPENDS:${PN}-dsoftbus += "${PN}-samgr ${PN}-syspara ${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-safwk ${PN}-thirdparty-libcoap"
RDEPENDS:${PN}-dsoftbus += "${PN}-security-deviceauth ${PN}-aafwk ${PN}-notification-ces ${PN}-appexecfwk ${PN}-libutilsecurec"

PACKAGES =+ "${PN}-dsoftbus-ptest"
# Test discovery/DiscSdkTest segfaults
OPENHARMONY_PTEST_IS_BROKEN += "dsoftbus"
FILES:${PN}-dsoftbus-ptest = "${libdir}/${BPN}-dsoftbus/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-dsoftbus/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-dsoftbus/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/dsoftbus_standard ${D}${libdir}/${BPN}-dsoftbus/ptest/unittest
}
RDEPENDS:${PN}-dsoftbus-ptest += "musl libcxx"
RDEPENDS:${PN}-dsoftbus-ptest += "${PN}-dsoftbus ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-dsoftbus-ptest"

# //foundation/distributedschedule/samgr
PACKAGES =+ "${PN}-samgr"
FILES:${PN}-samgr = " \
    ${bindir}/samgr \
    ${libdir}/libsamgr*${SOLIBS} \
    ${libdir}/liblsamgr*${SOLIBS} \
    ${systemd_unitdir}/samgr.service \
"
SYSTEMD_PACKAGES += "${PN}-samgr"
SYSTEMD_SERVICE:${PN}-samgr = "samgr.service"
SRC_URI += "file://samgr.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/samgr.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/samgr_standard.cfg
}
RDEPENDS:${PN}-samgr += "musl libcxx"
RDEPENDS:${PN}-samgr += "${PN}-hilog ${PN}-ipc ${PN}-libutils ${PN}-thirdparty-libxml2"
RDEPENDS:${PN} += "${PN}-samgr"

PACKAGES =+ "${PN}-samgr-ptest"
# Test unittest/SystemAbilityMgrTest segfaults
OPENHARMONY_PTEST_IS_BROKEN += "samgr"
FILES:${PN}-samgr-ptest = "${libdir}/${BPN}-samgr/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-samgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-samgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/samgr/samgr ${D}${libdir}/${BPN}-samgr/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/samgr

    echo "samgr.service" > ${D}${libdir}/${BPN}-samgr/ptest/systemd-units
}
RDEPENDS:${PN}-samgr-ptest += "musl libcxx"
RDEPENDS:${PN}-samgr-ptest += "${PN}-samgr ${PN}-libutils ${PN}-hilog ${PN}-ipc"
RDEPENDS:${PN}-ptest += "${PN}-samgr-ptest"

# //foundation/distributedschedule/safwk
PACKAGES =+ "${PN}-safwk"
FILES:${PN}-safwk = "\
    ${bindir}/sa_main \
    ${libdir}/libsystem_ability_fwk*${SOLIBS} \
"
RDEPENDS:${PN}-safwk += "musl libcxx"
RDEPENDS:${PN}-safwk += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc"
RDEPENDS:${PN}-safwk += "${PN}-thirdparty-libxml2"
RDEPENDS:${PN} += "${PN}-safwk"

# //base/global/resmgr_standard
PACKAGES =+ "${PN}-resmgr"
FILES:${PN}-resmgr = " \
    ${libdir}/libglobal_resmgr*${SOLIBS} \
    ${libdir}/module/libresourcemanager*${SOLIBS} \
"
RDEPENDS:${PN}-resmgr += "musl libcxx ${PN}-thirdparty-icu ${PN}-hilog ${PN}-aafwk ${PN}-ipc ${PN}-dmsfwk ${PN}-libutils ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-resmgr"

PACKAGES =+ "${PN}-resmgr-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "resmgr"
FILES:${PN}-resmgr-ptest = "${libdir}/${BPN}-resmgr/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-resmgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-resmgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/resmgr_standard/test ${D}${libdir}/${BPN}-resmgr/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/resmgr_standard
}
RDEPENDS:${PN}-resmgr-ptest += "musl libcxx"
RDEPENDS:${PN}-resmgr-ptest += "${PN}-resmgr ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-resmgr-ptest"

# //foundation/distributeddatamgr/appdatamgr
PACKAGES =+ "${PN}-appdatamgr"
FILES:${PN}-appdatamgr = " \
    ${libdir}/libnative_rdb*${SOLIBS} \
    ${libdir}/libnative_preferences*${SOLIBS} \
    ${libdir}/libnative_dataability*${SOLIBS} \
    ${libdir}/libnative_appdatafwk*${SOLIBS} \
    ${libdir}/module/libfileio*${SOLIBS} \
    ${libdir}/module/libfile*${SOLIBS} \
    ${libdir}/module/data/librdb*${SOLIBS} \
    ${libdir}/module/data/libstorage*${SOLIBS} \
    ${libdir}/module/data/libdataability*${SOLIBS} \
"
RDEPENDS:${PN}-appdatamgr += "musl libcxx libcrypto"
RDEPENDS:${PN}-appdatamgr += "${PN}-libutils ${PN}-ipc ${PN}-hilog ${PN}-thirdparty-libxml2 ${PN}-thirdparty-icu ${PN}-thirdparty-sqlite ${PN}-ace-napi ${PN}-appexecfwk ${PN}-dmsfwk"
RDEPENDS:${PN} += "${PN}-appdatamgr"

PACKAGES =+ "${PN}-appdatamgr-ptest"
# Test NativeRdbTest segfaults
OPENHARMONY_PTEST_IS_BROKEN += "appdatamgr"
FILES:${PN}-appdatamgr-ptest = "${libdir}/${BPN}-appdatamgr/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-appdatamgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-appdatamgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/appdatamgr ${D}${libdir}/${BPN}-appdatamgr/ptest/unittest
    mv ${D}${PTEST_PATH}/unittest/native_appdatamgr/* ${D}${libdir}/${BPN}-appdatamgr/ptest/unittest/
    rmdir ${D}${PTEST_PATH}/unittest/native_appdatamgr
}
RDEPENDS:${PN}-appdatamgr-ptest += "musl libcxx"
RDEPENDS:${PN}-appdatamgr-ptest += "${PN}-appdatamgr ${PN}-libutils ${PN}-hilog ${PN}-ipc ${PN}-thirdparty-icu"
RDEPENDS:${PN}-ptest += "${PN}-appdatamgr-ptest"

# //foundation/distributeddatamgr/distributeddatamgr
PACKAGES =+ "${PN}-distributeddatamgr"
SYSTEMD_PACKAGES += "${PN}-distributeddatamgr"
SYSTEMD_SERVICE:${PN}-distributeddatamgr = "distributed_data.service"
SRC_URI += "file://distributed_data.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/distributed_data.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/distributed_data.cfg
}
FILES:${PN}-distributeddatamgr = " \
    ${libdir}/libdistributeddata*${SOLIBS} \
    ${libdir}/libapp_distributeddata*${SOLIBS} \
    ${libdir}/libdistributeddb*${SOLIBS} \
    ${libdir}/module/data/libdistributeddata*${SOLIBS} \
    ${libdir}/openharmony/profile/distributeddata.xml \
"
RDEPENDS:${PN}-distributeddatamgr += "musl libcxx libcrypto"
RDEPENDS:${PN}-distributeddatamgr += "${PN}-hilog ${PN}-bytrace ${PN}-hisysevent ${PN}-dsoftbus ${PN}-thirdparty-jsoncpp ${PN}-libutils"
RDEPENDS:${PN}-distributeddatamgr += "${PN}-ipc ${PN}-samgr ${PN}-aafwk ${PN}-powermgr ${PN}-safwk ${PN}-security-permission ${PN}-thirdparty-icu"
RDEPENDS:${PN}-distributeddatamgr += "${PN}-security-huks ${PN}-aafwk ${PN}-notification-ces ${PN}-dmsfwk ${PN}-thirdparty-sqlite ${PN}-ace-napi"
RDEPENDS:${PN}-distributeddatamgr += "${PN}-security-dataclassification ${PN}-os-account ${PN}-power-batterymgr ${PN}-thirdparty-libxml2"
RDEPENDS:${PN} += "${PN}-distributeddatamgr"

PACKAGES =+ "${PN}-distributeddatamgr-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "distributeddatamgr"
FILES:${PN}-distributeddatamgr-ptest = "${libdir}/${BPN}-distributeddatamgr/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-distributeddatamgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-distributeddatamgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/distributeddatamgr ${D}${libdir}/${BPN}-distributeddatamgr/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/distributeddatamgr ${D}${libdir}/${BPN}-distributeddatamgr/ptest/unittest
}
RDEPENDS:${PN}-distributeddatamgr-ptest += "musl libcxx libcrypto"
RDEPENDS:${PN}-distributeddatamgr-ptest += "${PN}-distributeddatamgr ${PN}-libutils ${PN}-hilog ${PN}-thirdparty-sqlite ${PN}-dsoftbus"
RDEPENDS:${PN}-distributeddatamgr-ptest += "${PN}-bytrace ${PN}-hisysevent ${PN}-aafwk ${PN}-notification-ces ${PN}-os-account"
RDEPENDS:${PN}-distributeddatamgr-ptest += "${PN}-power-batterymgr ${PN}-security-huks ${PN}-ipc ${PN}-safwk ${PN}-samgr ${PN}-aafwk"
RDEPENDS:${PN}-distributeddatamgr-ptest += "${PN}-dmsfwk ${PN}-thirdparty-jsoncpp ${PN}-security-permission ${PN}-powermgr"
RDEPENDS:${PN}-ptest += "${PN}-distributeddatamgr-ptest"

# //base/account/os_account
PACKAGES =+ "${PN}-os-account"
SYSTEMD_PACKAGES += "${PN}-os-account"
SYSTEMD_SERVICE:${PN}-os-account = "accountmgr.service"
SRC_URI += "file://accountmgr.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/accountmgr.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/accountmgr.cfg
}
FILES:${PN}-os-account = " \
    ${libdir}/libaccount*${SOLIBS} \
    ${libdir}/module/account/*${SOLIBS} \
    ${libdir}/openharmony/profile/accountmgr.xml \
"
RDEPENDS:${PN}-os-account += "musl libcxx"
RDEPENDS:${PN}-os-account += "${PN}-hilog ${PN}-ipc ${PN}-samgr ${PN}-libutils ${PN}-aafwk ${PN}-notification-ces"
RDEPENDS:${PN}-os-account += "${PN}-hisysevent ${PN}-security-permission ${PN}-safwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-os-account"

PACKAGES =+ "${PN}-os-account-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "os-account"
FILES:${PN}-os-account-ptest = "${libdir}/${BPN}-os-account/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-os-account/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-os-account/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/os_account_standard ${D}${libdir}/${BPN}-os-account/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/os_account_standard ${D}${libdir}/${BPN}-os-account/ptest/unittest
}
RDEPENDS:${PN}-os-account-ptest += "musl libcxx"
RDEPENDS:${PN}-os-account-ptest += "${PN}-os-account ${PN}-hilog ${PN}-ipc ${PN}-samgr ${PN}-libutils ${PN}-aafwk"
RDEPENDS:${PN}-os-account-ptest += "${PN}-notification-ces ${PN}-hisysevent ${PN}-security-permission ${PN}-safwk"
RDEPENDS:${PN}-ptest += "${PN}-os-account-ptest"

# //base/security/dataclassification
PACKAGES =+ "${PN}-security-dataclassification"
FILES:${PN}-security-dataclassification = " \
    ${libdir}/libfbe_iudf_xattr*${SOLIBS} \
    ${libdir}/libhwdsl*${SOLIBS} \
"
RDEPENDS:${PN}-security-dataclassification += "musl libcxx"
RDEPENDS:${PN} += "${PN}-security-dataclassification"

# //foundation/distributedschedule/dmsfwk
PACKAGES =+ "${PN}-dmsfwk"
SYSTEMD_PACKAGES += "${PN}-dmsfwk"
SYSTEMD_SERVICE:${PN}-dmsfwk = "distributedsched.service"
SRC_URI += "file://distributedsched.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/distributedsched.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/distributedsched.cfg
}
FILES:${PN}-dmsfwk = " \
    ${libdir}/libdistributedschedsvr*${SOLIBS} \
    ${libdir}/libzuri*${SOLIBS} \
    ${libdir}/openharmony/profile/distributedsched.xml \
"
RDEPENDS:${PN}-dmsfwk += "musl libcxx"
RDEPENDS:${PN}-dmsfwk += "${PN}-hilog ${PN}-aafwk"
RDEPENDS:${PN}-dmsfwk += "${PN}-appexecfwk ${PN}-safwk"
RDEPENDS:${PN}-dmsfwk += "${PN}-samgr ${PN}-libutils"
RDEPENDS:${PN}-dmsfwk += "${PN}-dsoftbus ${PN}-ipc"
RDEPENDS:${PN} += "${PN}-dmsfwk"

PACKAGES =+ "${PN}-dmsfwk-ptest"
# Test doesn't produce any output
OPENHARMONY_PTEST_IS_BROKEN += "dmsfwk"
FILES:${PN}-dmsfwk-ptest = "${libdir}/${BPN}-dmsfwk/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-dmsfwk/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-dmsfwk/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/dmsfwk ${D}${libdir}/${BPN}-dmsfwk/ptest/unittest
}
RDEPENDS:${PN}-dmsfwk-ptest += "musl libcxx"
RDEPENDS:${PN}-dmsfwk-ptest += "${PN}-dmsfwk ${PN}-hilog ${PN}-aafwk ${PN}-appexecfwk ${PN}-safwk ${PN}-samgr ${PN}-dsoftbus ${PN}-ipc ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-dmsfwk-ptest"

# //base/security/permission
PACKAGES =+ "${PN}-security-permission"
FILES:${PN}-security-permission = "${libdir}/libpermission*${SOLIBS}"
RDEPENDS:${PN}-security-permission += "musl libcxx"
RDEPENDS:${PN}-security-permission += "${PN}-thirdparty-sqlite ${PN}-libutils"
RDEPENDS:${PN}-security-permission += "${PN}-hilog ${PN}-ipc ${PN}-safwk ${PN}-samgr"
RDEPENDS:${PN} += "${PN}-security-permission"

PACKAGES =+ "${PN}-security-permission-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "security-permission"
FILES:${PN}-security-permission-ptest = "${libdir}/${BPN}-security-permission/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-security-permission/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-security-permission/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/permission_standard/permission_standard ${D}${libdir}/${BPN}-security-permission/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/permission_standard
}
RDEPENDS:${PN}-security-permission-ptest += "musl libcxx"
RDEPENDS:${PN}-security-permission-ptest += "${PN}-security-permission ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-security-permission-ptest"

# //base/security/huks
PACKAGES =+ "${PN}-security-huks"
SYSTEMD_PACKAGES += "${PN}-security-huks"
SYSTEMD_SERVICE:${PN}-security-huks = "huks.service"
SRC_URI += "file://huks.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/huks.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/huks_service.cfg
}
FILES:${PN}-security-huks = " \
    ${libdir}/libhuks*${SOLIBS} \
    ${libdir}/openharmony/profile/huks_service.xml \
"
RDEPENDS:${PN}-security-huks += "musl libcxx libcrypto"
RDEPENDS:${PN}-security-huks += "${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-safwk"
RDEPENDS:${PN} += "${PN}-security-huks"

PACKAGES =+ "${PN}-security-huks-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "security-huks"
FILES:${PN}-security-huks-ptest = "${libdir}/${BPN}-security-huks/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-security-huks/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-security-huks/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/huks_standard/huks_standard_test ${D}${libdir}/${BPN}-security-huks/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/huks_standard
}
RDEPENDS:${PN}-security-huks-ptest += "musl libcxx"
RDEPENDS:${PN}-security-huks-ptest += "${PN}-security-huks"
RDEPENDS:${PN}-ptest += "${PN}-security-huks-ptest"

# //base/security/deviceauth
PACKAGES =+ "${PN}-security-deviceauth"
SYSTEMD_PACKAGES += "${PN}-security-deviceauth"
SYSTEMD_SERVICE:${PN}-security-deviceauth = "deviceauth.service"
SRC_URI += "file://deviceauth.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/deviceauth.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/deviceauth_service.cfg
}
FILES:${PN}-security-deviceauth = " \
    ${bindir}/deviceauth_service \
    ${libdir}/libdeviceauth*${SOLIBS} \
"
RDEPENDS:${PN}-security-deviceauth += "musl libcxx libcrypto"
RDEPENDS:${PN}-security-deviceauth += "${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-samgr"
RDEPENDS:${PN}-security-deviceauth += "${PN}-security-huks ${PN}-syspara ${PN}-dsoftbus"
RDEPENDS:${PN} += "${PN}-security-deviceauth"

PACKAGES =+ "${PN}-security-deviceauth-ptest"
FILES:${PN}-security-deviceauth-ptest = "${libdir}/${BPN}-security-deviceauth/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-security-deviceauth/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-security-deviceauth/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/deviceauth_standard/deviceauth_test ${D}${libdir}/${BPN}-security-deviceauth/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/deviceauth_standard
}
RDEPENDS:${PN}-security-deviceauth-ptest += "musl libcxx libcrypto libssl"
RDEPENDS:${PN}-security-deviceauth-ptest += "${PN}-security-deviceauth ${PN}-security-huks ${PN}-libutils ${PN}-dsoftbus"
RDEPENDS:${PN}-ptest += "${PN}-security-deviceauth-ptest"

# //foundation/multimodalinput/input
PACKAGES =+ "${PN}-multimodalinput"
FILES:${PN}-multimodalinput = " \
    ${bindir}/uinput_inject \
    ${libdir}/libmmi_*${SOLIBS} \
    ${libdir}/libmultimodalinput_*${SOLIBS} \
    ${libdir}/module/libinjecteventhandler*${SOLIBS} \
    ${libdir}/openharmony/profile/multimodalinputservice.xml \
"
RDEPENDS:${PN}-multimodalinput += "musl libcxx"
RDEPENDS:${PN}-multimodalinput += "${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-safwk ${PN}-peripheral-input ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-multimodalinput"

PACKAGES =+ "${PN}-multimodalinput-ptest"
FILES:${PN}-multimodalinput-ptest = "${libdir}/${BPN}-multimodalinput/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-multimodalinput/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-multimodalinput/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/multimodalinput_base ${D}${libdir}/${BPN}-multimodalinput/ptest/unittest
}
RDEPENDS:${PN}-multimodalinput-ptest += "musl libcxx"
RDEPENDS:${PN}-multimodalinput-ptest += "${PN}-multimodalinput ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-multimodalinput-ptest"

# //drivers/adapter/uhdf2
PACKAGES =+ "${PN}-uhdf2"
FILES:${PN}-uhdf2 = " \
    ${libdir}/libhdi.z*${SOLIBS} \
    ${libdir}/libhdf_*${SOLIBS} \
"
RDEPENDS:${PN}-uhdf2 += "musl libcxx"
RDEPENDS:${PN}-uhdf2 += "${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-samgr"
RDEPENDS:${PN} += "${PN}-uhdf2"

PACKAGES =+ "${PN}-uhdf2-ptest"
FILES:${PN}-uhdf2-ptest = "${libdir}/${BPN}-uhdf2/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-uhdf2/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-uhdf2/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/hdf ${D}${libdir}/${BPN}-uhdf2/ptest/unittest
}
RDEPENDS:${PN}-uhdf2-ptest += "musl libcxx"
RDEPENDS:${PN}-uhdf2-ptest += "${PN}-uhdf2"
RDEPENDS:${PN}-ptest += "${PN}-uhdf2-ptest"

# //drivers/peripheral/camera
PACKAGES =+ "${PN}-peripheral-camera"
FILES:${PN}-peripheral-camera = "${libdir}/libcamera_client*${SOLIBS}"
RDEPENDS:${PN}-peripheral-camera += "musl libcxx"
RDEPENDS:${PN}-peripheral-camera += "${PN}-uhdf2 ${PN}-ipc ${PN}-multimedia-camera ${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-peripheral-camera"

# //drivers/peripheral/display
PACKAGES =+ "${PN}-peripheral-display"
FILES:${PN}-peripheral-display = "${libdir}/libhdi_display_*${SOLIBS}"
RDEPENDS:${PN}-peripheral-display += "musl libcxx"
RDEPENDS:${PN}-peripheral-display += "${PN}-hilog ${PN}-libutils ${PN}-uhdf2 ${PN}-ipc"
RDEPENDS:${PN} += "${PN}-peripheral-display"

# //drivers/peripheral/input
PACKAGES =+ "${PN}-peripheral-input"
FILES:${PN}-peripheral-input = "${libdir}/libhdi_input*${SOLIBS}"
RDEPENDS:${PN}-peripheral-input += "musl libcxx"
RDEPENDS:${PN}-peripheral-input += "${PN}-hilog ${PN}-libutils ${PN}-uhdf2"
RDEPENDS:${PN} += "${PN}-peripheral-input"

# //base/miscservices/time
PACKAGES =+ "${PN}-timeservice"
SYSTEMD_PACKAGES += "${PN}-timeservice"
SYSTEMD_SERVICE:${PN}-timeservice = "time.service"
SRC_URI += "file://time.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/time.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/timeservice.cfg
}
FILES:${PN}-timeservice = " \
    ${libdir}/libtime_service*${SOLIBS} \
    ${libdir}/module/libsystemtime*${SOLIBS} \
    ${libdir}/openharmony/profile/time_service.xml \
"
RDEPENDS:${PN}-timeservice += "musl libcxx"
RDEPENDS:${PN}-timeservice += "${PN}-appexecfwk ${PN}-aafwk ${PN}-thirdparty-jsoncpp ${PN}-libutils ${PN}-notification-ans"
RDEPENDS:${PN}-timeservice += "${PN}-notification-ces ${PN}-hilog ${PN}-ipc ${PN}-safwk ${PN}-samgr ${PN}-dmsfwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-timeservice"

PACKAGES =+ "${PN}-timeservice-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "timeservice"
FILES:${PN}-timeservice-ptest = "${libdir}/${BPN}-timeservice/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-timeservice/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-timeservice/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/time_native/time_service ${D}${libdir}/${BPN}-timeservice/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/time_native
}
RDEPENDS:${PN}-timeservice-ptest += "musl libcxx"
RDEPENDS:${PN}-timeservice-ptest += "${PN}-timeservice ${PN}-dmsfwk ${PN}-libutils ${PN}-hilog ${PN}-ipc"
RDEPENDS:${PN}-ptest += "${PN}-timeservice-ptest"

# //base/hiviewdfx/hisysevent
PACKAGES =+ "${PN}-hisysevent"
FILES:${PN}-hisysevent = "${libdir}/libhisysevent*${SOLIBS}"
RDEPENDS:${PN}-hisysevent += "musl libcxx"
RDEPENDS:${PN}-hisysevent += "${PN}-libutilsecurec ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-hisysevent"

PACKAGES =+ "${PN}-hisysevent-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "hisysevent"
FILES:${PN}-hisysevent-ptest = "${libdir}/${BPN}-hisysevent/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hisysevent/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hisysevent/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/hisysevent_native/hisysevent_native ${D}${libdir}/${BPN}-hisysevent/ptest/moduletest
    rmdir ${D}${PTEST_PATH}/moduletest/hisysevent_native
}
RDEPENDS:${PN}-hisysevent-ptest += "musl libcxx"
RDEPENDS:${PN}-hisysevent-ptest += "${PN}-hisysevent ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-hisysevent-ptest"

# //base/powermgr/power_manager
PACKAGES =+ "${PN}-powermgr"
FILES:${PN}-powermgr = " \
    ${libdir}/libpowermgr*${SOLIBS} \
    ${libdir}/module/libpower*${SOLIBS} \
    ${libdir}/module/librunninglock*${SOLIBS} \
"
RDEPENDS:${PN}-powermgr += "musl libcxx"
RDEPENDS:${PN}-powermgr += "${PN}-libutils ${PN}-hilog ${PN}-ipc ${PN}-samgr ${PN}-syspara ${PN}-aafwk ${PN}-ace-napi"
RDEPENDS:${PN}-powermgr += "${PN}-appexecfwk ${PN}-notification-ces ${PN}-safwk ${PN}-hisysevent ${PN}-power-displaymgr"
RDEPENDS:${PN} += "${PN}-powermgr"

PACKAGES =+ "${PN}-powermgr-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "powermgr"
FILES:${PN}-powermgr-ptest = "${libdir}/${BPN}-powermgr/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-powermgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-powermgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/power_manager_native/powermgr_native ${D}${libdir}/${BPN}-powermgr/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/power_manager_native
}
RDEPENDS:${PN}-powermgr-ptest += "musl libcxx"
RDEPENDS:${PN}-powermgr-ptest += "${PN}-powermgr ${PN}-libutils ${PN}-ipc ${PN}-hilog ${PN}-samgr ${PN}-power-displaymgr ${PN}-syspara"
RDEPENDS:${PN}-ptest += "${PN}-powermgr-ptest"

# //base/powermgr/battery_manager
PACKAGES =+ "${PN}-power-batterymgr"
FILES:${PN}-power-batterymgr = " \
    ${libdir}/libbattery*${SOLIBS} \
    ${libdir}/module/libbatteryinfo*${SOLIBS} \
"
RDEPENDS:${PN}-power-batterymgr += "musl libcxx"
RDEPENDS:${PN}-power-batterymgr += "${PN}-appexecfwk ${PN}-libutils ${PN}-hilog ${PN}-ipc ${PN}-uhdf2"
RDEPENDS:${PN}-power-batterymgr += "${PN}-aafwk ${PN}-notification-ces ${PN}-safwk ${PN}-samgr ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-power-batterymgr"

# //base/powermgr/display_manager
PACKAGES =+ "${PN}-power-displaymgr"
FILES:${PN}-power-displaymgr = " \
    ${libdir}/libdisplaymgr*${SOLIBS} \
    ${libdir}/module/libbrightness*${SOLIBS} \
"
RDEPENDS:${PN}-power-displaymgr += "musl libcxx"
RDEPENDS:${PN}-power-displaymgr += "${PN}-libutils ${PN}-hilog ${PN}-ipc ${PN}-samgr ${PN}-safwk ${PN}-peripheral-display ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-power-displaymgr"

PACKAGES =+ "${PN}-power-displaymgr-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "power-displaymgr"
FILES:${PN}-power-displaymgr-ptest = "${libdir}/${BPN}-power-displaymgr/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-power-displaymgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-power-displaymgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/display_manager_native/displaymgr_native ${D}${libdir}/${BPN}-power-displaymgr/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/display_manager_native
}
RDEPENDS:${PN}-power-displaymgr-ptest += "musl libcxx libcrypto libssl"
RDEPENDS:${PN}-power-displaymgr-ptest += "${PN}-power-displaymgr ${PN}-libutils ${PN}-samgr"
RDEPENDS:${PN}-ptest += "${PN}-power-displaymgr-ptest"

# //foundation/ace/ace_engine
PACKAGES =+ "${PN}-ace-engine"
FILES:${PN}-ace-engine = " \
    ${libdir}/libace.z*${SOLIBS} \
    ${libdir}/libace_engine*${SOLIBS} \
    ${libdir}/libintl_qjs*${SOLIBS} \
    ${libdir}/module/libgrid*${SOLIBS} \
    ${libdir}/module/libprompt*${SOLIBS} \
    ${libdir}/module/libconfiguration*${SOLIBS} \
    ${libdir}/module/libdevice*${SOLIBS} \
    ${libdir}/module/libmediaquery*${SOLIBS} \
    ${libdir}/module/librouter*${SOLIBS} \
"
RDEPENDS:${PN}-ace-engine += "musl libcxx libcrypto libssl"
RDEPENDS:${PN}-ace-engine += "${PN}-ace-napi ${PN}-dmsfwk ${PN}-ipc ${PN}-libutils ${PN}-appexecfwk ${PN}-appdatamgr"
RDEPENDS:${PN}-ace-engine += "${PN}-thirdparty-icu ${PN}-resmgr ${PN}-aafwk ${PN}-multimodalinput ${PN}-syspara ${PN}-hisysevent"
RDEPENDS:${PN}-ace-engine += "${PN}-ark-runtime-core ${PN}-hilog ${PN}-js-worker ${PN}-i18n ${PN}-graphic ${PN}-bytrace"
RDEPENDS:${PN}-ace-engine += "${PN}-ark-js-runtime ${PN}-inputmethod ${PN}-multimedia-media ${PN}-multimedia-camera"
RDEPENDS:${PN} += "${PN}-ace-engine"

PACKAGES =+ "${PN}-ace-engine-ptest"
# Test doesn't produce any output
OPENHARMONY_PTEST_IS_BROKEN += "ace-engine"
FILES:${PN}-ace-engine-ptest = "${libdir}/${BPN}-ace-engine/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-ace-engine/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-ace-engine/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/ace_engine_standard ${D}${libdir}/${BPN}-ace-engine/ptest/unittest
}
RDEPENDS:${PN}-ace-engine-ptest += "musl libcxx libcrypto libssl"
RDEPENDS:${PN}-ace-engine-ptest += "${PN}-ace-engine ${PN}-libutils ${PN}-multimodalinput ${PN}-appexecfwk ${PN}-ipc ${PN}-resmgr ${PN}-hilog"
RDEPENDS:${PN}-ace-engine-ptest += "${PN}-aafwk ${PN}-dmsfwk ${PN}-graphic ${PN}-appdatamgr ${PN}-bytrace ${PN}-syspara ${PN}-hisysevent"
RDEPENDS:${PN}-ace-engine-ptest += "${PN}-multimedia-camera ${PN}-thirdparty-icu ${PN}-ace-napi ${PN}-inputmethod ${PN}-multimedia-media"
RDEPENDS:${PN}-ptest += "${PN}-ace-engine-ptest"

# //foundation/ace/napi
PACKAGES =+ "${PN}-ace-napi"
FILES:${PN}-ace-napi = "${libdir}/libace_napi*${SOLIBS}"
RDEPENDS:${PN}-ace-napi += "musl libcxx"
RDEPENDS:${PN}-ace-napi += "${PN}-ark-js-runtime ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-ace-napi"

# //base/miscservices/inputmethod
PACKAGES =+ "${PN}-inputmethod"
SYSTEMD_PACKAGES += "${PN}-inputmethod"
SYSTEMD_SERVICE:${PN}-inputmethod = "inputmethod.service"
SRC_URI += "file://inputmethod.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/inputmethod.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/inputmethodservice.cfg
}
FILES:${PN}-inputmethod = " \
    ${libdir}/libinputmethod_*${SOLIBS} \
    ${libdir}/module/libinputmethodengine*${SOLIBS} \
    ${libdir}/openharmony/profile/inputmethod_service.xml \
"
RDEPENDS:${PN}-inputmethod += "musl libcxx"
RDEPENDS:${PN}-inputmethod += "${PN}-resmgr ${PN}-ipc ${PN}-samgr ${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-inputmethod += "${PN}-appexecfwk ${PN}-aafwk ${PN}-safwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-inputmethod"

PACKAGES =+ "${PN}-inputmethod-ptest"
# Test unittest/InputMethodControllerTest segfaults
OPENHARMONY_PTEST_IS_BROKEN += "inputmethod"
FILES:${PN}-inputmethod-ptest = "${libdir}/${BPN}-inputmethod/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-inputmethod/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-inputmethod/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/inputmethod_native/inputmethod_service ${D}${libdir}/${BPN}-inputmethod/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/inputmethod_native
}
RDEPENDS:${PN}-inputmethod-ptest += "musl libcxx"
RDEPENDS:${PN}-inputmethod-ptest += "${PN}-inputmethod ${PN}-ipc ${PN}-libutils ${PN}-hilog ${PN}-samgr"
RDEPENDS:${PN}-ptest += "${PN}-inputmethod-ptest"

# //foundation/graphic/standard
PACKAGES =+ "${PN}-graphic"
FILES:${PN}-graphic = " \
    ${libdir}/libwm*${SOLIBS} \
    ${libdir}/libsurface*${SOLIBS} \
    ${libdir}/libvsync*${SOLIBS} \
    ${libdir}/libsemaphore*${SOLIBS} \
    ${libdir}/module/libdisplay*${SOLIBS} \
    ${libdir}/module/libwindow*${SOLIBS} \
    ${bindir}/bootanimation \
    ${systemd_unitdir}/weston.service \
"
SYSTEMD_PACKAGES += "${PN}-graphic"
SYSTEMD_SERVICE:${PN}-graphic = "weston.service"
SRC_URI += "file://weston.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/weston.service ${D}${systemd_unitdir}/system/
}
RDEPENDS:${PN}-graphic += "musl libcxx"
RDEPENDS:${PN}-graphic += "${PN}-multimodalinput ${PN}-hilog ${PN}-libutils ${PN}-thirdparty-weston ${PN}-thirdparty-wayland ${PN}-thirdparty-libffi ${PN}-thirdparty-libinput"
RDEPENDS:${PN}-graphic += "${PN}-graphic ${PN}-multimedia-media ${PN}-ipc ${PN}-display-gralloc ${PN}-samgr ${PN}-thirdparty-libdrm ${PN}-thirdparty-libevdev"
RDEPENDS:${PN}-graphic += "${PN}-appexecfwk ${PN}-distributeddatamgr ${PN}-dmsfwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-graphic"

PACKAGES =+ "${PN}-graphic-ptest"
# Test stalls with message: "binder: 3751:3751 transaction failed"
OPENHARMONY_PTEST_IS_BROKEN += "graphic"
FILES:${PN}-graphic-ptest = "${libdir}/${BPN}-graphic/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-graphic/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-graphic/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/graphic_standard ${D}${libdir}/${BPN}-graphic/ptest/unittest
    mv ${D}${PTEST_PATH}/systemtest/graphic_standard ${D}${libdir}/${BPN}-graphic/ptest/systemtest
}
RDEPENDS:${PN}-graphic-ptest += "musl libcxx"
RDEPENDS:${PN}-graphic-ptest += "${PN}-graphic ${PN}-samgr ${PN}-ipc ${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-graphic-ptest"

# //developtools/bytrace_standard
PACKAGES =+ "${PN}-bytrace"
FILES:${PN}-bytrace = " \
    ${bindir}/bytrace \
    ${libdir}/libbytrace_core*${SOLIBS} \
    ${libdir}/module/libbytrace*${SOLIBS} \
"
RDEPENDS:${PN}-bytrace += "musl libcxx"
RDEPENDS:${PN}-bytrace += "${PN}-syspara ${PN}-libutils ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-bytrace"

PACKAGES =+ "${PN}-bytrace-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "bytrace"
FILES:${PN}-bytrace-ptest = "${libdir}/${BPN}-bytrace/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-bytrace/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-bytrace/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/bytrace_standard/bytrace ${D}${libdir}/${BPN}-bytrace/ptest/moduletest
    rmdir ${D}${PTEST_PATH}/moduletest/bytrace_standard
}
RDEPENDS:${PN}-bytrace-ptest += "musl libcxx"
RDEPENDS:${PN}-bytrace-ptest += "${PN}-bytrace ${PN}-syspara ${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-bytrace-ptest"

# //foundation/multimedia/media_standard
PACKAGES =+ "${PN}-multimedia-media"
FILES:${PN}-multimedia-media = " \
    ${libdir}/libmedia_client*${SOLIBS} \
    ${libdir}/libmedia_local*${SOLIBS} \
    ${libdir}/libmedia_service*${SOLIBS} \
    ${libdir}/libvideodisplaymanager*${SOLIBS} \
    ${libdir}/media/libmedia_engine_gst*${SOLIBS} \
    ${libdir}/media/plugins/libgst_audio_server_sink*${SOLIBS} \
    ${libdir}/media/plugins/libgst_audio_capture_src*${SOLIBS} \
    ${libdir}/media/plugins/libgst_surface_video_src*${SOLIBS} \
    ${libdir}/module/multimedia/libmedia*${SOLIBS} \
    ${libdir}/openharmony/profile/media_service.xml \
"
SYSTEMD_PACKAGES += "${PN}-multimedia-media"
SYSTEMD_SERVICE:${PN}-multimedia-media = "media.service"
SRC_URI += "file://media.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/media.service ${D}${systemd_unitdir}/system/
}
RDEPENDS:${PN}-multimedia-media += "musl libcxx"
RDEPENDS:${PN}-multimedia-media += "${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-safwk ${PN}-graphic ${PN}-peripheral-display"
RDEPENDS:${PN}-multimedia-media += "${PN}-multimedia-audio ${PN}-thirdparty-gstreamer ${PN}-thirdparty-glib ${PN}-syspara ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-multimedia-media"

# //foundation/multimedia/audio_standard
PACKAGES =+ "${PN}-multimedia-audio"
FILES:${PN}-multimedia-audio = " \
    ${bindir}/pacat \
    ${bindir}/pacmd \
    ${bindir}/pactl \
    ${libdir}/libaudio_capturer*${SOLIBS} \
    ${libdir}/libaudio_client*${SOLIBS} \
    ${libdir}/libaudio_policy_*${SOLIBS} \
    ${libdir}/libaudio_renderer*${SOLIBS} \
    ${libdir}/libaudio_service*${SOLIBS} \
    ${libdir}/libaudio_capturer_source*${SOLIBS} \
    ${libdir}/libsndfile*${SOLIBS} \
    ${libdir}/libpulse*${SOLIBS} \
    ${libdir}/libcli*${SOLIBS} \
    ${libdir}/libprotocol-cli*${SOLIBS} \
    ${libdir}/libprotocol-native*${SOLIBS} \
    ${libdir}/libmodule-hdi-*${SOLIBS} \
    ${libdir}/libmodule-native-protocol-*${SOLIBS} \
    ${libdir}/libmodule-cli-protocol-unix*${SOLIBS} \
    ${libdir}/libmodule-pipe-*${SOLIBS} \
    ${libdir}/libmodule-suspend-on-idle*${SOLIBS} \
    ${libdir}/module/multimedia/libaudio*${SOLIBS} \
    ${libdir}/openharmony/profile/audio_policy.xml \
    ${libdir}/openharmony/profile/pulseaudio.xml \
"
RDEPENDS:${PN}-multimedia-audio += "musl libcxx"
RDEPENDS:${PN}-multimedia-audio += "${PN}-thirdparty-gstreamer ${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-distributeddatamgr"
RDEPENDS:${PN}-multimedia-audio += "${PN}-thirdparty-libxml2 ${PN}-thirdparty-glib ${PN}-safwk ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-multimedia-audio"

PACKAGES =+ "${PN}-audio-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "audio"
FILES:${PN}-audio-ptest = "${libdir}/${BPN}-audio/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-audio/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-audio/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/audio_standard ${D}${libdir}/${BPN}-audio/ptest/moduletest
}
RDEPENDS:${PN}-audio-ptest += "musl libcxx"
RDEPENDS:${PN}-audio-ptest += "${PN}-multimedia-audio ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-audio-ptest"

# //foundation/multimedia/camera_standard
PACKAGES =+ "${PN}-multimedia-camera"
FILES:${PN}-multimedia-camera = " \
    ${libdir}/libcamera_service*${SOLIBS} \
    ${libdir}/libcamera_framework*${SOLIBS} \
    ${libdir}/libmetada*${SOLIBS} \
    ${libdir}/module/multimedia/libcamera_napi*${SOLIBS} \
    ${libdir}/openharmony/profile/camera_service.xml \
"
RDEPENDS:${PN}-multimedia-camera += "musl libcxx"
RDEPENDS:${PN}-multimedia-camera += "${PN}-peripheral-camera ${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-safwk ${PN}-graphic ${PN}-samgr ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-multimedia-camera"

PACKAGES =+ "${PN}-camera-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "camera"
FILES:${PN}-camera-ptest = "${libdir}/${BPN}-camera/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-camera/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-camera/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/camera_standard ${D}${libdir}/${BPN}-camera/ptest/moduletest
}
RDEPENDS:${PN}-camera-ptest += "musl libcxx"
RDEPENDS:${PN}-camera-ptest += "${PN}-multimedia-camera ${PN}-hilog ${PN}-ipc ${PN}-graphic ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-camera-ptest"

# //foundation/multimedia/image_standard
PACKAGES =+ "${PN}-multimedia-image"
FILES:${PN}-multimedia-image = " \
    ${libdir}/libimage*${SOLIBS} \
    ${libdir}/libimageformatagent*${SOLIBS} \
    ${libdir}/libpixelconvertadapter*${SOLIBS} \
    ${libdir}/libpluginmanager*${SOLIBS} \
    ${libdir}/libjpegplugin*${SOLIBS} \
    ${libdir}/libgifplugin*${SOLIBS} \
    ${libdir}/libwebpplugin*${SOLIBS} \
    ${libdir}/libpngplugin*${SOLIBS} \
    ${libdir}/module/multimedia/libimage*${SOLIBS} \
"
RDEPENDS:${PN}-multimedia-image += "musl libcxx"
RDEPENDS:${PN}-multimedia-image += "${PN}-hilog ${PN}-hilog ${PN}-libutils ${PN}-bytrace ${PN}-ipc"
RDEPENDS:${PN} += "${PN}-multimedia-image"

# //device/hihope/hardware/display
PACKAGES =+ "${PN}-display-gralloc"
FILES:${PN}-display-gralloc = "${libdir}/libdisplay_gralloc*${SOLIBS}"
RDEPENDS:${PN}-display-gralloc += "musl libcxx"
RDEPENDS:${PN}-display-gralloc += "${PN}-thirdparty-libdrm ${PN}-libutils ${PN}-hilog ${PN}-thirdparty-libffi"
RDEPENDS:${PN} += "${PN}-display-gralloc"

# //base/global/i18n_standard
PACKAGES =+ "${PN}-i18n"
FILES:${PN}-i18n = " \
    ${libdir}/libintl_util*${SOLIBS} \
    ${libdir}/libzone_util*${SOLIBS} \
    ${libdir}/module/libi18n*${SOLIBS} \
    ${libdir}/module/libintl*${SOLIBS} \
"
RDEPENDS:${PN}-i18n += "musl libcxx"
RDEPENDS:${PN}-i18n += "${PN}-syspara ${PN}-thirdparty-icu ${PN}-thirdparty-libxml2 ${PN}-libutils ${PN}-telephony-core"
RDEPENDS:${PN}-i18n += "${PN}-thirdparty-libphonenumber ${PN}-hilog ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-i18n"

PACKAGES =+ "${PN}-i18n-ptest"
OPENHARMONY_PTEST_IS_BROKEN += "i18n"
FILES:${PN}-i18n-ptest = "${libdir}/${BPN}-i18n/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-i18n/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-i18n/ptest/run-ptest
    # This folder also contains unitest for libphonenumber
    mv ${D}${PTEST_PATH}/unittest/i18n_standard ${D}${libdir}/${BPN}-i18n/ptest/unittest
}
RDEPENDS:${PN}-i18n-ptest += "musl libcxx"
RDEPENDS:${PN}-i18n-ptest += "${PN}-i18n ${PN}-syspara ${PN}-thirdparty-icu ${PN}-thirdparty-libphonenumber ${PN}-thirdparty-protobuf"
RDEPENDS:${PN}-ptest += "${PN}-i18n-ptest"

# //base/telephony/core_service
PACKAGES =+ "${PN}-telephony-core"
FILES:${PN}-telephony-core = " \
    ${libdir}/libtel_core_*${SOLIBS} \
    ${libdir}/libtelephony_common*${SOLIBS} \
    ${libdir}/libsim*${SOLIBS} \
    ${libdir}/module/telephony/*${SOLIBS} \
    ${libdir}/openharmony/profile/telephony.xml \
"
RDEPENDS:${PN}-telephony-core += "musl libcxx"
RDEPENDS:${PN}-telephony-core += "${PN}-libutils ${PN}-hilog ${PN}-ipc ${PN}-samgr ${PN}-aafwk ${PN}-uhdf2 ${PN}-ace-napi"
RDEPENDS:${PN}-telephony-core += "${PN}-appexecfwk ${PN}-notification-ces ${PN}-safwk ${PN}-appdatamgr ${PN}-telephony-ril-adapter"
RDEPENDS:${PN} += "${PN}-telephony-core"

PACKAGES =+ "${PN}-telephony-core-ptest"
# Test stalls with message: "binder: 4296:4296 transaction failed"
OPENHARMONY_PTEST_IS_BROKEN += "telephony-core"
FILES:${PN}-telephony-core-ptest = "${libdir}/${BPN}-telephony-core/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-telephony-core/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-telephony-core/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/core_service/tel_core_service_gtest ${D}${libdir}/${BPN}-telephony-core/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/core_service
}
RDEPENDS:${PN}-telephony-core-ptest += "musl libcxx"
RDEPENDS:${PN}-telephony-core-ptest += "${PN}-telephony-core ${PN}-libutils ${PN}-ipc ${PN}-hilog ${PN}-samgr ${PN}-appexecfwk"
RDEPENDS:${PN}-ptest += "${PN}-telephony-core-ptest"

# //base/telephony/ril_adapter
PACKAGES =+ "${PN}-telephony-ril-adapter"
FILES:${PN}-telephony-ril-adapter = " \
    ${libdir}/libhril*${SOLIBS} \
    ${libdir}/libril_vendor*${SOLIBS} \
"
RDEPENDS:${PN}-telephony-ril-adapter += "musl libcxx"
RDEPENDS:${PN}-telephony-ril-adapter += "${PN}-uhdf2 ${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-faultlogger"
RDEPENDS:${PN} += "${PN}-telephony-ril-adapter"

PACKAGES =+ "${PN}-telephony-ril-adapter-ptest"
# Test unittest/tel_ril_adapter_gtest segfaults
OPENHARMONY_PTEST_IS_BROKEN += "telephony-ril-adapter"
FILES:${PN}-telephony-ril-adapter-ptest = "${libdir}/${BPN}-telephony-ril-adapter/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-telephony-ril-adapter/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-telephony-ril-adapter/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/ril_adapter/tel_ril_adapter_gtest ${D}${libdir}/${BPN}-telephony-ril-adapter/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/ril_adapter
}
RDEPENDS:${PN}-telephony-ril-adapter-ptest += "musl libcxx"
RDEPENDS:${PN}-telephony-ril-adapter-ptest += "${PN}-telephony-ril-adapter ${PN}-libutils ${PN}-uhdf2 ${PN}-appexecfwk ${PN}-ipc ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-telephony-ril-adapter-ptest"

# //base/hiviewdfx/faultloggerd
PACKAGES =+ "${PN}-faultlogger"
SYSTEMD_PACKAGES += "${PN}-faultlogger"
SYSTEMD_SERVICE:${PN}-faultlogger = "faultloggerd.service"
SRC_URI += "file://faultloggerd.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/faultloggerd.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/faultloggerd32.cfg
}
FILES:${PN}-faultlogger = " \
    ${bindir}/faultloggerd \
    ${bindir}/processdump \
    ${libdir}/libfaultloggerd*${SOLIBS} \
    ${libdir}/libdfx_signalhandler*${SOLIBS} \
"
RDEPENDS:${PN}-faultlogger += "musl libcxx"
RDEPENDS:${PN}-faultlogger += "${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-faultlogger"

# faultlogger-ptest
PACKAGES =+ "${PN}-faultlogger-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-faultlogger/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-faultlogger/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/hiviewdfx/faultlogger ${D}${libdir}/${BPN}-faultlogger/ptest/unittest
}
FILES:${PN}-faultlogger-ptest = "${libdir}/${BPN}-faultlogger/ptest"
RDEPENDS:${PN}-faultlogger-ptest += "musl libcxx"
RDEPENDS:${PN}-faultlogger-ptest += "${PN}-faultlogger ${PN}-libutils ${PN}-hilog ${PN}-syspara ${PN}-ipc ${PN}-samgr ${PN}-appexecfwk ${PN}-safwk ${PN}-hiview"
RDEPENDS:${PN}-ptest += "${PN}-faultlogger-ptest"

# thirdparty-iowow
PACKAGES =+ "${PN}-thirdparty-iowow"
FILES:${PN}-thirdparty-iowow = "${libdir}/libiowow*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-iowow += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-iowow"

# thirdparty-ejdb
PACKAGES =+ "${PN}-thirdparty-ejdb"
FILES:${PN}-thirdparty-ejdb = "${libdir}/libejdb*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-ejdb += "musl libcxx"
RDEPENDS:${PN}-thirdparty-ejdb += "${PN}-thirdparty-iowow"
RDEPENDS:${PN} += "${PN}-thirdparty-ejdb"

# //base/hiviewdfx/hiview
PACKAGES =+ "${PN}-hiview"
FILES:${PN}-hiview = " \
    ${bindir}/hiview \
    ${libdir}/libhiviewbase*${SOLIBS} \
"
RDEPENDS:${PN}-hiview += "musl libcxx"
RDEPENDS:${PN}-hiview += " \
    ${PN}-libutils \
    ${PN}-hilog \
    ${PN}-syspara \
    ${PN}-ipc \
    ${PN}-safwk \
    ${PN}-samgr \
    ${PN}-appexecfwk \
    ${PN}-thirdparty-iowow \
    ${PN}-thirdparty-ejdb \
"
RDEPENDS:${PN} += "${PN}-hiview"

# hiview-ptest
PACKAGES =+ "${PN}-hiview-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hiview/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hiview/ptest/run-ptest
    mv ${D}${PTEST_PATH}/moduletest/hiviewdfx/hiview ${D}${libdir}/${BPN}-hiview/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/hiview ${D}${libdir}/${BPN}-hiview/ptest/unittest
    mv ${D}${PTEST_PATH}/unittest/hiview_L2/* ${D}${libdir}/${BPN}-hiview/ptest/unittest/
}
FILES:${PN}-hiview-ptest = "${libdir}/${BPN}-hiview/ptest"
RDEPENDS:${PN}-hiview-ptest += "musl libcxx"
RDEPENDS:${PN}-hiview-ptest += "${PN}-hiview ${PN}-libutils ${PN}-hilog ${PN}-syspara ${PN}-thirdparty-iowow ${PN}-thirdparty-ejdb"
RDEPENDS:${PN}-ptest += "${PN}-hiview-ptest"

# //base/hiviewdfx/hicollie
PACKAGES =+ "${PN}-hicollie"
FILES:${PN}-hicollie = "${libdir}/libhicollie*${SOLIBS}"
RDEPENDS:${PN}-hicollie += "musl libcxx"
RDEPENDS:${PN}-hicollie += "${PN}-libutils ${PN}-hilog ${PN}-hisysevent"
RDEPENDS:${PN} += "${PN}-hicollie"

# hicollie-ptest
PACKAGES =+ "${PN}-hicollie-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hicollie/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hicollie/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/hiviewdfx/hicollie ${D}${libdir}/${BPN}-hicollie/ptest/unittest
}
FILES:${PN}-hicollie-ptest = "${libdir}/${BPN}-hicollie/ptest"
RDEPENDS:${PN}-hicollie-ptest += "musl libcxx"
RDEPENDS:${PN}-hicollie-ptest += "${PN}-hicollie ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-hicollie-ptest"

# //base/hiviewdfx/hitrace
PACKAGES =+ "${PN}-hitrace"
FILES:${PN}-hitrace = "${libdir}/libhitrace*${SOLIBS}"
RDEPENDS:${PN}-hitrace += "musl libcxx"
RDEPENDS:${PN}-hitrace += "${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-hitrace"

# hitrace-ptest
PACKAGES =+ "${PN}-hitrace-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hitrace/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hitrace/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/hiviewdfx/hitrace ${D}${libdir}/${BPN}-hitrace/ptest/unittest
}
FILES:${PN}-hitrace-ptest = "${libdir}/${BPN}-hitrace/ptest"
RDEPENDS:${PN}-hitrace-ptest += "musl libcxx"
RDEPENDS:${PN}-hitrace-ptest += "${PN}-hitrace ${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-ptest += "${PN}-hitrace-ptest"

# //ark/runtime_core
PACKAGES =+ "${PN}-ark-runtime-core"
FILES:${PN}-ark-runtime-core = " \
    ${libdir}/libarkbase*${SOLIBS} \
    ${libdir}/libarkfile*${SOLIBS} \
    ${libdir}/libarkziparchive*${SOLIBS} \
"
RDEPENDS:${PN}-ark-runtime-core += "musl libcxx"
RDEPENDS:${PN}-ark-runtime-core += "${PN}-libutilsecurec ${PN}-thirdparty-icu"
RDEPENDS:${PN} += "${PN}-ark-runtime-core"

# //ark/js_runtime
PACKAGES =+ "${PN}-ark-js-runtime"
FILES:${PN}-ark-js-runtime = " \
    ${libdir}/libark_jsruntime*${SOLIBS} \
    ${libdir}/ark/libark_ecma_debugger*${SOLIBS} \
"
RDEPENDS:${PN}-ark-js-runtime += "musl libcxx"
RDEPENDS:${PN}-ark-js-runtime += "${PN}-ark-runtime-core ${PN}-libutilsecurec ${PN}-thirdparty-icu"
RDEPENDS:${PN} += "${PN}-ark-js-runtime"

PACKAGES =+ "${PN}-ark-ptest"
# First tests pass but then stalls on HProfTest.GenerateFileForManualCheck
OPENHARMONY_PTEST_IS_BROKEN += "ark"
FILES:${PN}-ark-ptest = "${libdir}/${BPN}-ark/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-ark/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-ark/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/ark ${D}${libdir}/${BPN}-ark/ptest/unittest
}
RDEPENDS:${PN}-ark-ptest += "musl libcxx"
RDEPENDS:${PN}-ark-ptest += "${PN}-ark-runtime-core ${PN}-thirdparty-icu"
RDEPENDS:${PN}-ptest += "${PN}-ark-ptest"

# //base/compileruntime/js_worker_module
PACKAGES =+ "${PN}-js-worker"
FILES:${PN}-js-worker = " \
    ${libdir}/libworker_init*${SOLIBS} \
    ${libdir}/module/libworker*${SOLIBS} \
"
RDEPENDS:${PN}-js-worker += "musl libcxx"
RDEPENDS:${PN}-js-worker += "${PN}-hilog ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-js-worker"

# //foundation/distributedhardware/devicemanager
PACKAGES =+ "${PN}-distributedhardware-devicemanager"
FILES:${PN}-distributedhardware-devicemanager = " \
    ${libdir}/libdevicemanager*${SOLIBS} \
    ${libdir}/module/distributedhardware/libdevicemanager*${SOLIBS} \
"
RDEPENDS:${PN}-distributedhardware-devicemanager += "musl libcxx"
RDEPENDS:${PN}-distributedhardware-devicemanager += "${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-security-deviceauth ${PN}-aafwk ${PN}-appexecfwk"
RDEPENDS:${PN}-distributedhardware-devicemanager += "${PN}-dsoftbus ${PN}-safwk ${PN}-syspara ${PN}-hilog ${PN}-ace-napi"
RDEPENDS:${PN} += "${PN}-distributedhardware-devicemanager"

PACKAGES =+ "${PN}-distributedhardware-devicemanager-ptest"
FILES:${PN}-distributedhardware-devicemanager-ptest = "${libdir}/${BPN}-distributedhardware-devicemanager/ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-distributedhardware-devicemanager/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-distributedhardware-devicemanager/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/deviceManager_stander ${D}${libdir}/${BPN}-distributedhardware-devicemanager/ptest/unittest
}
RDEPENDS:${PN}-distributedhardware-devicemanager-ptest += "musl libcxx"
RDEPENDS:${PN}-distributedhardware-devicemanager-ptest += "${PN}-distributedhardware-devicemanager ${PN}-libutils"
RDEPENDS:${PN}-ptest += "${PN}-distributedhardware-devicemanager-ptest"

# //developtools/hdc_standard
PACKAGES =+ "${PN}-hdc"
FILES:${PN}-hdc = " \
    ${bindir}/hdcd \
    ${systemd_unitdir}/hdcd.service \
"
SYSTEMD_PACKAGES += "${PN}-hdc"
SYSTEMD_SERVICE:${PN}-hdc = "hdcd.service"
SRC_URI += "file://hdcd.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/hdcd.service ${D}${systemd_unitdir}/system/
}
RDEPENDS:${PN}-hdc += "musl libcxx"
RDEPENDS:${PN}-hdc += "${PN}-libutils ${PN}-syspara libcrypto"
RDEPENDS:${PN} += "${PN}-hdc"

PACKAGES =+ "${PN}-param-service"
SYSTEMD_PACKAGES += "${PN}-param-service"
SYSTEMD_SERVICE:${PN}-param-service = "param.service"
SRC_URI += "file://param.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/param.service ${D}${systemd_unitdir}/system/
}
FILES:${PN}-param-service = " \
    ${bindir}/getparam \
    ${bindir}/setparam \
    ${bindir}/param_service \
"
RDEPENDS:${PN}-param-service += "musl libcxx"
RDEPENDS:${PN} += "${PN}-param-service"

# Third Party Components (//third_party/*)

PACKAGES =+ "${PN}-thirdparty-jsoncpp"
FILES:${PN}-thirdparty-jsoncpp = "${libdir}/libjsoncpp*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-jsoncpp += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-jsoncpp"

PACKAGES =+ "${PN}-thirdparty-mtdev"
FILES:${PN}-thirdparty-mtdev = "${libdir}/libmtdev*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-mtdev += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-mtdev"

PACKAGES =+ "${PN}-thirdparty-sqlite"
FILES:${PN}-thirdparty-sqlite = "${libdir}/libsqlite*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-sqlite += "musl libcxx libcrypto ${PN}-libutils"
RDEPENDS:${PN} += "${PN}-thirdparty-sqlite"

PACKAGES =+ "${PN}-thirdparty-libxml2"
FILES:${PN}-thirdparty-libxml2 = "${libdir}/libxml2*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libxml2 += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-libxml2"

PACKAGES =+ "${PN}-thirdparty-icu"
FILES:${PN}-thirdparty-icu = "${libdir}/libhmicu*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-icu += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-icu"

PACKAGES =+ "${PN}-thirdparty-gstreamer"
FILES:${PN}-thirdparty-gstreamer = " \
    ${bindir}/gst-inspect \
    ${bindir}/gst-launch \
    ${libdir}/libgstpbutils*${SOLIBS} \
    ${libdir}/libgsttag*${SOLIBS} \
    ${libdir}/libgstfft*${SOLIBS} \
    ${libdir}/libgstvideo*${SOLIBS} \
    ${libdir}/libgstaudio*${SOLIBS} \
    ${libdir}/libgstriff*${SOLIBS} \
    ${libdir}/libgstrtp*${SOLIBS} \
    ${libdir}/libgstreamer*${SOLIBS} \
    ${libdir}/libgstbase*${SOLIBS} \
    ${libdir}/libgstplayer*${SOLIBS} \
    ${libdir}/media/plugins/libgstplayback*${SOLIBS} \
    ${libdir}/media/plugins/libgstvideoconvert*${SOLIBS} \
    ${libdir}/media/plugins/libgstvideoscale*${SOLIBS} \
    ${libdir}/media/plugins/libgstaudiomixer*${SOLIBS} \
    ${libdir}/media/plugins/libgstaudioparsers*${SOLIBS} \
    ${libdir}/media/plugins/libgstaudiorate*${SOLIBS} \
    ${libdir}/media/plugins/libgstaudiofx*${SOLIBS} \
    ${libdir}/media/plugins/libgstaudioconvert*${SOLIBS} \
    ${libdir}/media/plugins/libgstaudioresample*${SOLIBS} \
    ${libdir}/media/plugins/libgsttypefindfunctions*${SOLIBS} \
    ${libdir}/media/plugins/libgstsubparse*${SOLIBS} \
    ${libdir}/media/plugins/libgstrawpars*${SOLIBS} \
    ${libdir}/media/plugins/libgstapp*${SOLIBS} \
    ${libdir}/media/plugins/libgstlibav*${SOLIBS} \
    ${libdir}/media/plugins/libgstautodetect*${SOLIBS} \
    ${libdir}/media/plugins/libgstisomp4*${SOLIBS} \
    ${libdir}/media/plugins/libgstwavparse*${SOLIBS} \
    ${libdir}/media/plugins/libgstmultifile*${SOLIBS} \
    ${libdir}/media/plugins/libgstcoreelements*${SOLIBS} \
    ${libdir}/media/plugins/libgstcoretracers*${SOLIBS} \
"
RDEPENDS:${PN}-thirdparty-gstreamer += "musl libcxx"
RDEPENDS:${PN}-thirdparty-gstreamer += "${PN}-thirdparty-glib ${PN}-thirdparty-ffmpeg"
RDEPENDS:${PN} += "${PN}-thirdparty-gstreamer"

PACKAGES =+ "${PN}-thirdparty-glib"
FILES:${PN}-thirdparty-glib = " \
    ${libdir}/libglib*${SOLIBS} \
    ${libdir}/libgmodule*${SOLIBS} \
    ${libdir}/libgobject*${SOLIBS} \
"
RDEPENDS:${PN}-thirdparty-glib += "musl libcxx"
RDEPENDS:${PN}-thirdparty-glib += "${PN}-thirdparty-libffi"
RDEPENDS:${PN} += "${PN}-thirdparty-glib"

PACKAGES =+ "${PN}-thirdparty-ffmpeg"
FILES:${PN}-thirdparty-ffmpeg = "${libdir}/libohosffmpeg*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-ffmpeg += "musl libcxx"
RDEPENDS:${PN}-thirdparty-ffmpeg += ""
RDEPENDS:${PN} += "${PN}-thirdparty-ffmpeg"

PACKAGES =+ "${PN}-thirdparty-pixman"
FILES:${PN}-thirdparty-pixman = "${libdir}/libpixman*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-pixman += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-pixman"

PACKAGES =+ "${PN}-thirdparty-libinput"
FILES:${PN}-thirdparty-libinput = "${libdir}/libinput-third*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libinput += "musl libcxx"
RDEPENDS:${PN}-thirdparty-libinput += "${PN}-thirdparty-libevdev ${PN}-thirdparty-eudev ${PN}-thirdparty-mtdev"
RDEPENDS:${PN} += "${PN}-thirdparty-libinput"

PACKAGES =+ "${PN}-thirdparty-libevdev"
FILES:${PN}-thirdparty-libevdev = "${libdir}/libevdev*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libevdev += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-libevdev"

PACKAGES =+ "${PN}-thirdparty-eudev"
FILES:${PN}-thirdparty-eudev = " \
    ${bindir}/udevd \
    ${libdir}/libudev*${SOLIBS} \
"
RDEPENDS:${PN}-thirdparty-eudev += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-eudev"

PACKAGES =+ "${PN}-thirdparty-libdrm"
FILES:${PN}-thirdparty-libdrm = "${libdir}/libdrm*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libdrm += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-libdrm"

PACKAGES =+ "${PN}-thirdparty-libpng"
FILES:${PN}-thirdparty-libpng = "${libdir}/libpng*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libpng += "musl libcxx"
RDEPENDS:${PN}-thirdparty-libpng += "${PN}-multimedia-image ${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN} += "${PN}-thirdparty-libpng"

PACKAGES =+ "${PN}-thirdparty-libffi"
FILES:${PN}-thirdparty-libffi = "${libdir}/libffi*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libffi += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-libffi"

PACKAGES =+ "${PN}-thirdparty-libphonenumber"
FILES:${PN}-thirdparty-libphonenumber = "${libdir}/libphonenumber_standard*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libphonenumber += "musl libcxx"
RDEPENDS:${PN}-thirdparty-libphonenumber += "${PN}-thirdparty-icu ${PN}-thirdparty-protobuf"
RDEPENDS:${PN} += "${PN}-thirdparty-libphonenumber"

PACKAGES =+ "${PN}-thirdparty-protobuf"
FILES:${PN}-thirdparty-protobuf = "${libdir}/libprotobuf_standard*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-protobuf += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-protobuf"

PACKAGES =+ "${PN}-thirdparty-giflib"
FILES:${PN}-thirdparty-giflib = "${libdir}/libgif*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-giflib += "musl libcxx"
RDEPENDS:${PN}-thirdparty-giflib += "${PN}-libutils ${PN}-hilog ${PN}-multimedia-image"
RDEPENDS:${PN} += "${PN}-thirdparty-giflib"

PACKAGES =+ "${PN}-thirdparty-libcoap"
FILES:${PN}-thirdparty-libcoap = "${libdir}/libcoap*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libcoap += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-libcoap"

PACKAGES =+ "${PN}-thirdparty-wayland"
FILES:${PN}-thirdparty-wayland = " \
    ${bindir}/layer-add-surfaces \
    ${bindir}/LayerManagerControl \
    ${bindir}/simple-weston-client \
    ${libdir}/libilm*${SOLIBS} \
    ${libdir}/libivi*${SOLIBS} \
    ${libdir}/libscreen-info-module*${SOLIBS} \
"
RDEPENDS:${PN}-thirdparty-wayland += "musl libcxx"
RDEPENDS:${PN}-thirdparty-wayland += "${PN}-thirdparty-weston ${PN}-hilog ${PN}-thirdparty-libffi ${PN}-graphic"
RDEPENDS:${PN}-thirdparty-wayland += "${PN}-thirdparty-libevdev ${PN}-thirdparty-libinput ${PN}-thirdparty-libpng"
RDEPENDS:${PN} += "${PN}-thirdparty-wayland"

PACKAGES =+ "${PN}-thirdparty-weston"
FILES:${PN}-thirdparty-weston = " \
    ${bindir}/weston \
    ${libdir}/libweston*${SOLIBS} \
    ${libdir}/drm-backend*${SOLIBS} \
    ${libdir}/libtrace*${SOLIBS} \
    ${libdir}/libivi-shell*${SOLIBS} \
    ${libdir}/openharmony/profile/multimodalinputservice.xml \
"
RDEPENDS:${PN}-thirdparty-weston += "musl libcxx"
RDEPENDS:${PN}-thirdparty-weston += "${PN}-hilog ${PN}-libutils ${PN}-thirdparty-libxml2 ${PN}-thirdparty-libffi ${PN}-thirdparty-libdrm ${PN}-graphic"
RDEPENDS:${PN}-thirdparty-weston += "${PN}-thirdparty-libinput ${PN}-thirdparty-libevdev ${PN}-thirdparty-eudev ${PN}-thirdparty-pixman ${PN}-display-gralloc"
RDEPENDS:${PN} += "${PN}-thirdparty-weston"

# Disable all ptest suites that are know to not work for now. When the x-bit is
# not set, the ptest is visible (using `ptest-runner -l`), but no test cases
# will be run when executing it.
# TODO: Fix all components and tests and remove all of this
do_install_ptest:append() {
    for component in ${OPENHARMONY_PTEST_IS_BROKEN} ; do
        chmod -x ${D}${libdir}/${BPN}-$component/ptest/run-ptest
    done
}

PACKAGES:prepend:df-acts = "${PN}-acts "
do_install:append:df-acts() {
    mkdir -p ${D}${libexecdir}/${PN}/acts
    for d in config testcases ; do
        cp -dR --no-preserve=ownership ${B}/suites/acts/$d ${D}${libexecdir}/${PN}/acts/
    done
}
FILES:${PN}-acts = "${libexecdir}/${PN}/acts"
INSANE_SKIP:${PN}-acts = "file-rdeps"

EXCLUDE_FROM_SHLIBS = "1"

# To avoid excessive diskspace blowup, we are stripping our executables
INSANE_SKIP:${PN} += "already-stripped"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-u 1000 -U -s /bin/sh system"

# system haps
PACKAGES =+ "${PN}-systemhaps"
do_install:append() {
    install -m 777 -d ${D}/system/app
    install -m 666 ${S}/applications/standard/hap/Launcher.hap ${D}/system/app
    install -m 666 ${S}/applications/standard/hap/SystemUI-NavigationBar.hap ${D}/system/app
    install -m 666 ${S}/applications/standard/hap/SystemUI-StatusBar.hap ${D}/system/app
    install -m 666 ${S}/applications/standard/hap/SystemUI-SystemDialog.hap ${D}/system/app
    install -m 666 ${S}/applications/standard/hap/Settings.hap ${D}/system/app
}
FILES:${PN}-systemhaps = " \
    /system/app/* \
"
RDEPENDS:${PN} += "${PN}-systemhaps"
