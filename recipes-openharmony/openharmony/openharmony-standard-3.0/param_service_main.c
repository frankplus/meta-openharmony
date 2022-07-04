/*
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include <init_log.h>
#include <init_param.h>
#include <errno.h>
#include <unistd.h>

#include <signal.h>

void sig_handler(int signum) {
    INIT_LOGI("Requesting param service to stop...");
    ParamServiceAsyncStopRequest();
}

char *parse_args(int argc, char **argv) {
    char *fpath = NULL;

    if (argc == 2 && argv[1]) {
        if (access(argv[1], R_OK) == 0) {
            fpath = argv[1];
        }
    }

    return fpath;
}

int main(int argc, char **argv)
{
    char *params_fpath = "/system/etc/ohos.para";

    if (argc > 1)
        params_fpath = parse_args(argc, argv);

    if (!params_fpath) {
        INIT_LOGI("Invalid params file path!");
        return -EINVAL;
    }

    InitParamService();
    int ret = LoadDefaultParams(params_fpath);
    if (ret) {
        INIT_LOGE("Failed to load default params! Error code: %d", ret);
    }
    ret = LoadPersistParams();
    if (ret) {
        INIT_LOGE("Failed to load persist params! Error code: %d", ret);
    }

    struct sigaction action = { 0 };
    action.sa_handler = sig_handler;

    sigaction(SIGTERM, &action, NULL);
    sigaction(SIGINT, &action, NULL);
    sigaction(SIGHUP, &action, NULL);

    // Following call spins up libuv event loop that will keep service running
    INIT_LOGI("Starting param service...");
    StartParamService();
    INIT_LOGI("Param service stopped.");

    return 0;
}
