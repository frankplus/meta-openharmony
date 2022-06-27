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
DEPENDS += "hapsigner-native"
DEPENDS += "packing-tool-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-standard-${OPENHARMONY_VERSION}:"

require ${PN}-sources-${OPENHARMONY_VERSION}.inc
require java-tools.inc
require musl-ldso-paths-sanity-check.inc

SRC_URI += "${@bb.utils.contains('PTEST_ENABLED', '1', 'file://run-ptest', '', d)}"

# TODO: we probably want these
#SRC_URI += "file://hilog-Add-tests.patch"

SRC_URI += "file://bison_parser.patch;patchdir=${S}/third_party/libxkbcommon"
SRC_URI += "file://flexlexer.patch;patchdir=${S}/base/update/updater"

# Native node hacks
SRC_URI += "file://jsframwork-use-yocto-node.patch;patchdir=${S}/third_party/jsframework"
SRC_URI += "file://third_party_jsframework-path-posix.patch;patchdir=${S}/third_party/jsframework"
SRC_URI += "file://ts2abc-don-t-set-node_path-for-Linux-host-toolchain.patch;patchdir=${S}/ark/ts2abc"

SRC_URI += "git://gitlab.eclipse.org/eclipse/oniro-core/openharmony-vendor-oniro.git;protocol=https;branch=main;rev=f7ba03c8f0d90b0b12998b26e4ce0d7bac8e6a03;destsuffix=${S}/vendor/oniro"

SRC_URI += "file://display-Mock-interface-for-standard-system.patch"
SRC_URI += "file://display_device.c;subdir=${S}/drivers/peripheral/display/hal/default/standard_system"

SRC_URI += "file://ace_engine.patch;patchdir=${S}/foundation/ace/ace_engine"
SRC_URI += "file://ace_engine-js_inspector.patch;patchdir=${S}/foundation/ace/ace_engine"
SRC_URI += "file://graphic_config.patch;patchdir=${S}/foundation/graphic/standard"
SRC_URI += "file://camera_hal.patch;patchdir=${S}/drivers/peripheral"
SRC_URI += "file://ace-js2bundle_node-path.patch;patchdir=${S}/developtools/ace-js2bundle"
SRC_URI += "file://hc-gen-compiler.patch;patchdir=${S}/drivers/framework"
SRC_URI += "file://hdi-gen-compiler.patch;patchdir=${S}/drivers/framework"
SRC_URI += "file://build_node-path.patch;patchdir=${S}/build"
SRC_URI += "file://build_hapsigner-tool.patch;patchdir=${S}/build"
SRC_URI += "file://build_packing-tool-path.patch;patchdir=${S}/build"
SRC_URI += "file://third_party_fsck_msdos.patch;patchdir=${S}/third_party/fsck_msdos"
SRC_URI += "file://third_party_libinput.patch;patchdir=${S}/third_party/libinput"
SRC_URI += "file://third_party_lz4.patch;patchdir=${S}/third_party/lz4"
SRC_URI += "file://third_party_googletest.patch;patchdir=${S}/third_party/googletest"
SRC_URI += "file://foundation_multimodalinput_input-input-event-codes-h.patch;patchdir=${S}/foundation/multimodalinput/input"
SRC_URI += "file://foundation_graphic_standard-attrProcessFuncs.patch;patchdir=${S}/foundation/graphic/standard"
SRC_URI += "file://base_global_resmgr_standard.patch;patchdir=${S}/base/global/resmgr_standard"
SRC_URI += "file://base_account_os_account.patch;patchdir=${S}/base/account/os_account"
SRC_URI += "file://ace_engine-openssl-legacy-provider.patch;patchdir=${S}/foundation/ace/ace_engine"
SRC_URI += "file://developtools_hdc_standard-gcc.patch;patchdir=${S}/developtools/hdc_standard"
SRC_URI += "file://foundation_graphic_standard-hdi-display-layer.patch;patchdir=${S}/foundation/graphic/standard"
SRC_URI += "file://third_party_weston-hdi-display-layer.patch;patchdir=${S}/third_party/weston"
SRC_URI += "file://third_party_selinux-flex-bison-path.patch;patchdir=${S}/third_party/selinux"
SRC_URI += "file://features.json;subdir=${OHOS_BUILD_CONFIGS_DIR}"

# Patch to allow /system/profile and /system/usr to be symlinks to /usr/lib/openharmony
SRC_URI += "file://foundation_distributedschedule_safwk-slash-system-symlink.patch;patchdir=${S}/foundation/distributedschedule/safwk"
SRC_URI += "file://foundation_distributedschedule_samgr-slash-system-symlink.patch;patchdir=${S}/foundation/distributedschedule/samgr"

SRC_URI += "file://appspawn-procps.patch;patchdir=${S}/base/startup/appspawn_standard"

# Workaround for problem with nodejs 17:
# error:0308010C:digital envelope routines::unsupported
export NODE_OPTIONS = "--openssl-legacy-provider"
export OPENSSL_CONF = "${RECIPE_SYSROOT_NATIVE}/usr/lib/ssl-3/openssl.cnf"
export SSL_CERT_DIR = "${RECIPE_SYSROOT_NATIVE}/usr/lib/ssl-3/certs"
export OPENSSL_ENGINES = "${RECIPE_SYSROOT_NATIVE}/usr/lib/engines-3"
export OPENSSL_MODULES = "${RECIPE_SYSROOT_NATIVE}/usr/lib/ossl-modules"

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

