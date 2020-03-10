/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.PushProvider
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/26/17 11:50 AM
 *
 */

package com.iusworks.jaguar.provider.push;

public enum PushProviderEnum {

    Apple(1, "ap"), Xiaomi(2, "mi"), Huawei(3, "hw"), Leancloud(50, "lc"), Getui(51, "gt"),Huawei4(31,"hw");

    private Integer code;

    private String dpvKey;

    PushProviderEnum(Integer code, String dpvKey) {
        this.code = code;
        this.dpvKey = dpvKey;
    }

    public String getDpvKey() {
        return dpvKey;
    }

    public Integer getCode() {
        return code;
    }

    public static PushProviderEnum fromCode(Integer code) {
        if (code == Apple.code) {
            return Apple;
        }

        if (code == Xiaomi.code) {
            return Xiaomi;
        }

        if (code == Huawei.code) {
            return Huawei;
        }

        if (code == Leancloud.code) {
            return Leancloud;
        }

        if (code == Getui.code) {
            return Getui;
        }

        if(code.intValue() == Huawei4.code){
            return Huawei4;
        }

        return null;
    }

    @Override
    public String toString() {
        return "PushProviderEnum{" +
                "code=" + code +
                ", dpvKey='" + dpvKey + '\'' +
                '}';
    }


}
