/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.config.push.Apns
 *
 * cluries <cluries@me.com>,  July 2017
 *
 * LastModified: 7/17/17 10:04 AM
 *
 */

package com.iusworks.jaguar.config.push;

/**
 * Created by cluries on 17/07/2017.
 */
public class Apns {

    private String topic;

    private String cerpath;

    private String cerpass;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCerpath() {
        return cerpath;
    }

    public void setCerpath(String cerpath) {
        this.cerpath = cerpath;
    }

    public String getCerpass() {
        return cerpass;
    }

    public void setCerpass(String cerpass) {
        this.cerpass = cerpass;
    }


    @Override
    public String toString() {
        return "Apns{" +
                "topic='" + topic + '\'' +
                ", cerpath='" + cerpath + '\'' +
                ", cerpass='" + cerpass + '\'' +
                '}';
    }
}
