# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Remove this when https://github.com/kraj/meta-clang/pull/616 is merged.
#
# While waiting for PR #616 to be merged and backported to upstream kirkstone
# branch, we need to carry it like this in order to get the clang_rt.profile
# library needed for OpenHarmony 3.1 builds.
PACKAGECONFIG[profile] ="-DCOMPILER_RT_BUILD_PROFILE=ON,-DCOMPILER_RT_BUILD_PROFILE=OFF"
EXTRA_OECMAKE = "-DCOMPILER_RT_STANDALONE_BUILD=OFF \
                  -DCOMPILER_RT_DEFAULT_TARGET_ONLY=ON \
                  -DCMAKE_C_COMPILER_TARGET=${HOST_ARCH}${HF}${HOST_VENDOR}-${HOST_OS} \
                  -DCOMPILER_RT_BUILD_XRAY=OFF \
                  -DCOMPILER_RT_BUILD_SANITIZERS=OFF \
                  -DCOMPILER_RT_BUILD_MEMPROF=OFF \
                  -DCOMPILER_RT_BUILD_LIBFUZZER=OFF \
                  -DLLVM_ENABLE_PROJECTS='compiler-rt' \
                  -DLLVM_LIBDIR_SUFFIX=${LLVM_LIBDIR_SUFFIX} \
"
