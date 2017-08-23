/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.config.PushProperties
 *
 * cluries <cluries@me.com>,  July 2017
 *
 * LastModified: 7/17/17 9:47 AM
 *
 */

package com.iusworks.jaguar.config;

import com.iusworks.jaguar.config.push.PushItem;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "jaguar")
public class PushProperties {

    private List<PushItem> pushs = new ArrayList<>();

    public List<PushItem> getPushs() {
        return pushs;
    }

    public void setPushs(List<PushItem> pushs) {
        this.pushs = pushs;
    }

    public PushItem itemBySystemId(Integer systemId) {
        for (PushItem item : pushs) {
            if (systemId == item.getSystemId()) {
                return item;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "PushProperties{" +
                "pushs=" + pushs +
                '}';
    }
}







