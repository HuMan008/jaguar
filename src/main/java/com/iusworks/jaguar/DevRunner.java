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

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.GtPush;
import com.gexin.rp.sdk.http.IGtPush;
import com.iusworks.jaguar.dao.DeviceDAO;
import com.iusworks.jaguar.domain.DeviceState;
import com.iusworks.jaguar.provider.push.Dispatcher;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.apple.ApplePush;
import com.iusworks.jaguar.provider.push.getui.GetuiPush;
import com.iusworks.jaguar.provider.push.huawei.HuaweiPush;
import com.iusworks.jaguar.provider.push.huawei4.Huawei4Push;
import com.iusworks.jaguar.provider.push.xiaomi.MiPush;
import com.iusworks.jaguar.service.NotificationService;
import com.iusworks.jaguar.thrift.*;
import org.bson.types.ObjectId;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private Dispatcher dispatcher;
    //
    //    @Autowired
    //    private HuaweiPush huaweiPush;

    @Autowired
    private Huawei4Push huawei4Push;

    @Autowired
    private ApplePush applePush;

    @Autowired
    private JaguarService.Iface jaguarService;

    //    @Scheduled(initialDelay = 1000, fixedRate = 10000000)
    public void dodododo() {
        Integer size = 16;
        String startId = null;
        Short sid = 4;
        List<com.iusworks.jaguar.domain.Device> deviceList = deviceDAO.devicesWithPagination(sid, startId, size);
        Integer count = 0;
        Notification notification = new Notification();
        notification.setAction("abc");
        notification.setAlert("测试alert");
        notification.setTitle("测试title");
        notification.setStoraged("......");

        String notifyId = "1233121234567";

        while (deviceList != null && deviceList.size() > 0) {
            count += deviceList.size();
            for (com.iusworks.jaguar.domain.Device d : deviceList) {
                startId = d.getId();
            }
            deviceList = deviceDAO.devicesWithPagination(sid, startId, size);
            logger.error("-----------------Count: {}", count);

            dispatcher.batchPush(notification, deviceList, notifyId);
        }


    }

//        @Scheduled(initialDelay = 1000*30, fixedRate = 10000000)
    public void testHuawei() {
        //
            System.out.println("huaweipush 老的");
        Notification notification = new Notification();
        notification.setAction("abc");
        notification.setAlert("测试alert");
        notification.setTitle("测试title");
        notification.setStoraged("......");

        notification.putToExt("notifyId", "1222");

        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 6);
        com.iusworks.jaguar.domain.DevicePlatformVoucher hw = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        hw.setVoucher("AHzkDJO53BtBqLbrU5Np7pjWKzCICpwBEUsQcqlbegEVeeKrExWgYLDo8ZSRWF-YaVl" +
                "-jd2IyEUR2VjTHmLheiQ29fmsJdAef_3dVb2xNVQ2y24NEl28xMvIwd4s9XeYTw");
        hw.setState(0);
        hw.setReqTime(new Date());
        hw.setUpdatedAt(hw.getReqTime());

        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> dpv = new HashMap<>();
        dpv.put(PushProviderEnum.Huawei.getDpvKey(), hw);
        Map<String, String> infos = new HashMap<>();
        infos.put("F", "honor");
        device.setInfos(infos);

        device.setDpv(dpv);

        //        huaweiPush.push(notification, device, "123");
    }

    public void testMi() {

        Notification notification = new Notification();
        notification.setAction("abc");
        notification.setAlert("测试alert");
        notification.setTitle("测试title");
        notification.setStoraged("......");


        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();


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
        String token = TokenUtil.sanitizeTokenString("<9eb026b3 7d7f6a30 8f74d1eb ec02f572 3045e99e b522f2e3 0caabbfc
         288a728a>");
        logger.info("{}", token);
        apns.push(notification, token);
    }


    private void noti_android() {
        Notification notification = genNotification();
        leanCloudPush.push(notification, "12345678-4312-1234-1234-1234567890ab");
    }
*/

    //        @Scheduled(initialDelay = 1000, fixedRate = 10000000)
    public void shms4test() {
        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 7);
        com.iusworks.jaguar.domain.DevicePlatformVoucher hw = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        hw.setVoucher("ALiIIqG5K3iLC2NT6inNmKcQh_VVaX4MA3nhdNXtwt64RUnN5YRIceh5Buy7XGwxCfd_8TMG-e3hRwb" +
                "-DZ5f5o8lEpxiE9xeOzTx-zsPb37g_v2oxnQGpTIeR5mL19Cf_g");
        hw.setState(0);
        hw.setReqTime(new Date());
        hw.setUpdatedAt(hw.getReqTime());

        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> dpv = new HashMap<>();
        dpv.put(PushProviderEnum.Huawei.getDpvKey(), hw);
        //        dpv.put(PushProviderEnum.Xiaomi.getDpvKey(), hw);
        //        dpv.put(PushProviderEnum.Xiaomi.getDpvKey(), null);

        Map<String, String> infos = new HashMap<>();
        infos.put("F", "honor");
        //        device.setInfos(infos);

        device.setDpv(dpv);
        String aa = new ObjectId().toHexString();
        System.out.println(aa);
        Notification notification = genNotification();
        Map<String, String> ext = new HashMap<>();
        ext.put("channelId", "vstore_1");
        ext.put("channelName", "订单结果通知");
        ext.put("channelDescription", "加油消费、油卡充值订单结果通知");
//        ext.put("notifyId", aa);
//        ext.put("intent",
//                "#Intent;launchFlags=0x10008000;component=com.petroun.vstore/com.gotoil.home.view.activity.WelComeActivity;S.notifyIdStr=%s;end");


        notification.setExt(ext);
        huawei4Push.push(notification, device, aa);

    }

