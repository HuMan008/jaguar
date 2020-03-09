/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.huawei.HuaweiHelper
 *
 * cluries <cluries@me.com>,  December 2017
 *
 * LastModified: 12/25/17 9:54 AM
 *
 */

package com.iusworks.jaguar.provider.push.huawei;

import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.config.push.PushItem;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DevicePlatformVoucher;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class HuaweiHelper {

    private static Logger logger = LoggerFactory.getLogger(HuaweiHelper.class);

    public static Map<String, String> huaweiProperties(PushProperties pushProperties, int systemId) {
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

        return androids.get("huawei");
    }

    public static String version(PushProperties pushProperties,int systemId){
         Map<String,String>   map =huaweiProperties(pushProperties,systemId);
         return StringUtils.isEmpty(map) ? "" : map.containsKey("version")? map.get("version") : "";

    }

    public static String deviceTokenListString(List<Device> deviceList) {
        StringBuilder deviceTokenListBuilder = new StringBuilder();
        deviceTokenListBuilder.append("[");
        deviceList.forEach((d) -> {
            String token  =huaweiVoucher(d);
            if(!StringUtils.isEmpty(token)){

                deviceTokenListBuilder.append("\"");
                deviceTokenListBuilder.append(token);
                deviceTokenListBuilder.append("\",");
            }

        });
        deviceTokenListBuilder.deleteCharAt(deviceTokenListBuilder.length() - 1);
        deviceTokenListBuilder.append("]");
        return deviceTokenListBuilder.toString();
    }

    public static String huaweiVoucher(Device device) {
        Map<String, DevicePlatformVoucher> dvmap = device.getDpv();
        if (dvmap == null) {
            return null;
        }

        DevicePlatformVoucher dpv = dvmap.get(PushProviderEnum.Huawei.getDpvKey());
        if (dpv == null) {
            return null;
        }

        return dpv.getVoucher();
    }
}
