/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.Push
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/21/17 4:03 PM
 *
 */

package com.iusworks.jaguar.provider.push;


import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.thrift.Notification;

import java.util.List;
import java.util.Map;

public interface Pushable {

    boolean push(Notification notification, Device device, String notifyId);

    void batchPush(Notification notification, List<Device> deviceList, String notifyId);

    boolean isSupport(Map<String, String> deviceInfo);

    PushProviderEnum provider();
}
