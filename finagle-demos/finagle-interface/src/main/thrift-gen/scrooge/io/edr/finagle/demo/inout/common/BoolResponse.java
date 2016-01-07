/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package io.edr.finagle.demo.inout.common;

import org.apache.commons.lang.builder.HashCodeBuilder;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

// No additional import required for struct/union.

public class BoolResponse implements TBase<BoolResponse, BoolResponse._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("BoolResponse");

  private static final TField BASE_RESP_FIELD_DESC = new TField("baseResp", TType.STRUCT, (short)1);
  private static final TField VALUE_FIELD_DESC = new TField("value", TType.BOOL, (short)2);


  public BaseResponse baseResp;
  public boolean value;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    BASE_RESP((short)1, "baseResp"),
    VALUE((short)2, "value");
  
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
        case 1: // BASE_RESP
  	return BASE_RESP;
        case 2: // VALUE
  	return VALUE;
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
  private static final int __VALUE_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BASE_RESP, new FieldMetaData("baseResp", TFieldRequirementType.DEFAULT,
      new StructMetaData(TType.STRUCT, BaseResponse.class)));
    tmpMap.put(_Fields.VALUE, new FieldMetaData("value", TFieldRequirementType.DEFAULT,
      new FieldValueMetaData(TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(BoolResponse.class, metaDataMap);
  }


  public BoolResponse() {
  }

  public BoolResponse(
    BaseResponse baseResp,
    boolean value)
  {
    this();
    this.baseResp = baseResp;
    this.value = value;
    setValueIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BoolResponse(BoolResponse other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetBaseResp()) {
      this.baseResp = new BaseResponse(other.baseResp);
    }
    this.value = other.value;
  }

  public BoolResponse deepCopy() {
    return new BoolResponse(this);
  }

  @Override
  public void clear() {
    this.baseResp = null;
    setValueIsSet(false);
    this.value = false;
  }

  public BaseResponse getBaseResp() {
    return this.baseResp;
  }

  public BoolResponse setBaseResp(BaseResponse baseResp) {
    this.baseResp = baseResp;
    
    return this;
  }

  public void unsetBaseResp() {
    this.baseResp = null;
  }

  /** Returns true if field baseResp is set (has been asigned a value) and false otherwise */
  public boolean isSetBaseResp() {
    return this.baseResp != null;
  }

  public void setBaseRespIsSet(boolean value) {
    if (!value) {
      this.baseResp = null;
    }
  }

  public boolean isValue() {
    return this.value;
  }

  public BoolResponse setValue(boolean value) {
    this.value = value;
    setValueIsSet(true);

    return this;
  }

  public void unsetValue() {
  __isset_bit_vector.clear(__VALUE_ISSET_ID);
  }

  /** Returns true if field value is set (has been asigned a value) and false otherwise */
  public boolean isSetValue() {
    return __isset_bit_vector.get(__VALUE_ISSET_ID);
  }

  public void setValueIsSet(boolean value) {
    __isset_bit_vector.set(__VALUE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BASE_RESP:
      if (value == null) {
        unsetBaseResp();
      } else {
        setBaseResp((BaseResponse)value);
      }
      break;
    case VALUE:
      if (value == null) {
        unsetValue();
      } else {
        setValue((Boolean)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BASE_RESP:
      return getBaseResp();
    case VALUE:
      return new Boolean(isValue());
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BASE_RESP:
      return isSetBaseResp();
    case VALUE:
      return isSetValue();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BoolResponse)
      return this.equals((BoolResponse)that);
    return false;
  }

  public boolean equals(BoolResponse that) {
    if (that == null)
      return false;
    boolean this_present_baseResp = true && this.isSetBaseResp();
    boolean that_present_baseResp = true && that.isSetBaseResp();
    if (this_present_baseResp || that_present_baseResp) {
      if (!(this_present_baseResp && that_present_baseResp))
        return false;
      if (!this.baseResp.equals(that.baseResp))
        return false;
    }
    boolean this_present_value = true;
    boolean that_present_value = true;
    if (this_present_value || that_present_value) {
      if (!(this_present_value && that_present_value))
        return false;
      if (this.value != that.value)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    boolean present_baseResp = true && (isSetBaseResp());
    builder.append(present_baseResp);
    if (present_baseResp)
      builder.append(baseResp);
    boolean present_value = true;
    builder.append(present_value);
    if (present_value)
      builder.append(value);
    return builder.toHashCode();
  }

  public int compareTo(BoolResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    BoolResponse typedOther = (BoolResponse)other;

    lastComparison = Boolean.valueOf(isSetBaseResp()).compareTo(typedOther.isSetBaseResp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBaseResp()) {
      lastComparison = TBaseHelper.compareTo(this.baseResp, typedOther.baseResp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValue()).compareTo(typedOther.isSetValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValue()) {
      lastComparison = TBaseHelper.compareTo(this.value, typedOther.value);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) {
        break;
      }
      switch (field.id) {
        case 1: // BASE_RESP
          if (field.type == TType.STRUCT) {
            this.baseResp = new BaseResponse();
            this.baseResp.read(iprot);
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // VALUE
          if (field.type == TType.BOOL) {
            this.value = iprot.readBool();
            setValueIsSet(true);
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();
    
    oprot.writeStructBegin(STRUCT_DESC);
    if (this.baseResp != null) {
      oprot.writeFieldBegin(BASE_RESP_FIELD_DESC);
      this.baseResp.write(oprot);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(VALUE_FIELD_DESC);
    oprot.writeBool(this.value);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BoolResponse(");
    boolean first = true;
    sb.append("baseResp:");
    if (this.baseResp == null) {
      sb.append("null");
    } else {
      sb.append(this.baseResp);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("value:");
    sb.append(this.value);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }
}

