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
#MIRRORS:append = " git://gitee.com/.* http://114.116.235.68/source-mirror/ \n "

SRC_URI += "${GITEE_URL}/applications_hap.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e2edb8a0804ecf020b5dffdb6493ef386a9d688b;destsuffix=${OH_SRCDIR}/applications/standard/hap"
SRC_URI += "${GITEE_URL}/ark_js_runtime.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7496452bc10b8f88d624eab6ade1d856ca526e9a;destsuffix=${OH_SRCDIR}/ark/js_runtime"
SRC_URI += "${GITEE_URL}/ark_runtime_core.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=13a664ca53b5d815248933e95be074160c5edf15;destsuffix=${OH_SRCDIR}/ark/runtime_core"
SRC_URI += "${GITEE_URL}/ark_ts2abc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=870d4d6e84bd47c019c75199df56a26c6f420e47;destsuffix=${OH_SRCDIR}/ark/ts2abc"
SRC_URI += "${GITEE_URL}/account_os_account.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=2ce322ef90adb0ebd28801b585f96bce6ea50afe;destsuffix=${OH_SRCDIR}/base/account/os_account"
SRC_URI += "${GITEE_URL}/js_api_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=4f6cc83a0caa7493d95c174eb98f152f58ef3a26;destsuffix=${OH_SRCDIR}/base/compileruntime/js_api_module"
SRC_URI += "${GITEE_URL}/js_sys_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=60b4488fe2f1b53ca2341d65a5cd40a3c13181b4;destsuffix=${OH_SRCDIR}/base/compileruntime/js_sys_module"
SRC_URI += "${GITEE_URL}/js_util_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=1ba5cfdde760ea8f3e5b8560c22f51e76ea0663e;destsuffix=${OH_SRCDIR}/base/compileruntime/js_util_module"
SRC_URI += "${GITEE_URL}/js_worker_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=8a5a54674c53f5aaccdd00f5aab2a374c7c01b5f;destsuffix=${OH_SRCDIR}/base/compileruntime/js_worker_module"
SRC_URI += "${GITEE_URL}/global_i18n_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=91ce1baca3da972d6102f236d80520791aaef335;destsuffix=${OH_SRCDIR}/base/global/i18n_standard"
SRC_URI += "${GITEE_URL}/global_resmgr_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=82e075b9b79b5a773bd65dd4397819d3dee552eb;destsuffix=${OH_SRCDIR}/base/global/resmgr_standard"
SRC_URI += "${GITEE_URL}/hiviewdfx_faultloggerd.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9718c54601aade3c79c2effa47b3d1d4b8a28b45;destsuffix=${OH_SRCDIR}/base/hiviewdfx/faultloggerd"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiappevent.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=0aaa7a05ffad1fb384aae1d05d3aeb4b5b277d76;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiappevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hichecker.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d2aaf6ba4bc6cd002f0f616e674274a2ad6d7d77;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hichecker"
SRC_URI += "${GITEE_URL}/hiviewdfx_hicollie.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=bf3cac306862d52ef63a3df0e291ba71dbd3d98f;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hicollie"
SRC_URI += "${GITEE_URL}/hiviewdfx_hilog.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b7ec2ad41bd26ce0fc37005aee02e31460ea903a;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hilog"
SRC_URI += "${GITEE_URL}/hiviewdfx_hisysevent.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=1bb7ce492a0918b5d38b7bc6d18e53e9564aa2ae;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hisysevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hitrace.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=fc53a2a6ce663e41e350fd4e5c93d7566c15cdc7;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hitrace"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiview.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=55381cc8723134a85931023ebeb6a0d9334a20f6;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiview"
SRC_URI += "${GITEE_URL}/miscservices_inputmethod.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d63fa347fc0e2a34aa5d44e247b13d1caec072a0;destsuffix=${OH_SRCDIR}/base/miscservices/inputmethod"
SRC_URI += "${GITEE_URL}/miscservices_time.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9853f64117d8c741179ff3368805ff00dda42b53;destsuffix=${OH_SRCDIR}/base/miscservices/time"
SRC_URI += "${GITEE_URL}/msdp_device_status.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=20e7848b8a2e28ca150b4081628371be353f1e00;destsuffix=${OH_SRCDIR}/base/msdp/device_status"
SRC_URI += "${GITEE_URL}/notification_ans_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=bf33a670b759fed2efc13a52c7a3315386540f91;destsuffix=${OH_SRCDIR}/base/notification/ans_standard"
SRC_URI += "${GITEE_URL}/notification_ces_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=928347336741b235560f47eeceaa931fcfaf3b0a;destsuffix=${OH_SRCDIR}/base/notification/ces_standard"
SRC_URI += "${GITEE_URL}/powermgr_battery_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d3a26759f966899bead5219e4c25f8fd42801dbb;destsuffix=${OH_SRCDIR}/base/powermgr/battery_manager"
SRC_URI += "${GITEE_URL}/powermgr_display_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b5cbf49288b5ea1baddf79d6b01d1ef261b38085;destsuffix=${OH_SRCDIR}/base/powermgr/display_manager"
SRC_URI += "${GITEE_URL}/powermgr_power_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=901e8a4690e1826a4ff7eba2f0492dff7f82316d;destsuffix=${OH_SRCDIR}/base/powermgr/power_manager"
SRC_URI += "${GITEE_URL}/security_access_token.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=ff505e5c61e6902262086786ac0b8d5201503bfe;destsuffix=${OH_SRCDIR}/base/security/access_token"
SRC_URI += "${GITEE_URL}/security_appverify.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7b4c8c80e75ef99767ef134b8118b5e244cf6b10;destsuffix=${OH_SRCDIR}/base/security/appverify"
SRC_URI += "${GITEE_URL}/security_dataclassification.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=8a2060284547abf3d030dd4cbf7a5cdd422cf60a;destsuffix=${OH_SRCDIR}/base/security/dataclassification"
SRC_URI += "${GITEE_URL}/security_deviceauth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d5b1241e77b065a1a8c560155742cdf273e22813;destsuffix=${OH_SRCDIR}/base/security/deviceauth"
SRC_URI += "${GITEE_URL}/security_device_security_level.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a8326ac7a599d7638a592947888a72b4d076418f;destsuffix=${OH_SRCDIR}/base/security/device_security_level"
SRC_URI += "${GITEE_URL}/security_huks.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b0406f663b741956ed25d5a52bcdb1eee9fcf830;destsuffix=${OH_SRCDIR}/base/security/huks"
SRC_URI += "${GITEE_URL}/security_permission.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=807a32ca68f71834d54375d2f7eaf11f81a1127e;destsuffix=${OH_SRCDIR}/base/security/permission"
SRC_URI += "${GITEE_URL}/sensors_sensor.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7eb104f754313eff2b7407dca4e4e34cbcb6c5bc;destsuffix=${OH_SRCDIR}/base/sensors/sensor"
SRC_URI += "${GITEE_URL}/startup_appspawn.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=99bda30483b7c0bbaa6232a47faa3179df2a9b49;destsuffix=${OH_SRCDIR}/base/startup/appspawn_standard"
SRC_URI += "${GITEE_URL}/startup_init_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=de320147db83f54f861f0e5d3db531dffeba578d;destsuffix=${OH_SRCDIR}/base/startup/init_lite"
SRC_URI += "${GITEE_URL}/startup_syspara_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=3191b72a5f58b9ece5a907062a8a0fb76aa63f56;destsuffix=${OH_SRCDIR}/base/startup/syspara_lite"
SRC_URI += "${GITEE_URL}/telephony_call_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6d04d6c51c0452c1ad641be2bbf9e0cee4bd8055;destsuffix=${OH_SRCDIR}/base/telephony/call_manager"
SRC_URI += "${GITEE_URL}/telephony_cellular_call.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f90290c71124bb28aab0bd33c60cdd3b4b923022;destsuffix=${OH_SRCDIR}/base/telephony/cellular_call"
SRC_URI += "${GITEE_URL}/telephony_core_service.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9816b7e796aab583160e9cfe400ba0a3911db452;destsuffix=${OH_SRCDIR}/base/telephony/core_service"
SRC_URI += "${GITEE_URL}/telephony_ril_adapter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=12e7484e709e3bb551adf6012cfc875f972abccd;destsuffix=${OH_SRCDIR}/base/telephony/ril_adapter"
SRC_URI += "${GITEE_URL}/telephony_sms_mms.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f7fe578cdb84383dca50c0ee141691731c6210c8;destsuffix=${OH_SRCDIR}/base/telephony/sms_mms"
SRC_URI += "${GITEE_URL}/telephony_state_registry.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c2e48da440925c9fa4a8502fe6f57b68ff8a0d3e;destsuffix=${OH_SRCDIR}/base/telephony/state_registry"
SRC_URI += "${GITEE_URL}/telephony_data_storage.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=18e857e4f2d704efffc8bf6eb95320da06d77c0c;destsuffix=${OH_SRCDIR}/base/telephony/data_storage"
SRC_URI += "${GITEE_URL}/update_packaging_tools.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7970de2ae7edfa419bda473bc6ed78014872b150;destsuffix=${OH_SRCDIR}/base/update/packaging_tools;lfs=0"
SRC_URI += "${GITEE_URL}/update_updater.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=941ffd82f877e8ea79e0a13e43e8a67ef0891f57;destsuffix=${OH_SRCDIR}/base/update/updater;lfs=0"
SRC_URI += "${GITEE_URL}/update_updateservice.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=5666b282ed2f5a26602ae329532b974b709b9886;destsuffix=${OH_SRCDIR}/base/update/updateservice"
SRC_URI += "${GITEE_URL}/useriam_auth_executor_mgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=049ec72e2d3728b3731ce9aed14e5dfa930d8602;destsuffix=${OH_SRCDIR}/base/user_iam/auth_executor_mgr"
SRC_URI += "${GITEE_URL}/useriam_pin_auth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b26bbd8beaafed54bc3100507f1428c3b9317dcd;destsuffix=${OH_SRCDIR}/base/user_iam/pin_auth"
SRC_URI += "${GITEE_URL}/useriam_user_auth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=4d439ad3e3651293ad8effa11f1c9575e8b97c3f;destsuffix=${OH_SRCDIR}/base/user_iam/user_auth"
SRC_URI += "${GITEE_URL}/useriam_user_idm.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6a4689e75ca22743750c7926c02d2d6e48e69526;destsuffix=${OH_SRCDIR}/base/user_iam/user_idm"
SRC_URI += "${GITEE_URL}/build.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=4c7d9b331b7d86d353dfeefaad79dfc0cfa13657;destsuffix=${OH_SRCDIR}/build"
SRC_URI += "${GITEE_URL}/build_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=802cafcda0ca5e027f665a08f616eac464f55f2c;destsuffix=${OH_SRCDIR}/build/lite"
SRC_URI += "${GITEE_URL}/developtools_ace-ets2bundle.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=89749e41067f234f0694224bd2a4a02d590336d9;destsuffix=${OH_SRCDIR}/developtools/ace-ets2bundle"
SRC_URI += "${GITEE_URL}/developtools_ace-js2bundle.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=04be8c6644a9853eefdd069cf85547ba577857f3;destsuffix=${OH_SRCDIR}/developtools/ace-js2bundle"
SRC_URI += "${GITEE_URL}/developtools_bytrace_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=542680bb200efb3bf61c566f24a4480c763ce3d2;destsuffix=${OH_SRCDIR}/developtools/bytrace_standard"
SRC_URI += "${GITEE_URL}/developtools_hdc_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-3.1-Release;rev=5304e6ff48d783362d577b8cf1fb1b34e3e451d4;destsuffix=${OH_SRCDIR}/developtools/hdc_standard"
SRC_URI += "${GITEE_URL}/developtools_profiler.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=69cc2df0204581de2dec7d19dbce4107c2e43057;destsuffix=${OH_SRCDIR}/developtools/profiler;lfs=0"
SRC_URI += "${GITEE_URL}/device_qemu.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=5a8cfa754c3696925f3d6a60e7d760c90c70787c;destsuffix=${OH_SRCDIR}/device/qemu"
SRC_URI += "${GITEE_URL}/drivers_adapter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a8aef38d2a9a25d9ffda5e5ccf96597ab702cf67;destsuffix=${OH_SRCDIR}/drivers/adapter"

