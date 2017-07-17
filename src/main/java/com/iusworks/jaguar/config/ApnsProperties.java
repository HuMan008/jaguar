///*
// * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
// *
// * Unauthorized copying of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// * Jaguar com.iusworks.jaguar.config.ApnsProperties
// *
// * cluries <cluries@me.com>,  September 2016
// *
// * LastModified: 9/29/16 2:14 PM
// *
// */
//
//package com.iusworks.jaguar.config;
//
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "jaguar.push.apple")
//public class ApnsProperties {
//
//    private String certificate;
//    private String password;
//    private String topic;
//
//    @Override
//    public String toString() {
//        return "ApnsProperties{" +
//                "certificate='" + certificate + '\'' +
//                ", password='" + password + '\'' +
//                ", topic='" + topic + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ApnsProperties)) return false;
//
//        ApnsProperties that = (ApnsProperties) o;
//
//        if (certificate != null ? !certificate.equals(that.certificate) : that.certificate != null) return false;
//        if (password != null ? !password.equals(that.password) : that.password != null) return false;
//        return topic != null ? topic.equals(that.topic) : that.topic == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = certificate != null ? certificate.hashCode() : 0;
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + (topic != null ? topic.hashCode() : 0);
//        return result;
//    }
//
//    public String getCertificate() {
//        return certificate;
//    }
//
//    public void setCertificate(String certificate) {
//        this.certificate = certificate;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getTopic() {
//        return topic;
//    }
//
//    public void setTopic(String topic) {
//        this.topic = topic;
//    }
//}
