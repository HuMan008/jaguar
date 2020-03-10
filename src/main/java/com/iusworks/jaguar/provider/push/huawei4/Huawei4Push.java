/*
 * Copyright (C) 2020.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * jaguar com.iusworks.jaguar.provider.push.huawei4.Huawei4Push
 *
 * cluries <cluries@me.com>,  三月 2020
 *
 * LastModified: 20-3-10 下午3:44
 *
 */

package com.iusworks.jaguar.provider.push.huawei4;

import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.helper.ObjectHelper;
import com.iusworks.jaguar.provider.push.PushProviderEnum;
import com.iusworks.jaguar.provider.push.Pushable;

import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.tools.NotifyIDUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 华为推送4.0
 *
 * @author think <syj247@qq.com>、
 * @date 2020-3-6、17:49
 */
@Component
public class Huawei4Push implements Pushable {

    private static final Logger logger = LoggerFactory.getLogger(Huawei4Push.class);
    //hms core 4.0版本
    private static final String PUSHURLV4 = "https://push-api.cloud.huawei.com/v1/%s/messages:send";
    @Autowired
    private Huawei4AccessToken accessToken4;
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


        String token = accessToken4.tokenForSystemId(device.getSid().intValue());
        if (StringUtils.isEmpty(token)) {
            logger.error("empty huawei token for systemId:{}", device.getSid());
            return;
        }

        Map<String, String> hwProperties = Huawei4Helper.huaweiProperties(pushProperties, device.getSid());

        //        String[] tokenArray = deviceList.stream().map(d -> huaweiVoucher(d)).toArray(String[]::new);
        List<String> tokenListStr =
                deviceList.stream().map(d -> Huawei4Helper.huaweiVoucher(d)).collect(Collectors.toList());

        String authorization = "Bearer " + token;

        Map body = new HashMap();
        //true测试环境；false 正式环境
        body.put("validate_only", false);
        Message message = buildMsg(notification, tokenListStr, hwProperties, passThrough, notifyId);
        body.put("message", message);

