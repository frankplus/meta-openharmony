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

SRC_URI += "${GITEE_URL}/applications_hap.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ac29b80bbcd20b373df32311b7f0923524447fd3;destsuffix=${OH_SRCDIR}/applications/standard/hap"
SRC_URI += "${GITEE_URL}/ark_js_runtime.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f33d1e4800c2f3d9af3598f3965d3f3ec8a7b2f1;destsuffix=${OH_SRCDIR}/ark/js_runtime"
SRC_URI += "${GITEE_URL}/ark_runtime_core.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ac5b288f8a9d398533d63619331317083e5e95d8;destsuffix=${OH_SRCDIR}/ark/runtime_core"
SRC_URI += "${GITEE_URL}/ark_ts2abc.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=44c8034747fd69186af1605e4fb244d666d445d9;destsuffix=${OH_SRCDIR}/ark/ts2abc"
SRC_URI += "${GITEE_URL}/account_os_account.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=64c7b48fc61d4326aae46f57fd355c809a85ffca;destsuffix=${OH_SRCDIR}/base/account/os_account"
SRC_URI += "${GITEE_URL}/js_api_module.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=97d5509ef51b6b8340713d7e7aa45cf842351938;destsuffix=${OH_SRCDIR}/base/compileruntime/js_api_module"
SRC_URI += "${GITEE_URL}/js_sys_module.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=522cc8694def8d9fd8e8083348cf30757ccdc761;destsuffix=${OH_SRCDIR}/base/compileruntime/js_sys_module"
SRC_URI += "${GITEE_URL}/js_util_module.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=7b636972b36873bee39b0f4e7a7c25b9f3c15393;destsuffix=${OH_SRCDIR}/base/compileruntime/js_util_module"
SRC_URI += "${GITEE_URL}/js_worker_module.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=90b928a31ee40fddf91532a021418f24904accc1;destsuffix=${OH_SRCDIR}/base/compileruntime/js_worker_module"
SRC_URI += "${GITEE_URL}/global_i18n_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=59bbee215c1b8d853f48cb1899346c8628264a6f;destsuffix=${OH_SRCDIR}/base/global/i18n_standard"
SRC_URI += "${GITEE_URL}/global_resmgr_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=c919433431c86c0e876e41958aedc046fa5e1788;destsuffix=${OH_SRCDIR}/base/global/resmgr_standard"
SRC_URI += "${GITEE_URL}/hiviewdfx_faultloggerd.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=3979bbfb05cb87eac8d55be2dc1e5d262fab9405;destsuffix=${OH_SRCDIR}/base/hiviewdfx/faultloggerd"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiappevent.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=098412d15228f9cdbdb8dc5c152d3d5dc3b49d47;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiappevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hicollie.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=1fd8eaa5df0812fb062e8fc2a209199372929ab1;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hicollie"
SRC_URI += "${GITEE_URL}/hiviewdfx_hilog.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f52ae28af5c23cc0449abbc752940a2e4eefb2aa;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hilog"
SRC_URI += "${GITEE_URL}/hiviewdfx_hisysevent.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b249e2452715fc9a123db3b799a2a0e316631bc2;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hisysevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hitrace.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=adb9b446c63a049fac614ad777569b629b21fc39;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hitrace"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiview.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=32251dad5b7623631288a1947518e03494c1f7a4;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiview"
SRC_URI += "${GITEE_URL}/miscservices_inputmethod.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ecbacd7eba9bf7e62345c370f852dc0cc945e003;destsuffix=${OH_SRCDIR}/base/miscservices/inputmethod"
SRC_URI += "${GITEE_URL}/miscservices_time.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=43b76bc99f14117191e5f4b5a891b3f6c5309b50;destsuffix=${OH_SRCDIR}/base/miscservices/time"
SRC_URI += "${GITEE_URL}/notification_ans_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=e31ad068442db8ac2a5b92e030405cd052a773d2;destsuffix=${OH_SRCDIR}/base/notification/ans_standard"
SRC_URI += "${GITEE_URL}/notification_ces_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=510133d3ded7f39dbac11adda625a950868404f6;destsuffix=${OH_SRCDIR}/base/notification/ces_standard"
SRC_URI += "${GITEE_URL}/powermgr_battery_manager.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=efbfc22072f9fead6e3ab5f249a47a4cd20a38b8;destsuffix=${OH_SRCDIR}/base/powermgr/battery_manager"
SRC_URI += "${GITEE_URL}/powermgr_display_manager.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=69164482cdd6b437df51125ffd13d21b01968d39;destsuffix=${OH_SRCDIR}/base/powermgr/display_manager"
SRC_URI += "${GITEE_URL}/powermgr_power_manager.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=0d0fa61a167b19e815053db672491baba17ac64f;destsuffix=${OH_SRCDIR}/base/powermgr/power_manager"
SRC_URI += "${GITEE_URL}/security_appverify.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=2cabb0f76df1b8e1836532301bdb7f7e3e66495f;destsuffix=${OH_SRCDIR}/base/security/appverify"
SRC_URI += "${GITEE_URL}/security_dataclassification.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=54dc80d288087e4592769ae801e67f95f73cccb3;destsuffix=${OH_SRCDIR}/base/security/dataclassification"
SRC_URI += "${GITEE_URL}/security_deviceauth.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ae787c07373c1f6ba6009d4ed174ce5002274c90;destsuffix=${OH_SRCDIR}/base/security/deviceauth"
SRC_URI += "${GITEE_URL}/security_huks.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=06d716c25e4b1694469966764ac45b0b2f9674ab;destsuffix=${OH_SRCDIR}/base/security/huks"
SRC_URI += "${GITEE_URL}/security_permission.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=5ed87e08cd6289e6bae8fa3a93376ba455f3f69f;destsuffix=${OH_SRCDIR}/base/security/permission"
SRC_URI += "${GITEE_URL}/startup_appspawn.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ee347e93e7281ff736159fd718d887aad08ea5e0;destsuffix=${OH_SRCDIR}/base/startup/appspawn_standard"
SRC_URI += "${GITEE_URL}/startup_init_lite.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=c434c731f1dd2af0cd566551c67601920658bcd9;destsuffix=${OH_SRCDIR}/base/startup/init_lite"
SRC_URI += "${GITEE_URL}/startup_syspara_lite.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ad8f87b2e5ffdbc71234963f7274709985e256a1;destsuffix=${OH_SRCDIR}/base/startup/syspara_lite"
SRC_URI += "${GITEE_URL}/telephony_call_manager.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=c801c290a3edc4c50c90afc281d85291edc4c902;destsuffix=${OH_SRCDIR}/base/telephony/call_manager"
SRC_URI += "${GITEE_URL}/telephony_cellular_call.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=d236ff4174ada1c8fe2d8ca4a32d05c1ddfeb194;destsuffix=${OH_SRCDIR}/base/telephony/cellular_call"
SRC_URI += "${GITEE_URL}/telephony_core_service.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ba9fdca52f88ec91c8487e00bee4662bed8a9084;destsuffix=${OH_SRCDIR}/base/telephony/core_service"
SRC_URI += "${GITEE_URL}/telephony_ril_adapter.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=bd830b5e5a8673e7e04732a4c0c1fa7a8179070b;destsuffix=${OH_SRCDIR}/base/telephony/ril_adapter"
SRC_URI += "${GITEE_URL}/telephony_sms_mms.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f12f5c102dab490ad885dc602df6d147d09ba1f4;destsuffix=${OH_SRCDIR}/base/telephony/sms_mms"
SRC_URI += "${GITEE_URL}/telephony_state_registry.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=1d75b62b98a933f4c921d5e478d64680eb8f70ac;destsuffix=${OH_SRCDIR}/base/telephony/state_registry"
SRC_URI += "${GITEE_URL}/update_packaging_tools.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=edd5483176c47cd5f418c4f9d18dbbed7e5314e8;destsuffix=${OH_SRCDIR}/base/update/packaging_tools;lfs=0"
SRC_URI += "${GITEE_URL}/update_updater.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9139aa3c9f2408afc8b4d72405ecb9ad5d1bdcab;destsuffix=${OH_SRCDIR}/base/update/updater;lfs=0"
SRC_URI += "${GITEE_URL}/update_updateservice.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=593ca7b5e2b1288b808ef49790214c393a330f41;destsuffix=${OH_SRCDIR}/base/update/updateservice"
SRC_URI += "${GITEE_URL}/build.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=fae45dcbe24118d596578fd1936007b9d299a443;destsuffix=${OH_SRCDIR}/build"
SRC_URI += "${GITEE_URL}/developtools_bytrace_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b83852080c7d68e13b013deda64b3dab69554080;destsuffix=${OH_SRCDIR}/developtools/bytrace_standard"
SRC_URI += "${GITEE_URL}/developtools_hdc_standard.git;protocol=https;branch=OpenHarmony-3.1-Release;rev=5304e6ff48d783362d577b8cf1fb1b34e3e451d4;destsuffix=${OH_SRCDIR}/developtools/hdc_standard"
SRC_URI += "${GITEE_URL}/developtools_profiler.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=2d75f87399240e900bdfc5b57f7abe6a72c4f6d1;destsuffix=${OH_SRCDIR}/developtools/profiler;lfs=0"
SRC_URI += "${GITEE_URL}/device_qemu.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=00bf590c9d04ee5af641ccff7e8a02eb6ff727e0;destsuffix=${OH_SRCDIR}/device/qemu"
SRC_URI += "${GITEE_URL}/drivers_adapter.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9f6cd92cfa61cae193328e53577899f51d5b5fca;destsuffix=${OH_SRCDIR}/drivers/adapter"

