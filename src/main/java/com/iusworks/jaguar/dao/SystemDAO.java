/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.dao.SystemDAO
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/30/16 2:04 PM
 *
 */

package com.iusworks.jaguar.dao;


import com.iusworks.jaguar.domain.System;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SystemDAO extends GenericMongoDAO<System> {

    public List<System> findAll() {
        return mongoTemplate.findAll(System.class);
    }
}
