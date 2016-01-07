/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package io.edr.finagle.demo.inout;

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

public class HelloRequest implements TBase<HelloRequest, HelloRequest._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("HelloRequest");

  private static final TField BASE_REQ_FIELD_DESC = new TField("baseReq", TType.STRUCT, (short)1);
  private static final TField NAME_FIELD_DESC = new TField("name", TType.STRING, (short)2);


  public io.edr.finagle.demo.inout.common.BaseRequest baseReq;
  public String name;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    BASE_REQ((short)1, "baseReq"),
    NAME((short)2, "name");
  
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
        case 1: // BASE_REQ
  	return BASE_REQ;
        case 2: // NAME
  	return NAME;
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

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BASE_REQ, new FieldMetaData("baseReq", TFieldRequirementType.DEFAULT,
      new StructMetaData(TType.STRUCT, io.edr.finagle.demo.inout.common.BaseRequest.class)));
    tmpMap.put(_Fields.NAME, new FieldMetaData("name", TFieldRequirementType.DEFAULT,
      new FieldValueMetaData(TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(HelloRequest.class, metaDataMap);
  }


  public HelloRequest() {
  }

  public HelloRequest(
    io.edr.finagle.demo.inout.common.BaseRequest baseReq,
    String name)
  {
    this();
    this.baseReq = baseReq;
    this.name = name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HelloRequest(HelloRequest other) {
    if (other.isSetBaseReq()) {
      this.baseReq = new io.edr.finagle.demo.inout.common.BaseRequest(other.baseReq);
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
  }

  public HelloRequest deepCopy() {
    return new HelloRequest(this);
  }

  @Override
  public void clear() {
    this.baseReq = null;
    this.name = null;
  }

  public io.edr.finagle.demo.inout.common.BaseRequest getBaseReq() {
    return this.baseReq;
  }

  public HelloRequest setBaseReq(io.edr.finagle.demo.inout.common.BaseRequest baseReq) {
    this.baseReq = baseReq;
    
    return this;
  }

  public void unsetBaseReq() {
    this.baseReq = null;
  }

  /** Returns true if field baseReq is set (has been asigned a value) and false otherwise */
  public boolean isSetBaseReq() {
    return this.baseReq != null;
  }

  public void setBaseReqIsSet(boolean value) {
    if (!value) {
      this.baseReq = null;
    }
  }

  public String getName() {
    return this.name;
  }

  public HelloRequest setName(String name) {
    this.name = name;
    
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been asigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BASE_REQ:
      if (value == null) {
        unsetBaseReq();
      } else {
        setBaseReq((io.edr.finagle.demo.inout.common.BaseRequest)value);
      }
      break;
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BASE_REQ:
      return getBaseReq();
    case NAME:
      return getName();
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BASE_REQ:
      return isSetBaseReq();
    case NAME:
      return isSetName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof HelloRequest)
      return this.equals((HelloRequest)that);
    return false;
  }

  public boolean equals(HelloRequest that) {
    if (that == null)
      return false;
    boolean this_present_baseReq = true && this.isSetBaseReq();
    boolean that_present_baseReq = true && that.isSetBaseReq();
    if (this_present_baseReq || that_present_baseReq) {
      if (!(this_present_baseReq && that_present_baseReq))
        return false;
      if (!this.baseReq.equals(that.baseReq))
        return false;
    }
    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    boolean present_baseReq = true && (isSetBaseReq());
    builder.append(present_baseReq);
    if (present_baseReq)
      builder.append(baseReq);
    boolean present_name = true && (isSetName());
    builder.append(present_name);
    if (present_name)
      builder.append(name);
    return builder.toHashCode();
  }

  public int compareTo(HelloRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    HelloRequest typedOther = (HelloRequest)other;

    lastComparison = Boolean.valueOf(isSetBaseReq()).compareTo(typedOther.isSetBaseReq());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBaseReq()) {
      lastComparison = TBaseHelper.compareTo(this.baseReq, typedOther.baseReq);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = TBaseHelper.compareTo(this.name, typedOther.name);
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
        case 1: // BASE_REQ
          if (field.type == TType.STRUCT) {
            this.baseReq = new io.edr.finagle.demo.inout.common.BaseRequest();
            this.baseReq.read(iprot);
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // NAME
          if (field.type == TType.STRING) {
            this.name = iprot.readString();
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
    if (this.baseReq != null) {
      oprot.writeFieldBegin(BASE_REQ_FIELD_DESC);
      this.baseReq.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.name != null) {
      oprot.writeFieldBegin(NAME_FIELD_DESC);
      oprot.writeString(this.name);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("HelloRequest(");
    boolean first = true;
    sb.append("baseReq:");
    if (this.baseReq == null) {
      sb.append("null");
    } else {
      sb.append(this.baseReq);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }
}
