# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# These SRC_URIs are the gitee repositories of the OpenHarmony v3.0
# sources needed to build the Standard Linux target.

# Some of these repositories have lfs content but these are not needed
# to build the OpenHarmony standard system components and are explicitly
# disabled with the lfs=0 option in their SRC_URI entries.

GITEE_URL = "git://gitee.com/openharmony"
OH_SRCDIR = "src"
S = "${WORKDIR}/${OH_SRCDIR}"

# This China mirror backs up gitee source repositories
MIRRORS:append = " git://gitee.com/.* http://114.116.235.68/source-mirror/ \n "

SRC_URI += "${GITEE_URL}/applications_hap.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c23b547d268854c3cee585ea96aff69fc89a8e21;destsuffix=${OH_SRCDIR}/applications/standard/hap"
SRC_URI += "${GITEE_URL}/ark_js_runtime.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=4959aed3034951785e9563512f5f4341a4e4eeb0;destsuffix=${OH_SRCDIR}/ark/js_runtime"
SRC_URI += "${GITEE_URL}/ark_runtime_core.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=842855c6b4f23ac658948aca844d05b2d1936979;destsuffix=${OH_SRCDIR}/ark/runtime_core"
SRC_URI += "${GITEE_URL}/ark_ts2abc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=44c8034747fd69186af1605e4fb244d666d445d9;destsuffix=${OH_SRCDIR}/ark/ts2abc"
SRC_URI += "${GITEE_URL}/account_os_account.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=64c7b48fc61d4326aae46f57fd355c809a85ffca;destsuffix=${OH_SRCDIR}/base/account/os_account"
SRC_URI += "${GITEE_URL}/js_api_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2bd09842f20334e6fad5343032ccda24f06197ee;destsuffix=${OH_SRCDIR}/base/compileruntime/js_api_module"
SRC_URI += "${GITEE_URL}/js_sys_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=3d5d0b1141462128f87677a94bcd6903e97112d8;destsuffix=${OH_SRCDIR}/base/compileruntime/js_sys_module"
SRC_URI += "${GITEE_URL}/js_util_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=421a0ec14c682b3558238d7635a5c323bedab9cb;destsuffix=${OH_SRCDIR}/base/compileruntime/js_util_module"
SRC_URI += "${GITEE_URL}/js_worker_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=90b928a31ee40fddf91532a021418f24904accc1;destsuffix=${OH_SRCDIR}/base/compileruntime/js_worker_module"
SRC_URI += "${GITEE_URL}/global_i18n_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=59bbee215c1b8d853f48cb1899346c8628264a6f;destsuffix=${OH_SRCDIR}/base/global/i18n_standard"
SRC_URI += "${GITEE_URL}/global_resmgr_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c919433431c86c0e876e41958aedc046fa5e1788;destsuffix=${OH_SRCDIR}/base/global/resmgr_standard"
SRC_URI += "${GITEE_URL}/hiviewdfx_faultloggerd.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=de6c926a6ef3ebcdd91ba3317d9c444dcce51c55;destsuffix=${OH_SRCDIR}/base/hiviewdfx/faultloggerd"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiappevent.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=098412d15228f9cdbdb8dc5c152d3d5dc3b49d47;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiappevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hicollie.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=7171bcf3a0a47ff1ed263d58a2c23dc5ef0551b2;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hicollie"
SRC_URI += "${GITEE_URL}/hiviewdfx_hilog.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=f52ae28af5c23cc0449abbc752940a2e4eefb2aa;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hilog"
SRC_URI += "${GITEE_URL}/hiviewdfx_hisysevent.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b249e2452715fc9a123db3b799a2a0e316631bc2;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hisysevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hitrace.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=716ad82d05be042b754ab819fac109090a55ad73;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hitrace"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiview.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=cf020d5134cf10f6ca10cfa5cd0a22a8203013a4;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiview"
SRC_URI += "${GITEE_URL}/miscservices_inputmethod.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6bc55f3c0f3488b8ef78e398c770e2072aa0cb76;destsuffix=${OH_SRCDIR}/base/miscservices/inputmethod"
SRC_URI += "${GITEE_URL}/miscservices_time.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=a83d2e6957f6930f12e68cc852dd7aa3dc78cdbb;destsuffix=${OH_SRCDIR}/base/miscservices/time"
SRC_URI += "${GITEE_URL}/notification_ans_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=e1649047d30ab4d474b1d2ec2a5d7c7f66676c83;destsuffix=${OH_SRCDIR}/base/notification/ans_standard"
SRC_URI += "${GITEE_URL}/notification_ces_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6597a70c082c77681a0c7f1a9ae90830f05f0b71;destsuffix=${OH_SRCDIR}/base/notification/ces_standard"
SRC_URI += "${GITEE_URL}/powermgr_battery_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=cd2fad281f144df22863f3682e88bf41b624340d;destsuffix=${OH_SRCDIR}/base/powermgr/battery_manager"
SRC_URI += "${GITEE_URL}/powermgr_display_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=dbc471ad0ed60874d9b6fab1746d7818e5117007;destsuffix=${OH_SRCDIR}/base/powermgr/display_manager"
SRC_URI += "${GITEE_URL}/powermgr_power_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0d0fa61a167b19e815053db672491baba17ac64f;destsuffix=${OH_SRCDIR}/base/powermgr/power_manager"
SRC_URI += "${GITEE_URL}/security_appverify.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=d8bd86474d8d5092e86839835ba55797a341cbf7;destsuffix=${OH_SRCDIR}/base/security/appverify"
SRC_URI += "${GITEE_URL}/security_dataclassification.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=54dc80d288087e4592769ae801e67f95f73cccb3;destsuffix=${OH_SRCDIR}/base/security/dataclassification"
SRC_URI += "${GITEE_URL}/security_deviceauth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=f375ef89071acbafddad91e59aa4f9308a611d31;destsuffix=${OH_SRCDIR}/base/security/deviceauth"
SRC_URI += "${GITEE_URL}/security_huks.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0978a1f3f443e2b940a19dd56360ed47c63f24fd;destsuffix=${OH_SRCDIR}/base/security/huks"
SRC_URI += "${GITEE_URL}/security_permission.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=36e26ec96fa2243f988c07cd033cd9507b342c98;destsuffix=${OH_SRCDIR}/base/security/permission"
SRC_URI += "${GITEE_URL}/startup_appspawn.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6c18d48ceb65f166c2132f315ecfc899b40b683b;destsuffix=${OH_SRCDIR}/base/startup/appspawn_standard"
SRC_URI += "${GITEE_URL}/startup_init_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0fe07629934de3a3e71cbdb65815ebb56bfd4b61;destsuffix=${OH_SRCDIR}/base/startup/init_lite"
SRC_URI += "${GITEE_URL}/startup_syspara_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=1a73ffb0a72c99a8dfc94f180bc46606f06e11cd;destsuffix=${OH_SRCDIR}/base/startup/syspara_lite"
SRC_URI += "${GITEE_URL}/telephony_call_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c8a45c2478e07c3132edd6666ac00bd14d458f7d;destsuffix=${OH_SRCDIR}/base/telephony/call_manager"
SRC_URI += "${GITEE_URL}/telephony_cellular_call.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=8ccf526d0e753e1dc19c8d1789eabdef5fbca9cc;destsuffix=${OH_SRCDIR}/base/telephony/cellular_call"
SRC_URI += "${GITEE_URL}/telephony_core_service.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=f8a6f6d0a84d97d2eb799f3a0f5fa35c2ab23362;destsuffix=${OH_SRCDIR}/base/telephony/core_service"
SRC_URI += "${GITEE_URL}/telephony_ril_adapter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2107980a909a15980154ba7cf42cc58138dc480a;destsuffix=${OH_SRCDIR}/base/telephony/ril_adapter"
SRC_URI += "${GITEE_URL}/telephony_sms_mms.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=015fcdb58388e9639cad07d9c7c3af86c9648286;destsuffix=${OH_SRCDIR}/base/telephony/sms_mms"
SRC_URI += "${GITEE_URL}/telephony_state_registry.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=f0c6e8b57b191e6a53cdd26753ac655fa550f0c9;destsuffix=${OH_SRCDIR}/base/telephony/state_registry"
SRC_URI += "${GITEE_URL}/update_packaging_tools.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=edd5483176c47cd5f418c4f9d18dbbed7e5314e8;destsuffix=${OH_SRCDIR}/base/update/packaging_tools;lfs=0"
SRC_URI += "${GITEE_URL}/update_updater.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=9139aa3c9f2408afc8b4d72405ecb9ad5d1bdcab;destsuffix=${OH_SRCDIR}/base/update/updater;lfs=0"
SRC_URI += "${GITEE_URL}/update_updateservice.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=593ca7b5e2b1288b808ef49790214c393a330f41;destsuffix=${OH_SRCDIR}/base/update/updateservice"
SRC_URI += "${GITEE_URL}/build.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=873eab755ec27523e6b3a21a276eacfbdc6abc39;destsuffix=${OH_SRCDIR}/build"
SRC_URI += "${GITEE_URL}/developtools_bytrace_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b83852080c7d68e13b013deda64b3dab69554080;destsuffix=${OH_SRCDIR}/developtools/bytrace_standard"
SRC_URI += "${GITEE_URL}/developtools_hdc_standard.git;protocol=https;branch=OpenHarmony-3.1-Release;rev=5304e6ff48d783362d577b8cf1fb1b34e3e451d4;destsuffix=${OH_SRCDIR}/developtools/hdc_standard;lfs=0"
SRC_URI += "${GITEE_URL}/developtools_profiler.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2d75f87399240e900bdfc5b57f7abe6a72c4f6d1;destsuffix=${OH_SRCDIR}/developtools/profiler;lfs=0"
SRC_URI += "${GITEE_URL}/device_qemu.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2e51e55852f785ef2a3f96ad414274874ac06c46;destsuffix=${OH_SRCDIR}/device/qemu"
SRC_URI += "${GITEE_URL}/drivers_adapter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=7f68bf048bb3c4008ba313786bf357428bad3c24;destsuffix=${OH_SRCDIR}/drivers/adapter"
SRC_URI += "${GITEE_URL}/drivers_adapter_khdf_linux.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=1f0adcbe0349f6be36259556a25d881aba144a2f;destsuffix=${OH_SRCDIR}/drivers/adapter/khdf/linux"
SRC_URI += "${GITEE_URL}/drivers_framework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=a31f196451143d1156c050e12d4d1bdaa49d3170;destsuffix=${OH_SRCDIR}/drivers/framework"
SRC_URI += "${GITEE_URL}/drivers_peripheral.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=72d9184f6604b81ef2e949b8c5b5f93ed5bbf304;destsuffix=${OH_SRCDIR}/drivers/peripheral"
SRC_URI += "${GITEE_URL}/aafwk_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=fe964a1aa58181490c24533c34a16b83285f6033;destsuffix=${OH_SRCDIR}/foundation/aafwk/standard"
SRC_URI += "${GITEE_URL}/ace_ace_engine.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=8dece3b52181d4cb86fde8f6066d4654f5e22955;destsuffix=${OH_SRCDIR}/foundation/ace/ace_engine"
SRC_URI += "${GITEE_URL}/ace_napi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=a3f0278ffe8e7f68cbf9a4f63f921667b2969fbe;destsuffix=${OH_SRCDIR}/foundation/ace/napi"
SRC_URI += "${GITEE_URL}/appexecfwk_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=28db7514daacebf4392033656c1490e6b703de14;destsuffix=${OH_SRCDIR}/foundation/appexecfwk/standard"
SRC_URI += "${GITEE_URL}/communication_dsoftbus.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=3850687b3425ebdbef204a8df4925818713c90cf;destsuffix=${OH_SRCDIR}/foundation/communication/dsoftbus"
SRC_URI += "${GITEE_URL}/communication_ipc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=43d41eb78335b9629c9cdc31976b47be339a7f64;destsuffix=${OH_SRCDIR}/foundation/communication/ipc"
SRC_URI += "${GITEE_URL}/communication_wifi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=8f039126ff80813ed04ae1e9aa6d5523da33b671;destsuffix=${OH_SRCDIR}/foundation/communication/wifi"
SRC_URI += "${GITEE_URL}/distributeddatamgr_appdatamgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c6f6243e1211347287f66e9bbd7bc7c22299d5bd;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/appdatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_datamgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=db14eda98fd8c5ef2760e7f33b421c37631a0259;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributeddatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_file.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=946aac059e2b1496c58521245718d5077e756c37;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributedfile"
SRC_URI += "${GITEE_URL}/device_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=caf6a2172627f1b0084c0a9bf697e9a4ceb139d9;destsuffix=${OH_SRCDIR}/foundation/distributedhardware/devicemanager"
SRC_URI += "${GITEE_URL}/distributedschedule_dms_fwk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c10c4717767ba831df2674a19d2d110d52381bcf;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/dmsfwk"
SRC_URI += "${GITEE_URL}/distributedschedule_safwk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=54ae132edf235ecb5d4cb9a09dfce4efa79565a1;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/safwk"
SRC_URI += "${GITEE_URL}/distributedschedule_samgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0d5a71cb80dfd748bef3adb5e68d1c683c115c7d;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/samgr"
SRC_URI += "${GITEE_URL}/graphic_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=4946021ea8bc8ce51b1e8d5bf6ca029b3d241a27;destsuffix=${OH_SRCDIR}/foundation/graphic/standard"
SRC_URI += "${GITEE_URL}/multimedia_audio_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b5af881b0957916c2961c043f229828176e85452;destsuffix=${OH_SRCDIR}/foundation/multimedia/audio_standard"
SRC_URI += "${GITEE_URL}/multimedia_camera_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=5bccd8b39e07dfc34d8c09b7d94b45aa102645a7;destsuffix=${OH_SRCDIR}/foundation/multimedia/camera_standard"
SRC_URI += "${GITEE_URL}/multimedia_histreamer.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=f7737a36af7d25b3be7ce9e4bb5571137221b81c;destsuffix=${OH_SRCDIR}/foundation/multimedia/histreamer"
SRC_URI += "${GITEE_URL}/multimedia_image_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=33789ba562a00428a0b17d45c99bb2a0552325cd;destsuffix=${OH_SRCDIR}/foundation/multimedia/image_standard"
SRC_URI += "${GITEE_URL}/multimedia_medialibrary_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b7dc6bb4abfa05cce72773c78c25a5a20f30ac72;destsuffix=${OH_SRCDIR}/foundation/multimedia/medialibrary_standard"
SRC_URI += "${GITEE_URL}/multimedia_media_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=08f323e8f6de583ca723fdbe13ba1d0c668e3f95;destsuffix=${OH_SRCDIR}/foundation/multimedia/media_standard"
SRC_URI += "${GITEE_URL}/multimodalinput_input.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6da800f688c139e8b0bb806ca6655f97a6591c0e;destsuffix=${OH_SRCDIR}/foundation/multimodalinput/input"
SRC_URI += "${GITEE_URL}/productdefine_common.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=7b9be5277930ab8b77c6eeb6fc8d63535767ccb0;destsuffix=${OH_SRCDIR}/productdefine/common"
SRC_URI += "${GITEE_URL}/test_developertest.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=151309bf6cdc7e31493a3461d3c7f17a1b371c09;destsuffix=${OH_SRCDIR}/test/developertest"
SRC_URI += "${GITEE_URL}/test_xdevice.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b0845abc4da7c5deef3335f7052ec9fa29ef4c34;destsuffix=${OH_SRCDIR}/test/xdevice"
SRC_URI += "${GITEE_URL}/xts_acts.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=327e88c1285344dc1aad3ee14404307e52d9e965;destsuffix=${OH_SRCDIR}/test/xts/acts"
SRC_URI += "${GITEE_URL}/xts_tools_full.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=45ec1d2b5abf912aedbe117f8818e8fbbed513f4;destsuffix=${OH_SRCDIR}/test/xts/tools"
SRC_URI += "${GITEE_URL}/third_party_abseil-cpp.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=ada6ca6fdd88d2ba3e74fb3fd31eee4051be5fbb;destsuffix=${OH_SRCDIR}/third_party/abseil-cpp"
SRC_URI += "${GITEE_URL}/third_party_boringssl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c92e20647d2944be8294e1650481bee91dbab14e;destsuffix=${OH_SRCDIR}/third_party/boringssl;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_bounds_checking_function.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=dfa450f1a4f3bee9937e83f12ec878e54fc830ec;destsuffix=${OH_SRCDIR}/third_party/bounds_checking_function"
SRC_URI += "${GITEE_URL}/third_party_bzip2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=3fc944e95ce647874a3ee298b934f39846c6ef41;destsuffix=${OH_SRCDIR}/third_party/bzip2"
SRC_URI += "${GITEE_URL}/third_party_cares.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=1bd286ee282c7d76bc8e8eb0ca00a8c7470a82ab;destsuffix=${OH_SRCDIR}/third_party/cares"
SRC_URI += "${GITEE_URL}/third_party_cJSON.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=c3fccacd8316b80ef8332fda6a6403456a5e2c23;destsuffix=${OH_SRCDIR}/third_party/cJSON"
SRC_URI += "${GITEE_URL}/third_party_curl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=4683fbddfca910c0381e8708aacbde1c56f179d2;destsuffix=${OH_SRCDIR}/third_party/curl"
SRC_URI += "${GITEE_URL}/third_party_e2fsprogs.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=df922adc826a784d38bdc497688fd8d5e4d4b044;destsuffix=${OH_SRCDIR}/third_party/e2fsprogs"
SRC_URI += "${GITEE_URL}/third_party_ejdb.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0ae7877cee3570f9ce5b3d4c9c54fe12fc2d4262;destsuffix=${OH_SRCDIR}/third_party/ejdb"
SRC_URI += "${GITEE_URL}/third_party_eudev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6be68ab77fe334f7e9e61d498d82f772e4993ff7;destsuffix=${OH_SRCDIR}/third_party/eudev"
SRC_URI += "${GITEE_URL}/third_party_expat.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=140421a5ecf6322730bdfe857322bc284fccb144;destsuffix=${OH_SRCDIR}/third_party/expat"
SRC_URI += "${GITEE_URL}/third_party_ffmpeg.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=06b7d65d19d2f1258470d7a735dfe21934bd3213;destsuffix=${OH_SRCDIR}/third_party/ffmpeg"
SRC_URI += "${GITEE_URL}/third_party_flutter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=84794460d54ea7c55580492d3ab61ee88da5c1a2;destsuffix=${OH_SRCDIR}/third_party/flutter;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_giflib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=f936616fa048745a33ca0fd97d4473b825b6947e;destsuffix=${OH_SRCDIR}/third_party/giflib"
SRC_URI += "${GITEE_URL}/third_party_glib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=69d1a01335510cf5aaacb22080606037a3f219d9;destsuffix=${OH_SRCDIR}/third_party/glib"
SRC_URI += "${GITEE_URL}/third_party_googletest.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=fe7e36cbc95a8297f886abcae90197730997bf3a;destsuffix=${OH_SRCDIR}/third_party/googletest"
SRC_URI += "${GITEE_URL}/third_party_grpc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=11e25cced7b44f950329816913f87a14842c9d30;destsuffix=${OH_SRCDIR}/third_party/grpc;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_gstreamer.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=3615706ff8ac0d15b6846b040fa47eabc797445a;destsuffix=${OH_SRCDIR}/third_party/gstreamer;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_icu.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2e3ebcca0745070e79a70f01816545d3320c6fc6;destsuffix=${OH_SRCDIR}/third_party/icu"
SRC_URI += "${GITEE_URL}/third_party_iowow.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=1ae941f8ae6c062c55a5fd807838f6b68b6200e8;destsuffix=${OH_SRCDIR}/third_party/iowow"
SRC_URI += "${GITEE_URL}/third_party_jinja2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=581d6675a2e0c72903a23f6be97841f895e8d5b0;destsuffix=${OH_SRCDIR}/third_party/jinja2"
SRC_URI += "${GITEE_URL}/third_party_jsframework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2d56682f10913233780b1829ffa8d09976ef2e45;destsuffix=${OH_SRCDIR}/third_party/jsframework"
SRC_URI += "${GITEE_URL}/third_party_json.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=bea9d330f596aebf27f2a6e037cba4e7d5db1773;destsuffix=${OH_SRCDIR}/third_party/json"
SRC_URI += "${GITEE_URL}/third_party_jsoncpp.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=e7123406a982e6033709cced3564bcfddc18925d;destsuffix=${OH_SRCDIR}/third_party/jsoncpp"
SRC_URI += "${GITEE_URL}/third_party_libcoap.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=49657cceb4b119572ddb7e0f6e662e803020bb45;destsuffix=${OH_SRCDIR}/third_party/libcoap"
SRC_URI += "${GITEE_URL}/third_party_libdrm.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6f4f8616bd00dd6100d0d0007b95943e20e00f36;destsuffix=${OH_SRCDIR}/third_party/libdrm"
SRC_URI += "${GITEE_URL}/third_party_libevdev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=9710e41318623a7cd8d4b89f8bbcf860f2988903;destsuffix=${OH_SRCDIR}/third_party/libevdev"
SRC_URI += "${GITEE_URL}/third_party_libffi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=fddfd453553200f00ebd3a0957efc7a6cd07a83e;destsuffix=${OH_SRCDIR}/third_party/libffi"
SRC_URI += "${GITEE_URL}/third_party_libinput.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b8c1db84d3531299d6d12ec5bcd32107b99f1bd9;destsuffix=${OH_SRCDIR}/third_party/libinput"
SRC_URI += "${GITEE_URL}/third_party_libjpeg.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=9a945d8b16e45fac6166e6613bc51fc72c08fd1c;destsuffix=${OH_SRCDIR}/third_party/libjpeg"
SRC_URI += "${GITEE_URL}/third_party_libphonenumber.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=1df9cfac4f2569391d0e904b89cb58cd3409f3be;destsuffix=${OH_SRCDIR}/third_party/libphonenumber;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libpng.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=ed7e9058e9f9d40d55c14e94e34f0b9eb263bf26;destsuffix=${OH_SRCDIR}/third_party/libpng"
SRC_URI += "${GITEE_URL}/third_party_libsnd.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=11640c8570477dd9b3d741d3dfd9941b7325a98d;destsuffix=${OH_SRCDIR}/third_party/libsnd"
SRC_URI += "${GITEE_URL}/third_party_libunwind.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=60ec73774087bdecd62072a1cfbd669580c35ca2;destsuffix=${OH_SRCDIR}/third_party/libunwind"
SRC_URI += "${GITEE_URL}/third_party_libuv.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=96413c54af34a4a0c24631ac0c3cde26517f6d67;destsuffix=${OH_SRCDIR}/third_party/libuv;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libxkbcommon.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=6a1d1c70b69f2b196fec561e45effac724f21633;destsuffix=${OH_SRCDIR}/third_party/libxkbcommon"
SRC_URI += "${GITEE_URL}/third_party_libxml2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0dc10b41e44f538d94e1c6accdec968ad90b779f;destsuffix=${OH_SRCDIR}/third_party/libxml2"
SRC_URI += "${GITEE_URL}/third_party_lz4.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=dfa507fd11b5a13066e3d702283bd28442aa176d;destsuffix=${OH_SRCDIR}/third_party/lz4"
SRC_URI += "${GITEE_URL}/third_party_markupsafe.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=882baa262ebb7bc7620a3d32be8fe54a31b07fb4;destsuffix=${OH_SRCDIR}/third_party/markupsafe"
SRC_URI += "${GITEE_URL}/third_party_mbedtls.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=42e18a8f04b367d87e4aa377de5d739c00fa46d3;destsuffix=${OH_SRCDIR}/third_party/mbedtls"
SRC_URI += "${GITEE_URL}/third_party_miniz.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=9bfdbdceb9f908ec15948ae153305a5f6dbd00c6;destsuffix=${OH_SRCDIR}/third_party/miniz"
SRC_URI += "${GITEE_URL}/third_party_mksh.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=21c915cf18a64b07e8e2ebc462a6f736f88a2b03;destsuffix=${OH_SRCDIR}/third_party/mksh"
SRC_URI += "${GITEE_URL}/third_party_mtdev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=eb44e36678433ac2b23fa914350c840123b276b4;destsuffix=${OH_SRCDIR}/third_party/mtdev"
SRC_URI += "${GITEE_URL}/third_party_node.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=03403242edc6a835409f1f1183b7421e78687203;destsuffix=${OH_SRCDIR}/third_party/node;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_pixman.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=a9c75c112946193d8de911e3533163ceffc022e7;destsuffix=${OH_SRCDIR}/third_party/pixman;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_protobuf.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=51edf2fa019fa4504cd0f63b7d405e0b90e896b9;destsuffix=${OH_SRCDIR}/third_party/protobuf"
SRC_URI += "${GITEE_URL}/third_party_pulseaudio.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0f62302a576387009496f036b8b1480967d840df;destsuffix=${OH_SRCDIR}/third_party/pulseaudio"
SRC_URI += "${GITEE_URL}/third_party_qrcodegen.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=cff785c33b497199a81bffec5a278980d8ace815;destsuffix=${OH_SRCDIR}/third_party/qrcodegen"
SRC_URI += "${GITEE_URL}/third_party_quickjs.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=484ff46bef85dd98526253cb5e995ca3e7908882;destsuffix=${OH_SRCDIR}/third_party/quickjs"
SRC_URI += "${GITEE_URL}/third_party_re2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=3477d93880ee1ee27d749746d5f3df744fd63943;destsuffix=${OH_SRCDIR}/third_party/re2"
SRC_URI += "${GITEE_URL}/third_party_sqlite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=2df506013e67eb6b9acc8f4c9ad3efd317735b2b;destsuffix=${OH_SRCDIR}/third_party/sqlite"
SRC_URI += "${GITEE_URL}/third_party_toybox.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=8065f9ef151ec108227f3f439dbb6ba56c987ce0;destsuffix=${OH_SRCDIR}/third_party/toybox"
SRC_URI += "${GITEE_URL}/third_party_wayland-ivi-extension.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=ff7b005bd86a445f09d69352dc9f6ad2d7f4a88f;destsuffix=${OH_SRCDIR}/third_party/wayland-ivi-extension"
SRC_URI += "${GITEE_URL}/third_party_wayland-protocols_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=9313073cbbdc3e274d7133366efb36c2692e6e2b;destsuffix=${OH_SRCDIR}/third_party/wayland-protocols_standard"
SRC_URI += "${GITEE_URL}/third_party_wayland_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=ef48794ab9b66708865a70979a4e025558db0ce0;destsuffix=${OH_SRCDIR}/third_party/wayland_standard"
SRC_URI += "${GITEE_URL}/third_party_weston.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=a66a6bea4b93eca1077a8a2156b58084a8328b4d;destsuffix=${OH_SRCDIR}/third_party/weston"
SRC_URI += "${GITEE_URL}/third_party_wpa_supplicant.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=45069e0558911a5bebf1bb3136efd58a48b041ed;destsuffix=${OH_SRCDIR}/third_party/wpa_supplicant"
SRC_URI += "${GITEE_URL}/third_party_xkeyboardconfig.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=0a9a2de029183789a6d87f7add90d2e81f594adc;destsuffix=${OH_SRCDIR}/third_party/XKeyboardConfig"
SRC_URI += "${GITEE_URL}/third_party_zlib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=30155e711e4949ad61c48fc9cf357a0d316b3133;destsuffix=${OH_SRCDIR}/third_party/zlib"
SRC_URI += "${GITEE_URL}/utils.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=061ca56baf4817fb43d1e151734ac0903ed66b02;destsuffix=${OH_SRCDIR}/utils"
SRC_URI += "${GITEE_URL}/utils_native.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=182f938ddc964629a43838eed425f736a2ad9b6f;destsuffix=${OH_SRCDIR}/utils/native"
SRC_URI += "${GITEE_URL}/resources.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=b2263816741a1d612b91d0a5cc64ec1500a3ac2b;destsuffix=${OH_SRCDIR}/utils/resources"
SRC_URI += "${GITEE_URL}/signcenter_tool.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.0.1-LTS;rev=3e26d612c8beeb7d9ac6ff2348b3e981b3b75979;destsuffix=${OH_SRCDIR}/prebuilts/signcenter"

