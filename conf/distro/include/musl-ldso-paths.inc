# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

MUSL_LDSO_PATHS = " \
	module \
	module/ability \
	module/account \
	module/app \
	module/data \
	module/distributedhardware \
	module/multimedia \
	module/telephony \
"

MUSL_LDSO_PATHS:append:openharmony-3.1 = " \
	module/application \
	module/bundle \
	module/events \
	module/multimodalinput \
	module/net \
	module/security \
"
