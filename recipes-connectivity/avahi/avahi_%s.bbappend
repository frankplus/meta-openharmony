# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://hdc.service"

do_install:append () {
    install -d ${D}${sysconfdir}/avahi/services/
    install -m 0644 -g avahi ${WORKDIR}/hdc.service ${D}${sysconfdir}/avahi/services/hdc.service
}
