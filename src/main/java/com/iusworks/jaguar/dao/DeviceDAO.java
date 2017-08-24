/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.dao.DeviceDAO
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/24/16 11:45 PM
 *
 */

package com.iusworks.jaguar.dao;

import com.iusworks.jaguar.domain.Device;
import com.iusworks.jaguar.domain.DeviceState;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DeviceDAO extends GenericMongoDAO<Device> {

//    private static Logger logger = LoggerFactory.getLogger(DeviceDAO.class);

    public boolean discardVoucher(String id, String oldVucher) {
        Criteria criteria = Criteria.where("id").is(id).and("vouch").is(oldVucher);
        Update update = new Update();
        update.set("vouch", "");
        Query query = Query.query(criteria);
        WriteResult writeResult = mongoTemplate.upsert(query, update, Device.class);
        return writeResult != null && writeResult.getN() > 0;
    }

    public boolean upsert(Device device) {
        Set<String> querys = new HashSet<>();
        querys.add("sid");
        querys.add("uid");
        return super.upsert(device, querys, null);
    }

    public Device fetchBySystemIdAndUid(Short systemId, String uid) {

        Criteria criteria = Criteria.where("sid").is(systemId).and("uid").is(uid);
        Query query = Query.query(criteria);
        List<Device> devices = mongoTemplate.find(query, Device.class);
        if (devices == null || devices.size() < 1) {
            return null;
        }
        return devices.get(0);
    }

    public List<Device> devicesOnlyIncludeMust(Short systemId, Integer deviceType) {
        Criteria criteria = Criteria.where("sid").is(systemId).and("state").is(DeviceState.Normal.getValue()).and("type").is(deviceType);
        Query query = Query.query(criteria);
        query.fields().include("vouch").include("uid").include("type").include("sid");

        return mongoTemplate.find(query, Device.class);
    }
}
