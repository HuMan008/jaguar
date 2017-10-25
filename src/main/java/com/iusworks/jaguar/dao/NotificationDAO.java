/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.dao.NotificationDAO
 *
 * cluries <cluries@me.com>,  October 2016
 *
 * LastModified: 10/12/16 10:42 AM
 *
 */

package com.iusworks.jaguar.dao;


import com.iusworks.jaguar.domain.Notifi;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NotificationDAO extends GenericMongoDAO<Notifi> {

    public List<Notifi> histories(Short systemId, String uid, Integer start) {
        //Criteria criteria = Criteria.where("sid").is(systemId).and("uid").in(uid, null).and("datetime").gt(start);
        Criteria criteria = Criteria.where("uid").in(uid, null)
                .and("sid").is(systemId).and("datetime").gt(start);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, Notifi.class);
    }
}
