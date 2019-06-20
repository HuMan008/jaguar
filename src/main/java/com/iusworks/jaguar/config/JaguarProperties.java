/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.config.JaguarProperties
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/29/16 1:39 PM
 *
 */

package com.iusworks.jaguar.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "jaguar")
public class JaguarProperties {

    private String host;

    private Integer ppport;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPpport() {
        return ppport;
    }

    public void setPpport(Integer ppport) {
        this.ppport = ppport;
    }

    @Override
    public String toString() {
        return "JaguarProperties{" +
                "host='" + host + '\'' +
                ", ppport=" + ppport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JaguarProperties)) return false;
        JaguarProperties that = (JaguarProperties) o;
        return Objects.equals(host, that.host) &&
                Objects.equals(ppport, that.ppport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, ppport);
    }
}
