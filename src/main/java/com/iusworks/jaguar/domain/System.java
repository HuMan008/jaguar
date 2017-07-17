/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.domain.System
 *
 * cluries <cluries@me.com>,  October 2016
 *
 * LastModified: 9/30/16 2:13 PM
 *
 */

package com.iusworks.jaguar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;

@Document(collection = "jag_system")
public class System {

    @Id
    private Integer id;

}
