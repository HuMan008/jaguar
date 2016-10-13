/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.thrift.NotificationRequest
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

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-10-12")
public class NotificationRequest implements org.apache.thrift.TBase<NotificationRequest, NotificationRequest._Fields>, java.io.Serializable, Cloneable, Comparable<NotificationRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NotificationRequest");

  private static final org.apache.thrift.protocol.TField SYSTEM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("systemId", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SIGNATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("signature", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField NOTIFICATION_FIELD_DESC = new org.apache.thrift.protocol.TField("notification", org.apache.thrift.protocol.TType.STRUCT, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new NotificationRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new NotificationRequestTupleSchemeFactory());
  }

  public short systemId; // required
  public int time; // required
  public String signature; // required
  public Notification notification; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SYSTEM_ID((short)1, "systemId"),
    TIME((short)2, "time"),
    SIGNATURE((short)3, "signature"),
    NOTIFICATION((short)4, "notification");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SYSTEM_ID
          return SYSTEM_ID;
        case 2: // TIME
          return TIME;
        case 3: // SIGNATURE
          return SIGNATURE;
        case 4: // NOTIFICATION
          return NOTIFICATION;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SYSTEMID_ISSET_ID = 0;
  private static final int __TIME_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SYSTEM_ID, new org.apache.thrift.meta_data.FieldMetaData("systemId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("time", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SIGNATURE, new org.apache.thrift.meta_data.FieldMetaData("signature", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NOTIFICATION, new org.apache.thrift.meta_data.FieldMetaData("notification", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Notification.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NotificationRequest.class, metaDataMap);
  }

  public NotificationRequest() {
  }

  public NotificationRequest(
    short systemId,
    int time,
    String signature,
    Notification notification)
  {
    this();
    this.systemId = systemId;
    setSystemIdIsSet(true);
    this.time = time;
    setTimeIsSet(true);
    this.signature = signature;
    this.notification = notification;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NotificationRequest(NotificationRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.systemId = other.systemId;
    this.time = other.time;
    if (other.isSetSignature()) {
      this.signature = other.signature;
    }
    if (other.isSetNotification()) {
      this.notification = new Notification(other.notification);
    }
  }

  public NotificationRequest deepCopy() {
    return new NotificationRequest(this);
  }

  @Override
  public void clear() {
    setSystemIdIsSet(false);
    this.systemId = 0;
    setTimeIsSet(false);
    this.time = 0;
    this.signature = null;
    this.notification = null;
  }

  public short getSystemId() {
    return this.systemId;
  }

  public NotificationRequest setSystemId(short systemId) {
    this.systemId = systemId;
    setSystemIdIsSet(true);
    return this;
  }

  public void unsetSystemId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SYSTEMID_ISSET_ID);
  }

  /** Returns true if field systemId is set (has been assigned a value) and false otherwise */
  public boolean isSetSystemId() {
    return EncodingUtils.testBit(__isset_bitfield, __SYSTEMID_ISSET_ID);
  }

  public void setSystemIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SYSTEMID_ISSET_ID, value);
  }

  public int getTime() {
    return this.time;
  }

  public NotificationRequest setTime(int time) {
    this.time = time;
    setTimeIsSet(true);
    return this;
  }

  public void unsetTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  /** Returns true if field time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return EncodingUtils.testBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  public void setTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIME_ISSET_ID, value);
  }

  public String getSignature() {
    return this.signature;
  }

  public NotificationRequest setSignature(String signature) {
    this.signature = signature;
    return this;
  }

  public void unsetSignature() {
    this.signature = null;
  }

  /** Returns true if field signature is set (has been assigned a value) and false otherwise */
  public boolean isSetSignature() {
    return this.signature != null;
  }

  public void setSignatureIsSet(boolean value) {
    if (!value) {
      this.signature = null;
    }
  }

  public Notification getNotification() {
    return this.notification;
  }

  public NotificationRequest setNotification(Notification notification) {
    this.notification = notification;
    return this;
  }

  public void unsetNotification() {
    this.notification = null;
  }

  /** Returns true if field notification is set (has been assigned a value) and false otherwise */
  public boolean isSetNotification() {
    return this.notification != null;
  }

  public void setNotificationIsSet(boolean value) {
    if (!value) {
      this.notification = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SYSTEM_ID:
      if (value == null) {
        unsetSystemId();
      } else {
        setSystemId((Short)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((Integer)value);
      }
      break;

    case SIGNATURE:
      if (value == null) {
        unsetSignature();
      } else {
        setSignature((String)value);
      }
      break;

    case NOTIFICATION:
      if (value == null) {
        unsetNotification();
      } else {
        setNotification((Notification)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SYSTEM_ID:
      return getSystemId();

    case TIME:
      return getTime();

    case SIGNATURE:
      return getSignature();

    case NOTIFICATION:
      return getNotification();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SYSTEM_ID:
      return isSetSystemId();
    case TIME:
      return isSetTime();
    case SIGNATURE:
      return isSetSignature();
    case NOTIFICATION:
      return isSetNotification();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof NotificationRequest)
      return this.equals((NotificationRequest)that);
    return false;
  }

  public boolean equals(NotificationRequest that) {
    if (that == null)
      return false;

    boolean this_present_systemId = true;
    boolean that_present_systemId = true;
    if (this_present_systemId || that_present_systemId) {
      if (!(this_present_systemId && that_present_systemId))
        return false;
      if (this.systemId != that.systemId)
        return false;
    }

    boolean this_present_time = true;
    boolean that_present_time = true;
    if (this_present_time || that_present_time) {
      if (!(this_present_time && that_present_time))
        return false;
      if (this.time != that.time)
        return false;
    }

    boolean this_present_signature = true && this.isSetSignature();
    boolean that_present_signature = true && that.isSetSignature();
    if (this_present_signature || that_present_signature) {
      if (!(this_present_signature && that_present_signature))
        return false;
      if (!this.signature.equals(that.signature))
        return false;
    }

    boolean this_present_notification = true && this.isSetNotification();
    boolean that_present_notification = true && that.isSetNotification();
    if (this_present_notification || that_present_notification) {
      if (!(this_present_notification && that_present_notification))
        return false;
      if (!this.notification.equals(that.notification))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_systemId = true;
    list.add(present_systemId);
    if (present_systemId)
      list.add(systemId);

    boolean present_time = true;
    list.add(present_time);
    if (present_time)
      list.add(time);

    boolean present_signature = true && (isSetSignature());
    list.add(present_signature);
    if (present_signature)
      list.add(signature);

    boolean present_notification = true && (isSetNotification());
    list.add(present_notification);
    if (present_notification)
      list.add(notification);

    return list.hashCode();
  }

  @Override
  public int compareTo(NotificationRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSystemId()).compareTo(other.isSetSystemId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSystemId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.systemId, other.systemId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time, other.time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSignature()).compareTo(other.isSetSignature());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSignature()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.signature, other.signature);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNotification()).compareTo(other.isSetNotification());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNotification()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.notification, other.notification);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("NotificationRequest(");
    boolean first = true;

    sb.append("systemId:");
    sb.append(this.systemId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("time:");
    sb.append(this.time);
    first = false;
    if (!first) sb.append(", ");
    sb.append("signature:");
    if (this.signature == null) {
      sb.append("null");
    } else {
      sb.append(this.signature);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("notification:");
    if (this.notification == null) {
      sb.append("null");
    } else {
      sb.append(this.notification);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // alas, we cannot check 'systemId' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'time' because it's a primitive and you chose the non-beans generator.
    if (signature == null) {
      throw new TProtocolException("Required field 'signature' was not present! Struct: " + toString());
    }
    if (notification == null) {
      throw new TProtocolException("Required field 'notification' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (notification != null) {
      notification.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NotificationRequestStandardSchemeFactory implements SchemeFactory {
    public NotificationRequestStandardScheme getScheme() {
      return new NotificationRequestStandardScheme();
    }
  }

  private static class NotificationRequestStandardScheme extends StandardScheme<NotificationRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NotificationRequest struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SYSTEM_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.systemId = iprot.readI16();
              struct.setSystemIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.time = iprot.readI32();
              struct.setTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SIGNATURE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.signature = iprot.readString();
              struct.setSignatureIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // NOTIFICATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.notification = new Notification();
              struct.notification.read(iprot);
              struct.setNotificationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetSystemId()) {
        throw new TProtocolException("Required field 'systemId' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetTime()) {
        throw new TProtocolException("Required field 'time' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, NotificationRequest struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SYSTEM_ID_FIELD_DESC);
      oprot.writeI16(struct.systemId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TIME_FIELD_DESC);
      oprot.writeI32(struct.time);
      oprot.writeFieldEnd();
      if (struct.signature != null) {
        oprot.writeFieldBegin(SIGNATURE_FIELD_DESC);
        oprot.writeString(struct.signature);
        oprot.writeFieldEnd();
      }
      if (struct.notification != null) {
        oprot.writeFieldBegin(NOTIFICATION_FIELD_DESC);
        struct.notification.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NotificationRequestTupleSchemeFactory implements SchemeFactory {
    public NotificationRequestTupleScheme getScheme() {
      return new NotificationRequestTupleScheme();
    }
  }

  private static class NotificationRequestTupleScheme extends TupleScheme<NotificationRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NotificationRequest struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI16(struct.systemId);
      oprot.writeI32(struct.time);
      oprot.writeString(struct.signature);
      struct.notification.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NotificationRequest struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.systemId = iprot.readI16();
      struct.setSystemIdIsSet(true);
      struct.time = iprot.readI32();
      struct.setTimeIsSet(true);
      struct.signature = iprot.readString();
      struct.setSignatureIsSet(true);
      struct.notification = new Notification();
      struct.notification.read(iprot);
      struct.setNotificationIsSet(true);
    }
  }

}

