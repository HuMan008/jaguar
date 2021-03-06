/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.iusworks.jaguar.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-10-16")
public class NotificationReportRequest implements org.apache.thrift.TBase<NotificationReportRequest, NotificationReportRequest._Fields>, java.io.Serializable, Cloneable, Comparable<NotificationReportRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NotificationReportRequest");

  private static final org.apache.thrift.protocol.TField SYSTEM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("systemId", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SIGNATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("signature", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField REPORT_FIELD_DESC = new org.apache.thrift.protocol.TField("report", org.apache.thrift.protocol.TType.STRUCT, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new NotificationReportRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new NotificationReportRequestTupleSchemeFactory();

  public short systemId; // required
  public int time; // required
  public java.lang.String signature; // required
  public NotificationReport report; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SYSTEM_ID((short)1, "systemId"),
    TIME((short)2, "time"),
    SIGNATURE((short)3, "signature"),
    REPORT((short)4, "report");

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
        case 4: // REPORT
          return REPORT;
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
    tmpMap.put(_Fields.REPORT, new org.apache.thrift.meta_data.FieldMetaData("report", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, NotificationReport.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NotificationReportRequest.class, metaDataMap);
  }

  public NotificationReportRequest() {
  }

  public NotificationReportRequest(
    short systemId,
    int time,
    java.lang.String signature,
    NotificationReport report)
  {
    this();
    this.systemId = systemId;
    setSystemIdIsSet(true);
    this.time = time;
    setTimeIsSet(true);
    this.signature = signature;
    this.report = report;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NotificationReportRequest(NotificationReportRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.systemId = other.systemId;
    this.time = other.time;
    if (other.isSetSignature()) {
      this.signature = other.signature;
    }
    if (other.isSetReport()) {
      this.report = new NotificationReport(other.report);
    }
  }

  public NotificationReportRequest deepCopy() {
    return new NotificationReportRequest(this);
  }

  @Override
  public void clear() {
    setSystemIdIsSet(false);
    this.systemId = 0;
    setTimeIsSet(false);
    this.time = 0;
    this.signature = null;
    this.report = null;
  }

  public short getSystemId() {
    return this.systemId;
  }

  public NotificationReportRequest setSystemId(short systemId) {
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

  public NotificationReportRequest setTime(int time) {
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

  public NotificationReportRequest setSignature(java.lang.String signature) {
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

  public NotificationReport getReport() {
    return this.report;
  }

  public NotificationReportRequest setReport(NotificationReport report) {
    this.report = report;
    return this;
  }

  public void unsetReport() {
    this.report = null;
  }

  /** Returns true if field report is set (has been assigned a value) and false otherwise */
  public boolean isSetReport() {
    return this.report != null;
  }

  public void setReportIsSet(boolean value) {
    if (!value) {
      this.report = null;
    }
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

    case REPORT:
      if (value == null) {
        unsetReport();
      } else {
        setReport((NotificationReport)value);
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

    case REPORT:
      return getReport();

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
    case REPORT:
      return isSetReport();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof NotificationReportRequest)
      return this.equals((NotificationReportRequest)that);
    return false;
  }

  public boolean equals(NotificationReportRequest that) {
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

    boolean this_present_report = true && this.isSetReport();
    boolean that_present_report = true && that.isSetReport();
    if (this_present_report || that_present_report) {
      if (!(this_present_report && that_present_report))
        return false;
      if (!this.report.equals(that.report))
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

    hashCode = hashCode * 8191 + ((isSetReport()) ? 131071 : 524287);
    if (isSetReport())
      hashCode = hashCode * 8191 + report.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(NotificationReportRequest other) {
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
    lastComparison = java.lang.Boolean.valueOf(isSetReport()).compareTo(other.isSetReport());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReport()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.report, other.report);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("NotificationReportRequest(");
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
    sb.append("report:");
    if (this.report == null) {
      sb.append("null");
    } else {
      sb.append(this.report);
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
    if (report == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'report' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (report != null) {
      report.validate();
    }
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

  private static class NotificationReportRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NotificationReportRequestStandardScheme getScheme() {
      return new NotificationReportRequestStandardScheme();
    }
  }

  private static class NotificationReportRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<NotificationReportRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NotificationReportRequest struct) throws org.apache.thrift.TException {
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
          case 4: // REPORT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.report = new NotificationReport();
              struct.report.read(iprot);
              struct.setReportIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, NotificationReportRequest struct) throws org.apache.thrift.TException {
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
      if (struct.report != null) {
        oprot.writeFieldBegin(REPORT_FIELD_DESC);
        struct.report.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NotificationReportRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NotificationReportRequestTupleScheme getScheme() {
      return new NotificationReportRequestTupleScheme();
    }
  }

  private static class NotificationReportRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<NotificationReportRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NotificationReportRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI16(struct.systemId);
      oprot.writeI32(struct.time);
      oprot.writeString(struct.signature);
      struct.report.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NotificationReportRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.systemId = iprot.readI16();
      struct.setSystemIdIsSet(true);
      struct.time = iprot.readI32();
      struct.setTimeIsSet(true);
      struct.signature = iprot.readString();
      struct.setSignatureIsSet(true);
      struct.report = new NotificationReport();
      struct.report.read(iprot);
      struct.setReportIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

