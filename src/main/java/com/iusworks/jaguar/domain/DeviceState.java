/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.domain.DeviceState
 *
 * cluries <cluries@me.com>,  January 2017
 *
 * LastModified: 1/16/17 11:22 AM
 *
 */

package com.iusworks.jaguar.domain;


public enum DeviceState {
    Normal((byte) 0),
    Unused((byte) 1),
    Disabled((byte) 99);

    DeviceState(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    private Byte value;


}
