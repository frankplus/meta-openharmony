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

FROM ubuntu:20.04

ARG DEBIAN_FRONTEND="noninteractive"

RUN apt-get update -qq \
 && apt-get install -qq -y eatmydata ca-certificates \
 && eatmydata apt-get clean && rm -rf /var/lib/apt/lists/*

COPY --chown=root:root ppa/zyga-ubuntu-oh-tools-focal.list /etc/apt/sources.list.d/
COPY --chown=root:root ppa/zyga-ubuntu-oh-tools.gpg /etc/apt/trusted.gpg.d/

RUN eatmydata apt-get update -qq \
 && eatmydata apt-get install -qq -y \
        bash git-repo apt-utils build-essential chrpath cpio diffstat gawk git sudo wget \
        language-pack-en-base time locales python-is-python3 python3-distutils libssl-dev \
        iproute2 iputils-ping curl jq ca-certificates git-lfs \
        lz4 zstd git-restore-mtime \
        python3-cairo \
 && eatmydata apt-get clean && rm -rf /var/lib/apt/lists/*
RUN locale-gen

# Let's just have /bin/sh as bash
RUN echo "dash dash/sh boolean false" | debconf-set-selections \
 && DEBIAN_FRONTEND=noninteractive dpkg-reconfigure dash

# For some reason for containers built using kaniko ping errors with:
# `permission denied` for user other than root. Even running container in
# privileged mode doesn't help for non-root user. Most probably it is related
# to this issue: https://github.com/GoogleContainerTools/kaniko/issues/1851
# ping is required by Yocto testing machinery and therefore temporarily setting
# suid to get it working
RUN chmod u+s $(command -v ping)

RUN useradd --create-home --uid 1000 --shell /usr/bin/bash builder
COPY --chown=builder:builder .gitconfig /home/builder/.gitconfig
USER builder
WORKDIR /home/builder
