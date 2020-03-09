/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.huawei.HuaweiPush
 *
 * cluries <cluries@me.com>,  October 2017
 *
 * LastModified: 9/27/17 11:31 PM
 *
 */

package com.iusworks.jaguar.provider.push.huawei;

import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DevicePlatformVoucher;
import com.iusworks.jaguar.helper.ObjectHelper;
import com.iusworks.jaguar.provider.push.PushDataHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;
import com.iusworks.jaguar.thrift.Notification;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HuaweiPush implements Pushable {

    @Autowired
    private HuaweiAccessToken accessToken;

    private static final Logger logger = LoggerFactory.getLogger(HuaweiPush.class);

    private static final String PUSHURL = "https://api.push.hicloud.com/pushsend.do";

    @Autowired
    private PushProperties pushProperties;

    @Override
    public boolean push(Notification notification, Device device, String notifyId) {
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);
        batchPush(notification, deviceList, notifyId);
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public void batchPush(Notification notification, List<Device> deviceList, String notifyId) {
        List<Device> passThroughLists = new ArrayList<>();
        List<Device> systemLists = new ArrayList<>();

        deviceList.forEach((d) -> {
            if (isSystemLevelSupport(d.getInfos())) {
                systemLists.add(d);
            } else {
                passThroughLists.add(d);
            }
        });

        if (passThroughLists.size() > 0) {
            doBatchPush(notification, passThroughLists, notifyId, true);
        }

        if (systemLists.size() > 0) {
            doBatchPush(notification, systemLists, notifyId, false);
        }
    }


    private void doBatchPush(Notification notification, List<Device> deviceList, String notifyId, boolean passThrough) {

        if (deviceList.size() < 1) {
            return;
        }
        Device device = deviceList.get(0);


        String token = accessToken.tokenForSystemId(device.getSid().intValue());
        if (StringUtils.isEmpty(token)) {
            logger.error("empty huawei token for systemId:{}", device.getSid());
            return;
        }

        Map<String, String> hwProperties = HuaweiHelper.huaweiProperties(pushProperties, device.getSid());

        String deviceListString =HuaweiHelper.deviceTokenListString(deviceList);
        String current = String.valueOf(Instant.now().getEpochSecond());
        Map<String, Object> payload = payloadForNotification(notification, hwProperties.get("action")
                , hwProperties.get("appPkgName"), passThrough);

        Map<String, Object> params = new HashMap<>();
        params.put("access_token", token);
        params.put("nsp_ts", current);
        params.put("nsp_svc", "openpush.message.api.send");
        params.put("device_token_list", deviceListString);
        params.put("payload", ObjectHelper.jsonString(payload));


        String nsp_ctx = "{\"ver\":\"1\", \"appId\":\"" + hwProperties.get("appId") + "\"}";

        try {
            HttpResponse<String> response = Unirest.post(PUSHURL).queryString("nsp_ctx", nsp_ctx).
                    header("Content-Type", "application/x-www-form-urlencoded").fields(params).asString();
            logger.info("{}", response.getBody());
        } catch (Exception ex) {
            logger.error("{}", ex);
        }
    }

    private Map<String, Object> payloadForNotification(Notification notification, String appAction, String appPkgName, boolean passThrough) {
        Map<String, Object> payload = new HashMap<>();
        HPS hps = new HPS();

        String logicPayload = PushDataHelper.jsonStringData(notification, appAction);

        //1 透传异步消息
        //3 系统通知栏异步消息
        hps.getMsg().setType(passThrough ? 1 : 3);
        if (passThrough) {
            hps.getMsg().setBody(logicPayload);
        } else {
            HPSMSGBODY hpsmsgbody = new HPSMSGBODY();
            hpsmsgbody.setContent(notification.alert);
            hpsmsgbody.setTitle(notification.title);
            hps.getMsg().getAction().getParam().setAppPkgName(appPkgName);
            hps.getMsg().setBody(hpsmsgbody);
        }

        Map<String, String> ext = new HashMap<>();
        ext.put("ext", logicPayload);
        hps.getExt().getCustomize().add(ext);

        payload.put("hps", hps);

        return payload;
    }



    @Override
    @SuppressWarnings("all")
    public boolean isSystemLevelSupport(Map<String, String> deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }

        String factory = deviceInfo.get("F");
        if (StringUtils.isEmpty(factory)) {
            return false;
        }

        factory = factory.toLowerCase();

        return factory.contains("huawei") || factory.contains("honor");
    }

    @Override
    public boolean canUseForSystemLevel() {
        return true;
    }

    @Override
    public PushProviderEnum provider() {
        return PushProviderEnum.Huawei;
    }



    class HPS {

        private MSG msg = new MSG();
        private EXT ext = new EXT();

        public MSG getMsg() {
            return msg;
        }

        public void setMsg(MSG msg) {
            this.msg = msg;
        }

        public EXT getExt() {
            return ext;
        }

        public void setExt(EXT ext) {
            this.ext = ext;
        }

        @Override
        public String toString() {
            return "HPS{" +
                    "msg=" + msg +
                    ", ext=" + ext +
                    '}';
        }

        class MSG {
            private Integer type;
            private Object body;
            private ACTION action = new ACTION();

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }

            public Object getBody() {
                return body;
            }

            public void setBody(Object body) {
                this.body = body;
            }

            public ACTION getAction() {
                return action;
            }

            public void setAction(ACTION action) {
                this.action = action;
            }

            @Override
            public String toString() {
                return "MSG{" +
                        "type=" + type +
                        ", body=" + body +
                        ", action=" + action +
                        '}';
            }

            class ACTION {

                private Integer type = 3;
                private PARAM param = new PARAM();

                public Integer getType() {
                    return type;
                }

                public void setType(Integer type) {
                    this.type = type;
                }

                public PARAM getParam() {
                    return param;
                }

                public void setParam(PARAM param) {
                    this.param = param;
                }

                @Override
                public String toString() {
                    return "ACTION{" +
                            "type=" + type +
                            ", param=" + param +
                            '}';
                }

                class PARAM {
                    //                    private String intent = "";
                    private String appPkgName;

//                    public String getIntent() {
//                        return intent;
//                    }
//
//                    public void setIntent(String intent) {
//                        this.intent = intent;
//                    }

                    public String getAppPkgName() {
                        return appPkgName;
                    }

                    public void setAppPkgName(String appPkgName) {
                        this.appPkgName = appPkgName;
                    }

                    @Override
                    public String toString() {
                        return "PARAM{" +
//                                "intent='" + intent + '\'' +
                                ", appPkgName='" + appPkgName + '\'' +
                                '}';
                    }
                }
            }
        }

        class EXT {

            private List<Object> customize = new ArrayList<>();

            public List<Object> getCustomize() {
                return customize;
            }


            @Override
            public String toString() {
                return "EXT{" +
                        "customize=" + customize +
                        '}';
            }
        }
    }


    class HPSMSGBODY {

        private String content;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "HPSMSGBODY{" +
                    "content='" + content + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

}