# The 2 next repositories are set to different revisions from the ones
# they are set to in the official release of OpenHarmony 3.0-LTS.
# These revisions are known to boot the Linux kernel on the HiSpark AI
# Camera Dev Kit (Hi3516DV300 board), enabling support for emmc mmcblk0
# (i.e. where the rootfs resides).
SRC_URI += "${GITEE_URL}/drivers_adapter_khdf_linux.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c2122b866972b9ecf49df0e03732a8b712f3ee97;destsuffix=${OH_SRCDIR}/drivers/adapter/khdf/linux"
SRC_URI += "${GITEE_URL}/drivers_framework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=54fb2f84e045d9c0ce153f8bb8aaed18acddb968;destsuffix=${OH_SRCDIR}/drivers/framework"

SRC_URI += "${GITEE_URL}/drivers_interface.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=4bdf4e1ea870bc08024718a5775c22757d9c9582;destsuffix=${OH_SRCDIR}/drivers/interface"
SRC_URI += "${GITEE_URL}/drivers_peripheral.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6b0270147fb817a94acacaa34d418d8acb9e9223;destsuffix=${OH_SRCDIR}/drivers/peripheral"
SRC_URI += "${GITEE_URL}/aafwk_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=4c0d3f8985b2212782476d9f2010acac90fda7e3;destsuffix=${OH_SRCDIR}/foundation/aafwk/standard"
SRC_URI += "${GITEE_URL}/ace_ace_engine.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7afad33399106b8ad7dee01155ee5abcf891945b;destsuffix=${OH_SRCDIR}/foundation/ace/ace_engine"
SRC_URI += "${GITEE_URL}/ace_napi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=39818fc2a5b94036f67516d71ee958106b1ee473;destsuffix=${OH_SRCDIR}/foundation/ace/napi"
SRC_URI += "${GITEE_URL}/appexecfwk_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=2fbe791f0efcaa9fe9d9b250d620825cf46e6de3;destsuffix=${OH_SRCDIR}/foundation/appexecfwk/standard"
SRC_URI += "${GITEE_URL}/communication_bluetooth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a48bbe8b8e3cb5779b36d4b293df0c3f270b80bf;destsuffix=${OH_SRCDIR}/foundation/communication/bluetooth"
SRC_URI += "${GITEE_URL}/communication_dsoftbus.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=da97439341843dfd8f308fb7008b1855695c0ac3;destsuffix=${OH_SRCDIR}/foundation/communication/dsoftbus"
SRC_URI += "${GITEE_URL}/communication_ipc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b57e9d4f8ba9999fa628109161be18cb54c5cdc8;destsuffix=${OH_SRCDIR}/foundation/communication/ipc"
SRC_URI += "${GITEE_URL}/communication_netmanager_base.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=ca202b67da1b56758476113e4dba04fc08c4e6a2;destsuffix=${OH_SRCDIR}/foundation/communication/netmanager_base"
SRC_URI += "${GITEE_URL}/communication_wifi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=3010f18a8b65f4253da84c30a8ede420f677c54f;destsuffix=${OH_SRCDIR}/foundation/communication/wifi"
SRC_URI += "${GITEE_URL}/device_profile_core.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=8e6de1110271f4fdc84861ed520d944ea7250eb8;destsuffix=${OH_SRCDIR}/foundation/deviceprofile/device_profile_core"
SRC_URI += "${GITEE_URL}/distributeddatamgr_appdatamgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d81a3a0847ada890b54958dc8b781661ccc63b3c;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/appdatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_datamgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=bf526a43ffe09a56189941509b7e370ffde73688;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributeddatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_file.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=fc71766724b53a0c10e80e3b5c1b052492fff080;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributedfile"
SRC_URI += "${GITEE_URL}/distributeddatamgr_objectstore.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d6dcc5035e42cb301b3811d95af72295103bd52c;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/objectstore"
SRC_URI += "${GITEE_URL}/device_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9e63e807f66e4c84228023e6b4ca926854c355ab;destsuffix=${OH_SRCDIR}/foundation/distributedhardware/devicemanager"
SRC_URI += "${GITEE_URL}/distributedschedule_dms_fwk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=8ad00ac6fef89310a1287cb6707258c87d93bc97;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/dmsfwk"
SRC_URI += "${GITEE_URL}/distributedschedule_safwk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=0bacb815f8c23ef864518b4d11471dbafbafc6d3;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/safwk"
SRC_URI += "${GITEE_URL}/distributedschedule_samgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f58b55bfec7a86d0a980ab587471069ccec897c2;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/samgr"
SRC_URI += "${GITEE_URL}/filemanagement_storage_service.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9b18a0f45ddc67b987e6bd43e51b793a1eadc36d;destsuffix=${OH_SRCDIR}/foundation/filemanagement/storage_service"
SRC_URI += "${GITEE_URL}/graphic_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6715f5ef422e0dacd1729fd0d5290e776e1eece5;destsuffix=${OH_SRCDIR}/foundation/graphic/standard"
SRC_URI += "${GITEE_URL}/multimedia_audio_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e854608eda8dac9de36b64f8903d010015e96736;destsuffix=${OH_SRCDIR}/foundation/multimedia/audio_standard"
SRC_URI += "${GITEE_URL}/multimedia_camera_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c572ea10a4fd68fb3feb6cfdba1147e6291286d7;destsuffix=${OH_SRCDIR}/foundation/multimedia/camera_standard"
SRC_URI += "${GITEE_URL}/multimedia_histreamer.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e6b61f9e7a15aadcfa9f76211aac7b8cb183860d;destsuffix=${OH_SRCDIR}/foundation/multimedia/histreamer"
SRC_URI += "${GITEE_URL}/multimedia_image_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=defa14bfcc2c13112cc576f09e4e6f1039e45670;destsuffix=${OH_SRCDIR}/foundation/multimedia/image_standard"
SRC_URI += "${GITEE_URL}/multimedia_medialibrary_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=92935254b04af14f5540549d5740a27274ce1001;destsuffix=${OH_SRCDIR}/foundation/multimedia/medialibrary_standard"
SRC_URI += "${GITEE_URL}/multimedia_media_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=549fe1d69fd6dcd1337685fa0b0e03fe578955f3;destsuffix=${OH_SRCDIR}/foundation/multimedia/media_standard"
SRC_URI += "${GITEE_URL}/multimodalinput_input.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e1ced2c1a2fe882ae059a489829156a4b620f2e9;destsuffix=${OH_SRCDIR}/foundation/multimodalinput/input"
SRC_URI += "${GITEE_URL}/windowmanager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=daebf66ede55284a304101a429b0e8f73a108bff;destsuffix=${OH_SRCDIR}/foundation/windowmanager"
SRC_URI += "${GITEE_URL}/interface_sdk-js.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a929ef8077abb615158b75b74e5521d2bfe76581;destsuffix=${OH_SRCDIR}/interface"
SRC_URI += "${GITEE_URL}/productdefine_common.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f267c29dfa55db0a5b6d748aaac4ed91244c50ac;destsuffix=${OH_SRCDIR}/productdefine/common"
SRC_URI += "${GITEE_URL}/test_developertest.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=8cbc4138d1e9344a8b32e92878f768bc5591898d;destsuffix=${OH_SRCDIR}/test/developertest"
SRC_URI += "${GITEE_URL}/third_party_abseil-cpp.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a39d14eb12acc93c52cc173c0175539b95f3f63f;destsuffix=${OH_SRCDIR}/third_party/abseil-cpp"
SRC_URI += "${GITEE_URL}/third_party_boost.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=5bd7e37d78bfa159759369d4afbb12b5032d8aa2;destsuffix=${OH_SRCDIR}/third_party/boost;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_boringssl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=330d708e1dc16d4dd338a58b1cc3e0c8cd961e51;destsuffix=${OH_SRCDIR}/third_party/boringssl;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_bounds_checking_function.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a43d234d8b2a20588607a3284a660af1f651965f;destsuffix=${OH_SRCDIR}/third_party/bounds_checking_function"
SRC_URI += "${GITEE_URL}/third_party_bzip2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=2075b570cd4cbddb45263b5cf7085076f1e22c6b;destsuffix=${OH_SRCDIR}/third_party/bzip2"
SRC_URI += "${GITEE_URL}/third_party_cares.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e91625077a7d540ad9fbd4ea3df774effdbf24d3;destsuffix=${OH_SRCDIR}/third_party/cares"
SRC_URI += "${GITEE_URL}/third_party_cJSON.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=57a34c3083d3d97bca3e3558aaf76926971859d9;destsuffix=${OH_SRCDIR}/third_party/cJSON"
SRC_URI += "${GITEE_URL}/third_party_curl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6e31d7def46d82450c88fe6d017a5d43df9a6fe4;destsuffix=${OH_SRCDIR}/third_party/curl"
SRC_URI += "${GITEE_URL}/third_party_e2fsprogs.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6f4fb5108432c8f2d9e47daf4eaaa94ac9b5c705;destsuffix=${OH_SRCDIR}/third_party/e2fsprogs"
SRC_URI += "${GITEE_URL}/third_party_ejdb.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f0463fa6c2ca3d9364525c0ca16c1787cc635976;destsuffix=${OH_SRCDIR}/third_party/ejdb"
SRC_URI += "${GITEE_URL}/third_party_egl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b8e3047530bce4e56dafa4f1ea5eea56cd77b68c;destsuffix=${OH_SRCDIR}/third_party/EGL"
SRC_URI += "${GITEE_URL}/third_party_eudev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=890f3229cc6d4c0a31e7920dc740b0916c38b5df;destsuffix=${OH_SRCDIR}/third_party/eudev"
SRC_URI += "${GITEE_URL}/third_party_exfat-utils.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=93cdc776f1d80986312d82149f574d3ec078d214;destsuffix=${OH_SRCDIR}/third_party/exfat-utils"
SRC_URI += "${GITEE_URL}/third_party_expat.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c299d0a4d0e70d0809ebff81a0c81275b75736fd;destsuffix=${OH_SRCDIR}/third_party/expat"
SRC_URI += "${GITEE_URL}/third_party_f2fs-tools.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=045b625c0f02123d86f800c1ea1ca7587e812b40;destsuffix=${OH_SRCDIR}/third_party/f2fs-tools"
SRC_URI += "${GITEE_URL}/third_party_ffmpeg.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=3c2931b6ecb6abb7c845197c79a26a79fb4ded7d;destsuffix=${OH_SRCDIR}/third_party/ffmpeg"
#SRC_URI += "${GITEE_URL}/third_party_flatbuffers.git;protocol=https;nobranch=1;branch=OpenHarmony-3.0-LTS;rev=503ba88ad7048df58d4fce0443f681c77f05f354;destsuffix=${OH_SRCDIR}/third_party/flatbuffers;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_flutter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f56c51f210e04b1856cbfb2cae81c10bbb070422;destsuffix=${OH_SRCDIR}/third_party/flutter;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_fsck_msdos.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=707f44ba9bd50cc6b0aad2b22e09f8b9cfd172fb;destsuffix=${OH_SRCDIR}/third_party/fsck_msdos"
SRC_URI += "${GITEE_URL}/third_party_gettext.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7f6b21954643e899a9d86bee83658bcef75b13d9;destsuffix=${OH_SRCDIR}/third_party/gettext"
SRC_URI += "${GITEE_URL}/third_party_giflib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=65f4f86b26527ab5f12cdf3b688b1227bbb0a678;destsuffix=${OH_SRCDIR}/third_party/giflib"
SRC_URI += "${GITEE_URL}/third_party_glib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=5b9e48fb56c4a6446cba7bf3d80557736dbf52a6;destsuffix=${OH_SRCDIR}/third_party/glib"
SRC_URI += "${GITEE_URL}/third_party_googletest.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=1507b2df99421d8a35d8efffec7ea0addec0935c;destsuffix=${OH_SRCDIR}/third_party/googletest"
SRC_URI += "${GITEE_URL}/third_party_gptfdisk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f7317bae19ff8b38f0cb97a60e6679740486b374;destsuffix=${OH_SRCDIR}/third_party/gptfdisk"
SRC_URI += "${GITEE_URL}/third_party_grpc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=ee84502ea75ec1e73bd8765ea0752e8ef870b1e9;destsuffix=${OH_SRCDIR}/third_party/grpc;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_gstreamer.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9efd200a7674619bb42c93c8b3c0e997f7e5c7ed;destsuffix=${OH_SRCDIR}/third_party/gstreamer;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_icu.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=53527683521ad0fb53b573075c452b80d73327bb;destsuffix=${OH_SRCDIR}/third_party/icu"
SRC_URI += "${GITEE_URL}/third_party_iowow.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=4ceda3111f8cc40912b6da6caf9454745f9d907f;destsuffix=${OH_SRCDIR}/third_party/iowow"
SRC_URI += "${GITEE_URL}/third_party_jinja2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f6da2ab10d9663caa5c33485200019e51eda6c5b;destsuffix=${OH_SRCDIR}/third_party/jinja2"
SRC_URI += "${GITEE_URL}/third_party_jsframework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=3983741d43d282e6b50449c988ee1e8c799ba157;destsuffix=${OH_SRCDIR}/third_party/jsframework"
SRC_URI += "${GITEE_URL}/third_party_json.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b990ace7da0b27dd78b771b9dcfa07991d26dab7;destsuffix=${OH_SRCDIR}/third_party/json"
SRC_URI += "${GITEE_URL}/third_party_jsoncpp.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=933db22e0cf06cd3a8a1705d78773acd907ce3f3;destsuffix=${OH_SRCDIR}/third_party/jsoncpp"
SRC_URI += "${GITEE_URL}/third_party_libcoap.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=518e66959bb20cd264fb5a1011e89f1829a5243b;destsuffix=${OH_SRCDIR}/third_party/libcoap"
SRC_URI += "${GITEE_URL}/third_party_libdrm.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=58e7c5d2bb23be914cc438ba356c0030392b008e;destsuffix=${OH_SRCDIR}/third_party/libdrm"
SRC_URI += "${GITEE_URL}/third_party_libevdev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=ac566698bb22fce22954f0827512984d7ebd7603;destsuffix=${OH_SRCDIR}/third_party/libevdev"
SRC_URI += "${GITEE_URL}/third_party_libexif.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=34728aebee9a0a45a3b6f61fd0e09abfd38d341b;destsuffix=${OH_SRCDIR}/third_party/libexif"
SRC_URI += "${GITEE_URL}/third_party_libffi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=66962913be641f90546c5a69e2abd17c35068e4d;destsuffix=${OH_SRCDIR}/third_party/libffi"
SRC_URI += "${GITEE_URL}/third_party_libinput.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c2191e5259eb2b97944905abd8c28efdcd96fea0;destsuffix=${OH_SRCDIR}/third_party/libinput"
SRC_URI += "${GITEE_URL}/third_party_libjpeg.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=064bfd10e9cf7ee82c1f5c67e4872aa2fa44f71c;destsuffix=${OH_SRCDIR}/third_party/libjpeg"
SRC_URI += "${GITEE_URL}/third_party_libphonenumber.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=21585c1eac7518b8360dd4652e45bca0c4a94018;destsuffix=${OH_SRCDIR}/third_party/libphonenumber;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libpng.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f9775057a8bc3b99fedc687e2a2f3b06dec60487;destsuffix=${OH_SRCDIR}/third_party/libpng"
SRC_URI += "${GITEE_URL}/third_party_libpsl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c2e76526c400448f9474d480bd5ba7f846babf1f;destsuffix=${OH_SRCDIR}/third_party/libpsl"
SRC_URI += "${GITEE_URL}/third_party_libsnd.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6affbbf58ee0da24b6b0d5f2de30aa7f2a0485e4;destsuffix=${OH_SRCDIR}/third_party/libsnd"
SRC_URI += "${GITEE_URL}/third_party_libsoup.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f4ad2f9d62bc665d178111e498b3684df62e10cf;destsuffix=${OH_SRCDIR}/third_party/libsoup"
SRC_URI += "${GITEE_URL}/third_party_libunwind.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7449c4f6ddcbcecd98bf51aadb0e718d8ad00fe7;destsuffix=${OH_SRCDIR}/third_party/libunwind"
SRC_URI += "${GITEE_URL}/third_party_libusb.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=bb782398f013621e51664004acf96e5f9460fe7a;destsuffix=${OH_SRCDIR}/third_party/libusb"
SRC_URI += "${GITEE_URL}/third_party_libuv.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=bea9c7bcc3e18b8af343b027e726d4bc18988c11;destsuffix=${OH_SRCDIR}/third_party/libuv;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libxkbcommon.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=3af97c7fbfc596970de73035d6b79224f8ecaa2e;destsuffix=${OH_SRCDIR}/third_party/libxkbcommon"
SRC_URI += "${GITEE_URL}/third_party_libxml2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=1103e1dc6c23fbd59e0a0b99226efa39ff7d842e;destsuffix=${OH_SRCDIR}/third_party/libxml2"
SRC_URI += "${GITEE_URL}/third_party_lz4.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=580a141ef59cf7acc6e20f4ecc75e933f6b2ddee;destsuffix=${OH_SRCDIR}/third_party/lz4"
SRC_URI += "${GITEE_URL}/third_party_markupsafe.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=80fadad55e775dd51af9870152bfa8b8422e74c2;destsuffix=${OH_SRCDIR}/third_party/markupsafe"
SRC_URI += "${GITEE_URL}/third_party_mbedtls.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=dbea0ea33e2cf8487650171a2f4a71448c690758;destsuffix=${OH_SRCDIR}/third_party/mbedtls"
SRC_URI += "${GITEE_URL}/third_party_miniz.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=8319e8cdc89c2ad80ed05c6cc9c33b875c78f621;destsuffix=${OH_SRCDIR}/third_party/miniz"
SRC_URI += "${GITEE_URL}/third_party_mksh.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b166316c7a76070bde3ad3fa328f0612fa1ffb85;destsuffix=${OH_SRCDIR}/third_party/mksh"
SRC_URI += "${GITEE_URL}/third_party_mtdev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7f4332cd116705217ae4f7ccde0beb0e2f9f9322;destsuffix=${OH_SRCDIR}/third_party/mtdev"
SRC_URI += "${GITEE_URL}/third_party_newfs_msdos.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=2192a081b337147dca070b35cfb53e0da6bd3585;destsuffix=${OH_SRCDIR}/third_party/newfs_msdos"
SRC_URI += "${GITEE_URL}/third_party_nghttp2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=fa0877a0c82f51e80a866fc5e483192bcd3623ca;destsuffix=${OH_SRCDIR}/third_party/nghttp2"
SRC_URI += "${GITEE_URL}/third_party_node.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=77a64bd1bb2d4a0415e053cfa3188b429f338371;destsuffix=${OH_SRCDIR}/third_party/node;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_ntfs-3g.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=d9bbd80bfb99ea493d21ffde5455cbd32676462b;destsuffix=${OH_SRCDIR}/third_party/ntfs-3g"
SRC_URI += "${GITEE_URL}/third_party_opengles.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=53ebadbeb4972971615e924f2dcb4ccc8ba20be7;destsuffix=${OH_SRCDIR}/third_party/openGLES"
SRC_URI += "${GITEE_URL}/third_party_opensles.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9c32e6cb82ae10b602da58885c940b2611d506c6;destsuffix=${OH_SRCDIR}/third_party/openSLES"
SRC_URI += "${GITEE_URL}/third_party_parse5.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=7d06f52e72cb05090c0ea34c3f52656d9364a13c;destsuffix=${OH_SRCDIR}/third_party/parse5"
SRC_URI += "${GITEE_URL}/third_party_pcre2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=57cbb1960442c0f826deb96b914f3f30470e1263;destsuffix=${OH_SRCDIR}/third_party/pcre2"
SRC_URI += "${GITEE_URL}/third_party_pixman.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=0e233e1b237336be24fa81444e55f26a5c61e294;destsuffix=${OH_SRCDIR}/third_party/pixman;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_popt.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=290b5a5a203ddaabb6c0b3034515eef07b7b22ea;destsuffix=${OH_SRCDIR}/third_party/popt"
SRC_URI += "${GITEE_URL}/third_party_protobuf.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f7f9dd873481192a15936f0bd3a6209898148bd3;destsuffix=${OH_SRCDIR}/third_party/protobuf"
SRC_URI += "${GITEE_URL}/third_party_pulseaudio.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=6b4553eb4b9e1a6d0d94ccf277e9d3289dec3645;destsuffix=${OH_SRCDIR}/third_party/pulseaudio"
SRC_URI += "${GITEE_URL}/third_party_pyyaml.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=5f145feb6548c5a28e8528c6f80ea8190c92b439;destsuffix=${OH_SRCDIR}/third_party/pyyaml"
SRC_URI += "${GITEE_URL}/third_party_qrcodegen.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=ff46d5232d9bc64bb2d9a4a3bd9246cab119445b;destsuffix=${OH_SRCDIR}/third_party/qrcodegen"
SRC_URI += "${GITEE_URL}/third_party_quickjs.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=0f59676c0934bb7a2b5cbdd19fee464e3d673150;destsuffix=${OH_SRCDIR}/third_party/quickjs"
SRC_URI += "${GITEE_URL}/third_party_re2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=a3e0fcf1ccb3b21185529aaa074b8256ed047d15;destsuffix=${OH_SRCDIR}/third_party/re2"
SRC_URI += "${GITEE_URL}/third_party_selinux.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=5366d0d94bc24eb651a959722bd5c0513ef7752c;destsuffix=${OH_SRCDIR}/third_party/selinux"
SRC_URI += "${GITEE_URL}/third_party_sqlite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c9b47fe05c022f2361d4237976017e2f51341e33;destsuffix=${OH_SRCDIR}/third_party/sqlite"
SRC_URI += "${GITEE_URL}/third_party_typescript.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e00d5cbc8eaa4f37e84256ea038f1d6ec6ec2cf2;destsuffix=${OH_SRCDIR}/third_party/typescript"
SRC_URI += "${GITEE_URL}/third_party_toybox.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f4e0ecd510292055cafffcb4d4b0306e5ea609b1;destsuffix=${OH_SRCDIR}/third_party/toybox"
SRC_URI += "${GITEE_URL}/third_party_wayland-ivi-extension.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9556378fdeb200ebb1cbaad0245fa976148d92af;destsuffix=${OH_SRCDIR}/third_party/wayland-ivi-extension"
SRC_URI += "${GITEE_URL}/third_party_wayland-protocols_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=0bc2ffadb4fa035576a2791fb2d12f725ef101ba;destsuffix=${OH_SRCDIR}/third_party/wayland-protocols_standard"
SRC_URI += "${GITEE_URL}/third_party_wayland_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f4f66ab4e3a2a7536c8b9baa2c1ea797c6cb93b8;destsuffix=${OH_SRCDIR}/third_party/wayland_standard"
SRC_URI += "${GITEE_URL}/third_party_weex-loader.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=b0bc6e56faf214c65fc6774650d299c17ceed8f8;destsuffix=${OH_SRCDIR}/third_party/weex-loader"
SRC_URI += "${GITEE_URL}/third_party_weston.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=015b25787779d153107990f1f843f3179ae91809;destsuffix=${OH_SRCDIR}/third_party/weston"
SRC_URI += "${GITEE_URL}/third_party_wpa_supplicant.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=3de47ca4d9731d2c94509d84b13923188f8464e3;destsuffix=${OH_SRCDIR}/third_party/wpa_supplicant"
SRC_URI += "${GITEE_URL}/third_party_xkeyboardconfig.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=095361a477e178757ee27f47ab20ff660d9691c5;destsuffix=${OH_SRCDIR}/third_party/XKeyboardConfig"
SRC_URI += "${GITEE_URL}/third_party_zlib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=9ab2ba234581cc6ec7357011fb583a2d4d5c6576;destsuffix=${OH_SRCDIR}/third_party/zlib"
SRC_URI += "${GITEE_URL}/utils.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=c6790398d23b88b3f421a0454553f927cde16cde;destsuffix=${OH_SRCDIR}/utils"
SRC_URI += "${GITEE_URL}/utils_native.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=72b83a79b468d8aa2b4efa4bf040583d87577897;destsuffix=${OH_SRCDIR}/utils/native"
SRC_URI += "${GITEE_URL}/resources.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=f3079ef097ec279f026ab84e62fb1af645654ad6;destsuffix=${OH_SRCDIR}/utils/resources"

# Prebuilt HAP signing tool
### CONTAINS BINARIES. NEED TO BE BUILT FROM SOURCES INSTEAD ###
SRC_URI += "${GITEE_URL}/signcenter_tool.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=10ff08d5b10fac544475c634e9b36a74ad3a08ab;destsuffix=${OH_SRCDIR}/prebuilts/signcenter"
SRC_URI += "${GITEE_URL}/developtools_packing_tool.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1-Release;rev=e44a0c2f2ec99bcefee75285258dc5e90582cd6f;destsuffix=${OH_SRCDIR}/developtools/packing_tool"

SRC_URI += "https://repo.huaweicloud.com/harmonyos/compiler/restool/2.007/restool-2.007.tar.gz;name=restool;subdir=${OH_SRCDIR}/prebuilts/build-tools/common"
SRC_URI[restool.sha256sum] = "4cd763315d80ceecbde5f64f20252d7edf89f0083ac7c1794b12fbe556a9b826"

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
    https://repo.huaweicloud.com/.*     http://114.116.235.68/source-mirror/ \n \
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

do_unpack[postfuncs] += "create_symlinks"

require oniro-prebuilts.inc