# TODO: This should be removed whenever upstream OpenHarmony provides qemu
# compatible gralloc implementation. Currently we have to use OpenHarmony 3.1
# HiHope implementation from a repository that contains, alongside required
# sources, binaries that we do not want. Therefore it is cloned to temporary
# repository, from which only necessary files will be copied to the OH_SRCDIR
# and the rest will be removed in the do_unpack stage postfunc.
OH_GRALLOC_TMP_DIR = "tmp_hihope"
SRC_URI += "${GITEE_URL}/device_board_hihope.git;protocol=https;branch=OpenHarmony-3.1-Release;rev=baab11f2692d9ddbebf8790a5e4a0edd48bdb9bf;destsuffix=${OH_GRALLOC_TMP_DIR};lfs=0"
SRC_URI += "https://repo.huaweicloud.com/harmonyos/compiler/restool/1.023-d/restool.tar.gz;name=restool;subdir=${OH_SRCDIR}/prebuilts/build-tools/common"
SRC_URI[restool.sha256sum] = "c76d2277a9c746240339ae4b4ea7f62da57f0ed1254cd7e3742a679464fd8fc5"
SRC_URI += "https://repo.huaweicloud.com/harmonyos/compiler/ace-loader/1.0/ace-loader-1.0.tar.gz;name=jsloader;subdir=${OH_SRCDIR}/prebuilts/sdk/js-loader/build-tools"
SRC_URI[jsloader.sha256sum] = "88dfe71c534adf48e9196abe3fcddae5f35b96dc6f7a8b871d83d0308f583cfe"

