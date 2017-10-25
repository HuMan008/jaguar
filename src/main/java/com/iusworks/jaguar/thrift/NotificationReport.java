/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.iusworks.jaguar.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-10-16")
public class NotificationReport implements org.apache.thrift.TBase<NotificationReport, NotificationReport._Fields>, java.io.Serializable, Cloneable, Comparable<NotificationReport> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NotificationReport");

  private static final org.apache.thrift.protocol.TField NOTIFICATION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("notificationId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField RECV_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("recvTime", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField CHANNEL_FIELD_DESC = new org.apache.thrift.protocol.TField("channel", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DEVICE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("deviceName", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new NotificationReportStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new NotificationReportTupleSchemeFactory();

  public java.lang.String notificationId; // required
  public int recvTime; // required
  public java.lang.String channel; // required
  public java.lang.String deviceName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NOTIFICATION_ID((short)1, "notificationId"),
    RECV_TIME((short)2, "recvTime"),
    CHANNEL((short)3, "channel"),
    DEVICE_NAME((short)4, "deviceName");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

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
        case 1: // NOTIFICATION_ID
          return NOTIFICATION_ID;
        case 2: // RECV_TIME
          return RECV_TIME;
        case 3: // CHANNEL
          return CHANNEL;
        case 4: // DEVICE_NAME
          return DEVICE_NAME;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __RECVTIME_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.DEVICE_NAME};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NOTIFICATION_ID, new org.apache.thrift.meta_data.FieldMetaData("notificationId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RECV_TIME, new org.apache.thrift.meta_data.FieldMetaData("recvTime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CHANNEL, new org.apache.thrift.meta_data.FieldMetaData("channel", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DEVICE_NAME, new org.apache.thrift.meta_data.FieldMetaData("deviceName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NotificationReport.class, metaDataMap);
  }

  public NotificationReport() {
  }

  public NotificationReport(
    java.lang.String notificationId,
    int recvTime,
    java.lang.String channel)
  {
    this();
    this.notificationId = notificationId;
    this.recvTime = recvTime;
    setRecvTimeIsSet(true);
    this.channel = channel;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NotificationReport(NotificationReport other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetNotificationId()) {
      this.notificationId = other.notificationId;
    }
    this.recvTime = other.recvTime;
    if (other.isSetChannel()) {
      this.channel = other.channel;
    }
    if (other.isSetDeviceName()) {
      this.deviceName = other.deviceName;
    }
  }

  public NotificationReport deepCopy() {
    return new NotificationReport(this);
  }

  @Override
  public void clear() {
    this.notificationId = null;
    setRecvTimeIsSet(false);
    this.recvTime = 0;
    this.channel = null;
    this.deviceName = null;
  }

  public java.lang.String getNotificationId() {
    return this.notificationId;
  }

  public NotificationReport setNotificationId(java.lang.String notificationId) {
    this.notificationId = notificationId;
    return this;
  }

  public void unsetNotificationId() {
    this.notificationId = null;
  }

  /** Returns true if field notificationId is set (has been assigned a value) and false otherwise */
  public boolean isSetNotificationId() {
    return this.notificationId != null;
  }

  public void setNotificationIdIsSet(boolean value) {
    if (!value) {
      this.notificationId = null;
    }
  }

  public int getRecvTime() {
    return this.recvTime;
  }

  public NotificationReport setRecvTime(int recvTime) {
    this.recvTime = recvTime;
    setRecvTimeIsSet(true);
    return this;
  }

  public void unsetRecvTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RECVTIME_ISSET_ID);
  }

  /** Returns true if field recvTime is set (has been assigned a value) and false otherwise */
  public boolean isSetRecvTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RECVTIME_ISSET_ID);
  }

  public void setRecvTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RECVTIME_ISSET_ID, value);
  }

  public java.lang.String getChannel() {
    return this.channel;
  }

  public NotificationReport setChannel(java.lang.String channel) {
    this.channel = channel;
    return this;
  }

  public void unsetChannel() {
    this.channel = null;
  }

  /** Returns true if field channel is set (has been assigned a value) and false otherwise */
  public boolean isSetChannel() {
    return this.channel != null;
  }

  public void setChannelIsSet(boolean value) {
    if (!value) {
      this.channel = null;
    }
  }

  public java.lang.String getDeviceName() {
    return this.deviceName;
  }

  public NotificationReport setDeviceName(java.lang.String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public void unsetDeviceName() {
    this.deviceName = null;
  }

  /** Returns true if field deviceName is set (has been assigned a value) and false otherwise */
  public boolean isSetDeviceName() {
    return this.deviceName != null;
  }

  public void setDeviceNameIsSet(boolean value) {
    if (!value) {
      this.deviceName = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case NOTIFICATION_ID:
      if (value == null) {
        unsetNotificationId();
      } else {
        setNotificationId((java.lang.String)value);
      }
      break;

    case RECV_TIME:
      if (value == null) {
        unsetRecvTime();
      } else {
        setRecvTime((java.lang.Integer)value);
      }
      break;

    case CHANNEL:
      if (value == null) {
        unsetChannel();
      } else {
        setChannel((java.lang.String)value);
      }
      break;

    case DEVICE_NAME:
      if (value == null) {
        unsetDeviceName();
      } else {
        setDeviceName((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case NOTIFICATION_ID:
      return getNotificationId();

    case RECV_TIME:
      return getRecvTime();

    case CHANNEL:
      return getChannel();

    case DEVICE_NAME:
      return getDeviceName();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case NOTIFICATION_ID:
      return isSetNotificationId();
    case RECV_TIME:
      return isSetRecvTime();
    case CHANNEL:
      return isSetChannel();
    case DEVICE_NAME:
      return isSetDeviceName();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof NotificationReport)
      return this.equals((NotificationReport)that);
    return false;
  }

  public boolean equals(NotificationReport that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_notificationId = true && this.isSetNotificationId();
    boolean that_present_notificationId = true && that.isSetNotificationId();
    if (this_present_notificationId || that_present_notificationId) {
      if (!(this_present_notificationId && that_present_notificationId))
        return false;
      if (!this.notificationId.equals(that.notificationId))
        return false;
    }

    boolean this_present_recvTime = true;
    boolean that_present_recvTime = true;
    if (this_present_recvTime || that_present_recvTime) {
      if (!(this_present_recvTime && that_present_recvTime))
        return false;
      if (this.recvTime != that.recvTime)
        return false;
    }

    boolean this_present_channel = true && this.isSetChannel();
    boolean that_present_channel = true && that.isSetChannel();
    if (this_present_channel || that_present_channel) {
      if (!(this_present_channel && that_present_channel))
        return false;
      if (!this.channel.equals(that.channel))
        return false;
    }

    boolean this_present_deviceName = true && this.isSetDeviceName();
    boolean that_present_deviceName = true && that.isSetDeviceName();
    if (this_present_deviceName || that_present_deviceName) {
      if (!(this_present_deviceName && that_present_deviceName))
        return false;
      if (!this.deviceName.equals(that.deviceName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetNotificationId()) ? 131071 : 524287);
    if (isSetNotificationId())
      hashCode = hashCode * 8191 + notificationId.hashCode();

    hashCode = hashCode * 8191 + recvTime;

    hashCode = hashCode * 8191 + ((isSetChannel()) ? 131071 : 524287);
    if (isSetChannel())
      hashCode = hashCode * 8191 + channel.hashCode();

    hashCode = hashCode * 8191 + ((isSetDeviceName()) ? 131071 : 524287);
    if (isSetDeviceName())
      hashCode = hashCode * 8191 + deviceName.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(NotificationReport other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetNotificationId()).compareTo(other.isSetNotificationId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNotificationId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.notificationId, other.notificationId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRecvTime()).compareTo(other.isSetRecvTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRecvTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.recvTime, other.recvTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetChannel()).compareTo(other.isSetChannel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChannel()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.channel, other.channel);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDeviceName()).compareTo(other.isSetDeviceName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDeviceName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deviceName, other.deviceName);
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
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("NotificationReport(");
    boolean first = true;

    sb.append("notificationId:");
    if (this.notificationId == null) {
      sb.append("null");
    } else {
      sb.append(this.notificationId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("recvTime:");
    sb.append(this.recvTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("channel:");
    if (this.channel == null) {
      sb.append("null");
    } else {
      sb.append(this.channel);
    }
    first = false;
    if (isSetDeviceName()) {
      if (!first) sb.append(", ");
      sb.append("deviceName:");
      if (this.deviceName == null) {
        sb.append("null");
      } else {
        sb.append(this.deviceName);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (notificationId == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'notificationId' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'recvTime' because it's a primitive and you chose the non-beans generator.
    if (channel == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'channel' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NotificationReportStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NotificationReportStandardScheme getScheme() {
      return new NotificationReportStandardScheme();
    }
  }

  private static class NotificationReportStandardScheme extends org.apache.thrift.scheme.StandardScheme<NotificationReport> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NotificationReport struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NOTIFICATION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.notificationId = iprot.readString();
              struct.setNotificationIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RECV_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.recvTime = iprot.readI32();
              struct.setRecvTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CHANNEL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.channel = iprot.readString();
              struct.setChannelIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DEVICE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.deviceName = iprot.readString();
              struct.setDeviceNameIsSet(true);
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
      if (!struct.isSetRecvTime()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'recvTime' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, NotificationReport struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.notificationId != null) {
        oprot.writeFieldBegin(NOTIFICATION_ID_FIELD_DESC);
        oprot.writeString(struct.notificationId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(RECV_TIME_FIELD_DESC);
      oprot.writeI32(struct.recvTime);
      oprot.writeFieldEnd();
      if (struct.channel != null) {
        oprot.writeFieldBegin(CHANNEL_FIELD_DESC);
        oprot.writeString(struct.channel);
        oprot.writeFieldEnd();
      }
      if (struct.deviceName != null) {
        if (struct.isSetDeviceName()) {
          oprot.writeFieldBegin(DEVICE_NAME_FIELD_DESC);
          oprot.writeString(struct.deviceName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NotificationReportTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NotificationReportTupleScheme getScheme() {
      return new NotificationReportTupleScheme();
    }
  }

  private static class NotificationReportTupleScheme extends org.apache.thrift.scheme.TupleScheme<NotificationReport> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NotificationReport struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.notificationId);
      oprot.writeI32(struct.recvTime);
      oprot.writeString(struct.channel);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDeviceName()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetDeviceName()) {
        oprot.writeString(struct.deviceName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NotificationReport struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.notificationId = iprot.readString();
      struct.setNotificationIdIsSet(true);
      struct.recvTime = iprot.readI32();
      struct.setRecvTimeIsSet(true);
      struct.channel = iprot.readString();
      struct.setChannelIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.deviceName = iprot.readString();
        struct.setDeviceNameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

