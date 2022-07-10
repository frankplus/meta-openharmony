# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Configuration of OpenSSL recipe

# Disable various ciphers and protocols
PACKAGECONFIG:pn-openssl:df-openharmony = "no-bf no-camellia no-cast no-ct no-md2 no-rc5 no-rc2 no-ripemd no-rmd160 no-ssl2 no-ssl3 no-ssl3-method"

# Arm 32-bit configuration
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DOPENSSL_CPUID_OBJ"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DOPENSSL_BN_ASM_MONT"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DOPENSSL_BN_ASM_GF2m"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DSHA1_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DSHA256_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DSHA512_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DKECCAK1600_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DAES_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DBSAES_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DGHASH_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DECP_NISTZ256_ASM"
CFLAGS:pn-openssl:df-openharmony:append:arm = " -DPOLY1305_ASM"

# Arm 64-bit configuration
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DOPENSSL_CPUID_OBJ"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DOPENSSL_BN_ASM_MONT"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DSHA1_ASM"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DSHA256_ASM"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DSHA512_ASM"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DKECCAK1600_ASM"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DVPAES_ASM"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DECP_NISTZ256_ASM"
CFLAGS:pn-openssl:df-openharmony:append:aarch64 = " -DPOLY1305_ASM"
