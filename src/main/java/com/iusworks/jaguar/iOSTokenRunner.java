/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.iOSTokenRunner
 *
 * cluries <cluries@me.com>,  August 2017
 *
 * LastModified: 8/23/17 10:10 AM
 *
 */

package com.iusworks.jaguar;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class iOSTokenRunner {

    @Scheduled(initialDelay = 10000, fixedRate = 300000)
    public void remove() {

    }
}