OHOS_BUILD_CONFIGS_DIR = "${B}/../preloader/${OHOS_PRODUCT_NAME}"

OHOS_PROJECT_BUILD_CONFIG_DIR = "${B}/build_configs"

GN_ARGS += 'target_os="ohos"'
GN_ARGS += 'target_cpu="${OHOS_DEVICE_CPU_ARCH}"'
GN_ARGS += 'product_name="${OHOS_PRODUCT_NAME}"'
GN_ARGS += 'device_name="${OHOS_DEVICE_NAME}"'
GN_ARGS += 'is_standard_system=true'
GN_ARGS += 'is_debug=false'
GN_ARGS += 'is_component_build=true'
GN_ARGS += 'release_test_suite=false'
GN_ARGS += 'treat_warnings_as_errors=false'
GN_ARGS += 'node_path="${RECIPE_SYSROOT_NATIVE}/usr/bin"'
GN_ARGS += 'host_toolchain="//oniro:clang_x64"'
GN_ARGS += 'install_oniro_third_party=false'
GN_ARGS += 'flex_path="${RECIPE_SYSROOT_NATIVE}/usr/bin/flex"'
GN_ARGS += 'bison_path="${RECIPE_SYSROOT_NATIVE}/usr/bin/bison"'

# OpenHarmony unit tests are statically linked and therefore not stripped
# binaries sum up to almost 80GB which makes it difficult to build OpenHarmony
# with tests on a normal desktop, let alone the CI runner
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'remove_unstripped_execs=true', '', d)}"
GN_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'remove_unstripped_so=true', '', d)}"

# OpenHarmony build system generates all possible targets, but only `packages`
# target is build with ninja in the end
NINJA_ARGS = "packages"
NINJA_ARGS += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'make_test', '', d)}"

# Copy FlexLexer.h from recipe sysroot
do_copy_to_srcdir() {
    cp ${RECIPE_SYSROOT_NATIVE}/usr/include/FlexLexer.h \
       ${S}/base/update/updater/services/script/yacc
}

addtask do_copy_to_srcdir after do_prepare_recipe_sysroot do_unpack before do_configure

