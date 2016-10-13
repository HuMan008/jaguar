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

package com.iusworks.jaguar.provider.apple;


import com.iusworks.jaguar.config.ApnsProperties;
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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;


@Component
public class APNS {

    private static Logger logger = LoggerFactory.getLogger(APNS.class);

    private static EventLoopGroup prodEventLoopGroup = new NioEventLoopGroup(16);
    private static EventLoopGroup devEventLoopGroup = new NioEventLoopGroup();

    private ApnsClient apnsClientProd;
    private ApnsClient apnsClientDev;

    @Autowired
    private ApnsProperties apnsProperties;

    @PostConstruct
    void construct() throws Exception {
        ApnsClientBuilder builder = new ApnsClientBuilder();

        File file;
        try {
            file = new File(apnsProperties.getCertificate());
            builder.setClientCredentials(file, apnsProperties.getPassword());
        } catch (Exception ex) {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(apnsProperties.getCertificate());
            file = resource.getFile();
            builder.setClientCredentials(file, apnsProperties.getPassword());
        }

        apnsClientProd = builder.setEventLoopGroup(prodEventLoopGroup).build();
        apnsClientDev = builder.setEventLoopGroup(devEventLoopGroup).build();

        reConnectToApns();
    }

    @PreDestroy
    void destruct() throws Exception {
        if (apnsClientProd.isConnected()) {
            apnsClientProd.disconnect();
        }

        if (apnsClientDev.isConnected()) {
            apnsClientDev.disconnect();
        }
    }

    private void reConnectToApns() throws Exception {
        if (apnsClientProd.isConnected()) {
            apnsClientProd.disconnect().await();
        }

        if (apnsClientDev.isConnected()) {
            apnsClientDev.disconnect().await();
        }

        Future<Void> futureProd = apnsClientProd.connect(ApnsClient.PRODUCTION_APNS_HOST);
        Future<Void> futureDev = apnsClientDev.connect(ApnsClient.DEVELOPMENT_APNS_HOST);

        try {
            futureProd.await();
            futureDev.await();
        } catch (InterruptedException ex) {
            logger.error("{}", ex);
        }
    }

    public void push(Notification notification, String token) {
        ApnsPayloadBuilder apnsPayloadBuilder = new ApnsPayloadBuilder();
        apnsPayloadBuilder.setAlertBody(notification.getAlert());
        apnsPayloadBuilder.setAlertTitle(notification.getTitle());
        apnsPayloadBuilder.setBadgeNumber(notification.getBadge());

        if (notification.getCategory() != null) {
            apnsPayloadBuilder.setCategoryName(notification.getCategory());
        }

        if (notification.getSound() != null) {
            apnsPayloadBuilder.setSoundFileName(notification.getSound());
        }

        if (notification.getAction() != null) {
            apnsPayloadBuilder.addCustomProperty("action", notification.getAction());
        }

        if (notification.getExtSize() > 0) {
            apnsPayloadBuilder.addCustomProperty("ext", notification.getExt());
        }

        String payload = apnsPayloadBuilder.buildWithDefaultMaximumLength();
        SimpleApnsPushNotification apnsPushNotification = new SimpleApnsPushNotification(token, apnsProperties.getTopic(),
                payload);

        ApnsClient apnsClient;
        if (notification.getEnv() != null && notification.getEnv() == Environment.Dev) {
            apnsClient = apnsClientDev;
        } else {
            apnsClient = apnsClientProd;
        }

        try {
            Future<PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture = apnsClient.sendNotification(apnsPushNotification);
            PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                    sendNotificationFuture.get();
            if (!pushNotificationResponse.isAccepted()) {
                if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
                    logger.error("Notifi rejected by the APNs gateway: {} \t and the token is invalid as of {}",
                            pushNotificationResponse.getRejectionReason(),
                            pushNotificationResponse.getTokenInvalidationTimestamp());
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
}
