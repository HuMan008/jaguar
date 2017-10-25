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
import com.iusworks.jaguar.domain.DevicePlatformVoucher;
import com.iusworks.jaguar.provider.push.PushDataHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MiPush implements Pushable {

    @Autowired
    private PushProperties pushProperties;

    @PostConstruct
    public void construct() {
        Constants.useOfficial();
    }

    private static Logger logger = LoggerFactory.getLogger(MiPush.class);


    @Override
    public boolean isSupport(Map<String, String> deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }

        String factory = deviceInfo.get("F");
        if (StringUtils.isEmpty(factory)) {
            return false;
        }


        return factory.toLowerCase().contains("xiaomi");
    }

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {

        Map<String, String> mi = xiaomi(device.getSid());
        if (mi == null) {
            logger.error("PushItem for android -> xiaomi not found");
            return false;
        }


        Message message = buildMessage(notification, notifyId, mi.get("package"), mi.get("action"), isSupport(device.getInfos()));
        Sender sender = new Sender(mi.get("appSecret"));

        try {
            DevicePlatformVoucher dpv = device.getDpv().get(PushProviderEnum.Xiaomi.getDpvKey());
            if (dpv == null) {
                logger.error("Device:{} for xiaomi voucher not found");
            }
            sender.send(message, dpv.getVoucher(), 3);
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return true;
    }

    @Override
    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {

        if (deviceList.size() < 1) {
            logger.error("empty device list");
            return;
        }

        int deviceSid = (int) deviceList.get(0).getSid();

        Map<String, String> mi = xiaomi(deviceSid);
        if (mi == null) {
            logger.error("PushItem for android -> xiaomi not found");
            return;
        }



        Sender sender = new Sender(mi.get("appSecret"));

        try {
            List<String> voucherList = new ArrayList<>();
            List<String> voucherListForPassThrough = new ArrayList<>();

            for (Device device : deviceList) {
                String voucher = xiaomiVoucher(device);
                if (StringUtils.isEmpty(voucher)) {
                    logger.error("Device:{} for xiaomi voucher not found");
                    continue;
                }
                if (isSupport(device.getInfos())) {
                    voucherList.add(voucher);
                } else {
                    voucherListForPassThrough.add(voucher);
                }
            }

            if (voucherList.size()>0) {
                Message message = buildMessage(notification, notifyId, mi.get("package"), mi.get("action"), false);
                sender.send(message, voucherList, 3);
            }

            if (voucherListForPassThrough.size()>0){
                Message message = buildMessage(notification, notifyId, mi.get("package"), mi.get("action"), true);
                sender.send(message, voucherListForPassThrough, 3);
            }

        } catch (Exception ex) {
            logger.error("{}", ex);
        }
    }

    private String xiaomiVoucher(Device device) {
        Map<String, DevicePlatformVoucher> dvmap = device.getDpv();
        if (dvmap == null) {
            return null;
        }
        DevicePlatformVoucher dpv = dvmap.get(PushProviderEnum.Xiaomi.getDpvKey());
        if (dpv == null) {
            return null;
        }

        return dpv.getVoucher();
    }


    @Override
    public PushProviderEnum provider() {
        return PushProviderEnum.Xiaomi;
    }


    private Map<String, String> xiaomi(int deviceSid) {
        PushItem pushItem = pushProperties.itemBySystemId(deviceSid);
        if (pushItem == null) {
            logger.error("PushItem for systemId:{} not found", deviceSid);
            return null;
        }

        Map<String, Map<String, String>> androids = pushItem.getAndroids();
        if (androids.size() < 1) {
            logger.error("PushItem for android configure not found");
            return null;
        }

        return androids.get("xiaomi");
    }


    private Message buildMessage(Notification notification, String notifyId, String packageName, String appAction, boolean passThrough) {
        Message.Builder builder = new Message.Builder();
        builder.payload(notification.getAlert());
        builder.title(notification.getTitle());
        builder.description(notification.getAlert());
        builder.restrictedPackageName(packageName);
        builder.notifyId(notifyId.hashCode());
        builder.payload(PushDataHelper.jsonStringData(notification, appAction));

        if (passThrough) {
            builder.extra(Constants.EXTRA_PARAM_NOTIFY_FOREGROUND, "0");
        } else {
            builder.passThrough(1);
        }

        if (notification.getExtSize() > 0) {
            notification.getExt().forEach((k, v) -> builder.extra(k, v));
        }
        return builder.build();
    }

}
