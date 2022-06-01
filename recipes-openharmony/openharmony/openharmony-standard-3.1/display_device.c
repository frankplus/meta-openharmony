/*
 * SPDX-FileCopyrightText: Huawei Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include "display_device.h"
#include "display_type.h"

// MAX_BRIGHTNESS as defined in:
// base/powermgr/display_manager/service/native/include/screen_action.h
#define MAX_BRIGHTNESS 255

static uint32_t backlight_level = MAX_BRIGHTNESS;
static DispPowerStatus power_status = POWER_STATUS_ON;

int32_t SetDisplayPowerStatus(uint32_t devId, DispPowerStatus status)
{
    if (devId == 0 && status < POWER_STATUS_BUTT) {
        power_status = status;
        return DISPLAY_SUCCESS;
    }
    return DISPLAY_PARAM_ERR;
}

int32_t GetDisplayPowerStatus(uint32_t devId, DispPowerStatus *status)
{
    if (devId == 0 && status != NULL) {
        *status = power_status;
        return DISPLAY_SUCCESS;
    }
    return DISPLAY_PARAM_ERR;
}

int32_t SetDisplayBacklight(uint32_t devId, uint32_t level)
{
    if (devId == 0 && level <= MAX_BRIGHTNESS) {
        backlight_level = level;
        return DISPLAY_SUCCESS;
    }
    return DISPLAY_PARAM_ERR;
}

int32_t GetDisplayBacklight(uint32_t devId, uint32_t *level)
{
    if (devId == 0 && level != NULL) {
        *level = backlight_level;
        return DISPLAY_SUCCESS;
    }
    return DISPLAY_PARAM_ERR;
}

int32_t DeviceInitialize(DeviceFuncs **funcs)
{
    if (funcs == NULL) {
        return DISPLAY_NULL_PTR;
    }

    DeviceFuncs *dFuncs = (DeviceFuncs *)calloc(1, sizeof(DeviceFuncs));
    if (funcs == NULL) {
        return DISPLAY_NOMEM;
    }

    dFuncs->SetDisplayPowerStatus = SetDisplayPowerStatus;
    dFuncs->GetDisplayPowerStatus = GetDisplayPowerStatus;
    dFuncs->SetDisplayBacklight = SetDisplayBacklight;
    dFuncs->GetDisplayBacklight = GetDisplayBacklight;

    *funcs = dFuncs;
    return DISPLAY_SUCCESS;
}

int32_t DeviceUninitialize(DeviceFuncs *funcs)
{
    if (funcs == NULL) {
        return DISPLAY_NULL_PTR;
    }

    free(funcs);

    return DISPLAY_SUCCESS;
}
