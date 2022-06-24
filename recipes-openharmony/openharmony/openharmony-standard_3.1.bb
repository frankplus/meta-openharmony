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
SRC_URI += "file://features.json;subdir=${OHOS_BUILD_CONFIGS_DIR}"

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
RDEPENDS:${PN}-ptest += "${PN}-libutils ${PN}-libsyspara ${PN}-libbegetutil"

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
RDEPENDS:${PN}-libparam-watcheragent += "musl libcxx ${PN}-libagent-log ${PN}-libparam-client ${PN}-libutils ${PN}"
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
    rm -f ${D}${sysconfdir}/init/hilogd.cfg
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

EXCLUDE_FROM_SHLIBS = "1"

# We have the following problem:
# ERROR: openharmony-standard-3.0-r0 do_package_qa: QA Issue: /usr/lib/module/multimedia/libcamera_napi.z.so contained in package openharmony-standard requires libwms_client.z.so, but no providers found in RDEPENDS:openharmony-standard? [file-rdeps]
# and seems to be a bug in OpenHarmony 3.0
INSANE_SKIP:${PN} = "file-rdeps"

# To avoid excessive diskspace blowup, we are stripping our executables
INSANE_SKIP:${PN} += "already-stripped"

# Need this to allow libnative_window.so and libnative_drawing.so symlinks
INSANE_SKIP:${PN} += "dev-so"
