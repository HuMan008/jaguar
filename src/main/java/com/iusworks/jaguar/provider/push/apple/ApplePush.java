/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.apple.APNS
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/29/16 2:08 PM
 *
 */

package com.iusworks.jaguar.provider.push.apple;


import com.iusworks.jaguar.AppleTokenDiscarder;
import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.config.push.PushItem;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DeviceState;
import com.iusworks.jaguar.provider.push.Push;
import com.iusworks.jaguar.thrift.Environment;
import com.iusworks.jaguar.thrift.Notification;
import com.relayrides.pushy.apns.ApnsClient;
import com.relayrides.pushy.apns.ApnsClientBuilder;
import com.relayrides.pushy.apns.ClientNotConnectedException;
import com.relayrides.pushy.apns.PushNotificationResponse;
import com.relayrides.pushy.apns.util.ApnsPayloadBuilder;
import com.relayrides.pushy.apns.util.SimpleApnsPushNotification;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class ApplePush implements Push {

    private static Logger logger = LoggerFactory.getLogger(ApplePush.class);

    private static EventLoopGroup pushEventLoopGroup = new NioEventLoopGroup(32);

    private Map<Integer, APNSClientPack> apnsClientMaps = new HashMap<>();

    @Autowired
    private PushProperties pushProperties;

    @PostConstruct
    void construct() throws Exception {
        for (PushItem pushItem : pushProperties.getPushs()) {
            if (pushItem.getApns() == null) {
                return;
            }

            APNSClientPack pack = new APNSClientPack();

            String cerpath = pushItem.getApns().getCerpath();
            String cerpass = pushItem.getApns().getCerpass();

            pack.setApnsClientProd(clientWithCertificate(cerpath, cerpass));

            String cerpathdev = cerpath.replace(".p12", "_dev.p12");
            pack.setApnsClientDev(clientWithCertificate(cerpathdev, cerpass));

            if (pack.getApnsClientDev() != null || pack.getApnsClientProd() != null) {
                apnsClientMaps.put(pushItem.getSystemId(), pack);
            }
        }

        reConnectToApns();
    }

    private ApnsClient clientWithCertificate(String cer, String password) throws Exception {
        ApnsClientBuilder builder = new ApnsClientBuilder();
        File file = null;

        try {
            file = new File(cer);
            if (!file.exists()) {
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource resource = resolver.getResource(cer);
                file = resource.getFile();
            }
        } catch (IOException ex) {
            logger.error("{}", ex.getMessage());
            file = null;
        }

        if (file == null) {
            logger.error("Certificate:{} not found", cer);
            return null;
        }

        builder.setClientCredentials(file, password);

        return builder.setEventLoopGroup(pushEventLoopGroup).build();
    }


    @PreDestroy
    void destruct() throws Exception {

        for (APNSClientPack pack : apnsClientMaps.values()) {
            ApnsClient clientProd = pack.getApnsClientProd();
            if (clientProd != null && clientProd.isConnected()) {
                clientProd.disconnect();
            }

            ApnsClient clientDev = pack.getApnsClientDev();
            if (clientDev != null && clientDev.isConnected()) {
                clientDev.disconnect();
            }
        }
    }

    private void reConnectToApns() throws Exception {
        for (APNSClientPack pack : apnsClientMaps.values()) {
            ApnsClient clientProd = pack.getApnsClientProd();

            if (clientProd != null && clientProd.isConnected()) {
                clientProd.disconnect().await();
            }

            ApnsClient clientDev = pack.getApnsClientDev();
            if (clientDev != null && clientDev.isConnected()) {
                clientDev.disconnect().await();
            }

            Future<Void> futureProd = null;
            if (clientProd != null) {
                futureProd = clientProd.connect(ApnsClient.PRODUCTION_APNS_HOST);
            }

            Future<Void> futureDev = null;
            if (clientDev != null) {
                futureDev = clientDev.connect(ApnsClient.DEVELOPMENT_APNS_HOST);
            }

            try {
                if (futureProd != null) {
                    futureProd.await();
                }
                if (futureDev != null) {
                    futureDev.await();
                }
            } catch (InterruptedException ex) {
                logger.error("{}", ex);
            }
        }
    }

    public boolean push(Notification notification, Device device, String notifyId) {
        if (device.getState().byteValue() != DeviceState.Normal.getValue().byteValue()) {
            return false;
        }


        PushItem pushItem = pushProperties.itemBySystemId((int) device.getSid());
        if (pushItem == null) {
            logger.error("PushItem is null for device:{}", device);
            return false;
        }

        APNSClientPack clientPairs = apnsClientMaps.get((int) device.getSid());
        if (clientPairs == null) {
            logger.error("APNSClientPack not found for:{}", device);
            return false;
        }

        ApnsClient client = null;
        if (notification.getEnv() == Environment.Dev) {
            client = clientPairs.getApnsClientDev();
        } else if (notification.getEnv() == Environment.Prod || notification.getEnv() == null) {
            client = clientPairs.getApnsClientProd();
        }

        if (client == null) {
            logger.info("Can not found env:{} for apns", notification.getEnv());
            return false;
        }

        dopush(notification, device, pushItem.getApns().getTopic(), client);
        return true;
    }

    public void dopush(Notification notification, Device device, String topic, ApnsClient apnsClient) {
        String token = device.getVouch();
        if (StringUtils.isEmpty(token)) {
            return;
        }

        ApnsPayloadBuilder apnsPayloadBuilder = new ApnsPayloadBuilder();
        apnsPayloadBuilder.setAlertBody(notification.getAlert());
        if (!StringUtils.isEmpty(notification.getTitle())) {
            apnsPayloadBuilder.setAlertTitle(notification.getTitle());
        }
        apnsPayloadBuilder.setBadgeNumber(notification.getBadge());

        if (!StringUtils.isEmpty(notification.getCategory())) {
            apnsPayloadBuilder.setCategoryName(notification.getCategory());
        }

        if (!StringUtils.isEmpty(notification.getSound())) {
            apnsPayloadBuilder.setSoundFileName(notification.getSound());
        }

        if (!StringUtils.isEmpty(notification.getAction())) {
            apnsPayloadBuilder.addCustomProperty("action", notification.getAction());
        }

        if (notification.getExtSize() > 0) {
            apnsPayloadBuilder.addCustomProperty("ext", notification.getExt());
        }

        String payload = apnsPayloadBuilder.buildWithDefaultMaximumLength();
        SimpleApnsPushNotification apnsPushNotification = new SimpleApnsPushNotification(token, topic,
                payload);

        try {
            Future<PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture = apnsClient.sendNotification(apnsPushNotification);
            PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                    sendNotificationFuture.get();
            if (!pushNotificationResponse.isAccepted()) {
                if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
                    logger.error("Notifi rejected by the APNs gateway: {} \t and the token is invalid as of {}",
                            pushNotificationResponse.getRejectionReason(),
                            pushNotificationResponse.getTokenInvalidationTimestamp());
                    // 处理错误Token
                    AppleTokenDiscarder.appendDiscardQueue(device.getId(), token);
                } else {
                    logger.error("Notifi rejected by the APNs gateway: {}", pushNotificationResponse.getRejectionReason());
                }
            } else {
                logger.info("Push to Apple Success:{}", pushNotificationResponse);
            }
        } catch (final Exception ex) {
            logger.error("Failed to send push notification. {}", ex);

            if (ex.getCause() instanceof ClientNotConnectedException) {
                logger.info("Waiting for client to reconnect…");
                try {
                    apnsClient.getReconnectionFuture().await();
                    logger.info("Reconnected.");
                } catch (InterruptedException iex) {
                    logger.error("{}", iex);
                }
            }
        }
    }


    class APNSClientPack {

        private ApnsClient apnsClientProd;

        private ApnsClient apnsClientDev;

        public ApnsClient getApnsClientProd() {
            return apnsClientProd;
        }

        public void setApnsClientProd(ApnsClient apnsClientProd) {
            this.apnsClientProd = apnsClientProd;
        }

        public ApnsClient getApnsClientDev() {
            return apnsClientDev;
        }

        public void setApnsClientDev(ApnsClient apnsClientDev) {
            this.apnsClientDev = apnsClientDev;
        }
    }
}
