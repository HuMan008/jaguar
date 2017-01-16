/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.DevRunner
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/24/16 11:46 PM
 *
 */

/*
package com.iusworks.jaguar;

import com.iusworks.jaguar.dao.NotificationDAO;
import com.iusworks.jaguar.provider.apple.APNS;
import com.iusworks.jaguar.provider.leancloud.LeanCloudPush;
import com.iusworks.jaguar.service.NotificationService;
import com.iusworks.jaguar.thrift.Environment;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.thrift.NotificationRequest;
import com.relayrides.pushy.apns.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DevRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(DevRunner.class);

    @Autowired
    private APNS apns;

    @Autowired
    private LeanCloudPush leanCloudPush;


    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void run(String... args) throws Exception {
//        if (true) {
//            noti_ios();
//        }
//        noti_android();

//        Notifi notification = new Notifi();
//        notification.setId((new ObjectId()).toHexString());
//        notification.setSid((short)999);
//        notification.setUid("test");
//        notification.setAction("test");
//        notification.setTitle("test");
//        notification.setAlert("devtest");
//        notification.setDatetime((int) Instant.now().getEpochSecond());
//        notification.setStoraged("devtest");
//        notificationDAO.insert(notification);


        Notification notification = genNotification();
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setSystemId((short) 4);
        notificationRequest.setNotification(notification);
        notificationService.notify(notificationRequest);
    }

    private Notification genNotification() {
        Notification notification = new Notification();
        notification.setAlert("测试推送");
        notification.setTitle("测试标题");
        notification.setBadge(1);
        notification.setSound("default");
        notification.setAction("detail:1");
        notification.setEnv(Environment.Dev);

        return notification;
    }

    private void noti_ios() {
        Notification notification = genNotification();
        String token = TokenUtil.sanitizeTokenString("<9eb026b3 7d7f6a30 8f74d1eb ec02f572 3045e99e b522f2e3 0caabbfc 288a728a>");
        logger.info("{}", token);
        apns.push(notification, token);
    }


    private void noti_android() {
        Notification notification = genNotification();
        leanCloudPush.push(notification, "12345678-4312-1234-1234-1234567890ab");
    }

}

*/
