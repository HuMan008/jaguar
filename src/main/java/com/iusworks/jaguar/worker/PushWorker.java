/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.worker.Worker
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/27/17 6:16 PM
 *
 */

package com.iusworks.jaguar.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PushWorker implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(PushWorker.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.error("================");
    }


}
