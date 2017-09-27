/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.thrift.DeviceRequest
 *
 * cluries <cluries@me.com>,  September 2017
 *
 * LastModified: 9/22/17 4:19 PM
 *
 */

/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.iusworks.jaguar.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-09-22")
public class DeviceRequest implements org.apache.thrift.TBase<DeviceRequest, DeviceRequest._Fields>, java.io.Serializable, Cloneable, Comparable<DeviceRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DeviceRequest");

  private static final org.apache.thrift.protocol.TField SYSTEM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("systemId", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SIGNATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("signature", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DEVICE_FIELD_DESC = new org.apache.thrift.protocol.TField("device", org.apache.thrift.protocol.TType.STRUCT, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DeviceRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DeviceRequestTupleSchemeFactory();

  public short systemId; // required
  public int time; // required
  public String signature; // required
  public Device device; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SYSTEM_ID((short)1, "systemId"),
    TIME((short)2, "time"),
    SIGNATURE((short)3, "signature"),
    DEVICE((short)4, "device");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
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
        case 4: // DEVICE
          return DEVICE;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SYSTEM_ID, new org.apache.thrift.meta_data.FieldMetaData("systemId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("time", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SIGNATURE, new org.apache.thrift.meta_data.FieldMetaData("signature", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DEVICE, new org.apache.thrift.meta_data.FieldMetaData("device", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Device.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DeviceRequest.class, metaDataMap);
  }

  public DeviceRequest() {
  }

  public DeviceRequest(
    short systemId,
    int time,
    String signature,
    Device device)
  {
    this();
    this.systemId = systemId;
    setSystemIdIsSet(true);
    this.time = time;
    setTimeIsSet(true);
    this.signature = signature;
    this.device = device;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DeviceRequest(DeviceRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.systemId = other.systemId;
    this.time = other.time;
    if (other.isSetSignature()) {
      this.signature = other.signature;
    }
    if (other.isSetDevice()) {
      this.device = new Device(other.device);
    }
  }

  public DeviceRequest deepCopy() {
    return new DeviceRequest(this);
  }

  @Override
  public void clear() {
    setSystemIdIsSet(false);
    this.systemId = 0;
    setTimeIsSet(false);
    this.time = 0;
    this.signature = null;
    this.device = null;
  }

  public short getSystemId() {
    return this.systemId;
  }

  public DeviceRequest setSystemId(short systemId) {
    this.systemId = systemId;
    setSystemIdIsSet(true);
    return this;
  }

  public void unsetSystemId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SYSTEMID_ISSET_ID);
  }

  /** Returns true if field systemId is set (has been assigned a value) and false otherwise */
  public boolean isSetSystemId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SYSTEMID_ISSET_ID);
  }

  public void setSystemIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SYSTEMID_ISSET_ID, value);
  }

  public int getTime() {
    return this.time;
  }

  public DeviceRequest setTime(int time) {
    this.time = time;
    setTimeIsSet(true);
    return this;
  }

  public void unsetTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  /** Returns true if field time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  public void setTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIME_ISSET_ID, value);
  }

  public String getSignature() {
    return this.signature;
  }

  public DeviceRequest setSignature(String signature) {
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

  public Device getDevice() {
    return this.device;
  }

  public DeviceRequest setDevice(Device device) {
    this.device = device;
    return this;
  }

  public void unsetDevice() {
    this.device = null;
  }

  /** Returns true if field device is set (has been assigned a value) and false otherwise */
  public boolean isSetDevice() {
    return this.device != null;
  }

  public void setDeviceIsSet(boolean value) {
    if (!value) {
      this.device = null;
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

    case DEVICE:
      if (value == null) {
        unsetDevice();
      } else {
        setDevice((Device)value);
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

    case DEVICE:
      return getDevice();

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
    case DEVICE:
      return isSetDevice();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DeviceRequest)
      return this.equals((DeviceRequest)that);
    return false;
  }

  public boolean equals(DeviceRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

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

    boolean this_present_device = true && this.isSetDevice();
    boolean that_present_device = true && that.isSetDevice();
    if (this_present_device || that_present_device) {
      if (!(this_present_device && that_present_device))
        return false;
      if (!this.device.equals(that.device))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + systemId;

    hashCode = hashCode * 8191 + time;

    hashCode = hashCode * 8191 + ((isSetSignature()) ? 131071 : 524287);
    if (isSetSignature())
      hashCode = hashCode * 8191 + signature.hashCode();

    hashCode = hashCode * 8191 + ((isSetDevice()) ? 131071 : 524287);
    if (isSetDevice())
      hashCode = hashCode * 8191 + device.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(DeviceRequest other) {
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
    lastComparison = Boolean.valueOf(isSetDevice()).compareTo(other.isSetDevice());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDevice()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.device, other.device);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DeviceRequest(");
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
    sb.append("device:");
    if (this.device == null) {
      sb.append("null");
    } else {
      sb.append(this.device);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'systemId' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'time' because it's a primitive and you chose the non-beans generator.
    if (signature == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'signature' was not present! Struct: " + toString());
    }
    if (device == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'device' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (device != null) {
      device.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DeviceRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DeviceRequestStandardScheme getScheme() {
      return new DeviceRequestStandardScheme();
    }
  }

  private static class DeviceRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<DeviceRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DeviceRequest struct) throws org.apache.thrift.TException {
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
          case 4: // DEVICE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.device = new Device();
              struct.device.read(iprot);
              struct.setDeviceIsSet(true);
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
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'systemId' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetTime()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'time' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DeviceRequest struct) throws org.apache.thrift.TException {
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
      if (struct.device != null) {
        oprot.writeFieldBegin(DEVICE_FIELD_DESC);
        struct.device.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DeviceRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DeviceRequestTupleScheme getScheme() {
      return new DeviceRequestTupleScheme();
    }
  }

  private static class DeviceRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<DeviceRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DeviceRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI16(struct.systemId);
      oprot.writeI32(struct.time);
      oprot.writeString(struct.signature);
      struct.device.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DeviceRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.systemId = iprot.readI16();
      struct.setSystemIdIsSet(true);
      struct.time = iprot.readI32();
      struct.setTimeIsSet(true);
      struct.signature = iprot.readString();
      struct.setSignatureIsSet(true);
      struct.device = new Device();
      struct.device.read(iprot);
      struct.setDeviceIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