# TODO: This should be removed whenever upstream OpenHarmony provides qemu
# compatible gralloc implementation. Currently we have to use OpenHarmony 3.1
# HiHope implementation from a repository that contains, alongside required
# sources, binaries that we do not want. Therefore it is cloned to temporary
# repository, from which only necessary files will be copied to the OH_SRCDIR
# and the rest will be removed in the do_unpack stage postfunc.
OH_GRALLOC_TMP_DIR = "tmp_hihope"
SRC_URI += "${GITEE_URL}/device_board_hihope.git;protocol=https;branch=OpenHarmony-3.1-Release;rev=baab11f2692d9ddbebf8790a5e4a0edd48bdb9bf;destsuffix=${OH_GRALLOC_TMP_DIR}"

# The 2 next repositories are set to different revisions from the ones
# they are set to in the official release of OpenHarmony 3.0-LTS.
# These revisions are known to boot the Linux kernel on the HiSpark AI
# Camera Dev Kit (Hi3516DV300 board), enabling support for emmc mmcblk0
# (i.e. where the rootfs resides).
SRC_URI += "${GITEE_URL}/drivers_adapter_khdf_linux.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=1ee1a4cc8d4e043f66f29b123352ab78c8d1ea6a;destsuffix=${OH_SRCDIR}/drivers/adapter/khdf/linux"
SRC_URI += "${GITEE_URL}/drivers_framework.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=0defe33c93924da28b6782a4d1c24de1db8ba56a;destsuffix=${OH_SRCDIR}/drivers/framework"

