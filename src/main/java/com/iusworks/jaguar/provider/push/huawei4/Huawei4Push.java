/*
 * Copyright (C) 2020.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * jaguar com.iusworks.jaguar.provider.push.huawei4.Huawei4Push
 *
 * cluries <cluries@me.com>,  三月 2020
 *
 * LastModified: 20-3-10 下午3:44
 *
 */

package com.iusworks.jaguar.provider.push.huawei4;

import com.alibaba.fastjson.JSON;
import com.huawei.push.android.AndroidNotification;
import com.huawei.push.android.ClickAction;
import com.huawei.push.message.AndroidConfig;
import com.huawei.push.message.Message;
import com.huawei.push.messaging.HuaweiApp;
import com.huawei.push.messaging.HuaweiMessaging;
import com.huawei.push.model.Importance;
import com.huawei.push.model.Urgency;
import com.huawei.push.model.Visibility;
import com.huawei.push.reponse.SendResponse;
import com.huawei.push.util.InitAppUtils;
import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DevicePlatformVoucher;
import com.iusworks.jaguar.helper.ObjectHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.NotifyIDUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import java.util.stream.Collectors;

/**
 * 华为推送4.0
 *
 * @author think <syj247@qq.com>、
 * @date 2020-3-6、17:49
 */
@Component
public class Huawei4Push implements Pushable {

    private static final String ProperitesKey = "huawei4";
    private static final String ProperitesKey_param_appId = "appId";
    private static final String ProperitesKey_param_appSecert = "appSecret";

    private static final Logger logger = LoggerFactory.getLogger(Huawei4Push.class);


    @Autowired
    private PushProperties pushProperties;

    private Map<Integer, HuaweiApp> huaweiAppMap = new HashMap<>();


    @PostConstruct
    void construct() throws Exception {
        pushProperties.getPushs().forEach(p -> {
            if (p.getAndroids().containsKey(ProperitesKey)) {
                int sid = p.getSystemId();
                Map<String, String> huawei4Map = p.getAndroids().get(ProperitesKey);
                HuaweiApp huaweiApp = InitAppUtils.initializeApp(huawei4Map.get(ProperitesKey_param_appId),
                        huawei4Map.get(ProperitesKey_param_appSecert));
                huaweiAppMap.put(sid, huaweiApp);
            }
        });
    }


    @Override
    public boolean push(Notification notification, Device device, String notifyId) {
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);
        batchPush(notification, deviceList, notifyId);
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {
        List<Device> passThroughLists = new ArrayList<>();
        List<Device> systemLists = new ArrayList<>();

        deviceList.forEach((d) -> {
            if (isSystemLevelSupport(d.getInfos())) {
                systemLists.add(d);
            } else {
                passThroughLists.add(d);
            }
        });

        if (passThroughLists.size() > 0) {
            doBatchPush(notification, passThroughLists, notifyId, true);
        }

        if (systemLists.size() > 0) {
            doBatchPush(notification, systemLists, notifyId, false);
        }
    }

    private void doBatchPush(Notification notification, List<Device> deviceList, String notifyId, boolean passThrough) {

        if (deviceList.size() < 1) {
            return;
        }
        Device device = deviceList.get(0);

        HuaweiApp app = huaweiAppMap.get(device.getSid().intValue());
        HuaweiMessaging huaweiMessaging = HuaweiMessaging.getInstance(app);
        List<String> tokenListStr =
                deviceList.stream().map(d -> Huawei4Helper.huaweiVoucher(d)).filter(e -> !e.isEmpty()).collect(Collectors.toList());
        if (tokenListStr.isEmpty()) {
            logger.error("Device Token is Empty");
            return;
        }
        Map<String, String> hwProperties = Huawei4Helper.huaweiProperties(pushProperties, device.getSid());
        Message message = buildMsg(notification, tokenListStr, hwProperties, passThrough, notifyId);
        try {
            SendResponse response = huaweiMessaging.sendMessage(message);
            System.out.println(JSON.toJSONString(response));

        } catch (Exception e) {
            logger.error("{}", e);
        }


    }

    private Message buildMsg(Notification notification, List<String> tokens, Map<String, String> huaweiProperties,
                             boolean passThrough, String notifyId) {
        int notifyIdIntValue = NotifyIDUtils.generatorID(notifyId);
        notification.getExt().put("notifyId", String.valueOf(notifyIdIntValue));
        notification.getExt().put("notifyIdStr", notifyId);
        String intentStr = huaweiProperties.get("intent");
        if (intentStr.indexOf("notifyIdStr") != -1) {
            intentStr = String.format(intentStr, notifyId);
        } else if (intentStr.indexOf("notifyId") != -1) {
            intentStr = String.format(intentStr, notifyIdIntValue);
        }
        notification.getExt().put("intent", intentStr);
        Map<String, Object> map = new HashMap<>();
        map.put("title", notification.getTitle());
        map.put("alert", notification.getAlert());
        map.put("ext", notification.getExt());
        if (passThrough) {
            AndroidConfig androidConfig =
                    AndroidConfig.builder().setCollapseKey(-1).setUrgency(Urgency.HIGH.getValue()).setBiTag(notification.getExt().get("notifyId")).build();

            return Message.builder().setData(ObjectHelper.jsonString(map)).setAndroidConfig(androidConfig).addAllToken(tokens).build();
        } else {
            com.huawei.push.message.Notification huaweiNotication =
                    com.huawei.push.message.Notification.builder().setTitle(notification.getTitle()).setBody(notification.getAlert()).build();

            AndroidNotification androidNotification =
                    AndroidNotification.builder().setDefaultSound(true).setTitle(notification.getTitle()).setBody(notification.getAlert()).setClickAction(clickAction(notification)).setChannelId(notification.getExt().get("channelId")).setNotifySummary(notification.getExt().containsKey("channelName") ? notification.getExt().get("channelName") : "").setStyle(0).setAutoCancel(true).setNotifyId(notifyIdIntValue).setGroup(huaweiProperties.get("appId")).setImportance(Importance.NORMAL.getValue()).setVisibility(Visibility.PUBLIC.getValue()).setUseDefaultLight(true).setUseDefaultVibrate(true).setForegroundShow(false).build();

            AndroidConfig androidConfig =
                    AndroidConfig.builder().setCollapseKey(-1).setBiTag(notifyId).setNotification(androidNotification).build();

            return Message.builder().setNotification(huaweiNotication).setAndroidConfig(androidConfig).addAllToken(tokens).build();
        }
    }


    private ClickAction clickAction(Notification notification) {
        if (notification.getExt().containsKey("intent")) {
            return ClickAction.builder().setType(1).setIntent(notification.getExt().get("intent")).build();
        } else if (notification.getExt().containsKey("action")) {
            return ClickAction.builder().setType(1).setAction(notification.getExt().get("action")).build();
        } else if (notification.getExt().containsKey("url")) {
            return ClickAction.builder().setType(2).setUrl(notification.getExt().get("url")).build();
        } else {
            return ClickAction.builder().setType(3).build();
        }
    }

    @Override
    @SuppressWarnings("all")
    public boolean isSystemLevelSupport(Map<String, String> deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }

        String factory = deviceInfo.get("F");
        if (StringUtils.isEmpty(factory)) {
            return false;
        }

        factory = factory.toLowerCase();

        return factory.contains("huawei") || factory.contains("honor");
    }

    @Override
    public boolean canUseForSystemLevel() {
        return true;
    }

    @Override
    public PushProviderEnum provider() {
        return PushProviderEnum.Huawei4;
    }


}
