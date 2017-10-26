/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.huawei.HuaweiPush
 *
 * cluries <cluries@me.com>,  October 2017
 *
 * LastModified: 9/27/17 11:31 PM
 *
 */

package com.iusworks.jaguar.provider.push.huawei;

import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HuaweiPush implements Pushable {

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {
        return false;
    }

    @Override
    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {

    }

    @Override
    public boolean isSystemLevelSupport(Map<String, String> deviceInfo) {
        return false;
    }

    @Override
    public boolean canUseForSystemLevel() {
        return false;
    }

    @Override
    public PushProviderEnum provider() {
        return PushProviderEnum.Huawei;
    }

}
