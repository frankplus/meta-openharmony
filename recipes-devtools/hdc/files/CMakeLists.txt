# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

cmake_minimum_required(VERSION 3.16)
project(hdc_tool)

set(HDC hdc)

find_package(PkgConfig REQUIRED)

add_executable(${HDC}
	src/common/async_cmd.cpp
	src/common/task.cpp
	src/common/uart.cpp
	src/common/session.cpp
	src/common/usb.cpp
	src/common/file_descriptor.cpp
	src/common/forward.cpp
	src/common/file.cpp
	src/common/auth.cpp
	src/common/tcp.cpp
	src/common/debug.cpp
	src/common/base.cpp
	src/common/transfer.cpp
	src/common/channel.cpp
	src/host/host_usb.cpp
	src/host/host_app.cpp
	src/host/host_forward.cpp
	src/host/main.cpp
	src/host/server.cpp
	src/host/host_uart.cpp
	src/host/server_for_client.cpp
	src/host/client.cpp
	src/host/host_tcp.cpp
	src/host/translate.cpp
	src/host/host_unity.cpp
)

set_property(TARGET ${HDC} PROPERTY CXX_STANDARD 17)

target_link_libraries(${HDC} PRIVATE libboundscheck.a)

set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)
target_link_libraries(${HDC} PRIVATE Threads::Threads)

target_include_directories(${HDC} PRIVATE libsecurec/include/)
target_compile_definitions(${HDC} PRIVATE HDC_HOST)
target_compile_definitions(${HDC} PRIVATE HARMONY_PROJECT)
target_compile_definitions(${HDC} PRIVATE HDC_SUPPORT_UART)

pkg_check_modules(LIBUSB REQUIRED libusb-1.0)
target_link_libraries(${HDC} PRIVATE ${LIBUSB_LIBRARIES})
target_include_directories(${HDC} PRIVATE ${LIBUSB_INCLUDE_DIRS})
target_compile_options(${HDC} PRIVATE ${LIBUSB_CFLAGS_OTHER})

pkg_check_modules(LIBUV REQUIRED libuv)
target_link_libraries(${HDC} PRIVATE ${LIBUV_LIBRARIES})
target_include_directories(${HDC} PRIVATE ${LIBUV_INCLUDE_DIRS})
target_compile_options(${HDC} PRIVATE ${LIBUV_CFLAGS_OTHER})

pkg_check_modules(LIBSSL REQUIRED libssl)
target_link_libraries(${HDC} PRIVATE ${LIBSSL_LIBRARIES})
target_include_directories(${HDC} PRIVATE ${LIBSSL_INCLUDE_DIRS})
target_compile_options(${HDC} PRIVATE ${LIBSSL_CFLAGS_OTHER})

pkg_check_modules(LIBCRYPTO REQUIRED libcrypto)
target_link_libraries(${HDC} PRIVATE ${LIBCRYPTO_LIBRARIES})
target_include_directories(${HDC} PRIVATE ${LIBCRYPTO_INCLUDE_DIRS})
target_compile_options(${HDC} PRIVATE ${LIBCRYPTO_CFLAGS_OTHER})

pkg_check_modules(LIBLZ4 REQUIRED liblz4)
target_link_libraries(${HDC} PRIVATE ${LIBLZ4_LIBRARIES})
target_include_directories(${HDC} PRIVATE ${LIBLZ4_INCLUDE_DIRS})
target_compile_options(${HDC} PRIVATE ${LIBLZ4_CFLAGS_OTHER})

install(TARGETS ${HDC} DESTINATION bin)