# These premirrors are used to fetch npm packages that are usually
# downloaded with 'npm install' from the 'build/prebuilts_download.sh'
# script. The main reason is because of the volatility of npm repos
# but also because these particular registry servers don't handle
# quoted characters correctly in URIs, i.e. when '@' is quoted as '%40'
PREMIRRORS:append = " \
    https://registry.npm.taobao.org/.*  http://114.116.235.68/source-mirror/ \n \
    https://registry.nlark.com/.*       http://114.116.235.68/source-mirror/ \n \
    https://registry.npmmirror.com/.*   http://114.116.235.68/source-mirror/ \n \
    https://registry.npmjs.org/.*       http://114.116.235.68/source-mirror/ \n \
    "

# NPM package shrinkwrap files. These files describe all dependencies
# needed by a npm package usually downloaded and installed by the
# command 'npm install'
SRC_URI += "npmsw://${THISDIR}/openharmony-${OPENHARMONY_VERSION}/npm-shrinkwrap_jsframework.json;dev=1;destsuffix=${OH_SRCDIR}/third_party/jsframework"
SRC_URI += "npmsw://${THISDIR}/openharmony-${OPENHARMONY_VERSION}/npm-shrinkwrap_ace-ets2bundle-compiler.json;dev=1;destsuffix=${OH_SRCDIR}/developtools/ace-ets2bundle/compiler"
SRC_URI += "npmsw://${THISDIR}/openharmony-${OPENHARMONY_VERSION}/npm-shrinkwrap_ace-js2bundle-ace-loader.json;dev=1;destsuffix=${OH_SRCDIR}/developtools/ace-js2bundle/ace-loader"
SRC_URI += "npmsw://${THISDIR}/openharmony-${OPENHARMONY_VERSION}/npm-shrinkwrap_ts2panda.json;dev=1;destsuffix=${OH_SRCDIR}/ark/ts2abc/ts2panda"
SRC_URI += "npmsw://${THISDIR}/openharmony-${OPENHARMONY_VERSION}/npm-shrinkwrap_sdk-ace-loader.json;dev=1;destsuffix=${OH_SRCDIR}/prebuilts/sdk/js-loader/build-tools/ace-loader"

