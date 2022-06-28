# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# This post do_install() function checks if all subfloders copied from
# system/lib/module are set in the distro variable MUSL_LDSO_PATHS.
#
# This variable is used to generate the content of the file
# /etc/ld-musl-${MUSL_LDSO_ARCH}.path so this function stops bitbake
# if one of the coipied folder from /system/lib/module/* is not in
# MUSL_LDSO_PATHS.

musl_ldso_paths_sanity_check() {
	cd "${D}/${libdir}"
	MODULE_LIB_PATHS="$(find module -type d)"
	for folder in ${MODULE_LIB_PATHS}; do
		PAT="(^|[[:space:]])${folder}([[:space:]]|$)"
		if [[ ! "${MUSL_LDSO_PATHS}" =~ ${PAT} ]]; then
			echo
			echo "'${folder}' not in MUSL_LDSO_PATHS"
			echo "Add it in conf/distro/include/musl-ldso-paths.inc"
			echo

			return 1
		fi
	done
}

do_install[postfuncs] += "musl_ldso_paths_sanity_check"
