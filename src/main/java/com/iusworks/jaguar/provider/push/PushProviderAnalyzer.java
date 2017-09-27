/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.PushProviderAnalyzer
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/26/17 11:47 AM
 *
 */

package com.iusworks.jaguar.provider.push;


import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.provider.push.apple.ApplePush;
import com.iusworks.jaguar.provider.push.leancloud.LeanCloudPush;
import com.iusworks.jaguar.provider.push.xiaomi.MiPush;
import com.iusworks.jaguar.thrift.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PushProviderAnalyzer {

    @Autowired
    private ApplePush applePush;

    @Autowired
    private LeanCloudPush leanCloudPush;

    @Autowired
    private MiPush miPush;

    public Push analyzePusher(Device device) {

        if (device.getType() == DeviceType.iOS.getValue()) {
            return applePush;
        }

        return leanCloudPush;
    }
}
