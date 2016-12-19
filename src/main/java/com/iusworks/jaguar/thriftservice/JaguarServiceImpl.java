/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.service.JaguarServiceImpl
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/23/16 11:09 AM
 *
 */

package com.iusworks.jaguar.thriftservice;


import com.iusworks.jaguar.domain.Notifi;
import com.iusworks.jaguar.service.DeviceService;
import com.iusworks.jaguar.service.NotificationService;
import com.iusworks.jaguar.thrift.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JaguarServiceImpl implements JaguarService.Iface {

    private static Logger logger = LoggerFactory.getLogger(JaguarServiceImpl.class);

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private NotificationService notificationService;

    private static final int AppServerGatewaySystemID = 4;

    @Override
    public boolean device(DeviceRequest deviceRequest) throws JaguarException {

        if (deviceRequest.getSystemId() != AppServerGatewaySystemID) {
            throw new JaguarException(9000, "Unsupported system id");
        }

        return deviceService.persistDevice(deviceRequest);
    }

    @Override
    public boolean push(NotificationRequest notificationRequest) throws JaguarException {
        if (notificationRequest.getSystemId() != AppServerGatewaySystemID) {
            throw new JaguarException(9000, "Unsupported system id");
        }

        Notifi notifi = notificationService.persist(notificationRequest);
        logger.info("NotificationRequest:{}", notifi);

        Map<String, String> ext = new HashMap<>();
        ext.put("notifyId", notifi.getId());
        if (notificationRequest.getNotification().getExt() != null) {
            ext.putAll(notificationRequest.getNotification().getExt());
        }
        notificationRequest.getNotification().setExt(ext);
        notificationService.notify(notificationRequest);

        return true;
    }

    @Override
    public List<NotificationHistory> notificationHistory(QueryNotificationRequest queryNotificationRequest) throws JaguarException {

        return notificationService.histories(queryNotificationRequest.getSystemId(), queryNotificationRequest.getUid(),
                queryNotificationRequest.getStart());
    }
}
