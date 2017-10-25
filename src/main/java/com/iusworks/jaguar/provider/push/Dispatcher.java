/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.Dispatcher
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/25/17 3:10 PM
 *
 */

package com.iusworks.jaguar.provider.push;


import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.thrift.DeviceType;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.thrift.NotificationRequest;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Dispatcher {

    private static Logger logger = LoggerFactory.getLogger(Dispatcher.class);

    @Autowired
    private PushProviderAnalyzer pushProviderAnalyzer;

    private Pushable pusher(Device device) {
        return pushProviderAnalyzer.analyzePusher(device);
    }

    public void push(Notification notification, Device device, String notifyId) {
        Pushable pusher = pusher(device);
        if (pusher != null) {
            pusher.push(notification, device, notifyId);
        } else {
            pushProviderAnalyzer.getAndroidProviders().forEach((e) -> e.push(notification, device, notifyId));
        }
    }


    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {
        if (deviceList == null || deviceList.size() < 1) {
            return;
        }

        if (deviceList.size() == 1) {
            push(notification, deviceList.get(0), notifyId);
            return;
        }

        Map<Integer, List<Device>> providerDispatchListMap = new HashMap<>();

        List<Device> pushAllDevices = new ArrayList<>();
        for (Device device : deviceList) {
            Pushable pusher = pusher(device);
            if (pusher == null) {
                // push all
                pushAllDevices.add(device);
                continue;
            }

            List<Device> ps = providerDispatchListMap.get(pusher.provider().getCode());
            if (ps == null) {
                ps = new ArrayList<>();
                providerDispatchListMap.put(pusher.provider().getCode(), ps);
            }

            ps.add(device);
        }

        for (Map.Entry<Integer, List<Device>> entry : providerDispatchListMap.entrySet()) {
            PushProviderEnum penum = PushProviderEnum.fromCode(entry.getKey());
            if (penum == null) {
                continue;
            }
            logger.info("Batch provider:", penum);
            Pushable pushable = pushProviderAnalyzer.pusherFromProvider(penum);
            pushable.batchPush(notification, entry.getValue(), notifyId);
        }

        pushProviderAnalyzer.getAndroidProviders().forEach((e) -> e.batchPush(notification, pushAllDevices, notifyId));
    }


}
