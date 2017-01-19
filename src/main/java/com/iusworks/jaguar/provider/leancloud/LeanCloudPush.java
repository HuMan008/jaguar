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


import com.iusworks.jaguar.config.LeancloudProperties;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.AirHttpClient;
import com.iusworks.jaguar.tools.Hash;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class LeanCloudPush {

    private static Logger logger = LoggerFactory.getLogger(LeanCloudPush.class);

    @Autowired
    private LeancloudProperties leancloudProperties;

    private static final String PUSH_URL = "https://leancloud.cn/1.1/push";


//    private Map<String, String> addHeaders;
//
//    @PostConstruct
//    void construct() {
//        addHeaders = new HashedMap();
//        addHeaders.put("X-LC-Id", leancloudProperties.getAppId());
//        addHeaders.put("X-LC-Key", leancloudProperties.getAppKey());
//    }

    private Map<String, String> signHeaders() {
        long timestamp = Instant.now().toEpochMilli();
        String sign = Hash.md5(String.format("%d%s", timestamp, leancloudProperties.getMasterKey()));
        sign = String.format("%s,%d,master", sign, timestamp);
        Map<String, String> headers = new HashMap<>();
        headers.put("X-LC-Id", leancloudProperties.getAppId());
        headers.put("X-LC-Sign", sign);
        return headers;
    }

    public void push(Notification notification, String installationId) {

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

        String response = AirHttpClient.POSTJSON(PUSH_URL, payload, signHeaders());
        logger.info("response:{}", response);
    }


}
