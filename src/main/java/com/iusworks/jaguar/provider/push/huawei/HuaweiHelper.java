/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.huawei.Huawei4Helper
 *
 * cluries <cluries@me.com>,  December 2017
 *
 * LastModified: 12/25/17 9:54 AM
 *
 */

package com.iusworks.jaguar.provider.push.huawei;

import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.config.push.PushItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Deprecated
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
}
