/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.iusworks.jaguar.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-10-16")
public class DevicePlatformVoucher implements org.apache.thrift.TBase<DevicePlatformVoucher, DevicePlatformVoucher._Fields>, java.io.Serializable, Cloneable, Comparable<DevicePlatformVoucher> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DevicePlatformVoucher");

  private static final org.apache.thrift.protocol.TField PLATFORM_FIELD_DESC = new org.apache.thrift.protocol.TField("platform", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField VOUCHER_FIELD_DESC = new org.apache.thrift.protocol.TField("voucher", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("state", org.apache.thrift.protocol.TType.I16, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DevicePlatformVoucherStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DevicePlatformVoucherTupleSchemeFactory();

  public java.lang.String platform; // required
  public java.lang.String voucher; // required
  public short state; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PLATFORM((short)1, "platform"),
    VOUCHER((short)2, "voucher"),
    STATE((short)3, "state");

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
        case 1: // PLATFORM
          return PLATFORM;
        case 2: // VOUCHER
          return VOUCHER;
        case 3: // STATE
          return STATE;
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
  private static final int __STATE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PLATFORM, new org.apache.thrift.meta_data.FieldMetaData("platform", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VOUCHER, new org.apache.thrift.meta_data.FieldMetaData("voucher", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STATE, new org.apache.thrift.meta_data.FieldMetaData("state", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DevicePlatformVoucher.class, metaDataMap);
  }

  public DevicePlatformVoucher() {
  }

  public DevicePlatformVoucher(
    java.lang.String platform,
    java.lang.String voucher,
    short state)
  {
    this();
    this.platform = platform;
    this.voucher = voucher;
    this.state = state;
    setStateIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DevicePlatformVoucher(DevicePlatformVoucher other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetPlatform()) {
      this.platform = other.platform;
    }
    if (other.isSetVoucher()) {
      this.voucher = other.voucher;
    }
    this.state = other.state;
  }

  public DevicePlatformVoucher deepCopy() {
    return new DevicePlatformVoucher(this);
  }

  @Override
  public void clear() {
    this.platform = null;
    this.voucher = null;
    setStateIsSet(false);
    this.state = 0;
  }

  public java.lang.String getPlatform() {
    return this.platform;
  }

  public DevicePlatformVoucher setPlatform(java.lang.String platform) {
    this.platform = platform;
    return this;
  }

  public void unsetPlatform() {
    this.platform = null;
  }

  /** Returns true if field platform is set (has been assigned a value) and false otherwise */
  public boolean isSetPlatform() {
    return this.platform != null;
  }

  public void setPlatformIsSet(boolean value) {
    if (!value) {
      this.platform = null;
    }
  }

  public java.lang.String getVoucher() {
    return this.voucher;
  }

  public DevicePlatformVoucher setVoucher(java.lang.String voucher) {
    this.voucher = voucher;
    return this;
  }

  public void unsetVoucher() {
    this.voucher = null;
  }

  /** Returns true if field voucher is set (has been assigned a value) and false otherwise */
  public boolean isSetVoucher() {
    return this.voucher != null;
  }

  public void setVoucherIsSet(boolean value) {
    if (!value) {
      this.voucher = null;
    }
  }

  public short getState() {
    return this.state;
  }

  public DevicePlatformVoucher setState(short state) {
    this.state = state;
    setStateIsSet(true);
    return this;
  }

  public void unsetState() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __STATE_ISSET_ID);
  }

  /** Returns true if field state is set (has been assigned a value) and false otherwise */
  public boolean isSetState() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __STATE_ISSET_ID);
  }

  public void setStateIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __STATE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case PLATFORM:
      if (value == null) {
        unsetPlatform();
      } else {
        setPlatform((java.lang.String)value);
      }
      break;

    case VOUCHER:
      if (value == null) {
        unsetVoucher();
      } else {
        setVoucher((java.lang.String)value);
      }
      break;

    case STATE:
      if (value == null) {
        unsetState();
      } else {
        setState((java.lang.Short)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case PLATFORM:
      return getPlatform();

    case VOUCHER:
      return getVoucher();

    case STATE:
      return getState();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case PLATFORM:
      return isSetPlatform();
    case VOUCHER:
      return isSetVoucher();
    case STATE:
      return isSetState();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof DevicePlatformVoucher)
      return this.equals((DevicePlatformVoucher)that);
    return false;
  }

  public boolean equals(DevicePlatformVoucher that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_platform = true && this.isSetPlatform();
    boolean that_present_platform = true && that.isSetPlatform();
    if (this_present_platform || that_present_platform) {
      if (!(this_present_platform && that_present_platform))
        return false;
      if (!this.platform.equals(that.platform))
        return false;
    }

    boolean this_present_voucher = true && this.isSetVoucher();
    boolean that_present_voucher = true && that.isSetVoucher();
    if (this_present_voucher || that_present_voucher) {
      if (!(this_present_voucher && that_present_voucher))
        return false;
      if (!this.voucher.equals(that.voucher))
        return false;
    }

    boolean this_present_state = true;
    boolean that_present_state = true;
    if (this_present_state || that_present_state) {
      if (!(this_present_state && that_present_state))
        return false;
      if (this.state != that.state)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetPlatform()) ? 131071 : 524287);
    if (isSetPlatform())
      hashCode = hashCode * 8191 + platform.hashCode();

    hashCode = hashCode * 8191 + ((isSetVoucher()) ? 131071 : 524287);
    if (isSetVoucher())
      hashCode = hashCode * 8191 + voucher.hashCode();

    hashCode = hashCode * 8191 + state;

    return hashCode;
  }

  @Override
  public int compareTo(DevicePlatformVoucher other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetPlatform()).compareTo(other.isSetPlatform());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlatform()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.platform, other.platform);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetVoucher()).compareTo(other.isSetVoucher());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVoucher()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.voucher, other.voucher);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetState()).compareTo(other.isSetState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.state, other.state);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("DevicePlatformVoucher(");
    boolean first = true;

    sb.append("platform:");
    if (this.platform == null) {
      sb.append("null");
    } else {
      sb.append(this.platform);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("voucher:");
    if (this.voucher == null) {
      sb.append("null");
    } else {
      sb.append(this.voucher);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("state:");
    sb.append(this.state);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (platform == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'platform' was not present! Struct: " + toString());
    }
    if (voucher == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'voucher' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'state' because it's a primitive and you chose the non-beans generator.
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

  private static class DevicePlatformVoucherStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DevicePlatformVoucherStandardScheme getScheme() {
      return new DevicePlatformVoucherStandardScheme();
    }
  }

  private static class DevicePlatformVoucherStandardScheme extends org.apache.thrift.scheme.StandardScheme<DevicePlatformVoucher> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DevicePlatformVoucher struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PLATFORM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.platform = iprot.readString();
              struct.setPlatformIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VOUCHER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.voucher = iprot.readString();
              struct.setVoucherIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.state = iprot.readI16();
              struct.setStateIsSet(true);
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
      if (!struct.isSetState()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'state' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DevicePlatformVoucher struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.platform != null) {
        oprot.writeFieldBegin(PLATFORM_FIELD_DESC);
        oprot.writeString(struct.platform);
        oprot.writeFieldEnd();
      }
      if (struct.voucher != null) {
        oprot.writeFieldBegin(VOUCHER_FIELD_DESC);
        oprot.writeString(struct.voucher);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(STATE_FIELD_DESC);
      oprot.writeI16(struct.state);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DevicePlatformVoucherTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DevicePlatformVoucherTupleScheme getScheme() {
      return new DevicePlatformVoucherTupleScheme();
    }
  }

  private static class DevicePlatformVoucherTupleScheme extends org.apache.thrift.scheme.TupleScheme<DevicePlatformVoucher> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DevicePlatformVoucher struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.platform);
      oprot.writeString(struct.voucher);
      oprot.writeI16(struct.state);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DevicePlatformVoucher struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.platform = iprot.readString();
      struct.setPlatformIsSet(true);
      struct.voucher = iprot.readString();
      struct.setVoucherIsSet(true);
      struct.state = iprot.readI16();
      struct.setStateIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

