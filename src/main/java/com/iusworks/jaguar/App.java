/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Planet com.iusworks.plant.App
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/22/16 10:44 AM
 *
 */

package com.iusworks.jaguar;

import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    private static ApplicationContext applicationContext;

    private static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) throws TTransportException {
        applicationContext = SpringApplication.run(App.class, args);


    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}


