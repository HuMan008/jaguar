///*
// * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
// *
// * Unauthorized copying of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// * Jaguar com.iusworks.jaguar.config.LeancloudProperties
// *
// * cluries <cluries@me.com>,  September 2016
// *
// * LastModified: 9/29/16 2:16 PM
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
//@ConfigurationProperties(prefix = "jaguar.push.leancloud")
//public class LeancloudProperties {
//
//    private String appId;
//
//    private String appKey;
//
//    private String masterKey;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof LeancloudProperties)) return false;
//
//        LeancloudProperties that = (LeancloudProperties) o;
//
//        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
//        if (appKey != null ? !appKey.equals(that.appKey) : that.appKey != null) return false;
//        return masterKey != null ? masterKey.equals(that.masterKey) : that.masterKey == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = appId != null ? appId.hashCode() : 0;
//        result = 31 * result + (appKey != null ? appKey.hashCode() : 0);
//        result = 31 * result + (masterKey != null ? masterKey.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "LeancloudProperties{" +
//                "appId='" + appId + '\'' +
//                ", appKey='" + appKey + '\'' +
//                ", masterKey='" + masterKey + '\'' +
//                '}';
//    }
//
//    public String getAppId() {
//        return appId;
//    }
//
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
//
//    public String getAppKey() {
//        return appKey;
//    }
//
//    public void setAppKey(String appKey) {
//        this.appKey = appKey;
//    }
//
//    public String getMasterKey() {
//        return masterKey;
//    }
//
//    public void setMasterKey(String masterKey) {
//        this.masterKey = masterKey;
//    }
//}