# Allow network connectivity from do_unpack() task. This is needed for
# git lfs operations that are executed within do_unpack()
do_unpack[network] = "1"

npm_rebuild() {
    cd ${S}/third_party/jsframework/
    npm rebuild
    mkdir -p ${S}/prebuilts/build-tools/common/js-framework
    cp -rf ${S}/third_party/jsframework/node_modules ${S}/prebuilts/build-tools/common/js-framework/

    cd ${S}/developtools/ace-ets2bundle/compiler
    npm rebuild

    cd ${S}/developtools/ace-js2bundle/ace-loader
    npm rebuild

    cd ${S}/ark/ts2abc/ts2panda
    npm rebuild
    mkdir -p ${S}/prebuilts/build-tools/common/ts2abc
    cp -rf ${S}/ark/ts2abc/ts2panda/node_modules ${S}/prebuilts/build-tools/common/ts2abc/

    cd ${S}/prebuilts/sdk/js-loader/build-tools/ace-loader
    npm rebuild
}

do_configure[prefuncs] += "npm_rebuild"

# Create symlinks as done by the OpenHarmony repo manifest
create_symlinks() {
    if [ -f "${S}/build/core/gn/dotfile.gn" ]; then
        ln -sf "build/core/gn/dotfile.gn" "${S}/.gn"
    fi

    if [ -f "${S}/build/lite/build.py" ]; then
        ln -sf "build/lite/build.py" "${S}/build.py"
    fi

    if [ -f "${S}/build/build_scripts/build.sh" ]; then
        ln -sf "build/build_scripts/build.sh" "${S}/build.sh"
    fi

    if [ -f "${S}/test/xts/tools/build/ohos.build" ]; then
        ln -sf "tools/build/ohos.build" "${S}/test/xts/ohos.build"
    fi
}

# TODO: This should be removed whenever upstream OpenHarmony provides qemu
# compatible gralloc implementation.
# Cherry-pick display directory containing required gralloc implementation
# out of hihope board repository and remove the rest
cherry_pick_sources() {
    if [ ! -d "${WORKDIR}/${OH_GRALLOC_TMP_DIR}" ]; then
        bbfatal "OH_GRALLOC_TMP_DIR (${WORKDIR}/${OH_GRALLOC_TMP_DIR}) not found!"
    fi

    TARGET_DIR="${WORKDIR}/${OH_SRCDIR}/device/hihope/hardware/"
    install -d "${TARGET_DIR}"
    cp -R "${WORKDIR}/${OH_GRALLOC_TMP_DIR}/hardware/display" "${TARGET_DIR}"
    rm -r "${WORKDIR}/${OH_GRALLOC_TMP_DIR}"
}

do_unpack[postfuncs] += "create_symlinks"
do_unpack[postfuncs] += "cherry_pick_sources"

require oniro-prebuilts.inc
