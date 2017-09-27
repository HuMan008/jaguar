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

public enum PushProvider {
    
    Apple(1), Xiaomi(2), Huawei(3), Leancloud(50), Getui(51);

    private Integer code;


    PushProvider(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "PushProvider{" +
                "code=" + code +
                '}';
    }
}
