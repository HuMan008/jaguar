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

package com.iusworks.jaguar.provider.push.leancloud;


import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.config.push.PushItem;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DevicePlatformVoucher;
import com.iusworks.jaguar.provider.push.PushDataHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.Hash;
import com.mashape.unirest.http.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LeanCloudPush implements Pushable {

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
        headers.put("Content-Type", "application/json");
        return headers;
    }

    public boolean push(Notification notification, Device device, String notifyId) {

        Map<String, String> lc = leancloud(device.getSid());
        if (lc == null) {
            logger.error("PushItem for android -> leancloud not found");
            return false;
        }

        String appId = lc.get("appId");
        String masterKey = lc.get("masterKey");
        String appAction = lc.get("action");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(masterKey) || StringUtils.isEmpty(appAction)) {
            logger.error("PushItem for android -> leancloud empty appId or masterKey or appAction");
            return false;
        }

        String installationId = leancloudToken(device);
        this.dopush(notification, installationId, appId, masterKey, appAction);

        return true;
    }

    @Override
    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {
        if (deviceList.size() < 1) {
            logger.error("Empty device list");
            return;
        }

        int systemId = deviceList.get(0).getSid();
        Map<String, String> lc = leancloud(systemId);
        if (lc == null) {
            logger.error("PushItem for android -> leancloud not found");
            return;
        }

        String appId = lc.get("appId");
        String masterKey = lc.get("masterKey");
        String appAction = lc.get("action");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(masterKey) || StringUtils.isEmpty(appAction)) {
            logger.error("PushItem for android -> leancloud empty appId or masterKey or appAction");
            return;
        }

        Map<String, Object> where = new HashMap<>();
        List<String> installationIdList = new ArrayList<>();
        deviceList.forEach((d) -> installationIdList.add(leancloudToken(d)));
        where.put("$in", installationIdList);
        dopush(notification, where, appId, masterKey, appAction);
    }

    private String leancloudToken(Device device) {
        String token = device.getVouch();
        Map<String, DevicePlatformVoucher> dvmap = device.getDpv();
        if (dvmap == null) {
            return token;
        }
        DevicePlatformVoucher lcDPV = dvmap.get(PushProviderEnum.Leancloud.getDpvKey());
        if (lcDPV != null && !StringUtils.isEmpty(lcDPV.getVoucher())) {
            token = lcDPV.getVoucher();
        }

        return token;
    }

    @Override
    public boolean isSupport(Map<String, String> deviceInfo) {
        return true;
    }


    @Override
    public PushProviderEnum provider() {
        return PushProviderEnum.Leancloud;
    }


    private Map<String, String> leancloud(int systemId) {
        PushItem pushItem = pushProperties.itemBySystemId(systemId);
        if (pushItem == null) {
            logger.error("PushItem for systemId:{} not found", systemId);
            return null;
        }

        Map<String, Map<String, String>> androids = pushItem.getAndroids();
        if (androids.size() < 1) {
            logger.error("PushItem for android configure not found");
            return null;
        }
        Map<String, String> lc = androids.get("leancloud");
        return lc;
    }

    public void dopush(Notification notification, Object installationId, String appId, String masterKey, String appAction) {

        Map<String, Object> data = PushDataHelper.data(notification, appAction);

        Map<String, Object> where = new HashMap<>();
        if (installationId != null) {
            where.put("installationId", installationId);
        }
        Map<String, Object> payload = new HashMap();
        payload.put("data", data);
        payload.put("where", where);


        //String response = AirHttpClient.POSTJSON(PUSH_URL, payload, signHeaders(appId, masterKey));
        logger.info("LC=========={}", payload);
        try {
            String response = Unirest.post(PUSH_URL).headers(signHeaders(appId, masterKey)).body(payload).asString().getBody();
            logger.info("response:{}", response);
        } catch (Exception ex) {
            logger.error("{}", ex);
        }
    }

}
