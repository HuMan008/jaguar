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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@EnableScheduling
@SpringBootApplication
//@ComponentScan({"com.iusworks.jaguar",})
public class App {

    private static ApplicationContext applicationContext;

//    private static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) throws TTransportException {
        configUnirestObjectMapper();
        applicationContext = SpringApplication.run(App.class, args);
    }

    private static void configUnirestObjectMapper() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    @SuppressWarnings("all")
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}


