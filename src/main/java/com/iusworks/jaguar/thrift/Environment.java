/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.thrift.Environment
 *
 * cluries <cluries@me.com>,  October 2016
 *
 * LastModified: 10/12/16 3:26 PM
 *
 */

/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.iusworks.jaguar.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum Environment implements TEnum {
  Dev(1),
  Prod(2);

  private final int value;

  private Environment(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static Environment findByValue(int value) { 
    switch (value) {
      case 1:
        return Dev;
      case 2:
        return Prod;
      default:
        return null;
    }
  }
}