do_configure[prefuncs] += "generate_build_config_json_file"
do_configure[prefuncs] += "generate_platforms_build_file"
do_configure[prefuncs] += "generate_parts_json"
do_configure[prefuncs] += "generate_syscap_files"
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
    # same for /vendor/lib and /vendor/bin
    mkdir -p ${D}/vendor
    cp -r ${OHOS_PACKAGE_OUT_DIR}/vendor/lib/* ${D}${libdir}/
    install -m 755 -t ${D}${bindir}/ ${OHOS_PACKAGE_OUT_DIR}/vendor/bin/*
    ln -sfT ..${libdir} ${D}/vendor/lib
    ln -sfT ..${bindir} ${D}/vendor/bin

    # system ability configurations
    mkdir -p ${D}${libdir}/openharmony/profile
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/profile/* ${D}${libdir}/openharmony/profile
    ln -sfT ..${libdir}/openharmony/profile ${D}/system/profile

    # odd files in /system/usr
    mkdir -p ${D}${libdir}/openharmony/usr
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/usr/* ${D}${libdir}/openharmony/usr
    ln -sfT ..${libdir}/openharmony/usr ${D}/system/usr

    # OpenHarmony etc (configuration) files
    mkdir -p ${D}${sysconfdir}/openharmony
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/etc/* ${D}${sysconfdir}/openharmony
    ln -sfT ..${sysconfdir}/openharmony ${D}/system/etc
    # same for /vendor/etc
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/vendor/etc/* ${D}${sysconfdir}/openharmony
    ln -sfT ..${sysconfdir}/openharmony ${D}/vendor/etc

    # OpenHarmony font files
    mkdir -p ${D}${datadir}/fonts/openharmony
    cp -r  ${OHOS_PACKAGE_OUT_DIR}/system/fonts/* ${D}${datadir}/fonts/openharmony
    ln -sfT ..${datadir}/fonts/openharmony ${D}/system/fonts

    # Avoid file-conflict on /usr/bin/udevadm with //third_party/eudev and udev
    # recipe
    rm ${D}${bindir}/udevadm

    # Workaround! Build system does not install libcrypto.z.so (boringssl), so
    # we install it manually for now
    cp ${B}/developtools/profiler/libcrypto.z.so ${D}${libdir}/
}

inherit update-alternatives
ALTERNATIVE:${PN} = "basename chvt clear cmp crc32 cut diff dirname du env expr \
find flock free groups head id killall logger logname md5sum microcom mkfifo \
nohup nproc od patch pgrep printf readlink realpath renice reset seq setsid \
sha1sum sha256sum sort strings tail tee test time top tr tty uniq unlink \
uptime wc which whoami xargs yes passwd"
ALTERNATIVE_PRIORITY = "0"

PACKAGES =+ "${PN}-configs ${PN}-fonts"

RDEPENDS:${PN} += "${PN}-configs ${PN}-fonts"

RDEPENDS:${PN} += "musl libcxx libcrypto libssl libatomic"
RDEPENDS:${PN}-ptest += "musl libcxx libcrypto libssl"
RDEPENDS:${PN}-ptest += "${PN}-libutils ${PN}-libsyspara ${PN}-libbegetutil ${PN}-libeventhandler ${PN}-accesstoken"
RDEPENDS:${PN}-ptest += "${PN}-thirdparty-mbedtls ${PN}-thirdparty-sqlite"

# OpenHarmony libraries are not versioned properly.
# Move the unversioned .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/media ${libdir}/module ${libdir}/ark ${libdir}/openharmony ${libdir}/*${SOLIBS} ${libdir}/plugin"
FILES:${PN}-configs = "${sysconfdir}"
FILES:${PN}-fonts = "${datadir}/fonts"

FILES:${PN} += "/system/bin /system/lib /system/profile /system/usr /vendor/bin /vendor/lib"
FILES:${PN}-configs += "/system/etc /vendor/etc"
FILES:${PN}-fonts += "/system/fonts"

generate_build_config_json_file() {

    mkdir -p "${OHOS_BUILD_CONFIGS_DIR}"
    cat > "${OHOS_BUILD_CONFIGS_DIR}/build_config.json" << EOF
    {
      "system_type": "standard",
      "product_name": "${OHOS_PRODUCT_NAME}",
      "product_company": "${OHOS_PRODUCT_COMPANY}",
      "device_name": "${OHOS_DEVICE_NAME}",
      "device_company": "${OHOS_DEVICE_COMPANY}",
      "target_os": "ohos",
      "target_cpu": "arm",
      "kernel_version": "",
      "product_toolchain_label": "//oniro/sysroots/target:target_clang"
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

    mkdir -p "${OHOS_BUILD_CONFIGS_DIR}"
    cat > "${OHOS_BUILD_CONFIGS_DIR}/platforms.build" << EOF
    {
      "version": 2,
      "platforms": {
        "${OHOS_PRODUCT_PLATFORM_TYPE}": {
          "target_os": "ohos",
          "target_cpu": "${OHOS_DEVICE_CPU_ARCH}",
          "toolchain": "//oniro/sysroots/target:target_clang",
          "parts_config": "parts.json"
        }
      }
    }
EOF
}

OPENHARMONY_PARTS += "aafwk:ability_base"
OPENHARMONY_PARTS += "aafwk:ability_runtime"
OPENHARMONY_PARTS += "aafwk:aafwk_standard"
OPENHARMONY_PARTS += "aafwk:form_runtime"
OPENHARMONY_PARTS += "account:os_account_standard"
OPENHARMONY_PARTS += "ace:ace_engine_standard"
OPENHARMONY_PARTS += "ace:napi"
OPENHARMONY_PARTS += "appexecfwk:appexecfwk_standard"
OPENHARMONY_PARTS += "appexecfwk:bundle_framework"
OPENHARMONY_PARTS += "appexecfwk:eventhandler"
OPENHARMONY_PARTS += "ark:ark_js_runtime"
OPENHARMONY_PARTS += "ark:ark"
OPENHARMONY_PARTS += "ccruntime:jsapi_worker"
OPENHARMONY_PARTS += "common:common"
OPENHARMONY_PARTS += "communication:dsoftbus_standard"
OPENHARMONY_PARTS += "communication:ipc"
OPENHARMONY_PARTS += "communication:ipc_js"
OPENHARMONY_PARTS += "communication:netmanager_base"
OPENHARMONY_PARTS += "communication:wifi_standard"
OPENHARMONY_PARTS += "developtools:bytrace_standard"
OPENHARMONY_PARTS += "developtools:hdc_standard"
OPENHARMONY_PARTS += "deviceprofile:device_profile_core"
OPENHARMONY_PARTS += "distributeddatamgr:distributeddatamgr"
OPENHARMONY_PARTS += "distributeddatamgr:native_appdatamgr"
OPENHARMONY_PARTS += "distributedhardware:device_manager_base"
OPENHARMONY_PARTS += "distributedschedule:dmsfwk_standard"
OPENHARMONY_PARTS += "distributedschedule:safwk"
OPENHARMONY_PARTS += "distributedschedule:samgr_standard"
OPENHARMONY_PARTS += "filemanagement:storage_service"
OPENHARMONY_PARTS += "global:i18n_standard"
OPENHARMONY_PARTS += "global:resmgr_standard"
OPENHARMONY_PARTS += "graphic:graphic_standard"
OPENHARMONY_PARTS += "hdf:device_driver_framework"
OPENHARMONY_PARTS += "hdf:hdf"
OPENHARMONY_PARTS += "hdf:mocks"
OPENHARMONY_PARTS += "hdf:camera_device_driver"
OPENHARMONY_PARTS += "hdf:display_device_driver"
OPENHARMONY_PARTS += "hdf:input_device_driver"
OPENHARMONY_PARTS += "hdf:sensor_device_driver"
OPENHARMONY_PARTS += "hdf:wlan_device_driver"
OPENHARMONY_PARTS += "hdf:vibrator_device_driver"
OPENHARMONY_PARTS += "hdf:power_device_driver"
OPENHARMONY_PARTS += "hiviewdfx:faultloggerd"
OPENHARMONY_PARTS += "hiviewdfx:hichecker_native"
OPENHARMONY_PARTS += "hiviewdfx:hicollie_native"
OPENHARMONY_PARTS += "hiviewdfx:hilog"
OPENHARMONY_PARTS += "hiviewdfx:hilog_native"
OPENHARMONY_PARTS += "hiviewdfx:hilog_service"
OPENHARMONY_PARTS += "hiviewdfx:hisysevent_native"
OPENHARMONY_PARTS += "hiviewdfx:hitrace_native"
OPENHARMONY_PARTS += "hiviewdfx:hiviewdfx_hilog_native"
OPENHARMONY_PARTS += "miscservices:inputmethod_native"
OPENHARMONY_PARTS += "miscservices:time_native"
OPENHARMONY_PARTS += "multimedia:multimedia_audio_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_camera_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_histreamer"
OPENHARMONY_PARTS += "multimedia:multimedia_image_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_media_library_standard"
OPENHARMONY_PARTS += "multimedia:multimedia_media_standard"
OPENHARMONY_PARTS += "multimodalinput:multimodalinput_base"
OPENHARMONY_PARTS += "notification:ans_standard"
OPENHARMONY_PARTS += "notification:ces_standard"
OPENHARMONY_PARTS += "powermgr:battery_manager_native"
OPENHARMONY_PARTS += "powermgr:display_manager_native"
OPENHARMONY_PARTS += "powermgr:power_manager_native"
OPENHARMONY_PARTS += "securec:thirdparty_bounds_checking_function"
OPENHARMONY_PARTS += "security:access_token"
OPENHARMONY_PARTS += "security:appverify"
OPENHARMONY_PARTS += "security:dataclassification"
OPENHARMONY_PARTS += "security:deviceauth_standard"
OPENHARMONY_PARTS += "security:device_security_level"
OPENHARMONY_PARTS += "security:huks"
OPENHARMONY_PARTS += "security:permission_standard"
OPENHARMONY_PARTS += "sensors:sensor"
OPENHARMONY_PARTS += "startup:appspawn"
OPENHARMONY_PARTS += "startup:init"
OPENHARMONY_PARTS += "startup:startup_l2"
OPENHARMONY_PARTS += "telephony:core_service"
OPENHARMONY_PARTS += "telephony:ril_adapter"
OPENHARMONY_PARTS += "useriam:user_idm"
OPENHARMONY_PARTS += "utils:utils_base"
OPENHARMONY_PARTS += "window:window_manager"

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
    json_parts_config = {}

    json_parts['parts'] = []

    for part in d.getVar("OPENHARMONY_PARTS").split():
        json_parts['parts'].append(part)
        json_parts_config[part.translate(str.maketrans('-:.', '___'))] = True

    config_dir = d.getVar("OHOS_BUILD_CONFIGS_DIR")

    os.makedirs(config_dir, exist_ok=True)

    with open(os.path.join(config_dir, 'parts.json'), 'w') as outfile:
        outfile.write(json.dumps(json_parts, indent=2, sort_keys=True))

    with open(os.path.join(config_dir, 'parts_config.json'), 'w') as outfile:
        outfile.write(json.dumps(json_parts_config, indent=2, sort_keys=True))
}

generate_syscap_files() {
    cat > "${OHOS_BUILD_CONFIGS_DIR}/SystemCapability.json" << EOF
    {
      "product": "${OHOS_PRODUCT_NAME}",
      "api_version": 0,
      "system_type": "standard",
      "manufacturer_id": 0
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

PACKAGES =+ "${PN}-libsec-shared"
FILES:${PN}-libsec-shared = "${libdir}/libsec_shared*${SOLIBS}"
RDEPENDS:${PN}-libsec-shared += "musl libcxx"
RDEPENDS:${PN} += "${PN}-libsec-shared"

PACKAGES =+ "${PN}-libagent-log"
FILES:${PN}-libagent-log = "${libdir}/libagent_log*${SOLIBS}"
RDEPENDS:${PN}-libagent-log += "musl libcxx"
RDEPENDS:${PN} += "${PN}-libagent-log"

PACKAGES =+ "${PN}-libparam-client"
FILES:${PN}-libparam-client = "${libdir}/libparam_client*${SOLIBS}"
RDEPENDS:${PN}-libparam-client += "musl libcxx ${PN}-libagent-log"
RDEPENDS:${PN} += "${PN}-libparam-client"

PACKAGES =+ "${PN}-libparam-watcheragent"
FILES:${PN}-libparam-watcheragent = "${libdir}/libparam_watcheragent*${SOLIBS}"
RDEPENDS:${PN}-libparam-watcheragent += "musl libcxx"
RDEPENDS:${PN}-libparam-watcheragent += "${PN}-libagent-log ${PN}-libparam-client ${PN}-libutils ${PN}-samgr ${PN}-ipc"
RDEPENDS:${PN} += "${PN}-libparam-watcheragent"

PACKAGES =+ "${PN}-libsyspara"
FILES:${PN}-libsyspara = "${libdir}/libsyspara*${SOLIBS}"
RDEPENDS:${PN}-libsyspara += "musl libcxx libcrypto ${PN}-libutils ${PN}-libsysparam-hal ${PN}-libparam-client"
RDEPENDS:${PN} += "${PN}-libsyspara"

PACKAGES =+ "${PN}-libsyspara-watchagent"
FILES:${PN}-libsyspara-watchagent = "${libdir}/libsyspara_watchagent*${SOLIBS}"
RDEPENDS:${PN}-libsyspara-watchagent += "musl libcxx ${PN}-libparam-watcheragent"
RDEPENDS:${PN} += "${PN}-libsyspara-watchagent"

PACKAGES =+ "${PN}-libsysparam-hal"
FILES:${PN}-libsysparam-hal = "${libdir}/libsysparam_hal*${SOLIBS}"
RDEPENDS:${PN}-libsysparam-hal += "musl libcxx libcrypto ${PN}-libutils ${PN}-libparam-client"
RDEPENDS:${PN} += "${PN}-libsysparam-hal"

PACKAGES =+ "${PN}-libbegetutil"
FILES:${PN}-libbegetutil = "${libdir}/libbegetutil*${SOLIBS}"
RDEPENDS:${PN}-libbegetutil += "musl libcxx ${PN}-libparam-client"
RDEPENDS:${PN} += "${PN}-libbegetutil"

PACKAGES =+ "${PN}-libeventhandler"
FILES:${PN}-libeventhandler = "${libdir}/libeventhandler*${SOLIBS}"
RDEPENDS:${PN}-libeventhandler += "musl libcxx ${PN}-hilog ${PN}-hichecker ${PN}-hitrace"
RDEPENDS:${PN} += "${PN}-libeventhandler"

PACKAGES =+ "${PN}-thirdparty-mbedtls"
FILES:${PN}-thirdparty-mbedtls = "${libdir}/libmbedtls*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-mbedtls += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-mbedtls"

PACKAGES =+ "${PN}-thirdparty-sqlite"
FILES:${PN}-thirdparty-sqlite = "${libdir}/libsqlite*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-sqlite += "musl libcxx libcrypto ${PN}-libutils"
RDEPENDS:${PN} += "${PN}-thirdparty-sqlite"

PACKAGES =+ "${PN}-thirdparty-libxml2"
FILES:${PN}-thirdparty-libxml2 = "${libdir}/libxml2*${SOLIBS}"
RDEPENDS:${PN}-thirdparty-libxml2 += "musl libcxx"
RDEPENDS:${PN} += "${PN}-thirdparty-libxml2"

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
RDEPENDS:${PN}-hilog       += "${PN}-libsec-shared ${PN}-libutilsecurec ${PN}-libbegetutil ${PN}-libsyspara"

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
    mv ${D}${PTEST_PATH}/moduletest/appspawn/appspawn_l2 ${D}${libdir}/${BPN}-appspawn/ptest/moduletest
    mv ${D}${PTEST_PATH}/unittest/appspawn/appspawn_l2 ${D}${libdir}/${BPN}-appspawn/ptest/unittest
    rmdir ${D}${PTEST_PATH}/*/appspawn
    echo "appspawn.service" > ${D}${libdir}/${BPN}-appspawn/ptest/systemd-units
}
OPENHARMONY_PTEST_IS_BROKEN = "appspawn"
FILES:${PN}-appspawn = "\
    ${bindir}/appspawn* \
    ${libdir}/libappspawn* \
    ${systemd_unitdir}/appspawn.service \
"
FILES:${PN}-appspawn-ptest = "${libdir}/${BPN}-appspawn/ptest"
RDEPENDS:${PN} += "${PN}-appspawn"
RDEPENDS:${PN}-ptest += "${PN}-appspawn-ptest ${PN}-appspawn"
RDEPENDS:${PN}-appspawn-ptest += "${PN}-appspawn"
RDEPENDS:${PN}-appspawn       += "musl libcxx"
RDEPENDS:${PN}-appspawn-ptest += "musl libcxx"
RDEPENDS:${PN}-appspawn       += "${PN}-libutils ${PN}-hilog ${PN}-libbegetutil ${PN}-libsyspara"
RDEPENDS:${PN}-appspawn-ptest += "${PN}-libutils ${PN}-hilog ${PN}-libbegetutil ${PN}-libsyspara"
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
OPENHARMONY_PTEST_IS_BROKEN = "appexecfwk"
FILES:${PN}-appexecfwk = "\
    ${libdir}/libappexecfwk*${SOLIBS} \
"
FILES:${PN}-appexecfwk-ptest = "${libdir}/${BPN}-appexecfwk/ptest"
RDEPENDS:${PN} += "${PN}-appexecfwk"
RDEPENDS:${PN}-ptest += "${PN}-appexecfwk-ptest ${PN}-appexecfwk"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-appexecfwk"
RDEPENDS:${PN}-appexecfwk       += "musl libcxx"
RDEPENDS:${PN}-appexecfwk-ptest += "musl libcxx"
RDEPENDS:${PN}-appexecfwk       += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc ${PN}-libeventhandler ${PN}-hichecker ${PN}-hitrace"
# TODO: remove when needed parts are split out
RDEPENDS:${PN}-appexecfwk += "${PN}"
RDEPENDS:${PN}-appexecfwk-ptest += "${PN}"

