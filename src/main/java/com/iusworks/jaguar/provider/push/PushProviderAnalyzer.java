/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.PushProviderAnalyzer
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/26/17 11:47 AM
 *
 */

package com.iusworks.jaguar.provider.push;


import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.provider.push.apple.ApplePush;
import com.iusworks.jaguar.provider.push.getui.GetuiPush;
import com.iusworks.jaguar.provider.push.huawei.HuaweiPush;
import com.iusworks.jaguar.provider.push.huawei4.Huawei4Push;
import com.iusworks.jaguar.provider.push.leancloud.LeanCloudPush;
import com.iusworks.jaguar.provider.push.xiaomi.MiPush;
import com.iusworks.jaguar.thrift.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PushProviderAnalyzer {

    @Autowired
    private ApplePush applePush;

    @Autowired
    private MiPush miPush;

    @Autowired
    private Huawei4Push huawei4Push;
    //    @Autowired
    //    private GetuiPush getuiPush;


    private List<Pushable> prividers = new ArrayList();

    private List<Pushable> androidProviders = new ArrayList<>();


    @PostConstruct
    public void construct() {
        prividers.add(applePush);
        prividers.add(miPush);
        prividers.add(huawei4Push);

        androidProviders.add(miPush);
        androidProviders.add(huawei4Push);
        //        androidProviders.add(getuiPush);

    }

    public Pushable pusherFromProvider(PushProviderEnum providerEnum) {
        if (providerEnum == PushProviderEnum.Apple) return applePush;
        if (providerEnum == PushProviderEnum.Xiaomi) return miPush;
        if (providerEnum == PushProviderEnum.Huawei)
            return huawei4Push;
        //        if (providerEnum == PushProviderEnum.Getui) return getuiPush;
        return null;
    }

    public Pushable analyzePusher(Device device) {

        if (device.getType() == DeviceType.iOS.getValue()) {
            return applePush;
        }

        Map<String, String> inf = device.getInfos();
        if (miPush.isSystemLevelSupport(inf)) {
            return miPush;
        }

        if(huawei4Push.isSystemLevelSupport(inf)){
            return huawei4Push;
        }

        //        if (getuiPush.isSystemLevelSupport(inf)) {
        //            return getuiPush;
        //        }

        return null;
    }

    public List<Pushable> getPrividers() {
        return prividers;
    }

    public List<Pushable> getAndroidProviders() {
        return androidProviders;
    }
}
