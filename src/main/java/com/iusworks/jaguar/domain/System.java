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
//import org.springframework.data.mongodb.core.mapping.Document;


// TODO 第一期不做
//@Document(collection = "jag_system")
public class System {

    @Id
    private Integer id;

    private String apTopic;

    private String apCertificate;

    private String apPassword;

    private String lcAppid;

    private String lcAppkey;

    private String lcMasterkey;
}
