/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.service.NotificationService
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/29/16 2:03 PM
 *
 */

package com.iusworks.jaguar.service;


import com.iusworks.jaguar.dao.DeviceDAO;
import com.iusworks.jaguar.dao.NotificationDAO;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DeviceState;
import com.iusworks.jaguar.domain.Notifi;
import com.iusworks.jaguar.provider.push.BatchExecutorManager;
import com.iusworks.jaguar.provider.push.Dispatcher;
import com.iusworks.jaguar.provider.push.apple.ApplePush;
import com.iusworks.jaguar.provider.push.leancloud.LeanCloudPush;
import com.iusworks.jaguar.thrift.DeviceType;
import com.iusworks.jaguar.thrift.Notification;
import com.iusworks.jaguar.thrift.NotificationHistory;
import com.iusworks.jaguar.thrift.NotificationRequest;
import org.apache.commons.collections.ListUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private ApplePush apns;

    @Autowired
    private Dispatcher dispatcher;

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    /**
     * @param notificationRequest
     * @Unused notifyId
     */
    public void notify(NotificationRequest notificationRequest, String notifyId) {

        if (notificationRequest.getNotification().getUid() == null) {
            batchAllNotify(notificationRequest, notifyId);
            return;
        }

        Notification notification = notificationRequest.getNotification();
        Device device = deviceDAO.fetchBySystemIdAndUid(notificationRequest.getSystemId(), notification.getUid());
        if (device == null) {
            logger.error("Device Not found with systemid:{} uid:{}", notificationRequest.getSystemId(), notification.getUid());
            return;
        }

        if (device.getState().byteValue() != DeviceState.Normal.getValue().byteValue()) {
            logger.error("Device is unused:{}", device);
            return;
        }

        dispatcher.push(notification, device, notifyId);

        /*
        logger.info("Pushable:{} Device:{}", notification, device);
        if (device.getType() == DeviceType.iOS.getValue()) {
            apns.push(notification, device, notifyId);
        } else if (device.getType() == DeviceType.Android.getValue()) {
            leanCloudPush.push(notification, device, notifyId);
        } else {
            logger.error("Error Device Type:{}", device.getType());
        }
        */
    }

    private void batchAllNotify(NotificationRequest notificationRequest, String notifyId) {
        BatchExecutorManager.execute(new BatchAllNotifyWorker(notificationRequest, notifyId));
    }


    /**
     * @param notificationRequest
     */
    public Notifi persist(NotificationRequest notificationRequest) {
        Notifi notification = new Notifi();
        notification.setId((new ObjectId()).toHexString());
        notification.setSid(notificationRequest.getSystemId());
        notification.setUid(notificationRequest.getNotification().getUid());
        notification.setAction(notificationRequest.getNotification().getAction());
        notification.setTitle(notificationRequest.getNotification().getTitle());
        notification.setAlert(notificationRequest.getNotification().getAlert());
        if (notificationRequest.getNotification().getEventTime() > 0) {
            notification.setDatetime(notificationRequest.getNotification().getEventTime());
        } else {
            notification.setDatetime((int) Instant.now().getEpochSecond());
        }
        notification.setStoraged(notificationRequest.getNotification().getStoraged());
        notificationDAO.insert(notification);

        return notification;
    }

    /**
     * @param systemId
     * @param uid
     * @param startTime
     * @return
     */
    public List<NotificationHistory> histories(Short systemId, String uid, Integer startTime) {

        if (startTime < 1) {
            Device device = deviceDAO.fetchBySystemIdAndUid(systemId, uid);
            if (device == null) {
                startTime = (int) Instant.now().getEpochSecond();
            } else {
                startTime = new ObjectId(device.getId()).getTimestamp();
            }
        }

        List<NotificationHistory> notificationHistories = new ArrayList<>();
        List<Notifi> notifications = notificationDAO.histories(systemId, uid, startTime);


        notifications.forEach((Notifi n)
                -> notificationHistories.add(domainNotification2NotificationHistory(n, uid)));

        return notificationHistories;
    }

    /**
     * @param notification
     * @return
     */
    private NotificationHistory domainNotification2NotificationHistory(Notifi notification, String uid) {

        NotificationHistory notificationHistory = new NotificationHistory();
        notificationHistory.setMid(notification.getId());
        notificationHistory.setAction(notification.getAction());
        notificationHistory.setTitle(notification.getTitle());
        notificationHistory.setAlert(notification.getAlert());
        notificationHistory.setDatetime(notification.getDatetime());
        notificationHistory.setUid(uid);
        notificationHistory.setStoraged(notification.getStoraged());
        return notificationHistory;
    }


    private class BatchAllNotifyWorker implements Runnable {

        private NotificationRequest notificationRequest;

        private String notifyId;

        public BatchAllNotifyWorker(NotificationRequest notificationRequest, String notifyId) {
            this.notificationRequest = notificationRequest;
            this.notifyId = notifyId;
        }

        @Override
        public void run() {
            // TODO
            Integer size = 2048;
            String startId = null;
            List<Device> deviceList = deviceDAO.devicesWithPagination(notificationRequest.getSystemId(), startId, size);
            while (deviceList != null && deviceList.size() > 0) {
                List<Device> copyedList = new ArrayList<>(deviceList);
                dispatcher.batchPush(notificationRequest.getNotification(), copyedList, notifyId);

                Device lastDevice = deviceList.get(deviceList.size() - 1);
                startId = lastDevice.getId();

                deviceList = deviceDAO.devicesWithPagination(notificationRequest.getSystemId(), startId, size);
            }
        }
    }


//    public static void main(String[] args) {
//        int now = (int) Instant.now().getEpochSecond();
//        String id = new ObjectId().toHexString();
//        int now2 = (new ObjectId(id)).getTimestamp();
//
//        logger.info("{} {}", now, now2);
//    }

}
