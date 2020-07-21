/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.PushDataHelper
 *
 * cluries <cluries@me.com>,  October 2017
 *
 * LastModified: 10/25/17 5:39 PM
 *
 */

package com.iusworks.jaguar.provider.push;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.iusworks.jaguar.thrift.Notification;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PushDataHelper {

    private static final Set<String> SpecActions = new HashSet<>();

    private static ObjectWriter objectWriter = new ObjectMapper().writer();

    private static final Logger logger = LoggerFactory.getLogger(PushDataHelper.class);

    static {
        SpecActions.add("notice.balance");
        SpecActions.add("notice.article");
        SpecActions.add("notice.point");
        SpecActions.add("notice.text");
    }

    public static Map<String, Object> data(Notification notification, String appAction) {
        Map<String, Object> data = new HashedMap();
        if (notification.getCategory() != null) {
            data.put("category", notification.getCategory());
        }

        if (notification.getTitle() != null) {
            data.put("title", notification.getTitle());
        }


        {  // Action 放入ext中，除了之前的几种定义好的。 最外层的Action使用AppAction

            Map<String, String> ext = notification.getExt();
            if (ext == null) {
                ext = new HashMap<>();
            }

            if (notification.getAction() != null) {
                ext.put("action", notification.getAction());
            }

            data.put("action", appAction);

            if (notification.getAction() != null) {
                if (SpecActions.contains(notification.getAction())) {
                    data.put("action", notification.getAction());
                }
            }

            data.put("ext", ext);
        } //end


        if (notification.getSound() != null) {
            data.put("sound", notification.getSound());
            data.put("silent", false);
        } else {
            data.put("silent", true);
        }

        data.put("alert", notification.getAlert());

        return data;
    }


    public static String jsonStringData(Notification notification, String appAction) {
        try {
            return objectWriter.writeValueAsString(data(notification, appAction));
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return "";
    }
}
