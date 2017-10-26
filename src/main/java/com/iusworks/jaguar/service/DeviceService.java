/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.service.DeviceService
 *
 * cluries <cluries@me.com>,  October 2016
 *
 * LastModified: 10/8/16 11:35 AM
 *
 */

package com.iusworks.jaguar.service;


import com.iusworks.jaguar.dao.DeviceDAO;
import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.thrift.DevicePlatformVoucher;
import com.iusworks.jaguar.thrift.DevicePlatformVoucherRequest;
import com.iusworks.jaguar.thrift.DeviceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class DeviceService {

    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceDAO deviceDAO;


    public boolean persistDevice(DeviceRequest deviceRequest) {
        Device device = new Device();
        device.setSid(deviceRequest.getSystemId());
        device.setType((byte) deviceRequest.getDevice().getType().getValue());
        device.setTags(deviceRequest.getDevice().getTags());
        device.setCares(deviceRequest.getDevice().getCares());
        device.setUpdateAt(new Date());
        device.setUid(deviceRequest.getDevice().getUid());
        device.setVouch(deviceRequest.getDevice().getVoucher());
        device.setState((byte) deviceRequest.getDevice().getState());

        //After
        device.setDid(deviceRequest.getDevice().getDeviceId());

        device.setInfos(deviceRequest.getDevice().getDeviceInfo());

        Set<DevicePlatformVoucher> requestVouchers = deviceRequest.getDevice().getDpv();
        Map<String, com.iusworks.jaguar.domain.DevicePlatformVoucher> willInsertVouchers = new HashMap<>();
        if (requestVouchers != null) {
            for (DevicePlatformVoucher dpv : requestVouchers) {
                com.iusworks.jaguar.domain.DevicePlatformVoucher v = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
                v.setUpdatedAt(new Date());
                v.setVoucher(dpv.getVoucher());
                v.setState((int) dpv.getState());
                willInsertVouchers.put(dpv.getPlatform(), v);
            }
        }

        device.setDpv(willInsertVouchers);

        logger.info("Update Device:{}", device);
        return deviceDAO.upsert(device);
    }


    public boolean updatePlatformVoucher(DevicePlatformVoucherRequest request) {

        com.iusworks.jaguar.domain.DevicePlatformVoucher v = new com.iusworks.jaguar.domain.DevicePlatformVoucher();
        v.setState((int) request.getDpv().getState());
        v.setVoucher(request.getDpv().getVoucher());
        v.setUpdatedAt(new Date());

        return deviceDAO.updatePlatformDeviceVoucher(request.getSystemId(), request.getUid(),
                request.getDpv().getPlatform(), v);
    }
}
