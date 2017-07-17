/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.leancloud.LeanCloudPush
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/29/16 2:09 PM
 *
 */

package com.iusworks.jaguar.provider.leancloud;


import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.config.push.PushItem;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.AirHttpClient;
import com.iusworks.jaguar.tools.Hash;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class LeanCloudPush {

    private static Logger logger = LoggerFactory.getLogger(LeanCloudPush.class);

    @Autowired
    private PushProperties pushProperties;

    private static final String PUSH_URL = "https://leancloud.cn/1.1/push";


    private Map<String, String> signHeaders(String appId, String masterKey) {
        long timestamp = Instant.now().toEpochMilli();
        String sign = Hash.md5(String.format("%d%s", timestamp, masterKey));
        sign = String.format("%s,%d,master", sign, timestamp);
        Map<String, String> headers = new HashMap<>();
        headers.put("X-LC-Id", appId);
        headers.put("X-LC-Sign", sign);
        return headers;
    }

    public void push(Notification notification, Device device) {
        PushItem pushItem = pushProperties.itemBySystemId((int) device.getSid());
        if (pushItem == null) {
            return;
        }

        Map<String, Map<String, String>> androids = pushItem.getAndroids();
        if (androids.size() < 1) {
            return;
        }
        Map<String, String> lc = androids.get("leancloud");
        if (lc == null) {
            return;
        }

        String appId = lc.get("appId");
        String masterKey = lc.get("masterKey");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(masterKey)) {
            return;
        }

        this.dopush(notification, device.getVouch(), appId, masterKey);
    }

    public void dopush(Notification notification, String installationId, String appId, String masterKey) {

        Map<String, Object> data = new HashedMap();
        if (notification.getCategory() != null) {
            data.put("category", notification.getCategory());
        }

        if (notification.getTitle() != null) {
            data.put("title", notification.getTitle());
        }

        if (notification.getAction() != null) {
            data.put("action", notification.getAction());
        }

        if (notification.getExtSize() > 0) {
            data.put("ext", notification.getExt());
        }


        if (notification.getSound() != null) {
            data.put("sound", notification.getSound());
        }


        data.put("silent", true);
        data.put("alert", notification.getAlert());

        Map<String, String> where = new HashedMap();
        if (installationId != null) {
            where.put("installationId", installationId);
        }
        Map<String, Object> payload = new HashedMap();
        payload.put("data", data);
        payload.put("where", where);

        String response = AirHttpClient.POSTJSON(PUSH_URL, payload, signHeaders(appId, masterKey));
        logger.info("response:{}", response);
    }

}
