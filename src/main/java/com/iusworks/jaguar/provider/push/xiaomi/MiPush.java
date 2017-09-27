/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.xiaomi.MiPush
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/21/17 4:02 PM
 *
 */

package com.iusworks.jaguar.provider.push.xiaomi;


import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.config.push.PushItem;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.provider.push.Push;
import com.iusworks.jaguar.thrift.Notification;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class MiPush implements Push {

    @Autowired
    private PushProperties pushProperties;

    @PostConstruct
    public void construct() {
        Constants.useOfficial();
    }

    private static Logger logger = LoggerFactory.getLogger(MiPush.class);

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {


        PushItem pushItem = pushProperties.itemBySystemId((int) device.getSid());
        if (pushItem == null) {
            logger.error("PushItem for systemId:{} not found", device.getSid());
            return false;
        }

        Map<String, Map<String, String>> androids = pushItem.getAndroids();
        if (androids.size() < 1) {
            logger.error("PushItem for android configure not found");
            return false;
        }

        Map<String, String> mi = androids.get("xiaomi");
        if (mi == null) {
            logger.error("PushItem for android -> xiaomi not found");
            return false;
        }

        Message message = buildMessage(notification, notifyId, mi.get("package"));
        Sender sender = new Sender(mi.get("appSecret"));
        try {
            sender.send(message, "fIG7ehjGfh5cJ995ABTB3mH8zTHi6JyCrhkjE0z3WMw=", 3);
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return true;
    }

    private Message buildMessage(Notification notification, String notifyId, String packageName) {
        Message.Builder builder = new Message.Builder();
        builder.payload(notification.getAlert());
        builder.title(notification.getTitle());
        builder.description(notification.getAlert());
        builder.restrictedPackageName(packageName);
        if (notification.getExtSize() > 0) {
            notification.getExt().forEach((k, v) -> builder.extra(k, v));
        }
        return builder.build();
    }

}
