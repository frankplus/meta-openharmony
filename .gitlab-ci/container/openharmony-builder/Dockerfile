# Copyright 2021 Huawei Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# SPDX-License-Identifier: Apache-2.0

# This builds a container image for use in building OpenHarmony images,
# following the build system specification in
# https://repo.huaweicloud.com/harmonyos/os/2.0/tool_chain/Standard_System.md

FROM ubuntu:18.04

ARG DEBIAN_FRONTEND="noninteractive"

RUN apt-get update -qq \
 && apt-get install -qq -y \
      bash locales \
      apt-utils vim software-properties-common openssh-server iputils-ping \
      curl net-tools bsdmainutils kmod bc rsync gawk ssh ccache zip python-dev \
      make m4 gcc-multilib ca-certificates-java unzip python3-yaml perl \
      openssl libssl1.1 gnupg xsltproc x11proto-core-dev tcl python3-crypto \
      python-crypto libxml2-utils libxml2-dev libx11-dev libssl-dev \
      libgl1-mesa-dev lib32z1-dev lib32ncurses5-dev g++-multilib flex bison \
      doxygen git subversion tofrodos pigz expect python3-xlrd python-xlrd \
      git-core gperf build-essential zlib1g-dev libc6-dev-i386 lib32z-dev \
      openjdk-8-jdk \
      ruby cpio \
 && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN locale-gen

# Let's just have /bin/sh as bash
RUN echo "dash dash/sh boolean false" | debconf-set-selections \
 && dpkg-reconfigure dash

# For some reason for containers built using kaniko ping errors with:
# `permission denied` for user other than root. Even running container in
# privileged mode doesn't help for non-root user. Most probably it is related
# to this issue: https://github.com/GoogleContainerTools/kaniko/issues/1851
# ping is required by Yocto testing machinery and therefore temporarily setting
# suid to get it working
RUN chmod u+s $(command -v ping)

RUN apt-get update -qq \
 && apt-get install -qq -y python3.8 python3-pip \
 && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN update-alternatives --install /usr/bin/python python /usr/bin/python2.7 2 \
 && update-alternatives --install /usr/bin/python python /usr/bin/python3.6 3 \
 && update-alternatives --install /usr/bin/python python /usr/bin/python3.8 4 \
 && update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.6 2 \
 && update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.8 3
RUN pip3 install --trusted-host mirrors.aliyun.com -i https://mirrors.aliyun.com/pypi/simple setuptools \
 && pip3 install --trusted-host mirrors.aliyun.com -i https://mirrors.aliyun.com/pypi/simple kconfiglib \
 && pip3 install --trusted-host mirrors.aliyun.com -i http://mirrors.aliyun.com/pypi/simple pycryptodome \
 && pip3 install --trusted-host mirrors.aliyun.com -i https://mirrors.aliyun.com/pypi/simple ecdsa \
 && python3 -m pip install ohos-build==0.4.6

RUN mkdir -p /opt/harmonyos
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/compiler/clang/10.0.1-62608/linux/llvm.tar.gz \
 && tar xf /opt/harmonyos/llvm.tar.gz -C /opt/harmonyos \
 && rm /opt/harmonyos/llvm.tar.gz
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/compiler/hc-gen/0.65/linux/hc-gen-0.65-linux.tar \
 && tar xf /opt/harmonyos/hc-gen-0.65-linux.tar -C /opt/harmonyos \
 && rm /opt/harmonyos/hc-gen-0.65-linux.tar
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/compiler/gcc_riscv32/7.3.0/linux/gcc_riscv32-linux-7.3.0.tar.gz \
 && tar xf /opt/harmonyos/gcc_riscv32-linux-7.3.0.tar.gz -C /opt/harmonyos \
 && rm /opt/harmonyos/gcc_riscv32-linux-7.3.0.tar.gz
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/compiler/ninja/1.9.0/linux/ninja.1.9.0.tar \
 && tar xf /opt/harmonyos/ninja.1.9.0.tar -C /opt/harmonyos \
 && rm /opt/harmonyos/ninja.1.9.0.tar
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/compiler/gn/1717/linux/gn-linux-x86-1717.tar.gz \
 && tar xf /opt/harmonyos/gn-linux-x86-1717.tar.gz -C /opt/harmonyos \
 && rm /opt/harmonyos/gn-linux-x86-1717.tar.gz
RUN wget -q -P /opt/harmonyos https://mirrors.huaweicloud.com/nodejs/v12.20.0/node-v12.20.0-linux-x64.tar.gz \
 && tar xf /opt/harmonyos/node-v12.20.0-linux-x64.tar.gz -C /opt/harmonyos \
 && rm /opt/harmonyos/node-v12.20.0-linux-x64.tar.gz
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/develop_tools/hmos_app_packing_tool.jar
RUN wget -q -P /opt/harmonyos https://repo.huaweicloud.com/harmonyos/develop_tools/hapsigntoolv2.jar

RUN sed -i '$aexport PATH=/opt/harmonyos/llvm/bin:$PATH' /root/.bashrc \
 && sed -i '$aexport PATH=/opt/harmonyos/hc-gen:$PATH' /root/.bashrc \
 && sed -i '$aexport PATH=/opt/harmonyos/gcc_riscv32/bin:$PATH' /root/.bashrc \
 && sed -i '$aexport PATH=/opt/harmonyos/ninja:$PATH' /root/.bashrc \
 && sed -i '$aexport PATH=/opt/harmonyos/node-v12.20.0-linux-x64/bin:$PATH' /root/.bashrc \
 && sed -i '$aexport PATH=/opt/harmonyos:$PATH' /root/.bashrc \
 && sed -i '$aexport PATH=/root/.local/bin:$PATH' /root/.bashrc \
 && export PATH=/opt/harmonyos/llvm/bin:$PATH \
 && export PATH=/opt/harmonyos/hc-gen:$PATH \
 && export PATH=/opt/harmonyos/gcc_riscv32/bin:$PATH \
 && export PATH=/opt/harmonyos/ninja:$PATH \
 && export PATH=/opt/harmonyos/node-v12.20.0-linux-x64/bin:$PATH \
 && export PATH=/opt/harmonyos:$PATH \
 && export PATH=/root/.local/bin:$PATH \
 && npm install -g @ohos/hpm-cli --registry https://mirrors.huaweicloud.com/repository/npm/

RUN useradd --create-home --uid 1000 --shell /usr/bin/bash builder
COPY --chown=builder:builder .gitconfig /home/builder/.gitconfig
USER builder
WORKDIR /home/builder
