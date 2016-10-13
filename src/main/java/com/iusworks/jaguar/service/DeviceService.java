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
import com.iusworks.jaguar.thrift.DeviceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        logger.info("Update Device:{}", device);
        return deviceDAO.upsert(device);
    }
}
