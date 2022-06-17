# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

cmake_minimum_required(VERSION 3.16)
project(packing_tool)

find_package(Java 1.8)
include(UseJava)

set(PACKING_TOOL app_packing_tool)
set(UNPACKING_TOOL app_unpacking_tool)

add_jar(${PACKING_TOOL}
	adapter/ohos/BundleException.java
	adapter/ohos/CommandParser.java
	adapter/ohos/CompressEntrance.java
	adapter/ohos/Compressor.java
	adapter/ohos/CompressVerify.java
	adapter/ohos/Log.java
	adapter/ohos/ShowHelp.java
	adapter/ohos/Utility.java
	adapter/ohos/ModuleJsonUtil.java
	adapter/ohos/Version.java
	ENTRY_POINT ohos.CompressEntrance
)

install_jar(${PACKING_TOOL} lib)

add_jar(${UNPACKING_TOOL}
	adapter/ohos/BundleException.java
	adapter/ohos/CommandParser.java
	adapter/ohos/Log.java
	adapter/ohos/ShowHelp.java
	adapter/ohos/UncompressEntrance.java
	adapter/ohos/Uncompress.java
	adapter/ohos/UncompressVerify.java
	adapter/ohos/Utility.java
	adapter/ohos/HapInfo.java
	adapter/ohos/ReqPermission.java
	adapter/ohos/Distro.java
	adapter/ohos/AbilityInfo.java
	adapter/ohos/MetaData.java
	adapter/ohos/MetaDataInfo.java
	adapter/ohos/SkillInfo.java
	adapter/ohos/UsedScene.java
	adapter/ohos/PackInfo.java
	adapter/ohos/FormInfo.java
	adapter/ohos/ProfileInfo.java
	adapter/ohos/AppInfo.java
	adapter/ohos/DefPermission.java
	adapter/ohos/DefPermissionGroup.java
	adapter/ohos/CustomizeData.java
	adapter/ohos/UncomperssResult.java
	adapter/ohos/JsonUtil.java
	adapter/ohos/JsInfo.java
	adapter/ohos/DeviceConfig.java
	adapter/ohos/CommonEvent.java
	adapter/ohos/Shortcut.java
	adapter/ohos/IntentInfo.java
	adapter/ohos/ResourcesParser.java
	adapter/ohos/DistroFilter.java
	adapter/ohos/ApiVersion.java
	adapter/ohos/ScreenShape.java
	adapter/ohos/ScreenDensity.java
	adapter/ohos/ScreenWindow.java
	adapter/ohos/CountryCode.java
	adapter/ohos/HapZipInfo.java
	adapter/ohos/AbilityFormInfo.java
	INCLUDE_JARS lib/fastjson.jar
	ENTRY_POINT ohos.UncompressEntrance
)

install_jar(${UNPACKING_TOOL} lib)
