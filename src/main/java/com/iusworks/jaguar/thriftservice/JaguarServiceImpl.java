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


import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.domain.Notifi;
import com.iusworks.jaguar.service.DeviceService;
import com.iusworks.jaguar.service.NotificationService;
import com.iusworks.jaguar.thrift.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    private PushProperties pushProperties;

    @Override
    public boolean device(DeviceRequest deviceRequest) throws JaguarException {

        if (pushProperties.itemBySystemId((int) deviceRequest.getSystemId()) == null) {
            throw JaguarError.jaguarExceptionFromError(JaguarError.UnsupportedSystem);
        }

        return deviceService.persistDevice(deviceRequest);
    }

    @Override
    public boolean devicePlatformVoucher(DevicePlatformVoucherRequest dpvRequest) throws JaguarException {
        if (pushProperties.itemBySystemId((int) dpvRequest.getSystemId()) == null) {
            throw JaguarError.jaguarExceptionFromError(JaguarError.UnsupportedSystem);
        }

        return deviceService.updatePlatformVoucher(dpvRequest);
    }

    @Override
    public boolean push(NotificationRequest notificationRequest) throws JaguarException {
        if (pushProperties.itemBySystemId((int) notificationRequest.getSystemId()) == null) {
            throw JaguarError.jaguarExceptionFromError(JaguarError.UnsupportedSystem);
        }

        String uid = notificationRequest.getNotification().getUid();
        if (uid != null && uid.contains(",")) {
            List<String> uidList = Arrays.asList(uid.split(","));
            for (String uidOne : uidList) {
                uidOne = uidOne.trim();
                if (uidOne.length() < 1) {
                    continue;
                }

                NotificationRequest oneRequest = notificationRequest.deepCopy();
                oneRequest.getNotification().setUid(uidOne);
                notificationPreRequest(oneRequest);
            }
        } else {
            notificationPreRequest(notificationRequest);
        }

        return true;
    }


    @Override
    public boolean pushReport(NotificationReportRequest reportRequest) throws JaguarException, TException {
        return false;
    }

    @Override
    public List<NotificationHistory> notificationHistory(QueryNotificationRequest queryNotificationRequest) throws JaguarException {

        if (pushProperties.itemBySystemId((int) queryNotificationRequest.getSystemId()) == null) {
            throw JaguarError.jaguarExceptionFromError(JaguarError.UnsupportedSystem);
        }

        return notificationService.histories(queryNotificationRequest.getSystemId(), queryNotificationRequest.getUid(),
                queryNotificationRequest.getStart());
    }

    @Override
    public boolean notificationReport(NotificationReportRequest notificationReportRequest) throws JaguarException {
        return false;
    }


    private void notificationPreRequest(NotificationRequest notificationRequest) {
        Notifi notifi = notificationService.persist(notificationRequest);

        logger.info("NotificationRequest: {}", notifi);

        Map<String, String> ext = new HashMap<>();
        ext.put("notifyId", notifi.getId());
        if (notificationRequest.getNotification().getExt() != null) {
            ext.putAll(notificationRequest.getNotification().getExt());
        }
        notificationRequest.getNotification().setExt(ext);
        notificationService.notify(notificationRequest, notifi.getId());
    }


    private enum JaguarError {

        UnsupportedSystem(9000, "Unsupported System Id");

        private Integer code;
        private String message;

        JaguarError(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static JaguarException jaguarExceptionFromError(JaguarError error) {
            return new JaguarException(error.getCode(), error.getMessage());
        }
    }
}
