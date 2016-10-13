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

@Component
@ConfigurationProperties(prefix = "jaguar")
public class JaguarProperties {

    private String host;

    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JaguarProperties)) return false;

        JaguarProperties that = (JaguarProperties) o;

        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        return port != null ? port.equals(that.port) : that.port == null;

    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (port != null ? port.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JaguarProperties{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
