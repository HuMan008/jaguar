/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.getui.GetuiPush
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/21/17 5:34 PM
 *
 */

package com.iusworks.jaguar.provider.push.getui;

import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.*;
import com.gexin.rp.sdk.template.style.Style0;
import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.helper.ObjectHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.NotifyIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class GetuiPush implements Pushable {

    private static final String ProperitesKey = "gt";
    Logger logger = LoggerFactory.getLogger(GetuiPush.class);
    private Map<Integer, GTProperty> IGtPushMap = new HashMap<>();

    @Autowired
    private PushProperties pushProperties;


    @PostConstruct
    void construct() throws Exception {
        pushProperties.getPushs().forEach(p -> {
            if (p.getAndroids().containsKey(ProperitesKey)) {
                int sid = p.getSystemId();
                Map<String, String> gtMap = p.getAndroids().get(ProperitesKey);
                IGtPush iGtPush = new IGtPush(gtMap.get("appKey"), gtMap.get("masterSecret"));
                IGtPushMap.put(sid, new GTProperty(gtMap.get("appId"), gtMap.get("appSecret"), gtMap.get("appKey"),
                        gtMap.get("packageName"), gtMap.get("masterSecret"), iGtPush));
            }
        });
        logger.info("getui propeitiesMap {}", ObjectHelper.jsonString(IGtPushMap));
    }

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {
        List<Device> list = new ArrayList<>(1);
        list.add(device);
        batchPush(notification, list, notifyId);
        return true;
    }

    private AbstractTemplate buildNotification(Notification notification, String appId, String appKey,
                                               String notifyId, boolean isTrans) {
        int notifyIdIntValue = NotifyIDUtils.generatorID(notifyId);
        notification.getExt().put("notifyId", String.valueOf(notifyIdIntValue));
        notification.getExt().put("notifyIdStr", notifyId);
        if (isTrans) {
            TransmissionTemplate template = new TransmissionTemplate();
            template.setAppId(appId);
            template.setAppkey(appKey);
            template.setTransmissionType(2);
            template.setTransmissionContent(JSON.toJSONString(notification.getExt()));
            return template;
        }

        Style0 style = new Style0();
        // STEP2：设置推送标题、推送内容
        style.setTitle(notification.getTitle());
        style.setText(StringUtils.isEmpty(notification.getAlert()) ? notification.getExt().getOrDefault(
                "channel_description", "通知") : notification.getAlert());

        if (notification.getExt().containsKey("intent")) {
            StartActivityTemplate template = new StartActivityTemplate();
            template.setAppId(appId);
            template.setAppkey(appKey);
            style.setClearable(true);
            style.setChannel(notification.getExt().get("channelId"));
            style.setChannelName(notification.getExt().get("channelName"));
            style.setChannelLevel(3);
            template.setStyle(style);
            template.setNotifyid(notifyIdIntValue);
            template.setIntent(notification.getExt().get("intent"));
            return template;
        } else if (notification.getExt().containsKey("url")) {
            LinkTemplate template = new LinkTemplate();
            template.setStyle(style);
            template.setNotifyid(notifyIdIntValue);
            template.setUrl(notification.getExt().get("url"));
            return template;
        } else {
            // 打开首页
            NotificationTemplate template = new NotificationTemplate();
            template.setAppId(appId);
            template.setAppkey(appKey);
            template.setStyle(style);
            template.setTransmissionType(1);
            template.setNotifyid(notifyIdIntValue);
            return template;
        }


    }


    @Override
    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {
        Device device = deviceList.get(0);
        int sid = device.getSid();
        GTProperty gtProperty = IGtPushMap.get(sid);
        if (gtProperty == null) {
            logger.error("gt property is Empty");

        }
        IGtPush iGtPush = gtProperty.getiGtPush();
        if (iGtPush == null) {
            logger.error("IGtPush init fall");

        }
        boolean passThrough = !isSystemLevelSupport(device.getInfos());
        AbstractTemplate template = buildNotification(notification, gtProperty.getAppId(), gtProperty.getAppKey(),
                notifyId, passThrough);
        SingleMessage message = new SingleMessage();
        message.setData(template);
        IBatch batch = iGtPush.getBatch();
        try {
            for (Device d : deviceList) {
                Target target = new Target();
                target.setAppId(gtProperty.getAppId());
                target.setAlias(d.getUid());
                batch.add(message, target);
            }
            IPushResult iPushResult = batch.submit();
            logger.info("{}", ObjectHelper.jsonString(iPushResult));
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }

    @Override
    public boolean isSystemLevelSupport(Map<String, String> deviceInfo) {

        if (deviceInfo == null || StringUtils.isEmpty(deviceInfo.get("F"))) {
            return false;
        }
        String p = deviceInfo.get("F").toLowerCase();
        /**
         * 小米华为以外
         */
        if (p.contains("huawei") || p.contains("honor") || p.contains("xiaomi")) {
            return false;
        }
        return true;

    }

    @Override
    public boolean canUseForSystemLevel() {
        return false;
    }

    @Override
    public PushProviderEnum provider() {
        return PushProviderEnum.Getui;
    }


    public class GTProperty {
        String appId;
        String appSecret;
        String appKey;
        String packageName;
        String masterSecret;
        IGtPush iGtPush;

        public GTProperty(String appId, String appSecret, String appKey, String packageName, String masterSecret,
                          IGtPush iGtPush) {
            this.appId = appId;
            this.appSecret = appSecret;
            this.appKey = appKey;
            this.packageName = packageName;
            this.masterSecret = masterSecret;
            this.iGtPush = iGtPush;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getMasterSecret() {
            return masterSecret;
        }

        public void setMasterSecret(String masterSecret) {
            this.masterSecret = masterSecret;
        }

        public IGtPush getiGtPush() {
            return iGtPush;
        }

        public void setiGtPush(IGtPush iGtPush) {
            this.iGtPush = iGtPush;
        }
    }

    public Map<Integer, GTProperty> getIGtPushMap() {
        return IGtPushMap;
    }
}
