.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

OpenHarmony
###########

OpenHarmony is an open-source project incubated and operated by the OpenAtom
Foundation. It is an open-source operating system with a framework and platform
applicable to smart devices in all scenarios of a fully-connected world. It aims
to promote the development of the Internet of Everything (IoE).

OpenHarmony project provides a number of resources, including a Platform
Compatibility Specification (PCS), a test suite (XTS) for verification of
products and distributions following the PCS, and a complete reference
implementation with support for several development boards.

For more details on OpenHarmony project, `Click <https://gitee.com/openharmony>`__.

|main_project_name| provides a number of different Yocto recipes related to
OpenHarmony.

``openharmony-standard``
  builds OpenHarmony components from the OpenHarmony projects reference
  implementation, for use in Oniro and other Yocto based projects.

``oniro-openharmony-toolchain``
  builds an LLVM toolchain for use with the OpenHarmony reference implementation
  as a replacement for the default prebuilt toolchain.

``oniro-openharmony-bundle``
  is similar to the toolchain built with ``oniro-openharmony-toolchain``, but
  with the addition of selected open-source 3rd party components, which replaces
  the default versions of those same components provided in the OpenHarmony
  reference implementation.

``openharmony-standard-image``
  builds a demo image, integrating the ``openharmony-standard`` output into a
  bootable demo.

.. toctree::
   :maxdepth: 1

   openharmony-image
   openharmony-sdk