PACKAGES =+ "${PN}-samgr ${PN}-samgr-ptest"
SYSTEMD_PACKAGES += "${PN}-samgr"
SYSTEMD_SERVICE:${PN}-samgr = "samgr.service"
SRC_URI += "file://samgr.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/samgr.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/samgr_standard.cfg
}
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-samgr/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-samgr/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/samgr_standard/samgr ${D}${libdir}/${BPN}-samgr/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/samgr_standard
    echo "samgr.service" > ${D}${libdir}/${BPN}-samgr/ptest/systemd-units
}
OPENHARMONY_PTEST_IS_BROKEN = "samgr"
FILES:${PN}-samgr = "\
    ${bindir}/samgr \
    ${libdir}/libsamgr*${SOLIBS} \
    ${libdir}/liblsamgr*${SOLIBS} \
    ${systemd_unitdir}/samgr.service \
"
FILES:${PN}-samgr-ptest = "${libdir}/${BPN}-samgr/ptest"
RDEPENDS:${PN} += "${PN}-samgr"
RDEPENDS:${PN}-ptest += "${PN}-samgr-ptest ${PN}-samgr"
RDEPENDS:${PN}-samgr-ptest += "${PN}-samgr"
RDEPENDS:${PN}-samgr       += "musl libcxx"
RDEPENDS:${PN}-samgr-ptest += "musl libcxx"
RDEPENDS:${PN}-samgr       += "${PN}-libutils ${PN}-libbegetutil ${PN}-accesstoken ${PN}-libeventhandler ${PN}-hilog ${PN}-ipc"
RDEPENDS:${PN}-samgr-ptest += "${PN}-libutils ${PN}-libbegetutil ${PN}-accesstoken ${PN}-libeventhandler ${PN}-hilog ${PN}-ipc"
RDEPENDS:${PN}-samgr       += "${PN}-thirdparty-libxml2"
RDEPENDS:${PN}-samgr-ptest += "${PN}-thirdparty-libxml2"

