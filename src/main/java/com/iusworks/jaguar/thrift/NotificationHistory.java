/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.thrift.NotificationHistory
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
public class NotificationHistory implements org.apache.thrift.TBase<NotificationHistory, NotificationHistory._Fields>, java.io.Serializable, Cloneable, Comparable<NotificationHistory> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NotificationHistory");

  private static final org.apache.thrift.protocol.TField MID_FIELD_DESC = new org.apache.thrift.protocol.TField("mid", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField UID_FIELD_DESC = new org.apache.thrift.protocol.TField("uid", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DATETIME_FIELD_DESC = new org.apache.thrift.protocol.TField("datetime", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField ACTION_FIELD_DESC = new org.apache.thrift.protocol.TField("action", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField TITLE_FIELD_DESC = new org.apache.thrift.protocol.TField("title", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField ALERT_FIELD_DESC = new org.apache.thrift.protocol.TField("alert", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField STORAGED_FIELD_DESC = new org.apache.thrift.protocol.TField("storaged", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new NotificationHistoryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new NotificationHistoryTupleSchemeFactory();

  public String mid; // required
  public String uid; // required
  public int datetime; // required
  public String action; // required
  public String title; // required
  public String alert; // required
  public String storaged; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MID((short)1, "mid"),
    UID((short)2, "uid"),
    DATETIME((short)3, "datetime"),
    ACTION((short)4, "action"),
    TITLE((short)5, "title"),
    ALERT((short)6, "alert"),
    STORAGED((short)7, "storaged");

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
        case 1: // MID
          return MID;
        case 2: // UID
          return UID;
        case 3: // DATETIME
          return DATETIME;
        case 4: // ACTION
          return ACTION;
        case 5: // TITLE
          return TITLE;
        case 6: // ALERT
          return ALERT;
        case 7: // STORAGED
          return STORAGED;
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
  private static final int __DATETIME_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.STORAGED};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MID, new org.apache.thrift.meta_data.FieldMetaData("mid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UID, new org.apache.thrift.meta_data.FieldMetaData("uid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATETIME, new org.apache.thrift.meta_data.FieldMetaData("datetime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ACTION, new org.apache.thrift.meta_data.FieldMetaData("action", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TITLE, new org.apache.thrift.meta_data.FieldMetaData("title", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ALERT, new org.apache.thrift.meta_data.FieldMetaData("alert", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STORAGED, new org.apache.thrift.meta_data.FieldMetaData("storaged", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NotificationHistory.class, metaDataMap);
  }

  public NotificationHistory() {
  }

  public NotificationHistory(
    String mid,
    String uid,
    int datetime,
    String action,
    String title,
    String alert)
  {
    this();
    this.mid = mid;
    this.uid = uid;
    this.datetime = datetime;
    setDatetimeIsSet(true);
    this.action = action;
    this.title = title;
    this.alert = alert;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NotificationHistory(NotificationHistory other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetMid()) {
      this.mid = other.mid;
    }
    if (other.isSetUid()) {
      this.uid = other.uid;
    }
    this.datetime = other.datetime;
    if (other.isSetAction()) {
      this.action = other.action;
    }
    if (other.isSetTitle()) {
      this.title = other.title;
    }
    if (other.isSetAlert()) {
      this.alert = other.alert;
    }
    if (other.isSetStoraged()) {
      this.storaged = other.storaged;
    }
  }

  public NotificationHistory deepCopy() {
    return new NotificationHistory(this);
  }

  @Override
  public void clear() {
    this.mid = null;
    this.uid = null;
    setDatetimeIsSet(false);
    this.datetime = 0;
    this.action = null;
    this.title = null;
    this.alert = null;
    this.storaged = null;
  }

  public String getMid() {
    return this.mid;
  }

  public NotificationHistory setMid(String mid) {
    this.mid = mid;
    return this;
  }

  public void unsetMid() {
    this.mid = null;
  }

  /** Returns true if field mid is set (has been assigned a value) and false otherwise */
  public boolean isSetMid() {
    return this.mid != null;
  }

  public void setMidIsSet(boolean value) {
    if (!value) {
      this.mid = null;
    }
  }

  public String getUid() {
    return this.uid;
  }

  public NotificationHistory setUid(String uid) {
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

  public int getDatetime() {
    return this.datetime;
  }

  public NotificationHistory setDatetime(int datetime) {
    this.datetime = datetime;
    setDatetimeIsSet(true);
    return this;
  }

  public void unsetDatetime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DATETIME_ISSET_ID);
  }

  /** Returns true if field datetime is set (has been assigned a value) and false otherwise */
  public boolean isSetDatetime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DATETIME_ISSET_ID);
  }

  public void setDatetimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DATETIME_ISSET_ID, value);
  }

  public String getAction() {
    return this.action;
  }

  public NotificationHistory setAction(String action) {
    this.action = action;
    return this;
  }

  public void unsetAction() {
    this.action = null;
  }

  /** Returns true if field action is set (has been assigned a value) and false otherwise */
  public boolean isSetAction() {
    return this.action != null;
  }

  public void setActionIsSet(boolean value) {
    if (!value) {
      this.action = null;
    }
  }

  public String getTitle() {
    return this.title;
  }

  public NotificationHistory setTitle(String title) {
    this.title = title;
    return this;
  }

  public void unsetTitle() {
    this.title = null;
  }

  /** Returns true if field title is set (has been assigned a value) and false otherwise */
  public boolean isSetTitle() {
    return this.title != null;
  }

  public void setTitleIsSet(boolean value) {
    if (!value) {
      this.title = null;
    }
  }

  public String getAlert() {
    return this.alert;
  }

  public NotificationHistory setAlert(String alert) {
    this.alert = alert;
    return this;
  }

  public void unsetAlert() {
    this.alert = null;
  }

  /** Returns true if field alert is set (has been assigned a value) and false otherwise */
  public boolean isSetAlert() {
    return this.alert != null;
  }

  public void setAlertIsSet(boolean value) {
    if (!value) {
      this.alert = null;
    }
  }

  public String getStoraged() {
    return this.storaged;
  }

  public NotificationHistory setStoraged(String storaged) {
    this.storaged = storaged;
    return this;
  }

  public void unsetStoraged() {
    this.storaged = null;
  }

  /** Returns true if field storaged is set (has been assigned a value) and false otherwise */
  public boolean isSetStoraged() {
    return this.storaged != null;
  }

  public void setStoragedIsSet(boolean value) {
    if (!value) {
      this.storaged = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MID:
      if (value == null) {
        unsetMid();
      } else {
        setMid((String)value);
      }
      break;

    case UID:
      if (value == null) {
        unsetUid();
      } else {
        setUid((String)value);
      }
      break;

    case DATETIME:
      if (value == null) {
        unsetDatetime();
      } else {
        setDatetime((Integer)value);
      }
      break;

    case ACTION:
      if (value == null) {
        unsetAction();
      } else {
        setAction((String)value);
      }
      break;

    case TITLE:
      if (value == null) {
        unsetTitle();
      } else {
        setTitle((String)value);
      }
      break;

    case ALERT:
      if (value == null) {
        unsetAlert();
      } else {
        setAlert((String)value);
      }
      break;

    case STORAGED:
      if (value == null) {
        unsetStoraged();
      } else {
        setStoraged((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MID:
      return getMid();

    case UID:
      return getUid();

    case DATETIME:
      return getDatetime();

    case ACTION:
      return getAction();

    case TITLE:
      return getTitle();

    case ALERT:
      return getAlert();

    case STORAGED:
      return getStoraged();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MID:
      return isSetMid();
    case UID:
      return isSetUid();
    case DATETIME:
      return isSetDatetime();
    case ACTION:
      return isSetAction();
    case TITLE:
      return isSetTitle();
    case ALERT:
      return isSetAlert();
    case STORAGED:
      return isSetStoraged();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof NotificationHistory)
      return this.equals((NotificationHistory)that);
    return false;
  }

  public boolean equals(NotificationHistory that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_mid = true && this.isSetMid();
    boolean that_present_mid = true && that.isSetMid();
    if (this_present_mid || that_present_mid) {
      if (!(this_present_mid && that_present_mid))
        return false;
      if (!this.mid.equals(that.mid))
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

    boolean this_present_datetime = true;
    boolean that_present_datetime = true;
    if (this_present_datetime || that_present_datetime) {
      if (!(this_present_datetime && that_present_datetime))
        return false;
      if (this.datetime != that.datetime)
        return false;
    }

    boolean this_present_action = true && this.isSetAction();
    boolean that_present_action = true && that.isSetAction();
    if (this_present_action || that_present_action) {
      if (!(this_present_action && that_present_action))
        return false;
      if (!this.action.equals(that.action))
        return false;
    }

    boolean this_present_title = true && this.isSetTitle();
    boolean that_present_title = true && that.isSetTitle();
    if (this_present_title || that_present_title) {
      if (!(this_present_title && that_present_title))
        return false;
      if (!this.title.equals(that.title))
        return false;
    }

    boolean this_present_alert = true && this.isSetAlert();
    boolean that_present_alert = true && that.isSetAlert();
    if (this_present_alert || that_present_alert) {
      if (!(this_present_alert && that_present_alert))
        return false;
      if (!this.alert.equals(that.alert))
        return false;
    }

    boolean this_present_storaged = true && this.isSetStoraged();
    boolean that_present_storaged = true && that.isSetStoraged();
    if (this_present_storaged || that_present_storaged) {
      if (!(this_present_storaged && that_present_storaged))
        return false;
      if (!this.storaged.equals(that.storaged))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetMid()) ? 131071 : 524287);
    if (isSetMid())
      hashCode = hashCode * 8191 + mid.hashCode();

    hashCode = hashCode * 8191 + ((isSetUid()) ? 131071 : 524287);
    if (isSetUid())
      hashCode = hashCode * 8191 + uid.hashCode();

    hashCode = hashCode * 8191 + datetime;

    hashCode = hashCode * 8191 + ((isSetAction()) ? 131071 : 524287);
    if (isSetAction())
      hashCode = hashCode * 8191 + action.hashCode();

    hashCode = hashCode * 8191 + ((isSetTitle()) ? 131071 : 524287);
    if (isSetTitle())
      hashCode = hashCode * 8191 + title.hashCode();

    hashCode = hashCode * 8191 + ((isSetAlert()) ? 131071 : 524287);
    if (isSetAlert())
      hashCode = hashCode * 8191 + alert.hashCode();

    hashCode = hashCode * 8191 + ((isSetStoraged()) ? 131071 : 524287);
    if (isSetStoraged())
      hashCode = hashCode * 8191 + storaged.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(NotificationHistory other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMid()).compareTo(other.isSetMid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mid, other.mid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUid()).compareTo(other.isSetUid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uid, other.uid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDatetime()).compareTo(other.isSetDatetime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDatetime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.datetime, other.datetime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAction()).compareTo(other.isSetAction());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAction()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.action, other.action);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTitle()).compareTo(other.isSetTitle());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTitle()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.title, other.title);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAlert()).compareTo(other.isSetAlert());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAlert()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.alert, other.alert);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStoraged()).compareTo(other.isSetStoraged());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStoraged()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.storaged, other.storaged);
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
    StringBuilder sb = new StringBuilder("NotificationHistory(");
    boolean first = true;

    sb.append("mid:");
    if (this.mid == null) {
      sb.append("null");
    } else {
      sb.append(this.mid);
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
    sb.append("datetime:");
    sb.append(this.datetime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("action:");
    if (this.action == null) {
      sb.append("null");
    } else {
      sb.append(this.action);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("title:");
    if (this.title == null) {
      sb.append("null");
    } else {
      sb.append(this.title);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("alert:");
    if (this.alert == null) {
      sb.append("null");
    } else {
      sb.append(this.alert);
    }
    first = false;
    if (isSetStoraged()) {
      if (!first) sb.append(", ");
      sb.append("storaged:");
      if (this.storaged == null) {
        sb.append("null");
      } else {
        sb.append(this.storaged);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (mid == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'mid' was not present! Struct: " + toString());
    }
    if (uid == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uid' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'datetime' because it's a primitive and you chose the non-beans generator.
    if (action == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'action' was not present! Struct: " + toString());
    }
    if (title == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'title' was not present! Struct: " + toString());
    }
    if (alert == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'alert' was not present! Struct: " + toString());
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NotificationHistoryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NotificationHistoryStandardScheme getScheme() {
      return new NotificationHistoryStandardScheme();
    }
  }

  private static class NotificationHistoryStandardScheme extends org.apache.thrift.scheme.StandardScheme<NotificationHistory> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NotificationHistory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.mid = iprot.readString();
              struct.setMidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // UID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uid = iprot.readString();
              struct.setUidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATETIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.datetime = iprot.readI32();
              struct.setDatetimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ACTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.action = iprot.readString();
              struct.setActionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // TITLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.title = iprot.readString();
              struct.setTitleIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ALERT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.alert = iprot.readString();
              struct.setAlertIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // STORAGED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.storaged = iprot.readString();
              struct.setStoragedIsSet(true);
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
      if (!struct.isSetDatetime()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'datetime' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, NotificationHistory struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.mid != null) {
        oprot.writeFieldBegin(MID_FIELD_DESC);
        oprot.writeString(struct.mid);
        oprot.writeFieldEnd();
      }
      if (struct.uid != null) {
        oprot.writeFieldBegin(UID_FIELD_DESC);
        oprot.writeString(struct.uid);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(DATETIME_FIELD_DESC);
      oprot.writeI32(struct.datetime);
      oprot.writeFieldEnd();
      if (struct.action != null) {
        oprot.writeFieldBegin(ACTION_FIELD_DESC);
        oprot.writeString(struct.action);
        oprot.writeFieldEnd();
      }
      if (struct.title != null) {
        oprot.writeFieldBegin(TITLE_FIELD_DESC);
        oprot.writeString(struct.title);
        oprot.writeFieldEnd();
      }
      if (struct.alert != null) {
        oprot.writeFieldBegin(ALERT_FIELD_DESC);
        oprot.writeString(struct.alert);
        oprot.writeFieldEnd();
      }
      if (struct.storaged != null) {
        if (struct.isSetStoraged()) {
          oprot.writeFieldBegin(STORAGED_FIELD_DESC);
          oprot.writeString(struct.storaged);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NotificationHistoryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NotificationHistoryTupleScheme getScheme() {
      return new NotificationHistoryTupleScheme();
    }
  }

  private static class NotificationHistoryTupleScheme extends org.apache.thrift.scheme.TupleScheme<NotificationHistory> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NotificationHistory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.mid);
      oprot.writeString(struct.uid);
      oprot.writeI32(struct.datetime);
      oprot.writeString(struct.action);
      oprot.writeString(struct.title);
      oprot.writeString(struct.alert);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetStoraged()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetStoraged()) {
        oprot.writeString(struct.storaged);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NotificationHistory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.mid = iprot.readString();
      struct.setMidIsSet(true);
      struct.uid = iprot.readString();
      struct.setUidIsSet(true);
      struct.datetime = iprot.readI32();
      struct.setDatetimeIsSet(true);
      struct.action = iprot.readString();
      struct.setActionIsSet(true);
      struct.title = iprot.readString();
      struct.setTitleIsSet(true);
      struct.alert = iprot.readString();
      struct.setAlertIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.storaged = iprot.readString();
        struct.setStoragedIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

