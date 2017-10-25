/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.helper.DeviceHelper
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/26/17 3:35 PM
 *
 */

package com.iusworks.jaguar.helper;

import com.iusworks.jaguar.domain.Device;

@SuppressWarnings("all")
public class DeviceHelper {

    private static final String DN_NAME = "N";  // name
    private static final String DN_BRAND = "B"; // brand
    private static final String DN_SYSTEM_VERSION = "SV";   // system version
    private static final String DN_APP_VERSION = "AV";  // app version
    private static final String DN_FACTORY = "F";   // factory

    public static final String names(Device device, String defaults) {
        return value(DN_NAME, device, defaults);
    }

    public static final String brand(Device device, String defaults) {
        return value(DN_BRAND, device, defaults);
    }

    public static final String systemVersion(Device device, String defaults) {
        return value(DN_SYSTEM_VERSION, device, defaults);
    }

    public static final String appVersion(Device device, String defaults) {
        return value(DN_APP_VERSION, device, defaults);
    }

    public static final String factory(Device device, String defaults) {
        return value(DN_FACTORY, device, defaults);
    }

    public static final String value(String key, Device device, String defaults) {
        if (device.getInfos() == null) {
            return defaults;
        }

        return device.getInfos().getOrDefault(key, defaults);
    }

    public static boolean isXiaomi(Device device) {
        return false;
    }

    public static boolean isHuawei(Device device) {
        return false;
    }
    
}