//         @Scheduled(initialDelay = 1000, fixedRate = 10000000)
    public void sxiaomTest() {
        Notification notification = genNotification();

        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 7);
        com.iusworks.jaguar.domain.DevicePlatformVoucher mi = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        mi.setVoucher("X7SHH9pS4W84UrVh85btuNZtSZpGTa2weiSQuLyH/sIG4yOHYL4+1e4jj3dtkaV2");
        mi.setState(0);
        mi.setReqTime(new Date());
        mi.setUpdatedAt(mi.getReqTime());

        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> dpv = new HashMap<>();
        dpv.put(PushProviderEnum.Xiaomi.getDpvKey(), mi);
        device.setDpv(dpv);
        Map<String, String> infos = new HashMap<>();
        infos.put("F", "xiaomi");
//        device.setInfos(infos);

        String aa = new ObjectId().toHexString();
        Map<String, String> ext = new HashMap<>();
        ext.put("channelId", "vstore_1");
        ext.put("channelName", "a订单结果通知");
        ext.put("channelDescription", "a加油消费、油卡充值订单结果通知");
        ext.put("notifyId", aa);
        notification.setExt(ext);
        System.out.println(aa);
        miPush.push(notification, device, aa);
    }

    //        @Scheduled(initialDelay = 1000, fixedRate = 10000000)
    public void testApns() {
        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 7);
        com.iusworks.jaguar.domain.DevicePlatformVoucher hw = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        hw.setVoucher(",i8nm ");
        hw.setState(0);
        hw.setReqTime(new Date());
        hw.setUpdatedAt(hw.getReqTime());

        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> dpv = new HashMap<>();
        dpv.put(PushProviderEnum.Apple.getDpvKey(), hw);

        Map<String, String> infos = new HashMap<>();
        infos.put("F", "apple");
        //        device.setInfos(infos);

        device.setState(DeviceState.Normal.getValue());
        device.setDpv(dpv);
        String aa = new ObjectId().toHexString();
        System.out.println(aa);
        Notification notification = genNotification();
        //        notification.setEnv(Environment.Prod);
        notification.setEnv(Environment.Dev);
        Map<String, String> ext = new HashMap<>();
        ext.put("channelId", "vstore_1");
        ext.put("channelName", "a订单结果通知");
        ext.put("channelDescription", "a加油消费、油卡充值订单结果通知");
        //        ext.put("notifyId", aa);
        //        ext.put("intent",
        //                "#Intent;launchFlags=0x10008000;component=com.petroun.vstore/com.gotoil.home.view.activity
        // .WelComeActivity;S.notifyIdStr=%s;end");


        notification.setExt(ext);
        applePush.push(notification, device, aa);

    }

    @Autowired
    GetuiPush getuiPush;

    //    @Scheduled(initialDelay = 1000, fixedRate = 10000000)
    public void gttest() {
        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 7);
        device.setUid("AAAA");
        com.iusworks.jaguar.domain.DevicePlatformVoucher hw = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        hw.setVoucher("ALiIIqG5K3iLC2NT6inNmKcQh_VVaX4MA3nhdNXtwt64RUnN5YRIceh5Buy7XGwxCfd_8TMG-e3hRwb" +
                "-DZ5f5o8lEpxiE9xeOzTx-zsPb37g_v2oxnQGpTIeR5mL19Cf_g");
        hw.setState(0);
        hw.setReqTime(new Date());
        hw.setUpdatedAt(hw.getReqTime());

        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> dpv = new HashMap<>();
        dpv.put(PushProviderEnum.Getui.getDpvKey(), hw);
        //        dpv.put(PushProviderEnum.Xiaomi.getDpvKey(), hw);
        //        dpv.put(PushProviderEnum.Xiaomi.getDpvKey(), null);

        Map<String, String> infos = new HashMap<>();
        infos.put("F", "GT");
        //        device.setInfos(infos);

        device.setDpv(dpv);
        String aa = new ObjectId().toHexString();
        System.out.println(aa);
        Notification notification = genNotification();
        Map<String, String> ext = new HashMap<>();
        ext.put("channelId", "vstore_1");
        ext.put("channelName", "订单结果通知");
        ext.put("channelDescription", "加油消费、油卡充值订单结果通知");
        //        ext.put("notifyId", aa);
        //        ext.put("intent",
        //                "#Intent;launchFlags=0x10008000;component=com.petroun.vstore/com.gotoil.home.view.activity
        // .WelComeActivity;S.notifyIdStr=%s;end");


        notification.setExt(ext);
        getuiPush.push(notification, device, aa);


    }


    //        @Scheduled(initialDelay = 1000, fixedRate = 10000000)
    public void dispatcherTest() {

        com.iusworks.jaguar.domain.Device device = new com.iusworks.jaguar.domain.Device();
        device.setSid((short) 7);
        device.setUid("AAAA");
        com.iusworks.jaguar.domain.DevicePlatformVoucher hw = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        hw.setVoucher("ALiIIqG5K3iLC2NT6inNmKcQh_VVaX4MA3nhdNXtwt64RUnN5YRIceh5Buy7XGwxCfd_8TMG-e3hRwb" +
                "-DZ5f5o8lEpxiE9xeOzTx-zsPb37g_v2oxnQGpTIeR5mL19Cf_g");
        hw.setState(0);
        hw.setReqTime(new Date());
        hw.setUpdatedAt(hw.getReqTime());

        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> dpv = new HashMap<>();
        dpv.put(PushProviderEnum.Xiaomi.getDpvKey(), hw);

        Map<String, String> infos = new HashMap<>();
        infos.put("F", "vivo");
        infos.put("M", "PD1619");

        device.setInfos(infos);

        device.setDpv(dpv);
        device.setType((byte) 2);
        String aa = new ObjectId().toHexString();
        System.out.println(aa);
        Notification notification = genNotification();
        Map<String, String> ext = new HashMap<>();
        ext.put("channelId", "vstore_1");
        ext.put("channelName", "订单结果通知");
        ext.put("channelDescription", "加油消费、油卡充值订单结果通知");
        //        ext.put("notifyId", aa);
        //        ext.put("intent",
        //                "#Intent;launchFlags=0x10008000;component=com.petroun.vstore/com.gotoil.home.view.activity
        // .WelComeActivity;S.notifyIdStr=%s;end");


        notification.setExt(ext);
        dispatcher.push(notification, device, aa);


    }

}



