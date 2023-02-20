.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _OpenHarmony SDK:

OpenHarmony Tools
#################

OpenHarmony provides a number of dedicated host tools for working with
OpenHarmony systems.

The `openharmony-tools` recipe bundles up these tools for easy installation of these on a Linux host OS.


Building
********

To build `openharmony-tools` for OpenHarmony 3.0.1, an initialized build
environment is required::

    TEMPLATECONF=../meta-openharmony/conf source oe-core/oe-init-build-env

Once that is in place, the `bitbake` command is simply::

    DISTRO=oniro-openharmony-linux MACHINE=qemuarma7 bitbake openharmony-tools

The default configuration (as defined in
[local.conf.sample](conf/local.conf.sample)), enables building of ACTS and
inclusion in the `openharmony-tools` image.


Installation
************

To install the tools on a Linux host OS, you need to use the shell script
installer.  To install into /opt/openharmony-tools do something like this:

.. code-block:: console

    $ ./openharmony-tools-3.0-cortexa7-neon-vfpv4-1.99.99.sh -y -d /opt/openharmony-tools

As the installation above will explain, in order to use the tools you need to do the following:

In order to use the installed tools, you need to setup your shell environment
(this needs to be done each time you create a new shell):

.. code-block:: console

    $ . /opt/openharmony-tools/environment-setup-x86_64-oesdk-linux

And with that, you will have access to `hdc` and `xdevice` commands.


Running ACTS
============

To run ACTS tests, you need to connect to the OpenHarmony target device (or
QEMU) using `hdc` and then run the tests using `xdevice` command.
(remember to setup the environment as show in the section above)

As an example, running the `ActsContextTest.json` test case against QEMU target
(IP address 192.168.7.2), run

.. code-block::

    $ hdc tconn 192.168.7.2:35000
    Connect OK
    $ xdevice run acts -l ActsContextTest
    [2023-02-20 13:25:22,954] [Main] [INFO] [*************** xDevice Test Framework Starting ***************]
    [2023-02-20 13:25:22,975] [Utils] [INFO] [The running command is: hdc_std -s tcp:8710 reset]
    [2023-02-20 13:25:23,980] [Console] [INFO] [Input command: run -l ActsContextTest]
    ...

