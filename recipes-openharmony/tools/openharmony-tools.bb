# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenHarmony development tools"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

require sanity-check.inc

TOOLCHAIN_HOST_TASK = "nativesdk-hdc nativesdk-xdevice nativesdk-qemu nativesdk-qemu-helper"
TOOLCHAIN_TARGET_TASK = ""

TOOLCHAIN_OUTPUTNAME = "${PN}-${OPENHARMONY_VERSION}-${TUNE_PKGARCH}-${SDK_VERSION}"
SDK_TITLE = "OpenHarmony tools"
SDK_VERSION ?= "${DISTRO_VERSION}"

RDEPENDS = "${TOOLCHAIN_HOST_TASK}"

inherit populate_sdk
inherit toolchain-scripts-base
inherit nopackages

deltask install
deltask populate_sysroot

do_populate_sdk[stamp-extra-info] = "${PACKAGE_ARCH}"

create_sdk_files:append () {
	rm -f ${SDK_OUTPUT}/${SDKPATH}/site-config-*
	rm -f ${SDK_OUTPUT}/${SDKPATH}/environment-setup-*
	rm -f ${SDK_OUTPUT}/${SDKPATH}/version-*

	# Generate new (mini) sdk-environment-setup file
	script=${1:-${SDK_OUTPUT}/${SDKPATH}/environment-setup-${SDK_SYS}}
	touch $script
	echo 'export PATH=${SDKPATHNATIVE}${bindir_nativesdk}:${SDKPATHNATIVE}${sbindir_nativesdk}:${SDKPATHNATIVE}${base_bindir_nativesdk}:${SDKPATHNATIVE}${base_sbindir_nativesdk}:$PATH' >> $script
	echo 'export PYTHONPATH=${SDKPATHNATIVE}${libdir_nativesdk}/python*/site-packages:$PYTHONPATH' >> $script
	echo 'export OECORE_NATIVE_SYSROOT="${SDKPATHNATIVE}"' >> $script
	if [ -e "${SDK_OUTPUT}${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt" ]; then
		echo 'export GIT_SSL_CAINFO="${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt"' >>$script
		echo 'export SSL_CERT_FILE="${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt"' >>$script
	fi

	toolchain_create_sdk_version ${SDK_OUTPUT}/${SDKPATH}/version-${SDK_SYS}

	cat >> $script <<EOF
if [ -d "\$OECORE_NATIVE_SYSROOT/environment-setup.d" ]; then
	for envfile in \$OECORE_NATIVE_SYSROOT/environment-setup.d/*.sh; do
		. \$envfile
	done
fi
# We have to unset this else it can confuse oe-selftest and other tools
# which may also use the overlapping namespace.
unset OECORE_NATIVE_SYSROOT
EOF

	if [ "${SDKMACHINE}" = "i686" ]; then
		echo 'export NO32LIBS="0"' >>$script
		echo 'echo "$BB_ENV_PASSTHROUGH_ADDITIONS" | grep -q "NO32LIBS"' >>$script
		echo '[ $? != 0 ] && export BB_ENV_PASSTHROUGH_ADDITIONS="NO32LIBS $BB_ENV_PASSTHROUGH_ADDITIONS"' >>$script
	fi
}

do_populate_sdk[depends] += "${DEPENDS_QEMU_IMAGES}"
DEPENDS_QEMU_IMAGES = ""
DEPENDS_QEMU_IMAGES:qemuarma7 += "openharmony-standard-image:do_image_complete virtual/kernel:do_deploy"
addtask populate_sdk after do_unpack before do_build
SRC_URI:append:qemuarma7 = " file://post-relocate-setup.sh"
create_sdk_files:append:qemuarma7 () {
        mkdir -p ${SDK_OUTPUT}${SDKPATH}/images/${MACHINE}
        cp ${DEPLOY_DIR_IMAGE}/openharmony-standard-image-${MACHINE}.ext4 \
           ${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin \
           ${SDK_OUTPUT}${SDKPATH}/images/${MACHINE}/
        cat ${DEPLOY_DIR_IMAGE}/openharmony-standard-image-${MACHINE}.qemuboot.conf | \
            sed -e 's|^\(deploy_dir_image\) = .*|\1 = @SDKPATH@/images/${MACHINE}|' \
                -e 's|^\(qb_default_kernel\) = .*|\1 = zImage-${MACHINE}.bin|' \
                -e 's|^\(staging_bindir_native\) = .*|\1 = @SDKPATH@/sysroots/${SDK_SYS}/usr/bin|' \
                -e 's|^\(staging_dir_host\) = .*|\1 = @SDKPATH@/sysroots/foobar|' \
                -e 's|^\(staging_dir_native\) = .*|\1 = @SDKPATH@/sysroots/${SDK_SYS}|' \
                -e 's|^\(uninative_loader\) = .*|\1 = @SDKPATH@/sysroots/${SDK_SYS}/lib/ld-linux-${SDK_ARCH}.so.2|' \
                > ${SDK_OUTPUT}${SDKPATH}/images/${MACHINE}/openharmony-standard-image-${MACHINE}.qemuboot.conf
        mkdir -p ${SDK_OUTPUT}${SDKPATHNATIVE}/post-relocate-setup.d
        install -m 0755 ${WORKDIR}/post-relocate-setup.sh ${SDK_OUTPUT}${SDKPATH}/post-relocate-setup.sh
}

TOOLCHAIN_TARGET_TASK:append:df-acts = " openharmony-standard-acts"
create_sdk_files:append:df-acts () {
	pwd
	for f in ${SDK_OUTPUT}/${SDKTARGETSYSROOT}${libexecdir}/openharmony-standard/acts/* ; do
		f=$(basename $f)
		ln -sft ${SDK_OUTPUT}/${SDKPATHNATIVE}${libdir_nativesdk}/python* \
			../../../../${MULTIMACH_TARGET_SYS}${libexecdir}/openharmony-standard/acts/$f
	done
}

TOOLCHAIN_NEED_CONFIGSITE_CACHE = ""

INHIBIT_DEFAULT_DEPS = "1"
