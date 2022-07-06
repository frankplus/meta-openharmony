# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# OpenHarmony OS version
OPENHARMONY_VERSION ?= "3.1"
OPENHARMONY_VERSION_FULL = "${OPENHARMONY_VERSION}"
OPENHARMONY_VERSION_FULL:openharmony-3.0 = "3.0.1"
OPENHARMONY_VERSION_FULL:openharmony-3.1 = "3.1.1"

# Generic and version specific OpenHarmony DISTROOVERRIDES
# Enabling "openharmony" distro feature activates the "df-openharmony"
# override, which can therefore be used for overriding stuff independent of
# OpenHarmony version.
# For overriding for a specific OpenHarmony version, use e.g. "openharmony-3.0"
# OpenHarmony 3.0 specific override, And similar for other supported versions.
INHERIT += "distrooverrides"
DISTRO_FEATURES_OVERRIDES += "openharmony"
OPENHARMONY_OVERRIDES = ""
OPENHARMONY_OVERRIDES:df-openharmony = ":openharmony-${OPENHARMONY_VERSION}"
DISTROOVERRIDES:append = "${OPENHARMONY_OVERRIDES}"

PREFERRED_VERSION_openharmony-standard = "${OPENHARMONY_VERSION}"
