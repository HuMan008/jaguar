/*
 * Copyright (C) 2020.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * jaguar com.iusworks.jaguar.provider.push.huawei4.Huawei4Helper
 *
 * cluries <cluries@me.com>,  三月 2020
 *
 * LastModified: 20-3-9 上午10:06
 *
 */

package com.iusworks.jaguar.provider.push.huawei4;

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

public class Huawei4Helper {

    private static Logger logger = LoggerFactory.getLogger(Huawei4Helper.class);

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

        return androids.get("huawei4");
    }


    public static String huaweiVoucher(Device device) {
        Map<String, DevicePlatformVoucher> dvmap = device.getDpv();
        if (dvmap == null) {
            return null;
        }

        DevicePlatformVoucher dpv = dvmap.get(PushProviderEnum.Huawei4.getDpvKey());
        if (dpv == null) {
            return null;
        }

        return dpv.getVoucher();
    }


}