        try {
            HttpResponse<String> response = Unirest.post(String.format(PUSHURLV4, hwProperties.get("appId"))).header(
                    "Authorization", authorization).header("Content-Type", "application/json;charset=utf-8").body(ObjectHelper.jsonString(body)).asString();
            logger.debug("{}", response.getBody());
            logger.debug(ObjectHelper.jsonString(body));
        } catch (UnirestException e) {
            logger.error("{}", e.getMessage());
        }
    }

    private Message buildMsg(Notification notification, List<String> tokens, Map<String, String> huaweiProperties,
                             boolean passThrough, String notifyId) {
        int notifyIdIntValue =  NotifyIDUtils.generatorID(notifyId);
        notification.getExt().put("notifyId", String.valueOf(notifyIdIntValue));
        notification.getExt().put("notifyIdStr", notifyId);
        String  intentStr =huaweiProperties.get("intent")  ;
        if(intentStr.indexOf("notifyId")!=-1){
            intentStr = String.format(intentStr,notifyIdIntValue );
        }else if(intentStr.indexOf("notifyIdStr") !=-1){
            intentStr = String.format(intentStr,notifyId );
        }
        notification.getExt().put("intent" ,intentStr);
        Message message = new Message();
        message.setToken(tokens);
        if (passThrough) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", notification.getTitle());
            map.put("alert", notification.getAlert());
            map.put("ext", notification.getExt());
            message.setData(ObjectHelper.jsonString(map));
            message.setNotification(null);
            AndroidConfig androidConfig = new AndroidConfig();
            androidConfig.setNotification(null);
            message.setAndroid(androidConfig);
        } else {
            MyNotification myNotification = new MyNotification();
            myNotification.setTitle(notification.getTitle());
            myNotification.setBody(notification.getAlert());
            message.setNotification(myNotification);
            AndroidConfig androidConfig = new AndroidConfig();
            AndroidNotification androidNotification = new AndroidNotification();
            //            androidNotification.setTitle(notification.getTitle());
            //            androidNotification.setBody(notification.getAlert());
            //            androidNotification.setSound(notification.getSound());
            androidNotification.setDefault_sound(StringUtils.isEmpty(notification.getSound()));
            //            androidNotification.setTag(StringUtils.isEmpty(notification.getTags())?"":huaweiProperties
            // .get("appId"));
            /*
            // 自定义消息内容
            String key[],value[];
            if(!notification.getExt().isEmpty()){
                key = notification.getExt().entrySet().stream().map(e->e.getKey()).toArray(String[]::new);
                value = Arrays.stream(key).map(e->notification.getExt().get(e)).toArray(String[]::new);
                String keyStr =String.join(",",key);
                androidNotification.setBody_loc_key(keyStr);
                androidNotification.setTitle_loc_key(keyStr);
                androidNotification.setBody_loc_args(value);
                androidNotification.setTitle_loc_args(value);
            }*/
            androidNotification.setChannel_id(notification.getExt().get("channelId"));
            androidNotification.setNotify_summary(notification.getTitle());
            androidNotification.setNotify_id(NotifyIDUtils.generatorID(notification.getExt().get("notifyIdStr")));
            androidNotification.setTicker(notification.getTitle());
            ClickAction clickAction = new ClickAction();
            clickAction.setType(1);
            if (notification.getExt().containsKey("intent")) {
                clickAction.setIntent(intentStr);
            } else {
                clickAction.setAction(huaweiProperties.get("action"));
            }
            androidNotification.setClick_action(clickAction);
            //            androidNotification.setButtons(new Button[]);
            androidConfig.setNotification(androidNotification);
            message.setAndroid(androidConfig);
        }
        return message;
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

    class Message {
        String data = "";
        MyNotification notification = new MyNotification();
        AndroidConfig android = new AndroidConfig();
        //        ApnsConfig apns = new ApnsConfig();
        List<String> token;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public MyNotification getNotification() {
            return notification;
        }

        public void setNotification(MyNotification notification) {
            this.notification = notification;
        }

        public AndroidConfig getAndroid() {
            return android;
        }

        public void setAndroid(AndroidConfig android) {
            this.android = android;
        }

   /*     public ApnsConfig getApns() {
            return apns;
        }

        public void setApns(ApnsConfig apns) {
            this.apns = apns;
        }*/


        public List<String> getToken() {
            return token;
        }

        public void setToken(List<String> token) {
            this.token = token;
        }
    }

    class MyNotification {
        String title = "";
        String body = "";
        String image = "";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    class AndroidConfig {
        int collapse_key = -1;
        String urgency = "NORMAL";
        String category = "";
        String ttl = "";
        String bi_tag = "";
        //        int fast_app_target;
        String data = "";
        AndroidNotification notification = new AndroidNotification();

        public int getCollapse_key() {
            return collapse_key;
        }

        public void setCollapse_key(int collapse_key) {
            this.collapse_key = collapse_key;
        }

        public String getUrgency() {
            return urgency;
        }

        public void setUrgency(String urgency) {
            this.urgency = urgency;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTtl() {
            return ttl;
        }

        public void setTtl(String ttl) {
            this.ttl = ttl;
        }

        public String getBi_tag() {
            return bi_tag;
        }

        public void setBi_tag(String bi_tag) {
            this.bi_tag = bi_tag;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public AndroidNotification getNotification() {
            return notification;
        }

        public void setNotification(AndroidNotification notification) {
            this.notification = notification;
        }
    }

    class ApnsConfig {
        Object headers;
        Object payload;
        ApnsConfig.HmsOptions hms_options = new ApnsConfig.HmsOptions();

        public Object getHeaders() {
            return headers;
        }

        public void setHeaders(Object headers) {
            this.headers = headers;
        }

        public Object getPayload() {
            return payload;
        }

        public void setPayload(Object payload) {
            this.payload = payload;
        }

        public HmsOptions getHms_options() {
            return hms_options;
        }

        public void setHms_options(HmsOptions hms_options) {
            this.hms_options = hms_options;
        }

        class HmsOptions {
            //            目标用户类型，取值如下：
            //                    1：测试用户
            //2：正式用户
            //3：VoIP用户
            int target_user_type = 1;

            public int getTarget_user_type() {
                return target_user_type;
            }

            public void setTarget_user_type(int target_user_type) {
                this.target_user_type = target_user_type;
            }
        }
    }

    class AndroidNotification {
        String title = "";
        String body = "";
        String icon = "";
        String color = "";
        String sound = "";
        boolean default_sound = true;
        String tag = "";
        ClickAction click_action = new ClickAction();
        String body_loc_key = "";
        List<String> body_loc_args = new ArrayList<>();
        String title_loc_key = "";
        List<String> title_loc_args = new ArrayList<>();
        String channel_id = "";
        int style = 0;

        String notify_summary = "";
        int notify_id = 0;
        boolean foreground_show = false;
        String ticker;

        //Button[] buttons = new Button[3];
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStyle() {
            return style;
        }

        public void setStyle(int style) {
            this.style = style;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getNotify_summary() {
            return notify_summary;
        }

        public void setNotify_summary(String notify_summary) {
            this.notify_summary = notify_summary;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public boolean isDefault_sound() {
            return default_sound;
        }

        public void setDefault_sound(boolean default_sound) {
            this.default_sound = default_sound;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public ClickAction getClick_action() {
            return click_action;
        }

        public void setClick_action(ClickAction click_action) {
            this.click_action = click_action;
        }

        public String getBody_loc_key() {
            return body_loc_key;
        }

        public void setBody_loc_key(String body_loc_key) {
            this.body_loc_key = body_loc_key;
        }


        public String getTitle_loc_key() {
            return title_loc_key;
        }

        public void setTitle_loc_key(String title_loc_key) {
            this.title_loc_key = title_loc_key;
        }

        public List<String> getBody_loc_args() {
            return body_loc_args;
        }

        public void setBody_loc_args(List<String> body_loc_args) {
            this.body_loc_args = body_loc_args;
        }

        public List<String> getTitle_loc_args() {
            return title_loc_args;
        }

        public void setTitle_loc_args(List<String> title_loc_args) {
            this.title_loc_args = title_loc_args;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public int getNotify_id() {
            return notify_id;
        }

        public void setNotify_id(int notify_id) {
            this.notify_id = notify_id;
        }

        public boolean isForeground_show() {
            return foreground_show;
        }

        public void setForeground_show(boolean foreground_show) {
            this.foreground_show = foreground_show;
        }

        //        public Button[] getButtons() {
        //            return buttons;
        //        }
        //
        //        public void setButtons(Button[] buttons) {
        //            this.buttons = buttons;
        //        }
    }

    class ClickAction {
        //1：用户自定义点击行为
        //2：点击后打开特定url
        //3：点击后打开应用App
        //4：点击后打开富媒体信息
        int type = 1;
        String intent = "";
        String url = "";
        String rich_resource = "";
        String action = "";

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRich_resource() {
            return rich_resource;
        }

        public void setRich_resource(String rich_resource) {
            this.rich_resource = rich_resource;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

    }

    class Button {
        String name;
        /* 0：打开应用首页；
                 1：打开应用自定义页面；
                 2：打开指定的网页；
                 3：清除通知；
                 4：分享功能；*/ int action_type;
        /*    0：设置通过intent打开应用自定义页面；
                    1：设置通过action打开应用自定义页面；
            当action_type为1时，该字段必填。*/ int intent_type;
        /*
        当action_type为1，此字段按照intent_type字段设置应用页面的uri或者action，具体设置方式参考打开自定义App页面。
        当action_type为2，此字段设置打开指定网页的URL，URL使用的协议必须是HTTPS协议*/ String intent;
        //        最大长度1024。当字段action_type为0或1时，该字段用于在点击按钮后给应用透传数据，选填，格式必须为key-value形式：{"key1":"value1",
        // "key2":"value2",…}。当action_type为4时，此字段必选，为分享的内容。

        String data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAction_type() {
            return action_type;
        }

        public void setAction_type(int action_type) {
            this.action_type = action_type;
        }

        public int getIntent_type() {
            return intent_type;
        }

        public void setIntent_type(int intent_type) {
            this.intent_type = intent_type;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


}
