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
import com.iusworks.jaguar.helper.ObjectHelper;
import com.iusworks.jaguar.provider.push.PushDataHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.NotifyIDUtils;
import com.xiaomi.xmpush.server.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MiPush implements Pushable {

    private static Logger logger = LoggerFactory.getLogger(MiPush.class);
    @Autowired
    private PushProperties pushProperties;

    @PostConstruct
    public void construct() {
        Constants.useOfficial();
    }

    @Override
    public boolean isSystemLevelSupport(Map<String, String> deviceInfo) {
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
    public boolean canUseForSystemLevel() {
        return true;
    }

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {

        Map<String, String> mi = xiaomi(device.getSid());
        if (mi == null) {
            logger.error("PushItem for android -> xiaomi not found");
            return false;
        }


        boolean passThrough = !isSystemLevelSupport(device.getInfos());
        Message message = buildMessage(notification, notifyId, mi, passThrough);
        Sender sender = new Sender(mi.get("appSecret"));

        try {
            DevicePlatformVoucher dpv = device.getDpv().get(PushProviderEnum.Xiaomi.getDpvKey());
            if (dpv == null) {
                logger.error("Device:{} for xiaomi voucher not found");
                return false;
            }
            Result r = sender.send(message, dpv.getVoucher(), 3);
            logger.debug("{}", r.toString());

        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return true;
    }

    @Override
    public void  batchPush(Notification notification, List<Device> deviceList, String notifyId) {

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
                if (isSystemLevelSupport(device.getInfos())) {
                    voucherList.add(voucher);
                } else {
                    voucherListForPassThrough.add(voucher);
                }
            }

            if (voucherList.size() > 0) {
                Message message = buildMessage(notification, notifyId, mi, false);
                Result result = sender.send(message, voucherList, 3);
               logger.error("mipush: {}", ObjectHelper.jsonString(result));
            }

            if (voucherListForPassThrough.size() > 0) {
                Message message = buildMessage(notification, notifyId, mi, true);
                Result result  = sender.send(message, voucherListForPassThrough, 3);
                logger.error("mipush: {}", ObjectHelper.jsonString(result));
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


    private Message buildMessage(Notification notification, String notifyId, Map<String, String> mi,
                                 boolean passThrough) {
        notification.getExt().put("notifyId",String.valueOf(NotifyIDUtils.generatorID(notifyId)));
        notification.getExt().put("notifyIdStr",notifyId);
        String packageName = mi.get("package");
        String appAction = mi.get("action");
        Message.Builder builder = new Message.Builder();
        builder.title(notification.getTitle());
        builder.description(notification.getAlert());
        builder.restrictedPackageName(packageName);
        builder.notifyId(NotifyIDUtils.generatorID(notifyId));
        builder.payload(PushDataHelper.jsonStringData(notification, appAction));

        if (passThrough) {
            builder.passThrough(1);
            //            logger.info("Pass Throught=============");
        } else {
            builder.extra(Constants.EXTRA_PARAM_NOTIFY_FOREGROUND, "0");
            String channelId="";
            if(notification.getExt().containsKey("channelId")){
                channelId = notification.getExt().get("channelId");
            }else{
                channelId =getChannelId(notification,mi);
            }
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(channelId)){
                builder.extra(Constants.PARAM_CHANNEL_ID,channelId);
            }
            builder.passThrough(0);
        }

        return builder.build();
    }


    /**
     * 取channel_ID ，按名字取 如果取不到就返回空格
     * 名字不匹配 取第一个
     * @param notification
     * @param mi
     * @return
     */
    private String getChannelId(Notification notification,Map<String,String> mi){
        ChannelHelper channelHelper = new ChannelHelper(mi.get("appSecret"));
        String channelId = "";
        try {
            Result result = channelHelper.getChannelList(1);
            if (result.getErrorCode().getValue() == 0) {
                JSONObject jo = result.getData();
                JSONArray ja = (JSONArray) jo.get("list");
                for (int i = 0; i < ja.size(); i++) {
                    JSONObject joo = (JSONObject) ja.get(i);
                    if (notification.getTitle().equals(joo.get(Constants.PARAM_CHANNEL_NAME))) {
                        return (String) joo.get(Constants.PARAM_CHANNEL_ID);
                    }
                }
                if (org.apache.commons.lang3.StringUtils.isEmpty(channelId)) {
                    JSONArray JA = ((JSONArray) result.getData().get("list"));
                    if (JA.size() == 0) {
                        logger.error("Mi Push Server haven't PushConfig");
                        return "";
                    } else {
                        return  (String) ((JSONObject) JA.get(0)).get(Constants.PARAM_CHANNEL_ID);
                    }
                }
            }
        } catch (IOException io) {
            logger.error("{}", io);
        }
        return  "";
    }
}
