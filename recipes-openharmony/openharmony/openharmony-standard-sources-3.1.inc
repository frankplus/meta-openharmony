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
PREMIRRORS:append = " git://gitee.com/.* http://114.116.235.68/source-mirror/ \n "

SRC_URI += "${GITEE_URL}/applications_hap.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=0abaa472ca5164e109b5d55e9ee216ff3b9a6136;destsuffix=${OH_SRCDIR}/applications/standard/hap"
SRC_URI += "${GITEE_URL}/ark_js_runtime.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=3ec6b98a29f6759894defb921127b58393f91f94;destsuffix=${OH_SRCDIR}/ark/js_runtime"
SRC_URI += "${GITEE_URL}/ark_runtime_core.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=13a664ca53b5d815248933e95be074160c5edf15;destsuffix=${OH_SRCDIR}/ark/runtime_core"
SRC_URI += "${GITEE_URL}/ark_ts2abc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=748afa32928da22beac4d6b70e31798e7186c27f;destsuffix=${OH_SRCDIR}/ark/ts2abc"
SRC_URI += "${GITEE_URL}/account_os_account.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=2ce322ef90adb0ebd28801b585f96bce6ea50afe;destsuffix=${OH_SRCDIR}/base/account/os_account"
SRC_URI += "${GITEE_URL}/js_api_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=34d4a108dfc63ee5e342033bf996adc17ee5c7f6;destsuffix=${OH_SRCDIR}/base/compileruntime/js_api_module"
SRC_URI += "${GITEE_URL}/js_sys_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e81028129d2cfd82f83b91711142ee1a585150ea;destsuffix=${OH_SRCDIR}/base/compileruntime/js_sys_module"
SRC_URI += "${GITEE_URL}/js_util_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7eaa6c3cc31fc809fe4927884c4204366c999404;destsuffix=${OH_SRCDIR}/base/compileruntime/js_util_module"
SRC_URI += "${GITEE_URL}/js_worker_module.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8a5a54674c53f5aaccdd00f5aab2a374c7c01b5f;destsuffix=${OH_SRCDIR}/base/compileruntime/js_worker_module"
SRC_URI += "${GITEE_URL}/global_i18n_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c482ef59ca4cc812ac7b821c13daa79d47eeef31;destsuffix=${OH_SRCDIR}/base/global/i18n_standard"
SRC_URI += "${GITEE_URL}/global_resmgr_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=82e075b9b79b5a773bd65dd4397819d3dee552eb;destsuffix=${OH_SRCDIR}/base/global/resmgr_standard"
SRC_URI += "${GITEE_URL}/hiviewdfx_faultloggerd.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9718c54601aade3c79c2effa47b3d1d4b8a28b45;destsuffix=${OH_SRCDIR}/base/hiviewdfx/faultloggerd"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiappevent.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=0aaa7a05ffad1fb384aae1d05d3aeb4b5b277d76;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiappevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hichecker.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9855392c445182a7694db8e2d837acceb8610ef4;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hichecker"
SRC_URI += "${GITEE_URL}/hiviewdfx_hicollie.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=bf3cac306862d52ef63a3df0e291ba71dbd3d98f;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hicollie"
SRC_URI += "${GITEE_URL}/hiviewdfx_hilog.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=af86c804b755a6aeb2376cbfd74f1de7430e634a;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hilog"
SRC_URI += "${GITEE_URL}/hiviewdfx_hisysevent.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=1bb7ce492a0918b5d38b7bc6d18e53e9564aa2ae;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hisysevent"
SRC_URI += "${GITEE_URL}/hiviewdfx_hitrace.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b0c6322dd9e44842130d464669cf40a3c439c793;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hitrace"
SRC_URI += "${GITEE_URL}/hiviewdfx_hiview.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c5032d91e47426540d0b4a2dd4f4f6d8ee86c084;destsuffix=${OH_SRCDIR}/base/hiviewdfx/hiview"
SRC_URI += "${GITEE_URL}/miscservices_inputmethod.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=79ac8622739b607392ce0aabf2e26dcb13ca3163;destsuffix=${OH_SRCDIR}/base/miscservices/inputmethod"
SRC_URI += "${GITEE_URL}/miscservices_time.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d410556e100aed5266dd9127226d160c3a7f2c10;destsuffix=${OH_SRCDIR}/base/miscservices/time"
SRC_URI += "${GITEE_URL}/msdp_device_status.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=822fdfbadc5194e684302dc462532cc1c66748e4;destsuffix=${OH_SRCDIR}/base/msdp/device_status"
SRC_URI += "${GITEE_URL}/notification_ans_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d2198f4c817ac20c106f83d5db0b06a805621885;destsuffix=${OH_SRCDIR}/base/notification/ans_standard"
SRC_URI += "${GITEE_URL}/notification_ces_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=14c38421b155e6a14f41a2e1e8dd1887c6d458d9;destsuffix=${OH_SRCDIR}/base/notification/ces_standard"
SRC_URI += "${GITEE_URL}/powermgr_battery_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8f59d2b8aae58fb145497f63f33a94d12cd95fbc;destsuffix=${OH_SRCDIR}/base/powermgr/battery_manager"
SRC_URI += "${GITEE_URL}/powermgr_display_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=908495021b2b52322f92d6730a2bf7665294765c;destsuffix=${OH_SRCDIR}/base/powermgr/display_manager"
SRC_URI += "${GITEE_URL}/powermgr_power_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a881df5076edbbac22cf077786d383c6b83fad25;destsuffix=${OH_SRCDIR}/base/powermgr/power_manager"
SRC_URI += "${GITEE_URL}/security_access_token.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8f0802e291d0d4ebbe5a90d590445279bd5f4cd9;destsuffix=${OH_SRCDIR}/base/security/access_token"
SRC_URI += "${GITEE_URL}/security_appverify.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=1d16c2caf24e4c2f9d1a270dca7836197d417ab8;destsuffix=${OH_SRCDIR}/base/security/appverify"
SRC_URI += "${GITEE_URL}/security_dataclassification.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8a2060284547abf3d030dd4cbf7a5cdd422cf60a;destsuffix=${OH_SRCDIR}/base/security/dataclassification"
SRC_URI += "${GITEE_URL}/security_deviceauth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a14db483d454d98b29a265904ed146e5f99f85b9;destsuffix=${OH_SRCDIR}/base/security/deviceauth"
SRC_URI += "${GITEE_URL}/security_device_security_level.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a8326ac7a599d7638a592947888a72b4d076418f;destsuffix=${OH_SRCDIR}/base/security/device_security_level"
SRC_URI += "${GITEE_URL}/security_huks.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e4f0c051cdbea87b93e9879ecac0efd5f2ebf75d;destsuffix=${OH_SRCDIR}/base/security/huks"
SRC_URI += "${GITEE_URL}/security_permission.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=807a32ca68f71834d54375d2f7eaf11f81a1127e;destsuffix=${OH_SRCDIR}/base/security/permission"
SRC_URI += "${GITEE_URL}/sensors_sensor.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=54999891f299b412a9b0cdbfada6b61e33cd7110;destsuffix=${OH_SRCDIR}/base/sensors/sensor"
SRC_URI += "${GITEE_URL}/startup_appspawn.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b7447b8f4f48598068ea5381f1a33905fe552214;destsuffix=${OH_SRCDIR}/base/startup/appspawn_standard"
SRC_URI += "${GITEE_URL}/startup_init_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=5158c9ade29dda41a9a8466ba78d8d17ac007936;destsuffix=${OH_SRCDIR}/base/startup/init_lite"
SRC_URI += "${GITEE_URL}/startup_syspara_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=88785cff85a32f9df6dac85b92171058c914c11d;destsuffix=${OH_SRCDIR}/base/startup/syspara_lite"
SRC_URI += "${GITEE_URL}/telephony_call_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8ddd808f72754413403ca0c5fb64cb1da352345b;destsuffix=${OH_SRCDIR}/base/telephony/call_manager"
SRC_URI += "${GITEE_URL}/telephony_cellular_call.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f90290c71124bb28aab0bd33c60cdd3b4b923022;destsuffix=${OH_SRCDIR}/base/telephony/cellular_call"
SRC_URI += "${GITEE_URL}/telephony_core_service.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9816b7e796aab583160e9cfe400ba0a3911db452;destsuffix=${OH_SRCDIR}/base/telephony/core_service"
SRC_URI += "${GITEE_URL}/telephony_ril_adapter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=12e7484e709e3bb551adf6012cfc875f972abccd;destsuffix=${OH_SRCDIR}/base/telephony/ril_adapter"
SRC_URI += "${GITEE_URL}/telephony_sms_mms.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=6d5e3e06063f4c226cfb2193ef0ac8ec1fe14d3f;destsuffix=${OH_SRCDIR}/base/telephony/sms_mms"
SRC_URI += "${GITEE_URL}/telephony_state_registry.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c2e48da440925c9fa4a8502fe6f57b68ff8a0d3e;destsuffix=${OH_SRCDIR}/base/telephony/state_registry"
SRC_URI += "${GITEE_URL}/telephony_data_storage.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=18e857e4f2d704efffc8bf6eb95320da06d77c0c;destsuffix=${OH_SRCDIR}/base/telephony/data_storage"
SRC_URI += "${GITEE_URL}/update_packaging_tools.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=5871445b5a76a21c6c6c90b2a5422bd68bb92492;destsuffix=${OH_SRCDIR}/base/update/packaging_tools;lfs=0"
SRC_URI += "${GITEE_URL}/update_updater.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=941ffd82f877e8ea79e0a13e43e8a67ef0891f57;destsuffix=${OH_SRCDIR}/base/update/updater;lfs=0"
SRC_URI += "${GITEE_URL}/update_updateservice.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9628cb3056ef8a5a612b7e351510b872436fa2da;destsuffix=${OH_SRCDIR}/base/update/updateservice"
SRC_URI += "${GITEE_URL}/useriam_auth_executor_mgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7150609094ba36e5b1eb0369b68a5ccbeeca0e7b;destsuffix=${OH_SRCDIR}/base/user_iam/auth_executor_mgr"
SRC_URI += "${GITEE_URL}/useriam_pin_auth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b26bbd8beaafed54bc3100507f1428c3b9317dcd;destsuffix=${OH_SRCDIR}/base/user_iam/pin_auth"
SRC_URI += "${GITEE_URL}/useriam_user_auth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=4d439ad3e3651293ad8effa11f1c9575e8b97c3f;destsuffix=${OH_SRCDIR}/base/user_iam/user_auth"
SRC_URI += "${GITEE_URL}/useriam_user_idm.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=6a4689e75ca22743750c7926c02d2d6e48e69526;destsuffix=${OH_SRCDIR}/base/user_iam/user_idm"
SRC_URI += "${GITEE_URL}/build.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=1dca04afe12ec4e139a9e06ff1d232e449059333;destsuffix=${OH_SRCDIR}/build"
SRC_URI += "${GITEE_URL}/build_lite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f54e49a49860e62f21c13f3913f659f061e9fbb7;destsuffix=${OH_SRCDIR}/build/lite"
SRC_URI += "${GITEE_URL}/developtools_ace-ets2bundle.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=257f7969229c0868e12414dc75ae71d4b668550a;destsuffix=${OH_SRCDIR}/developtools/ace-ets2bundle"
SRC_URI += "${GITEE_URL}/developtools_ace-js2bundle.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c3660df9c56b97da7f198293f4d37e980b24a499;destsuffix=${OH_SRCDIR}/developtools/ace-js2bundle"
SRC_URI += "${GITEE_URL}/developtools_bytrace.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=542680bb200efb3bf61c566f24a4480c763ce3d2;destsuffix=${OH_SRCDIR}/developtools/bytrace_standard"
SRC_URI += "${GITEE_URL}/developtools_hdc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c3fe03e3ef3e6f6ff33f3abc45bf47e17aa07be3;destsuffix=${OH_SRCDIR}/developtools/hdc_standard"
SRC_URI += "${GITEE_URL}/developtools_profiler.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=90a585e7347577d08524bfd6cbacce1effd88a16;destsuffix=${OH_SRCDIR}/developtools/profiler;lfs=0"
SRC_URI += "${GITEE_URL}/device_qemu.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e9888280586b394dd5b0414eb823c3ee2bb7d9ec;destsuffix=${OH_SRCDIR}/device/qemu"
SRC_URI += "${GITEE_URL}/drivers_adapter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a8aef38d2a9a25d9ffda5e5ccf96597ab702cf67;destsuffix=${OH_SRCDIR}/drivers/adapter"
SRC_URI += "${GITEE_URL}/drivers_adapter_khdf_linux.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c2122b866972b9ecf49df0e03732a8b712f3ee97;destsuffix=${OH_SRCDIR}/drivers/adapter/khdf/linux"
SRC_URI += "${GITEE_URL}/drivers_framework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=625ea6bf4d2072e2d2eee79b23d4fca512cfec70;destsuffix=${OH_SRCDIR}/drivers/framework"
SRC_URI += "${GITEE_URL}/drivers_interface.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8e147d8152bf6a6b58c70c42c73cb1bf9ef4db17;destsuffix=${OH_SRCDIR}/drivers/interface"
SRC_URI += "${GITEE_URL}/drivers_peripheral.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=ae7e4979df84de17e11ac7fc75a3f5904a131014;destsuffix=${OH_SRCDIR}/drivers/peripheral"
SRC_URI += "${GITEE_URL}/ability_ability_runtime.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=943738254da14a6faf79fec5a414c3ea2885f7f1;destsuffix=${OH_SRCDIR}/foundation/aafwk/standard"
SRC_URI += "${GITEE_URL}/arkui_ace_engine.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=357ccda311213ea260e4266b29dc3405c23077a2;destsuffix=${OH_SRCDIR}/foundation/ace/ace_engine"
SRC_URI += "${GITEE_URL}/arkui_napi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c8bdaf9d206c9f411e095731bdc39c5ac39fb2bc;destsuffix=${OH_SRCDIR}/foundation/ace/napi"
SRC_URI += "${GITEE_URL}/bundlemanager_bundle_framework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=766de00e0992ca632975aa9a919bf6a760416dd1;destsuffix=${OH_SRCDIR}/foundation/appexecfwk/standard"
SRC_URI += "${GITEE_URL}/communication_bluetooth.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=80a4879b33c8dd91fe59e72408fc1ecae19ba4be;destsuffix=${OH_SRCDIR}/foundation/communication/bluetooth"
SRC_URI += "${GITEE_URL}/communication_dsoftbus.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b7730b8fbe7c862c02b16d508365716daaa21a65;destsuffix=${OH_SRCDIR}/foundation/communication/dsoftbus"
SRC_URI += "${GITEE_URL}/communication_ipc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=5670fa3a8c92356295377ad4d63f9fa4763a1c5d;destsuffix=${OH_SRCDIR}/foundation/communication/ipc"
SRC_URI += "${GITEE_URL}/communication_netmanager_base.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a645a28411a017e85f9d3b894f06917916e0caea;destsuffix=${OH_SRCDIR}/foundation/communication/netmanager_base"
SRC_URI += "${GITEE_URL}/communication_wifi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=607f09fe4e073bc47be6befee5566a90fa60b979;destsuffix=${OH_SRCDIR}/foundation/communication/wifi"
SRC_URI += "${GITEE_URL}/device_profile_core.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8e6de1110271f4fdc84861ed520d944ea7250eb8;destsuffix=${OH_SRCDIR}/foundation/deviceprofile/device_profile_core"
SRC_URI += "${GITEE_URL}/distributeddatamgr_appdatamgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=ccce2e1af674435362880f4b9153c7dbfec67001;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/appdatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_datamgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b2b70c2443ec3b3567934d84789f16bdd41ea910;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributeddatamgr"
SRC_URI += "${GITEE_URL}/distributeddatamgr_file.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=fb6e2239db3a9d652c65b233a2984538e1f14206;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/distributedfile"
SRC_URI += "${GITEE_URL}/distributeddatamgr_objectstore.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7707cce16f50df54fc89fee7bb4a34110cf6654d;destsuffix=${OH_SRCDIR}/foundation/distributeddatamgr/objectstore"
SRC_URI += "${GITEE_URL}/device_manager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=80b28cd2361508afc9b058cb9a094f3c187cdb9e;destsuffix=${OH_SRCDIR}/foundation/distributedhardware/devicemanager"
SRC_URI += "${GITEE_URL}/distributedschedule_dms_fwk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e63b580a52b174ad8665e63de0f1200b8b7bbf1d;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/dmsfwk"
SRC_URI += "${GITEE_URL}/distributedschedule_safwk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=80363c513f62d27e592deb582a50bc87ba816962;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/safwk"
SRC_URI += "${GITEE_URL}/distributedschedule_samgr.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f58b55bfec7a86d0a980ab587471069ccec897c2;destsuffix=${OH_SRCDIR}/foundation/distributedschedule/samgr"
SRC_URI += "${GITEE_URL}/filemanagement_storage_service.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9b18a0f45ddc67b987e6bd43e51b793a1eadc36d;destsuffix=${OH_SRCDIR}/foundation/filemanagement/storage_service"
SRC_URI += "${GITEE_URL}/graphic_graphic_2d.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7bc75398314a7f9f0d64dbdd9efe75c03cf8d50b;destsuffix=${OH_SRCDIR}/foundation/graphic/standard"
SRC_URI += "${GITEE_URL}/multimedia_audio_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9083b42f0a9f66ca96c528e581dd06e96103ee5a;destsuffix=${OH_SRCDIR}/foundation/multimedia/audio_standard"
SRC_URI += "${GITEE_URL}/multimedia_camera_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9b17a4ae214660d265b394f18948e1e64fe93d2d;destsuffix=${OH_SRCDIR}/foundation/multimedia/camera_standard"
SRC_URI += "${GITEE_URL}/multimedia_histreamer.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e6b61f9e7a15aadcfa9f76211aac7b8cb183860d;destsuffix=${OH_SRCDIR}/foundation/multimedia/histreamer"
SRC_URI += "${GITEE_URL}/multimedia_image_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=2645a73c6f51b523301f475f962be5336a65e3fe;destsuffix=${OH_SRCDIR}/foundation/multimedia/image_standard"
SRC_URI += "${GITEE_URL}/multimedia_medialibrary_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=88ca47dd1058c10808dbf1ede34dbf3604576cd9;destsuffix=${OH_SRCDIR}/foundation/multimedia/medialibrary_standard"
SRC_URI += "${GITEE_URL}/multimedia_media_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=bce99de3a2afa7d8be73980b95f1e38d978dd5ce;destsuffix=${OH_SRCDIR}/foundation/multimedia/media_standard"
SRC_URI += "${GITEE_URL}/multimodalinput_input.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c4da1bfd998614c6ba9bf05d6c47e65af8bb5f8e;destsuffix=${OH_SRCDIR}/foundation/multimodalinput/input"
SRC_URI += "${GITEE_URL}/windowmanager.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d6f139e46e4af364fe99b7ba0463ece701f1914d;destsuffix=${OH_SRCDIR}/foundation/windowmanager"
SRC_URI += "${GITEE_URL}/interface_sdk-js.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c0f72aba8fe7b14e3e3ee55d692a8a9d5a3d551e;destsuffix=${OH_SRCDIR}/interface/sdk-js"
SRC_URI += "${GITEE_URL}/productdefine_common.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=2b50a713d27d07cb874e38ce702f5e687b960e7e;destsuffix=${OH_SRCDIR}/productdefine/common"
SRC_URI += "${GITEE_URL}/test_developertest.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d5608c95729dd93a0df0cb9a8ddb2c576c24a430;destsuffix=${OH_SRCDIR}/test/developertest"
SRC_URI += "${GITEE_URL}/third_party_abseil-cpp.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d4e70d1b05e1f3a8110a9a3a95e5a946cd531115;destsuffix=${OH_SRCDIR}/third_party/abseil-cpp"
SRC_URI += "${GITEE_URL}/third_party_boost.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=5bd7e37d78bfa159759369d4afbb12b5032d8aa2;destsuffix=${OH_SRCDIR}/third_party/boost;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_boringssl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=330d708e1dc16d4dd338a58b1cc3e0c8cd961e51;destsuffix=${OH_SRCDIR}/third_party/boringssl;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_bounds_checking_function.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a43d234d8b2a20588607a3284a660af1f651965f;destsuffix=${OH_SRCDIR}/third_party/bounds_checking_function"
SRC_URI += "${GITEE_URL}/third_party_bzip2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=2075b570cd4cbddb45263b5cf7085076f1e22c6b;destsuffix=${OH_SRCDIR}/third_party/bzip2"
SRC_URI += "${GITEE_URL}/third_party_cares.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e91625077a7d540ad9fbd4ea3df774effdbf24d3;destsuffix=${OH_SRCDIR}/third_party/cares"
SRC_URI += "${GITEE_URL}/third_party_cJSON.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=57a34c3083d3d97bca3e3558aaf76926971859d9;destsuffix=${OH_SRCDIR}/third_party/cJSON"
SRC_URI += "${GITEE_URL}/third_party_curl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c30eb7987ad5bd789bca620716e762129f6d5aae;destsuffix=${OH_SRCDIR}/third_party/curl"
SRC_URI += "${GITEE_URL}/third_party_e2fsprogs.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f33427ee8bf6ec2d202ead5c4efc32fdf54541cb;destsuffix=${OH_SRCDIR}/third_party/e2fsprogs"
SRC_URI += "${GITEE_URL}/third_party_ejdb.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f0463fa6c2ca3d9364525c0ca16c1787cc635976;destsuffix=${OH_SRCDIR}/third_party/ejdb"
SRC_URI += "${GITEE_URL}/third_party_egl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b8e3047530bce4e56dafa4f1ea5eea56cd77b68c;destsuffix=${OH_SRCDIR}/third_party/EGL"
SRC_URI += "${GITEE_URL}/third_party_eudev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=890f3229cc6d4c0a31e7920dc740b0916c38b5df;destsuffix=${OH_SRCDIR}/third_party/eudev"
SRC_URI += "${GITEE_URL}/third_party_exfat-utils.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=0d7230d902482e32070d715dc78eae6e076b48c0;destsuffix=${OH_SRCDIR}/third_party/exfat-utils"
SRC_URI += "${GITEE_URL}/third_party_expat.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=1f96393667d12494738d05dad2e03c96a4a8f5fe;destsuffix=${OH_SRCDIR}/third_party/expat"
SRC_URI += "${GITEE_URL}/third_party_f2fs-tools.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=045b625c0f02123d86f800c1ea1ca7587e812b40;destsuffix=${OH_SRCDIR}/third_party/f2fs-tools"
SRC_URI += "${GITEE_URL}/third_party_ffmpeg.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=695bc62151ac9043d7d655ec82a52d3315da248b;destsuffix=${OH_SRCDIR}/third_party/ffmpeg"
SRC_URI += "${GITEE_URL}/third_party_flutter.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=32b5e58093b98ef23fd1efd47f1e6d7d58e4f6b5;destsuffix=${OH_SRCDIR}/third_party/flutter;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_fsck_msdos.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c802b49daca21bad1aff8520060ef0134810572f;destsuffix=${OH_SRCDIR}/third_party/fsck_msdos"
SRC_URI += "${GITEE_URL}/third_party_gettext.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7f6b21954643e899a9d86bee83658bcef75b13d9;destsuffix=${OH_SRCDIR}/third_party/gettext"
SRC_URI += "${GITEE_URL}/third_party_giflib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=65f4f86b26527ab5f12cdf3b688b1227bbb0a678;destsuffix=${OH_SRCDIR}/third_party/giflib"
SRC_URI += "${GITEE_URL}/third_party_glib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=76d2b929e9713a1922a6bbf9c67f678eda71712f;destsuffix=${OH_SRCDIR}/third_party/glib"
SRC_URI += "${GITEE_URL}/third_party_googletest.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=1507b2df99421d8a35d8efffec7ea0addec0935c;destsuffix=${OH_SRCDIR}/third_party/googletest"
SRC_URI += "${GITEE_URL}/third_party_gptfdisk.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f7317bae19ff8b38f0cb97a60e6679740486b374;destsuffix=${OH_SRCDIR}/third_party/gptfdisk"
SRC_URI += "${GITEE_URL}/third_party_grpc.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=fc512eeefd8d5505d04ea55e57722c061953b22b;destsuffix=${OH_SRCDIR}/third_party/grpc;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_gstreamer.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f4cac98eb46987ef554c6fa014cd877c90855c75;destsuffix=${OH_SRCDIR}/third_party/gstreamer;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_icu.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=53527683521ad0fb53b573075c452b80d73327bb;destsuffix=${OH_SRCDIR}/third_party/icu"
SRC_URI += "${GITEE_URL}/third_party_iowow.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=4ceda3111f8cc40912b6da6caf9454745f9d907f;destsuffix=${OH_SRCDIR}/third_party/iowow"
SRC_URI += "${GITEE_URL}/third_party_jinja2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=efbdaa2f2324cd93237dc42ec1c032e5532d205f;destsuffix=${OH_SRCDIR}/third_party/jinja2"
SRC_URI += "${GITEE_URL}/third_party_jsframework.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=41789fbffdfb88ef7ab8dd98da46e0b7b444e823;destsuffix=${OH_SRCDIR}/third_party/jsframework"
SRC_URI += "${GITEE_URL}/third_party_json.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d7500d6ffe4a3e24c01cf94ffb125e9b984c9f2b;destsuffix=${OH_SRCDIR}/third_party/json"
SRC_URI += "${GITEE_URL}/third_party_jsoncpp.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=933db22e0cf06cd3a8a1705d78773acd907ce3f3;destsuffix=${OH_SRCDIR}/third_party/jsoncpp"
SRC_URI += "${GITEE_URL}/third_party_libcoap.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=3f413e7966723292080fa32499d1687eaefceddf;destsuffix=${OH_SRCDIR}/third_party/libcoap"
SRC_URI += "${GITEE_URL}/third_party_libdrm.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=58e7c5d2bb23be914cc438ba356c0030392b008e;destsuffix=${OH_SRCDIR}/third_party/libdrm"
SRC_URI += "${GITEE_URL}/third_party_libevdev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=ac566698bb22fce22954f0827512984d7ebd7603;destsuffix=${OH_SRCDIR}/third_party/libevdev"
SRC_URI += "${GITEE_URL}/third_party_libexif.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=e62a20a2fe8bc6d8229ea15d06f298c91c0d4e64;destsuffix=${OH_SRCDIR}/third_party/libexif"
SRC_URI += "${GITEE_URL}/third_party_libffi.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=66962913be641f90546c5a69e2abd17c35068e4d;destsuffix=${OH_SRCDIR}/third_party/libffi"
SRC_URI += "${GITEE_URL}/third_party_libinput.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c2191e5259eb2b97944905abd8c28efdcd96fea0;destsuffix=${OH_SRCDIR}/third_party/libinput"
SRC_URI += "${GITEE_URL}/third_party_libjpeg.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=064bfd10e9cf7ee82c1f5c67e4872aa2fa44f71c;destsuffix=${OH_SRCDIR}/third_party/libjpeg"
SRC_URI += "${GITEE_URL}/third_party_libnl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=3fd37ca55113ce0651aadce2384521882c86667c;destsuffix=${OH_SRCDIR}/third_party/libnl"
SRC_URI += "${GITEE_URL}/third_party_libphonenumber.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=21585c1eac7518b8360dd4652e45bca0c4a94018;destsuffix=${OH_SRCDIR}/third_party/libphonenumber;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libpng.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f9775057a8bc3b99fedc687e2a2f3b06dec60487;destsuffix=${OH_SRCDIR}/third_party/libpng"
SRC_URI += "${GITEE_URL}/third_party_libpsl.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c2e76526c400448f9474d480bd5ba7f846babf1f;destsuffix=${OH_SRCDIR}/third_party/libpsl"
SRC_URI += "${GITEE_URL}/third_party_libsnd.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7a7f66d2e843d57e94d29f4560dab1f280fc7a9d;destsuffix=${OH_SRCDIR}/third_party/libsnd"
SRC_URI += "${GITEE_URL}/third_party_libsoup.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=df61a34dd60a6ab59f0dace02dbb5486e9e1b4c6;destsuffix=${OH_SRCDIR}/third_party/libsoup"
SRC_URI += "${GITEE_URL}/third_party_libunwind.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7449c4f6ddcbcecd98bf51aadb0e718d8ad00fe7;destsuffix=${OH_SRCDIR}/third_party/libunwind"
SRC_URI += "${GITEE_URL}/third_party_libusb.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7450da22df05188eb271e18958a4e19325ed402d;destsuffix=${OH_SRCDIR}/third_party/libusb"
SRC_URI += "${GITEE_URL}/third_party_libuv.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=bea9c7bcc3e18b8af343b027e726d4bc18988c11;destsuffix=${OH_SRCDIR}/third_party/libuv;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_libxkbcommon.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=3af97c7fbfc596970de73035d6b79224f8ecaa2e;destsuffix=${OH_SRCDIR}/third_party/libxkbcommon"
SRC_URI += "${GITEE_URL}/third_party_libxml2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=461331d0b5d791d610cface8baea177d524d4f9d;destsuffix=${OH_SRCDIR}/third_party/libxml2"
SRC_URI += "${GITEE_URL}/third_party_lz4.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=580a141ef59cf7acc6e20f4ecc75e933f6b2ddee;destsuffix=${OH_SRCDIR}/third_party/lz4"
SRC_URI += "${GITEE_URL}/third_party_markupsafe.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d170c5ae1f8facb6301159f1716e484f92d3d234;destsuffix=${OH_SRCDIR}/third_party/markupsafe"
SRC_URI += "${GITEE_URL}/third_party_mbedtls.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c8cd64ee8c3f5edc590944e15ea236d9d496dc29;destsuffix=${OH_SRCDIR}/third_party/mbedtls"
SRC_URI += "${GITEE_URL}/third_party_miniz.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=8319e8cdc89c2ad80ed05c6cc9c33b875c78f621;destsuffix=${OH_SRCDIR}/third_party/miniz"
SRC_URI += "${GITEE_URL}/third_party_mksh.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b166316c7a76070bde3ad3fa328f0612fa1ffb85;destsuffix=${OH_SRCDIR}/third_party/mksh"
SRC_URI += "${GITEE_URL}/third_party_mtdev.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7f4332cd116705217ae4f7ccde0beb0e2f9f9322;destsuffix=${OH_SRCDIR}/third_party/mtdev"
SRC_URI += "${GITEE_URL}/third_party_newfs_msdos.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=431df86bb955e50851c33c8b826ad81b08381075;destsuffix=${OH_SRCDIR}/third_party/newfs_msdos"
SRC_URI += "${GITEE_URL}/third_party_nghttp2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=fa0877a0c82f51e80a866fc5e483192bcd3623ca;destsuffix=${OH_SRCDIR}/third_party/nghttp2"
SRC_URI += "${GITEE_URL}/third_party_node.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=77a64bd1bb2d4a0415e053cfa3188b429f338371;destsuffix=${OH_SRCDIR}/third_party/node;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_ntfs-3g.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=b29b2cbadb77825bf074f60e8e779afbd8bd569e;destsuffix=${OH_SRCDIR}/third_party/ntfs-3g"
SRC_URI += "${GITEE_URL}/third_party_opengles.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f6de01808a14d9d32bc3208bb502f004b8037be0;destsuffix=${OH_SRCDIR}/third_party/openGLES"
SRC_URI += "${GITEE_URL}/third_party_opensles.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=fdcfb18e8e8061238be68e55d52aa6e42ee2750e;destsuffix=${OH_SRCDIR}/third_party/openSLES"
SRC_URI += "${GITEE_URL}/third_party_parse5.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=7d06f52e72cb05090c0ea34c3f52656d9364a13c;destsuffix=${OH_SRCDIR}/third_party/parse5"
SRC_URI += "${GITEE_URL}/third_party_pcre2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=57cbb1960442c0f826deb96b914f3f30470e1263;destsuffix=${OH_SRCDIR}/third_party/pcre2"
SRC_URI += "${GITEE_URL}/third_party_pixman.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=0e233e1b237336be24fa81444e55f26a5c61e294;destsuffix=${OH_SRCDIR}/third_party/pixman;lfs=0"
SRC_URI += "${GITEE_URL}/third_party_popt.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=d0a4abf8a4ff5fd5a1c5c790f6039eadbff54739;destsuffix=${OH_SRCDIR}/third_party/popt"
SRC_URI += "${GITEE_URL}/third_party_protobuf.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f7f9dd873481192a15936f0bd3a6209898148bd3;destsuffix=${OH_SRCDIR}/third_party/protobuf"
SRC_URI += "${GITEE_URL}/third_party_pulseaudio.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=6b4553eb4b9e1a6d0d94ccf277e9d3289dec3645;destsuffix=${OH_SRCDIR}/third_party/pulseaudio"
SRC_URI += "${GITEE_URL}/third_party_pyyaml.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=5f145feb6548c5a28e8528c6f80ea8190c92b439;destsuffix=${OH_SRCDIR}/third_party/pyyaml"
SRC_URI += "${GITEE_URL}/third_party_qrcodegen.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=ff46d5232d9bc64bb2d9a4a3bd9246cab119445b;destsuffix=${OH_SRCDIR}/third_party/qrcodegen"
SRC_URI += "${GITEE_URL}/third_party_quickjs.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=0f59676c0934bb7a2b5cbdd19fee464e3d673150;destsuffix=${OH_SRCDIR}/third_party/quickjs"
SRC_URI += "${GITEE_URL}/third_party_re2.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=a3e0fcf1ccb3b21185529aaa074b8256ed047d15;destsuffix=${OH_SRCDIR}/third_party/re2"
SRC_URI += "${GITEE_URL}/third_party_selinux.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=5366d0d94bc24eb651a959722bd5c0513ef7752c;destsuffix=${OH_SRCDIR}/third_party/selinux"
SRC_URI += "${GITEE_URL}/third_party_sqlite.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c9b47fe05c022f2361d4237976017e2f51341e33;destsuffix=${OH_SRCDIR}/third_party/sqlite"
SRC_URI += "${GITEE_URL}/third_party_typescript.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=52c4e5df1634f68dace0aa76f68932bde0e47f6f;destsuffix=${OH_SRCDIR}/third_party/typescript"
SRC_URI += "${GITEE_URL}/third_party_toybox.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f4e0ecd510292055cafffcb4d4b0306e5ea609b1;destsuffix=${OH_SRCDIR}/third_party/toybox"
SRC_URI += "${GITEE_URL}/third_party_wayland-ivi-extension.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=9556378fdeb200ebb1cbaad0245fa976148d92af;destsuffix=${OH_SRCDIR}/third_party/wayland-ivi-extension"
SRC_URI += "${GITEE_URL}/third_party_wayland-protocols_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=0bc2ffadb4fa035576a2791fb2d12f725ef101ba;destsuffix=${OH_SRCDIR}/third_party/wayland-protocols_standard"
SRC_URI += "${GITEE_URL}/third_party_wayland_standard.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=f4f66ab4e3a2a7536c8b9baa2c1ea797c6cb93b8;destsuffix=${OH_SRCDIR}/third_party/wayland_standard"
SRC_URI += "${GITEE_URL}/third_party_weex-loader.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=950b4ba2a6c5568431d18f44f3cf2a005d36b2c3;destsuffix=${OH_SRCDIR}/third_party/weex-loader"
SRC_URI += "${GITEE_URL}/third_party_weston.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=015b25787779d153107990f1f843f3179ae91809;destsuffix=${OH_SRCDIR}/third_party/weston"
SRC_URI += "${GITEE_URL}/third_party_wpa_supplicant.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=3de47ca4d9731d2c94509d84b13923188f8464e3;destsuffix=${OH_SRCDIR}/third_party/wpa_supplicant"
SRC_URI += "${GITEE_URL}/third_party_xkeyboardconfig.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=095361a477e178757ee27f47ab20ff660d9691c5;destsuffix=${OH_SRCDIR}/third_party/XKeyboardConfig"
SRC_URI += "${GITEE_URL}/third_party_zlib.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=ae017ea807d6418e1c1627f975f65645a76eee6c;destsuffix=${OH_SRCDIR}/third_party/zlib"
SRC_URI += "${GITEE_URL}/utils.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=c6790398d23b88b3f421a0454553f927cde16cde;destsuffix=${OH_SRCDIR}/utils"
SRC_URI += "${GITEE_URL}/utils_native.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=72b83a79b468d8aa2b4efa4bf040583d87577897;destsuffix=${OH_SRCDIR}/utils/native"
SRC_URI += "${GITEE_URL}/resources.git;protocol=https;nobranch=1;branch=OpenHarmony-v3.1.1-Release;rev=34a081b0db3c356bd1d1e77e5b1b37127115a181;destsuffix=${OH_SRCDIR}/utils/resources"

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
