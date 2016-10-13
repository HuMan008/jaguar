/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.service.SystemService
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/30/16 2:05 PM
 *
 */

package com.iusworks.jaguar.service;


import com.iusworks.jaguar.dao.SystemDAO;
import com.iusworks.jaguar.domain.System;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

//TODO 第一期不做
//@Service
public class SystemService {

    private static Logger logger = LoggerFactory.getLogger(SystemService.class);

    @Autowired
    private SystemDAO systemDAO;

    @PostConstruct
    void construct() {
        List<System> systemList = systemDAO.findAll();
        logger.debug("{}", systemList);
    }
}
