# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenHarmony 4.0"


LICENSE = "0BSD & Apache-2.0 & BSD-2-Clause & BSD-3-Clause & BSL-1.0 & \
GPL-2.0-only & GPL-2.0-or-later & GPL-2-with-bison-exception & GPL-3.0-only & \
LGPL-2.1-only & LGPL-2.1-or-later & LGPL-3.0-only & CPL-1.0 & MIT & MIT-0 & \
MIT-Modern-Variant & Zlib & CC-BY-3.0 & CC-BY-SA-3.0 & CC-BY-NC-SA-3.0 & X11 & \
PD & OFL-1.1 & OpenSSL & MulanPSL-2.0 & bzip2-1.0.6 & ISC & ICU & IJG & Libpng & \
MPL-1.1 & MPL-2.0 & FTL"
LIC_FILES_CHKSUM = "file://build/LICENSE;md5=cfba563cea4ce607306f8a392f19bf6c"

DEPENDS += "bison-native"
DEPENDS += "perl-native"
DEPENDS += "ruby-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-${OPENHARMONY_VERSION}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony-standard-${OPENHARMONY_VERSION}:"

include ${PN}-sources-${OPENHARMONY_VERSION}.inc

require java-tools.inc

SRC_URI += "file://prebuilts_download.sh"

# The following is extracted from //build/prebuilts_download.py
python prebuilts_download() {
    import json
    import os
    import shutil
    import subprocess

    def _run_cmd(cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE,
                            stderr=subprocess.PIPE)
        sout, serr = res.communicate()
        return sout.rstrip().decode('utf-8'), serr, res.returncode

    def _node_modules_copy(config, code_dir, enable_symlink):
        for config_info in config:
            src_dir = os.path.join(code_dir, config_info.get('src'))
            dest_dir = os.path.join(code_dir, config_info.get('dest'))
            use_symlink = config_info.get('use_symlink')
            if os.path.exists(os.path.dirname(dest_dir)):
                shutil.rmtree(os.path.dirname(dest_dir))
            if use_symlink == 'True' and enable_symlink == True:
                os.makedirs(os.path.dirname(dest_dir), exist_ok=True)
                os.symlink(src_dir, dest_dir)
            else:
                shutil.copytree(src_dir, dest_dir, symlinks=True)

    def _file_handle(config, code_dir, host_platform):
        for config_info in config:
            src_dir = code_dir + config_info.get('src')
            dest_dir = code_dir + config_info.get('dest')
            tmp_dir = config_info.get('tmp')
            symlink_src = config_info.get('symlink_src')
            symlink_dest = config_info.get('symlink_dest')
            rename = config_info.get('rename')
            if os.path.exists(src_dir):
                if tmp_dir:
                    tmp_dir = code_dir + tmp_dir
                    shutil.move(src_dir, tmp_dir)
                    cmd = 'mv {}/*.mark {}'.format(dest_dir, tmp_dir)
                    _run_cmd(cmd)
                    if os.path.exists(dest_dir):
                        shutil.rmtree(dest_dir)
                    shutil.move(tmp_dir, dest_dir)
                elif rename:
                    if os.path.exists(dest_dir) and dest_dir != src_dir:
                        shutil.rmtree(dest_dir)
                    shutil.move(src_dir, dest_dir)
                    if symlink_src and symlink_dest:
                        if os.path.exists(dest_dir + symlink_dest):
                            os.remove(dest_dir + symlink_dest)
                        if host_platform == 'darwin' and os.path.basename(dest_dir) == "nodejs":
                            symlink_src = symlink_src.replace('linux', 'darwin')
                        os.symlink(os.path.basename(symlink_src), dest_dir + symlink_dest)
                else:
                    _run_cmd('chmod 755 {} -R'.format(dest_dir))

    def _install(config, code_dir):
        for config_info in config:
            install_dir = '{}/{}'.format(code_dir, config_info.get('install_dir'))
            script = config_info.get('script')
            cmd = '{}/{}'.format(install_dir, script)
            args = config_info.get('args')
            for arg in args:
                for key in arg.keys():
                    cmd = '{} --{}={}'.format(cmd, key, arg[key])
            dest_dir = '{}/{}'.format(code_dir, config_info.get('destdir'))
            cmd = '{} --destdir={}'.format(cmd, dest_dir)
            _run_cmd(cmd)

    def read_json_file(file_path):
        with open(file_path, 'r') as file:
            return json.load(file)

    config_file_path = os.path.join(d.getVar('S'), 'build/prebuilts_download_config.json')

    # Load the configuration
    config = read_json_file(config_file_path)

    # Extract the file_handle_config part
    file_handle_config = config.get('file_handle_config', [])

    # Assuming code_dir and host_platform values are defined
    code_dir = d.getVar('S')
    host_platform = 'linux'  # or 'darwin'
    host_cpu = 'x86_64'

    # Execute file handling based on the extracted configuration
    _file_handle(file_handle_config, code_dir, host_platform)

    # Extract node modules copy configuration and execute
    node_modules_copy_config = config.get('node_modules_copy')
    _node_modules_copy(node_modules_copy_config, code_dir, enable_symlink=False)

    # Extract the install configuration
    install_config = config.get(host_platform).get(host_cpu).get('install')
    
    if install_config:
        # Execute the install scripts as specified in the JSON file
        _install(install_config, code_dir)
}

configure_toolchain() {
    chmod +x ${WORKDIR}/prebuilts_download.sh
    ${WORKDIR}/prebuilts_download.sh ${S}
}

python_is_python3() { 
    PYTHON3_DIR=$(dirname $(which python3))
    if [ ! -f "${PYTHON3_DIR}/python" ]; then
        ln -sf "${PYTHON3_DIR}/python3" "${PYTHON3_DIR}/python"
    else
        echo "not creating symlink, python already exists in ${PYTHON3_DIR}"
    fi
}

do_configure[prefuncs] += "prebuilts_download configure_toolchain python_is_python3"

do_compile:append() {
    python3 ${S}/build/hb/main.py build --product-name rk3568
}
