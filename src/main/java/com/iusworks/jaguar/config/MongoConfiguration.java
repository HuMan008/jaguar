/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.config.MongoConfig
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/25/16 10:05 PM
 *
 */

package com.iusworks.jaguar.config;

import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoClientOptions mongoClientOptions() {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.writeConcern(WriteConcern.JOURNALED);
        builder.maxWaitTime(5000);
        builder.connectTimeout(2000);
        builder.socketTimeout(2000);
        builder.serverSelectionTimeout(1000);
        return builder.build();
    }
}
