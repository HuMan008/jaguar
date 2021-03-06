/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.iusworks.jaguar.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-10-16")
public class QueryNotificationRequest implements org.apache.thrift.TBase<QueryNotificationRequest, QueryNotificationRequest._Fields>, java.io.Serializable, Cloneable, Comparable<QueryNotificationRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryNotificationRequest");

  private static final org.apache.thrift.protocol.TField SYSTEM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("systemId", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SIGNATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("signature", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField UID_FIELD_DESC = new org.apache.thrift.protocol.TField("uid", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField START_FIELD_DESC = new org.apache.thrift.protocol.TField("start", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new QueryNotificationRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new QueryNotificationRequestTupleSchemeFactory();

  public short systemId; // required
  public int time; // required
  public java.lang.String signature; // required
  public java.lang.String uid; // required
  public int start; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SYSTEM_ID((short)1, "systemId"),
    TIME((short)2, "time"),
    SIGNATURE((short)3, "signature"),
    UID((short)4, "uid"),
    START((short)5, "start");

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
        case 1: // SYSTEM_ID
          return SYSTEM_ID;
        case 2: // TIME
          return TIME;
        case 3: // SIGNATURE
          return SIGNATURE;
        case 4: // UID
          return UID;
        case 5: // START
          return START;
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
  private static final int __SYSTEMID_ISSET_ID = 0;
  private static final int __TIME_ISSET_ID = 1;
  private static final int __START_ISSET_ID = 2;
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
    tmpMap.put(_Fields.UID, new org.apache.thrift.meta_data.FieldMetaData("uid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.START, new org.apache.thrift.meta_data.FieldMetaData("start", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryNotificationRequest.class, metaDataMap);
  }

  public QueryNotificationRequest() {
  }

  public QueryNotificationRequest(
    short systemId,
    int time,
    java.lang.String signature,
    java.lang.String uid,
    int start)
  {
    this();
    this.systemId = systemId;
    setSystemIdIsSet(true);
    this.time = time;
    setTimeIsSet(true);
    this.signature = signature;
    this.uid = uid;
    this.start = start;
    setStartIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryNotificationRequest(QueryNotificationRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.systemId = other.systemId;
    this.time = other.time;
    if (other.isSetSignature()) {
      this.signature = other.signature;
    }
    if (other.isSetUid()) {
      this.uid = other.uid;
    }
    this.start = other.start;
  }

  public QueryNotificationRequest deepCopy() {
    return new QueryNotificationRequest(this);
  }

  @Override
  public void clear() {
    setSystemIdIsSet(false);
    this.systemId = 0;
    setTimeIsSet(false);
    this.time = 0;
    this.signature = null;
    this.uid = null;
    setStartIsSet(false);
    this.start = 0;
  }

  public short getSystemId() {
    return this.systemId;
  }

  public QueryNotificationRequest setSystemId(short systemId) {
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

  public QueryNotificationRequest setTime(int time) {
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

  public java.lang.String getSignature() {
    return this.signature;
  }

  public QueryNotificationRequest setSignature(java.lang.String signature) {
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

  public java.lang.String getUid() {
    return this.uid;
  }

  public QueryNotificationRequest setUid(java.lang.String uid) {
    this.uid = uid;
    return this;
  }

  public void unsetUid() {
    this.uid = null;
  }

  /** Returns true if field uid is set (has been assigned a value) and false otherwise */
  public boolean isSetUid() {
    return this.uid != null;
  }

  public void setUidIsSet(boolean value) {
    if (!value) {
      this.uid = null;
    }
  }

  public int getStart() {
    return this.start;
  }

  public QueryNotificationRequest setStart(int start) {
    this.start = start;
    setStartIsSet(true);
    return this;
  }

  public void unsetStart() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __START_ISSET_ID);
  }

  /** Returns true if field start is set (has been assigned a value) and false otherwise */
  public boolean isSetStart() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __START_ISSET_ID);
  }

  public void setStartIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __START_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case SYSTEM_ID:
      if (value == null) {
        unsetSystemId();
      } else {
        setSystemId((java.lang.Short)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((java.lang.Integer)value);
      }
      break;

    case SIGNATURE:
      if (value == null) {
        unsetSignature();
      } else {
        setSignature((java.lang.String)value);
      }
      break;

    case UID:
      if (value == null) {
        unsetUid();
      } else {
        setUid((java.lang.String)value);
      }
      break;

    case START:
      if (value == null) {
        unsetStart();
      } else {
        setStart((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SYSTEM_ID:
      return getSystemId();

    case TIME:
      return getTime();

    case SIGNATURE:
      return getSignature();

    case UID:
      return getUid();

    case START:
      return getStart();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SYSTEM_ID:
      return isSetSystemId();
    case TIME:
      return isSetTime();
    case SIGNATURE:
      return isSetSignature();
    case UID:
      return isSetUid();
    case START:
      return isSetStart();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryNotificationRequest)
      return this.equals((QueryNotificationRequest)that);
    return false;
  }

  public boolean equals(QueryNotificationRequest that) {
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

    boolean this_present_uid = true && this.isSetUid();
    boolean that_present_uid = true && that.isSetUid();
    if (this_present_uid || that_present_uid) {
      if (!(this_present_uid && that_present_uid))
        return false;
      if (!this.uid.equals(that.uid))
        return false;
    }

    boolean this_present_start = true;
    boolean that_present_start = true;
    if (this_present_start || that_present_start) {
      if (!(this_present_start && that_present_start))
        return false;
      if (this.start != that.start)
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

    hashCode = hashCode * 8191 + ((isSetUid()) ? 131071 : 524287);
    if (isSetUid())
      hashCode = hashCode * 8191 + uid.hashCode();

    hashCode = hashCode * 8191 + start;

    return hashCode;
  }

  @Override
  public int compareTo(QueryNotificationRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSystemId()).compareTo(other.isSetSystemId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSystemId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.systemId, other.systemId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time, other.time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSignature()).compareTo(other.isSetSignature());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSignature()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.signature, other.signature);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUid()).compareTo(other.isSetUid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uid, other.uid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetStart()).compareTo(other.isSetStart());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStart()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.start, other.start);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("QueryNotificationRequest(");
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
    sb.append("uid:");
    if (this.uid == null) {
      sb.append("null");
    } else {
      sb.append(this.uid);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("start:");
    sb.append(this.start);
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
    if (uid == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uid' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'start' because it's a primitive and you chose the non-beans generator.
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

  private static class QueryNotificationRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public QueryNotificationRequestStandardScheme getScheme() {
      return new QueryNotificationRequestStandardScheme();
    }
  }

  private static class QueryNotificationRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<QueryNotificationRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryNotificationRequest struct) throws org.apache.thrift.TException {
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
          case 4: // UID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uid = iprot.readString();
              struct.setUidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // START
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.start = iprot.readI32();
              struct.setStartIsSet(true);
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
      if (!struct.isSetStart()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'start' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryNotificationRequest struct) throws org.apache.thrift.TException {
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
      if (struct.uid != null) {
        oprot.writeFieldBegin(UID_FIELD_DESC);
        oprot.writeString(struct.uid);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(START_FIELD_DESC);
      oprot.writeI32(struct.start);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryNotificationRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public QueryNotificationRequestTupleScheme getScheme() {
      return new QueryNotificationRequestTupleScheme();
    }
  }

  private static class QueryNotificationRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<QueryNotificationRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryNotificationRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI16(struct.systemId);
      oprot.writeI32(struct.time);
      oprot.writeString(struct.signature);
      oprot.writeString(struct.uid);
      oprot.writeI32(struct.start);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryNotificationRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.systemId = iprot.readI16();
      struct.setSystemIdIsSet(true);
      struct.time = iprot.readI32();
      struct.setTimeIsSet(true);
      struct.signature = iprot.readString();
      struct.setSignatureIsSet(true);
      struct.uid = iprot.readString();
      struct.setUidIsSet(true);
      struct.start = iprot.readI32();
      struct.setStartIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

