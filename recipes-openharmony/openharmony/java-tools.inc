# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Make sure we have java tools in $PATH

DEPENDS:append:oniro-openharmony-linux = " openjdk-8-native"
INHERIT:append:oniro-openharmony-linux = " java"
JAVA_BUILD_VERSION:oniro-openharmony-linux = "openjdk-8-native"
export JAVA_HOME:oniro-openharmony-linux = "${STAGING_LIBDIR_JVM_NATIVE}/${JAVA_BUILD_VERSION}"
export CLASSPATH:oniro-openharmony-linux += "${STAGING_LIBDIR_JVM_NATIVE}/${JAVA_BUILD_VERSION}/lib/tools.jar"
EXTRANATIVEPATH:append:oniro-openharmony-linux = " ../${baselib}/jvm/${JAVA_BUILD_VERSION}/bin"