PACKAGES =+ "${PN}-safwk ${PN}-safwk-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-safwk/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-safwk/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/safwk/safwk ${D}${libdir}/${BPN}-safwk/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/safwk
}
OPENHARMONY_PTEST_IS_BROKEN = "safwk"
FILES:${PN}-safwk = "\
    ${libdir}/libsystem_ability_fwk*${SOLIBS} \
"
FILES:${PN}-safwk-ptest = "${libdir}/${BPN}-safwk/ptest"
RDEPENDS:${PN} += "${PN}-safwk"
RDEPENDS:${PN}-ptest += "${PN}-safwk-ptest ${PN}-safwk"
RDEPENDS:${PN}-safwk-ptest += "${PN}-safwk"
RDEPENDS:${PN}-safwk       += "musl libcxx"
RDEPENDS:${PN}-safwk-ptest += "musl libcxx"
RDEPENDS:${PN}-safwk       += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc"
RDEPENDS:${PN}-safwk-ptest += "${PN}-libutils ${PN}-hilog ${PN}-samgr ${PN}-ipc"

PACKAGES =+ "${PN}-ipc ${PN}-ipc-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-ipc/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-ipc/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/ipc ${D}${libdir}/${BPN}-ipc/ptest/unittest
    mv ${D}${PTEST_PATH}/moduletest/ipc ${D}${libdir}/${BPN}-ipc/ptest/moduletest
}
OPENHARMONY_PTEST_IS_BROKEN = "ipc"
FILES:${PN}-ipc = "\
    ${libdir}/libipc*${SOLIBS} \
    ${libdir}/librpc*${SOLIBS} \
    ${libdir}/libdbinder*${SOLIBS} \
"
FILES:${PN}-ipc-ptest = "${libdir}/${BPN}-ipc/ptest"
RDEPENDS:${PN} += "${PN}-ipc"
RDEPENDS:${PN}-ptest += "${PN}-ipc-ptest ${PN}-ipc"
RDEPENDS:${PN}-ipc-ptest += "${PN}-ipc"
RDEPENDS:${PN}-ipc       += "musl libcxx"
RDEPENDS:${PN}-ipc-ptest += "musl libcxx"
RDEPENDS:${PN}-ipc       += "${PN}-libutils ${PN}-hilog ${PN}-dsoftbus ${PN}-hitrace"
RDEPENDS:${PN}-ipc-ptest += "${PN}-libutils ${PN}-hilog ${PN}-samgr"

