/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.config.push.PushItem
 *
 * cluries <cluries@me.com>,  July 2017
 *
 * LastModified: 7/17/17 10:04 AM
 *
 */

package com.iusworks.jaguar.config.push;

import java.util.Map;

/**
 * Created by cluries on 17/07/2017.
 */
public class PushItem {

    private Integer systemId;

    private Apns apns;

    private Map<String, Map<String, String>> androids;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Apns getApns() {
        return apns;
    }

    public void setApns(Apns apns) {
        this.apns = apns;
    }

    public Map<String, Map<String, String>> getAndroids() {
        return androids;
    }

    public void setAndroids(Map<String, Map<String, String>> androids) {
        this.androids = androids;
    }

    @Override
    public String toString() {
        return "PushItem{" +
                "systemId=" + systemId +
                ", apns=" + apns +
                ", androids=" + androids +
                '}';
    }
}
