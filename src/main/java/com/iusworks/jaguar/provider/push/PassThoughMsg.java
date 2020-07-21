/*
 * Copyright (C) 2020.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * jaguar com.iusworks.jaguar.provider.push.PassThoughMsg
 *
 * cluries <cluries@me.com>,  七月 2020
 *
 * LastModified: 20-7-6 下午2:19
 *
 */

package com.iusworks.jaguar.provider.push;

import com.iusworks.jaguar.helper.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 穿透数据结构
 *
 * @author think <syj247@qq.com>、
 * @date 2020-7-6、14:19
 */
public class PassThoughMsg {
    //标题
    String title;
    // 描述
    String alert;
    // 自定义数据
    Map<String, String> ext;

    public PassThoughMsg() {
    }

    public PassThoughMsg(String title, String alert, Map<String, String> ext) {
        this.title = title;
        this.alert = alert;
        this.ext = ext;
    }


    public String jsonString() {
        return ObjectHelper.jsonString(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }
}