PACKAGES =+ "${PN}-devicemanager ${PN}-devicemanager-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-devicemanager/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-devicemanager/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/device_manager_base/component_loader_test ${D}${libdir}/${BPN}-devicemanager/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/device_manager_base
}
OPENHARMONY_PTEST_IS_BROKEN = "devicemanager"
FILES:${PN}-devicemanager = "\
    ${libdir}/libdevicemanager*${SOLIBS} \
    ${libdir}/module/distributedhardware/libdevicemanager*${SOLIBS} \
"
FILES:${PN}-devicemanager-ptest = "${libdir}/${BPN}-devicemanager/ptest"
RDEPENDS:${PN} += "${PN}-devicemanager"
RDEPENDS:${PN}-ptest += "${PN}-devicemanager-ptest ${PN}-devicemanager"
RDEPENDS:${PN}-devicemanager-ptest += "${PN}-devicemanager"
RDEPENDS:${PN}-devicemanager       += "musl libcxx"
RDEPENDS:${PN}-devicemanager-ptest += "musl libcxx"
RDEPENDS:${PN}-devicemanager       += "${PN}-libsyspara ${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-libeventhandler ${PN}-safwk ${PN}-libsyspara-watchagent ${PN}-hilog ${PN}-accesstoken ${PN}-dsoftbus"
RDEPENDS:${PN}-devicemanager-ptest += "${PN}-libsyspara ${PN}-libutils ${PN}-ipc ${PN}-samgr ${PN}-libeventhandler ${PN}-safwk ${PN}-libsyspara-watchagent ${PN}-hilog ${PN}-accesstoken ${PN}-dsoftbus"
RDEPENDS:${PN}-devicemanager       += "${PN}-thirdparty-mbedtls"
RDEPENDS:${PN}-devicemanager-ptest += "${PN}-thirdparty-mbedtls"
# TODO: remove when needed parts are split out
RDEPENDS:${PN}-devicemanager       += "${PN}"
RDEPENDS:${PN}-devicemanager-ptest += "${PN}"

