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


package com.iusworks.jaguar;

import com.iusworks.jaguar.provider.push.xiaomi.MiPush;
import com.iusworks.jaguar.thrift.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DevRunner {

    private static Logger logger = LoggerFactory.getLogger(DevRunner.class);

//    @Autowired
//    private LeanCloudPush leanCloudPush;
//
//    @Autowired
//    private NotificationDAO notificationDAO;
//
//    @Autowired
//    private NotificationService notificationService;
//
//    @Autowired
//    private PushProperties pushProperties;

    @Autowired
    private DirectClient directClient;

    @Autowired
    private MiPush miPush;

    @Scheduled(initialDelay = 1000, fixedRate = 1000000)
    public void testMi() {

        Notification notification = new Notification();
        notification.setAction("abc");
        notification.setAlert("测试alert");
        notification.setTitle("测试title");
        notification.setStoraged("......");


        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 5);

        miPush.push(notification, device, null);

    }

    public void testDeviceRegiste() {

        DeviceRequest deviceRequest = new DeviceRequest();
        deviceRequest.setSystemId((short) 4);
        deviceRequest.setSignature("");

        Device device = new Device();
        device.setUid("systemUid");
        device.setDeviceId("deviceId");
        device.setType(DeviceType.iOS);

        HashSet<String> cares = new HashSet<>();
        cares.add("girl");
        cares.add("ps4 pro");
        cares.add("game");
        device.setCares(cares);

        DevicePlatformVoucher ios = new DevicePlatformVoucher();
        ios.setVoucher("ios token");
        ios.setState((short) 0);
        ios.setPlatform("ios");
        device.addToDpv(ios);

        device.addToTags("oil");
        device.addToTags("red");
        device.addToTags("blue");

        device.setVoucher("voucher");
        device.setState((short) 0);

        device.putToDeviceInfo("brand", "xiaomi");
        device.putToDeviceInfo("osversion", "1.5");
        device.putToDeviceInfo("name", "卜辞中");

        deviceRequest.setDevice(device);
        directClient.device(deviceRequest);
    }

    public void testDeivcePlatformVoucher() {
        logger.info("testDeivcePlatformVoucher");
        DevicePlatformVoucherRequest request = new DevicePlatformVoucherRequest();
        request.setUid("systemUid");
        request.setSignature("");
        request.setSystemId((short) 4);

        DevicePlatformVoucher voucher = new DevicePlatformVoucher();
        voucher.setPlatform("xiaomi");
        voucher.setState((short) 0);
        voucher.setVoucher("xiaomi token");

        request.setDpv(voucher);

        directClient.devicePlatformVoucher(request);
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

    /*
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
*/
}


