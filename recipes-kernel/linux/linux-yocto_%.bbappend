FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/openharmony:"

SRC_URI += "file://0001-OpenHarmony-5.0.3-Release-linux_6.6-adaptation.patch"

SRC_URI += " file://openharmony.cfg"

ORIGIN_URL = "git://github.com/eclipse-oniro-mirrors"

SRC_URI += "${ORIGIN_URL}/third_party_bounds_checking_function.git;protocol=https;branch=OpenHarmony-5.0.3-Release;name=third_party_bounds_checking_function;destsuffix=third_party/bounds_checking_function"
SRCREV_third_party_bounds_checking_function = "db9050d4cd95292166c1e66523b5124feb53fcf1"

SRC_URI += "${ORIGIN_URL}/third_party_FreeBSD.git;protocol=https;branch=OpenHarmony-5.0.3-Release;name=third_party_FreeBSD;destsuffix=third_party/FreeBSD"
SRCREV_third_party_FreeBSD = "b0c081622aebf863ae18dd31078c4a28a86edf10"

SRC_URI += "${ORIGIN_URL}/drivers_hdf_core.git;protocol=https;branch=OpenHarmony-5.0.3-Release;name=drivers_hdf_core;destsuffix=drivers/hdf_core"
SRCREV_drivers_hdf_core = "a3bf8649ce1f4995f053fcd6a55cd618ab2bcc46"

SRC_URI += "git://gitee.com/ohos-doc/vendor_oobemulator.git;protocol=https;branch=OpenHarmony-5.0.2-Release;name=vendor_oniro;destsuffix=vendor/oniro"
SRCREV_vendor_oniro = "0c6955bb4ce5c835fa30efccd38818b308210e0a"

SRC_URI += "file://hdf.patch"
SRC_URI += "file://hdf_patch.sh"

do_patch:append(){
    KERNEL_BUILD_ROOT=${S}
    bash ${WORKDIR}/hdf_patch.sh ${WORKDIR} ${KERNEL_BUILD_ROOT}
    
    # symlink include/linux/stdarg.h to include/stdarg.h if the symlink doesn't already exist
    if [ ! -L "${KERNEL_BUILD_ROOT}/include/stdarg.h" ]; then
        ln -s ${KERNEL_BUILD_ROOT}/include/linux/stdarg.h ${KERNEL_BUILD_ROOT}/include/stdarg.h
    fi
}

do_compile:prepend() { 
    export PRODUCT_PATH=vendor/oniro/std_emulator

    # Find the path of the g++ compiler
    GPP_PATH=$(which g++)
    if [ -z "$GPP_PATH" ]; then
        bberr "g++ not found."
        exit 1
    fi

    # Get the directory where g++ is located
    GPP_DIR=$(dirname "$GPP_PATH")

    # Check if c++ exists in the same directory
    if [ ! -f "$GPP_DIR/c++" ]; then
        # If c++ does not exist, create a symlink to g++
        ln -s "$GPP_PATH" "$GPP_DIR/c++"
        bbnote "Symbolic link for c++ created."
    fi

}

do_compile_kernelmodules:prepend() {
    export PRODUCT_PATH=vendor/oniro/std_emulator
}