PACKAGES =+ "${PN}-accesstoken ${PN}-accesstoken-ptest"
SYSTEMD_PACKAGES += "${PN}-accesstoken"
SYSTEMD_SERVICE:${PN}-accesstoken = "accesstoken.service tokensync.service"
SRC_URI += "file://accesstoken.service file://tokensync.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/accesstoken.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/access_token.cfg
    install -m 644 ${WORKDIR}/tokensync.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/token_sync.cfg
}
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-accesstoken/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-accesstoken/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/access_token/access_token ${D}${libdir}/${BPN}-accesstoken/ptest/unittest
    rmdir ${D}${PTEST_PATH}/unittest/access_token
    echo "accesstoken.service" >  ${D}${libdir}/${BPN}-accesstoken/ptest/systemd-units
    echo "tokensync.service"   >> ${D}${libdir}/${BPN}-accesstoken/ptest/systemd-units
}
OPENHARMONY_PTEST_IS_BROKEN = "accesstoken"
FILES:${PN}-accesstoken = "\
    ${libdir}/libaccesstoken*${SOLIBS} \
    ${libdir}/libtoken*sync*${SOLIBS} \
    ${libdir}/openharmony/profile/accesstoken_service.xml \
    ${libdir}/openharmony/profile/token_sync_service.xml \
    ${systemd_unitdir}/accesstoken.service \
    ${systemd_unitdir}/tokensync.service \
"
FILES:${PN}-accesstoken-ptest = "${libdir}/${BPN}-accesstoken/ptest"
RDEPENDS:${PN} += "${PN}-accesstoken"
RDEPENDS:${PN}-ptest += "${PN}-accesstoken-ptest ${PN}-accesstoken"
RDEPENDS:${PN}-accesstoken-ptest += "${PN}-accesstoken"
RDEPENDS:${PN}-accesstoken       += "musl libcxx"
RDEPENDS:${PN}-accesstoken-ptest += "musl libcxx"
RDEPENDS:${PN}-accesstoken       += "${PN}-libutils ${PN}-libsyspara ${PN}-libeventhandler ${PN}-hilog ${PN}-samgr ${PN}-ipc ${PN}-safwk ${PN}-devicemanager ${PN}-dsoftbus"
RDEPENDS:${PN}-accesstoken-ptest += "${PN}-libutils ${PN}-libsyspara ${PN}-libeventhandler ${PN}-hilog ${PN}-samgr ${PN}-ipc ${PN}-safwk ${PN}-devicemanager"
RDEPENDS:${PN}-accesstoken       += "${PN}-thirdparty-mbedtls ${PN}-thirdparty-sqlite"
RDEPENDS:${PN}-accesstoken-ptest += "${PN}-thirdparty-mbedtls ${PN}-thirdparty-sqlite"

PACKAGES =+ "${PN}-dsoftbus ${PN}-dsoftbus-ptest"
SYSTEMD_PACKAGES += "${PN}-dsoftbus"
SYSTEMD_SERVICE:${PN}-dsoftbus = "dsoftbus.service"
SRC_URI += "file://dsoftbus.service"
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/dsoftbus.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${sysconfdir}/openharmony/init/softbus_server.cfg
}
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-dsoftbus/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-dsoftbus/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/dsoftbus_standard ${D}${libdir}/${BPN}-dsoftbus/ptest/unittest
    echo "dsoftbus.service" > ${D}${libdir}/${BPN}-dsoftbus/ptest/systemd-units
}
OPENHARMONY_PTEST_IS_BROKEN = "dsoftbus"
FILES:${PN}-dsoftbus = "\
    ${libdir}/libsoftbus*${SOLIBS} \
    ${sysconfdir}/openharmony/communication/softbus \
    ${libdir}/openharmony/profile/softbus_server.xml \
    ${systemd_unitdir}/dsoftbus.service \
"
FILES:${PN}-dsoftbus-ptest = "${libdir}/${BPN}-dsoftbus/ptest"
RDEPENDS:${PN} += "${PN}-dsoftbus"
RDEPENDS:${PN}-ptest += "${PN}-dsoftbus-ptest ${PN}-dsoftbus"
RDEPENDS:${PN}-dsoftbus-ptest += "${PN}-dsoftbus"
RDEPENDS:${PN}-dsoftbus       += "musl libcxx"
RDEPENDS:${PN}-dsoftbus-ptest += "musl libcxx"
RDEPENDS:${PN}-dsoftbus       += "${PN}-samgr ${PN}-libsyspara ${PN}-hilog ${PN}-libutils ${PN}-ipc ${PN}-safwk"
RDEPENDS:${PN}-dsoftbus-ptest += "${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-dsoftbus       += "${PN}-thirdparty-mbedtls"
# TODO: remove when needed parts are split out
RDEPENDS:${PN}-dsoftbus       += "${PN}"
RDEPENDS:${PN}-dsoftbus-ptest += "${PN}"