SRC_URI += "${GITEE_URL}/drivers_peripheral.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=84884e14c845553a3d1bf044f69ea6ed49dc6f47;destsuffix=${OH_SRCDIR}/drivers/peripheral"
SRC_URI += "${GITEE_URL}/aafwk_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f86ef6ffecf3b1518ac65939bf0c0a9eb5b0934b;destsuffix=${OH_SRCDIR}/foundation/aafwk/standard"
SRC_URI += "${GITEE_URL}/ace_ace_engine.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=8fb1a473b25a457958e24f630f0b11ff7657ae35;destsuffix=${OH_SRCDIR}/foundation/ace/ace_engine"
SRC_URI += "${GITEE_URL}/ace_napi.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=43a608c3684f7a5b5888d9d9f7cc5c94be436031;destsuffix=${OH_SRCDIR}/foundation/ace/napi"
SRC_URI += "${GITEE_URL}/appexecfwk_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=71d89ae6f2f87f3b305675334ac25e476540645e;destsuffix=${OH_SRCDIR}/foundation/appexecfwk/standard"
SRC_URI += "${GITEE_URL}/communication_dsoftbus.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=8b0ca49fd4a3867c3fa733cd9e9b90651b5cd949;destsuffix=${OH_SRCDIR}/foundation/communication/dsoftbus"
SRC_URI += "${GITEE_URL}/communication_ipc.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=1f8af948221d174f285633291db942ec55058fae;destsuffix=${OH_SRCDIR}/foundation/communication/ipc"
SRC_URI += "${GITEE_URL}/communication_wifi.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=13bf56fd0798d177b54d526af57e68cf0268bde4;destsuffix=${OH_SRCDIR}/foundation/communication/wifi"
SRC_URI += "${GITEE_URL}/distributeddatamgr_appdatamgr.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=c6f6243e1211347287f66e9bbd7bc7c22299d5bd;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/appdatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_datamgr.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=fa3aef618fbea855116a287c9f89bf2a433eea62;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributeddatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_file.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=56f17f030900cc7fd1f7ca9e162d02d7ecf5f64e;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributedfile"
SRC_URI += "${GITEE_URL}/device_manager.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=48c198120596a1b72dab9692eb669858aa538c1f;destsuffix=${OH_SRCDIR}/foundation/distributedhardware/devicemanager"
SRC_URI += "${GITEE_URL}/distributedschedule_dms_fwk.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=16bda0af9e7cad07a6155243f2c672e1723f0241;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/dmsfwk"
SRC_URI += "${GITEE_URL}/distributedschedule_safwk.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=54ae132edf235ecb5d4cb9a09dfce4efa79565a1;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/safwk"
SRC_URI += "${GITEE_URL}/distributedschedule_samgr.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=61cd494e57f9d316c3f91d77f539742337bb5452;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/samgr"
SRC_URI += "${GITEE_URL}/graphic_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ab404063e0b9d60ac19493f20ed55193619bb58e;destsuffix=${OH_SRCDIR}/foundation/graphic/standard"
SRC_URI += "${GITEE_URL}/multimedia_audio_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b5af881b0957916c2961c043f229828176e85452;destsuffix=${OH_SRCDIR}/foundation/multimedia/audio_standard"
SRC_URI += "${GITEE_URL}/multimedia_camera_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=5bccd8b39e07dfc34d8c09b7d94b45aa102645a7;destsuffix=${OH_SRCDIR}/foundation/multimedia/camera_standard"
SRC_URI += "${GITEE_URL}/multimedia_histreamer.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f7737a36af7d25b3be7ce9e4bb5571137221b81c;destsuffix=${OH_SRCDIR}/foundation/multimedia/histreamer"
SRC_URI += "${GITEE_URL}/multimedia_image_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=33789ba562a00428a0b17d45c99bb2a0552325cd;destsuffix=${OH_SRCDIR}/foundation/multimedia/image_standard"
SRC_URI += "${GITEE_URL}/multimedia_medialibrary_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b7dc6bb4abfa05cce72773c78c25a5a20f30ac72;destsuffix=${OH_SRCDIR}/foundation/multimedia/medialibrary_standard"
SRC_URI += "${GITEE_URL}/multimedia_media_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=08f323e8f6de583ca723fdbe13ba1d0c668e3f95;destsuffix=${OH_SRCDIR}/foundation/multimedia/media_standard"
SRC_URI += "${GITEE_URL}/multimodalinput_input.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=6da800f688c139e8b0bb806ca6655f97a6591c0e;destsuffix=${OH_SRCDIR}/foundation/multimodalinput/input"
SRC_URI += "${GITEE_URL}/productdefine_common.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=7b9be5277930ab8b77c6eeb6fc8d63535767ccb0;destsuffix=${OH_SRCDIR}/productdefine/common"
SRC_URI += "${GITEE_URL}/test_developertest.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=151309bf6cdc7e31493a3461d3c7f17a1b371c09;destsuffix=${OH_SRCDIR}/test/developertest"
SRC_URI += "${GITEE_URL}/third_party_abseil-cpp.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ada6ca6fdd88d2ba3e74fb3fd31eee4051be5fbb;destsuffix=${OH_SRCDIR}/third_party/abseil-cpp"
SRC_URI += "${GITEE_URL}/third_party_boringssl.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9b342d6210db01534ee66b8ea5bd28f39e3ad5ca;destsuffix=${OH_SRCDIR}/third_party/boringssl;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_bounds_checking_function.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=e25097d4fa5a0f0e2ed70859eda09b1ae256c182;destsuffix=${OH_SRCDIR}/third_party/bounds_checking_function"
SRC_URI += "${GITEE_URL}/third_party_bzip2.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=d19d95fc6b5df8413881625ff1cfcb9d2b2bea2e;destsuffix=${OH_SRCDIR}/third_party/bzip2"
SRC_URI += "${GITEE_URL}/third_party_cares.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=1bd286ee282c7d76bc8e8eb0ca00a8c7470a82ab;destsuffix=${OH_SRCDIR}/third_party/cares"
SRC_URI += "${GITEE_URL}/third_party_cJSON.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=c3fccacd8316b80ef8332fda6a6403456a5e2c23;destsuffix=${OH_SRCDIR}/third_party/cJSON"
SRC_URI += "${GITEE_URL}/third_party_curl.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=4683fbddfca910c0381e8708aacbde1c56f179d2;destsuffix=${OH_SRCDIR}/third_party/curl"
SRC_URI += "${GITEE_URL}/third_party_e2fsprogs.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=538c30fa8b3e16dda183f96d315dfcd5cc9d52a9;destsuffix=${OH_SRCDIR}/third_party/e2fsprogs"
SRC_URI += "${GITEE_URL}/third_party_ejdb.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=c377ccf8f1d262a8d7894b983f9267212b93f34a;destsuffix=${OH_SRCDIR}/third_party/ejdb"
SRC_URI += "${GITEE_URL}/third_party_eudev.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=6be68ab77fe334f7e9e61d498d82f772e4993ff7;destsuffix=${OH_SRCDIR}/third_party/eudev"
SRC_URI += "${GITEE_URL}/third_party_expat.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=140421a5ecf6322730bdfe857322bc284fccb144;destsuffix=${OH_SRCDIR}/third_party/expat"
SRC_URI += "${GITEE_URL}/third_party_ffmpeg.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b9778734e513d8fc4828b33beb68cf8cb0167ed7;destsuffix=${OH_SRCDIR}/third_party/ffmpeg"
SRC_URI += "${GITEE_URL}/third_party_flatbuffers.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=503ba88ad7048df58d4fce0443f681c77f05f354;destsuffix=${OH_SRCDIR}/third_party/flatbuffers;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_flutter.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=84794460d54ea7c55580492d3ab61ee88da5c1a2;destsuffix=${OH_SRCDIR}/third_party/flutter;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_giflib.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f936616fa048745a33ca0fd97d4473b825b6947e;destsuffix=${OH_SRCDIR}/third_party/giflib"
SRC_URI += "${GITEE_URL}/third_party_glib.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=69d1a01335510cf5aaacb22080606037a3f219d9;destsuffix=${OH_SRCDIR}/third_party/glib"
SRC_URI += "${GITEE_URL}/third_party_googletest.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=fe7e36cbc95a8297f886abcae90197730997bf3a;destsuffix=${OH_SRCDIR}/third_party/googletest"
SRC_URI += "${GITEE_URL}/third_party_grpc.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=2a831401a0c68fa42eef2b90d9b09fd842ae433b;destsuffix=${OH_SRCDIR}/third_party/grpc;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_gstreamer.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=3615706ff8ac0d15b6846b040fa47eabc797445a;destsuffix=${OH_SRCDIR}/third_party/gstreamer;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_icu.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=f4070a206ca2eab6eb6f9aa1ec4da2b5193451d6;destsuffix=${OH_SRCDIR}/third_party/icu"
SRC_URI += "${GITEE_URL}/third_party_iowow.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=47cbf8e30b98c343c8f90839f6c3474147088c40;destsuffix=${OH_SRCDIR}/third_party/iowow"
SRC_URI += "${GITEE_URL}/third_party_jinja2.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=581d6675a2e0c72903a23f6be97841f895e8d5b0;destsuffix=${OH_SRCDIR}/third_party/jinja2"
SRC_URI += "${GITEE_URL}/third_party_jsframework.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=0fd6b5b2765433a453c2f946270aaddcc9aa060c;destsuffix=${OH_SRCDIR}/third_party/jsframework"
SRC_URI += "${GITEE_URL}/third_party_json.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=bea9d330f596aebf27f2a6e037cba4e7d5db1773;destsuffix=${OH_SRCDIR}/third_party/json"
SRC_URI += "${GITEE_URL}/third_party_jsoncpp.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=e7123406a982e6033709cced3564bcfddc18925d;destsuffix=${OH_SRCDIR}/third_party/jsoncpp"
SRC_URI += "${GITEE_URL}/third_party_libcoap.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=49657cceb4b119572ddb7e0f6e662e803020bb45;destsuffix=${OH_SRCDIR}/third_party/libcoap"
SRC_URI += "${GITEE_URL}/third_party_libdrm.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=3e400d72c491c042e268b7dfe8c138403e19ea7b;destsuffix=${OH_SRCDIR}/third_party/libdrm"
SRC_URI += "${GITEE_URL}/third_party_libevdev.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9710e41318623a7cd8d4b89f8bbcf860f2988903;destsuffix=${OH_SRCDIR}/third_party/libevdev"
SRC_URI += "${GITEE_URL}/third_party_libffi.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=fddfd453553200f00ebd3a0957efc7a6cd07a83e;destsuffix=${OH_SRCDIR}/third_party/libffi"
SRC_URI += "${GITEE_URL}/third_party_libinput.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b8c1db84d3531299d6d12ec5bcd32107b99f1bd9;destsuffix=${OH_SRCDIR}/third_party/libinput"
SRC_URI += "${GITEE_URL}/third_party_libjpeg.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9a945d8b16e45fac6166e6613bc51fc72c08fd1c;destsuffix=${OH_SRCDIR}/third_party/libjpeg"
SRC_URI += "${GITEE_URL}/third_party_libphonenumber.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=1df9cfac4f2569391d0e904b89cb58cd3409f3be;destsuffix=${OH_SRCDIR}/third_party/libphonenumber;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libpng.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ed7e9058e9f9d40d55c14e94e34f0b9eb263bf26;destsuffix=${OH_SRCDIR}/third_party/libpng"
SRC_URI += "${GITEE_URL}/third_party_libsnd.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=11640c8570477dd9b3d741d3dfd9941b7325a98d;destsuffix=${OH_SRCDIR}/third_party/libsnd"
SRC_URI += "${GITEE_URL}/third_party_libunwind.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=60ec73774087bdecd62072a1cfbd669580c35ca2;destsuffix=${OH_SRCDIR}/third_party/libunwind"
SRC_URI += "${GITEE_URL}/third_party_libuv.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=e32a371f239431a423559c65f6546cad87ee7ab0;destsuffix=${OH_SRCDIR}/third_party/libuv;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libxkbcommon.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=6a1d1c70b69f2b196fec561e45effac724f21633;destsuffix=${OH_SRCDIR}/third_party/libxkbcommon"
SRC_URI += "${GITEE_URL}/third_party_libxml2.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=0dc10b41e44f538d94e1c6accdec968ad90b779f;destsuffix=${OH_SRCDIR}/third_party/libxml2"
SRC_URI += "${GITEE_URL}/third_party_lz4.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=dfa507fd11b5a13066e3d702283bd28442aa176d;destsuffix=${OH_SRCDIR}/third_party/lz4"
SRC_URI += "${GITEE_URL}/third_party_markupsafe.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=882baa262ebb7bc7620a3d32be8fe54a31b07fb4;destsuffix=${OH_SRCDIR}/third_party/markupsafe"
SRC_URI += "${GITEE_URL}/third_party_mbedtls.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=a232044d6adf73c37caf7f53ff78d99cfcd851af;destsuffix=${OH_SRCDIR}/third_party/mbedtls"
SRC_URI += "${GITEE_URL}/third_party_miniz.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9bfdbdceb9f908ec15948ae153305a5f6dbd00c6;destsuffix=${OH_SRCDIR}/third_party/miniz"
SRC_URI += "${GITEE_URL}/third_party_mksh.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=21c915cf18a64b07e8e2ebc462a6f736f88a2b03;destsuffix=${OH_SRCDIR}/third_party/mksh"
SRC_URI += "${GITEE_URL}/third_party_mtdev.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=eb44e36678433ac2b23fa914350c840123b276b4;destsuffix=${OH_SRCDIR}/third_party/mtdev"
SRC_URI += "${GITEE_URL}/third_party_node.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=326033590925128d4b8296e41b4a89b24d11591f;destsuffix=${OH_SRCDIR}/third_party/node;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_pixman.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=a9c75c112946193d8de911e3533163ceffc022e7;destsuffix=${OH_SRCDIR}/third_party/pixman;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_protobuf.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=4cb0b799c61dc1c294ec26fbc8ee5bbc4d7ce51e;destsuffix=${OH_SRCDIR}/third_party/protobuf"
SRC_URI += "${GITEE_URL}/third_party_pulseaudio.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=0f62302a576387009496f036b8b1480967d840df;destsuffix=${OH_SRCDIR}/third_party/pulseaudio"
SRC_URI += "${GITEE_URL}/third_party_qrcodegen.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=cff785c33b497199a81bffec5a278980d8ace815;destsuffix=${OH_SRCDIR}/third_party/qrcodegen"
SRC_URI += "${GITEE_URL}/third_party_quickjs.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=d89f046cfb70e447aa3b47284b11478bde3a403a;destsuffix=${OH_SRCDIR}/third_party/quickjs"
SRC_URI += "${GITEE_URL}/third_party_re2.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=3477d93880ee1ee27d749746d5f3df744fd63943;destsuffix=${OH_SRCDIR}/third_party/re2"
SRC_URI += "${GITEE_URL}/third_party_sqlite.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=6ae8f77815102cadf6caeb5e9c1f7d4df953b0c9;destsuffix=${OH_SRCDIR}/third_party/sqlite"
SRC_URI += "${GITEE_URL}/third_party_toybox.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=8065f9ef151ec108227f3f439dbb6ba56c987ce0;destsuffix=${OH_SRCDIR}/third_party/toybox"
SRC_URI += "${GITEE_URL}/third_party_wayland-ivi-extension.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=ff7b005bd86a445f09d69352dc9f6ad2d7f4a88f;destsuffix=${OH_SRCDIR}/third_party/wayland-ivi-extension"
SRC_URI += "${GITEE_URL}/third_party_wayland-protocols_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=9313073cbbdc3e274d7133366efb36c2692e6e2b;destsuffix=${OH_SRCDIR}/third_party/wayland-protocols_standard"
SRC_URI += "${GITEE_URL}/third_party_wayland_standard.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=a8d253a9704917c120aacbac811c13eb739a62b7;destsuffix=${OH_SRCDIR}/third_party/wayland_standard"
SRC_URI += "${GITEE_URL}/third_party_weston.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=6661ace36d5927c4d97a95af1b91dea33ea24c19;destsuffix=${OH_SRCDIR}/third_party/weston"
SRC_URI += "${GITEE_URL}/third_party_wpa_supplicant.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=45069e0558911a5bebf1bb3136efd58a48b041ed;destsuffix=${OH_SRCDIR}/third_party/wpa_supplicant"
SRC_URI += "${GITEE_URL}/third_party_xkeyboardconfig.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=0a9a2de029183789a6d87f7add90d2e81f594adc;destsuffix=${OH_SRCDIR}/third_party/XKeyboardConfig"
SRC_URI += "${GITEE_URL}/third_party_zlib.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=a63dfcb64a95451403c52d6df16e3f086ac5a365;destsuffix=${OH_SRCDIR}/third_party/zlib"
SRC_URI += "${GITEE_URL}/utils.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=061ca56baf4817fb43d1e151734ac0903ed66b02;destsuffix=${OH_SRCDIR}/utils"
SRC_URI += "${GITEE_URL}/utils_native.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=182f938ddc964629a43838eed425f736a2ad9b6f;destsuffix=${OH_SRCDIR}/utils/native"
SRC_URI += "${GITEE_URL}/resources.git;protocol=https;branch=OpenHarmony-3.0-LTS;rev=b2263816741a1d612b91d0a5cc64ec1500a3ac2b;destsuffix=${OH_SRCDIR}/utils/resources"

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
