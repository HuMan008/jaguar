/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.huawei.HuaweiPush
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/21/17 5:33 PM
 *
 */

package com.iusworks.jaguar.provider.push.huawei;

import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.provider.push.Push;
import com.iusworks.jaguar.thrift.Notification;
import org.springframework.stereotype.Component;

@Component
public class HuaweiPush implements Push {

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {
        return false;
    }
}