PACKAGES =+ "${PN}-hichecker ${PN}-hichecker-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hichecker/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hichecker/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/hichecker/native ${D}${libdir}/${BPN}-hichecker/ptest/unittest
}
FILES:${PN}-hichecker = "\
    ${libdir}/libhichecker*${SOLIBS} \
"
FILES:${PN}-hichecker-ptest = "${libdir}/${BPN}-hichecker/ptest"
RDEPENDS:${PN} += "${PN}-hichecker"
RDEPENDS:${PN}-ptest += "${PN}-hichecker-ptest ${PN}-hichecker"
RDEPENDS:${PN}-hichecker-ptest += "${PN}-hichecker"
RDEPENDS:${PN}-hichecker       += "musl libcxx"
RDEPENDS:${PN}-hichecker-ptest += "musl libcxx"
RDEPENDS:${PN}-hichecker       += "${PN}-hilog"
#RDEPENDS:${PN}-hichecker-ptest += "${PN}-libutils ${PN}-hilog"
#RDEPENDS:${PN}-hichecker       += "${PN}-thirdparty-mbedtls"
## TODO: remove when needed parts are split out
RDEPENDS:${PN}-hichecker       += "${PN}"
#RDEPENDS:${PN}-hichecker-ptest += "${PN}"

PACKAGES =+ "${PN}-hitrace ${PN}-hitrace-ptest"
do_install_ptest_base[cleandirs] += "${D}${libdir}/${BPN}-hitrace/ptest"
do_install_ptest:append() {
    install -D ${WORKDIR}/run-ptest ${D}${libdir}/${BPN}-hitrace/ptest/run-ptest
    mv ${D}${PTEST_PATH}/unittest/hiviewdfx/hitrace ${D}${libdir}/${BPN}-hitrace/ptest/unittest
}
FILES:${PN}-hitrace = "\
    ${libdir}/libhitrace*${SOLIBS} \
    ${libdir}/module/libhitrace*${SOLIBS} \
"
FILES:${PN}-hitrace-ptest = "${libdir}/${BPN}-hitrace/ptest"
RDEPENDS:${PN} += "${PN}-hitrace"
RDEPENDS:${PN}-ptest += "${PN}-hitrace-ptest ${PN}-hitrace"
RDEPENDS:${PN}-hitrace-ptest += "${PN}-hitrace"
RDEPENDS:${PN}-hitrace       += "musl libcxx"
RDEPENDS:${PN}-hitrace-ptest += "musl libcxx"
RDEPENDS:${PN}-hitrace       += "${PN}-libutils ${PN}-hilog"
RDEPENDS:${PN}-hitrace-ptest += "${PN}-libutils ${PN}-hilog"
#RDEPENDS:${PN}-hitrace       += "${PN}-thirdparty-mbedtls"
## TODO: remove when needed parts are split out
RDEPENDS:${PN}-hitrace       += "${PN}"
#RDEPENDS:${PN}-hitrace-ptest += "${PN}"

# Disable all ptest suites that are know to not work for now. When the x-bit is
# not set, the ptest is visible (using `ptest-runner -l`), but no test cases
# will be run when executing it.
# TODO: Fix all components and tests and remove all of this
OPENHARMONY_PTEST_IS_BROKEN = "accesstoken appexecfwk appspawn devicemanager dsoftbus ipc safwk samgr"
do_install_ptest:append() {
    for component in ${OPENHARMONY_PTEST_IS_BROKEN} ; do
        chmod -x ${D}${libdir}/${BPN}-$component/ptest/run-ptest
    done
}

EXCLUDE_FROM_SHLIBS = "1"

# FIXME: this is a dirty workaround for a bunch of missing *.z.so files, either
# from parts we need to install, or some problems with "inner kits" not being
# installed to our image. These are most likely real problems that we need to
# fix, as the components needing these will most likely fail in all kinds of
# wonderful ways.
INSANE_SKIP:${PN} = "file-rdeps"

# To avoid excessive diskspace blowup, we are stripping our executables
INSANE_SKIP:${PN} += "already-stripped"

# Need this to allow libnative_window.so and libnative_drawing.so symlinks
INSANE_SKIP:${PN} += "dev-so"
