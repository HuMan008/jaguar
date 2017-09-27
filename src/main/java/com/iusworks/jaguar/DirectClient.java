/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.DirectClient
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 8/4/17 10:18 AM
 *
 */

package com.iusworks.jaguar;


import com.iusworks.jaguar.thrift.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DirectClient {

    private static Logger logger = LoggerFactory.getLogger(DirectClient.class);

    private static String host = "127.0.0.1";
    private static short port = 18090;


    @PostConstruct
    public void construct() throws Exception {
    }


    public boolean device(DeviceRequest deviceRequest) {
        TTransport transport = new TSocket(host, port);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        JaguarService.Client client = new JaguarService.Client(protocol);

        if (client == null) {
            logger.error("can not create client.");
            return false;
        }

        try {
            transport.open();
            return client.device(deviceRequest);
        } catch (JaguarException ex) {
            logger.error("{}", ex);
            return false;
        } catch (TException ex) {
            transport.close();
            logger.error("{}", ex);
            return false;
        } finally {
            transport.close();
        }
    }

    public boolean devicePlatformVoucher(DevicePlatformVoucherRequest request) {
        TTransport transport = new TSocket(host, port);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        JaguarService.Client client = new JaguarService.Client(protocol);

        if (client == null) {
            logger.error("can not create client.");
            return false;
        }

        try {
            transport.open();
            return client.devicePlatformVoucher(request);
        } catch (JaguarException ex) {
            logger.error("{}", ex);
            return false;
        } catch (TException ex) {
            transport.close();
            logger.error("{}", ex);
            return false;
        } finally {
            transport.close();
        }

    }

    public boolean push(NotificationRequest notificationRequest) {
        TTransport transport = new TSocket(host, port);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        JaguarService.Client client = new JaguarService.Client(protocol);

        if (notificationRequest.getNotification().getEnv() == null) {
//            if (properties.getEnvDev()) {
            notificationRequest.getNotification().setEnv(Environment.Dev);
//            } else {
//                notificationRequest.getNotification().setEnv(Environment.Prod);
//            }
        } else {

        }

        if (client == null) {
            logger.error("can not create client.");
            return false;
        }

        try {
            transport.open();
            return client.push(notificationRequest);
        } catch (JaguarException ex) {
            logger.error("{}", ex);
            return false;
        } catch (TException ex) {
            transport.close();
            logger.error("{}", ex);
            return false;
        } finally {
            transport.close();
        }
    }


}
