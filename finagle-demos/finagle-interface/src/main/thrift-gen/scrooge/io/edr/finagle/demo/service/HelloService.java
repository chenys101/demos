/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package io.edr.finagle.demo.service;

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

import com.twitter.util.Future;
import com.twitter.util.Function;
import com.twitter.util.Function2;
import com.twitter.util.Try;
import com.twitter.util.Return;
import com.twitter.util.Throw;
import com.twitter.finagle.thrift.ThriftClientRequest;

public class HelloService {
  public interface Iface {
    public io.edr.finagle.demo.inout.HelloResponse hello(io.edr.finagle.demo.inout.HelloRequest req) throws TException;
  }

  public interface AsyncIface {
    public void hello(io.edr.finagle.demo.inout.HelloRequest req, AsyncMethodCallback<AsyncClient.hello_call> resultHandler) throws TException;
  }

  public interface ServiceIface {
    public Future<io.edr.finagle.demo.inout.HelloResponse> hello(io.edr.finagle.demo.inout.HelloRequest req);
  }

  public static class Client implements TServiceClient, Iface {
    public static class Factory implements TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(TProtocol iprot, TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(TProtocol prot)
    {
      this(prot, prot);
    }

    public Client(TProtocol iprot, TProtocol oprot)
    {
      iprot_ = iprot;
      oprot_ = oprot;
    }

    protected TProtocol iprot_;
    protected TProtocol oprot_;

    protected int seqid_;

    public TProtocol getInputProtocol()
    {
      return this.iprot_;
    }

    public TProtocol getOutputProtocol()
    {
      return this.oprot_;
    }

    public io.edr.finagle.demo.inout.HelloResponse hello(io.edr.finagle.demo.inout.HelloRequest req) throws TException
    {
      send_hello(req);
      return recv_hello();
    }

    public void send_hello(io.edr.finagle.demo.inout.HelloRequest req) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("hello", TMessageType.CALL, ++seqid_));
      hello_args __args__ = new hello_args();
      __args__.setReq(req);
      __args__.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public io.edr.finagle.demo.inout.HelloResponse recv_hello() throws TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      if (msg.seqid != seqid_) {
        throw new TApplicationException(TApplicationException.BAD_SEQUENCE_ID, "hello failed: out of sequence response");
      }
      hello_result result = new hello_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "hello failed: unknown result");
    }
  }

  public static class AsyncClient extends TAsyncClient implements AsyncIface {
    public static class Factory implements TAsyncClientFactory<AsyncClient> {
      private final TAsyncClientManager clientManager;
      private final TProtocolFactory protocolFactory;
      public Factory(TAsyncClientManager clientManager, TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager clientManager, TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void hello(io.edr.finagle.demo.inout.HelloRequest req, AsyncMethodCallback<hello_call> __resultHandler__) throws TException {
      checkReady();
      hello_call __method_call__ = new hello_call(req, __resultHandler__, this, super.protocolFactory, super.transport);
      manager.call(__method_call__);
    }

    public static class hello_call extends TAsyncMethodCall {
      private io.edr.finagle.demo.inout.HelloRequest req;

      public hello_call(io.edr.finagle.demo.inout.HelloRequest req, AsyncMethodCallback<hello_call> __resultHandler__, TAsyncClient __client__, TProtocolFactory __protocolFactory__, TNonblockingTransport __transport__) throws TException {
        super(__client__, __protocolFactory__, __transport__, __resultHandler__, false);
        this.req = req;
      }

      public void write_args(TProtocol __prot__) throws TException {
        __prot__.writeMessageBegin(new TMessage("hello", TMessageType.CALL, 0));
        hello_args __args__ = new hello_args();
        __args__.setReq(req);
        __args__.write(__prot__);
        __prot__.writeMessageEnd();
      }

      public io.edr.finagle.demo.inout.HelloResponse getResult() throws TException {
        if (getState() != State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        TMemoryInputTransport __memoryTransport__ = new TMemoryInputTransport(getFrameBuffer().array());
        TProtocol __prot__ = super.client.getProtocolFactory().getProtocol(__memoryTransport__);
        return (new Client(__prot__)).recv_hello();
      }
     }
   }


  public static class ServiceToClient implements ServiceIface {
    private final com.twitter.finagle.Service<ThriftClientRequest, byte[]> service;
    private final TProtocolFactory protocolFactory;
    public ServiceToClient(com.twitter.finagle.Service<ThriftClientRequest, byte[]> service, TProtocolFactory protocolFactory) {
      
      this.service = service;
      this.protocolFactory = protocolFactory;
    }

    public Future<io.edr.finagle.demo.inout.HelloResponse> hello(io.edr.finagle.demo.inout.HelloRequest req) {
      try {
        // TODO: size
        TMemoryBuffer __memoryTransport__ = new TMemoryBuffer(512);
        TProtocol __prot__ = this.protocolFactory.getProtocol(__memoryTransport__);
        __prot__.writeMessageBegin(new TMessage("hello", TMessageType.CALL, 0));
        hello_args __args__ = new hello_args();
        __args__.setReq(req);
        __args__.write(__prot__);
        __prot__.writeMessageEnd();


        byte[] __buffer__ = Arrays.copyOfRange(__memoryTransport__.getArray(), 0, __memoryTransport__.length());
        ThriftClientRequest __request__ = new ThriftClientRequest(__buffer__, false);
        Future<byte[]> __done__ = this.service.apply(__request__);
        return __done__.flatMap(new Function<byte[], Future<io.edr.finagle.demo.inout.HelloResponse>>() {
          public Future<io.edr.finagle.demo.inout.HelloResponse> apply(byte[] __buffer__) {
            TMemoryInputTransport __memoryTransport__ = new TMemoryInputTransport(__buffer__);
            TProtocol __prot__ = ServiceToClient.this.protocolFactory.getProtocol(__memoryTransport__);
            try {
              return Future.value((new Client(__prot__)).recv_hello());
            } catch (Exception e) {
              return Future.exception(e);
            }
          }
        });
      } catch (TException e) {
        return Future.exception(e);
      }
    }
  }

  public static class Processor implements TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(Iface iface)
    {
      iface_ = iface;
      processMap_.put("hello", new hello());
    }

    protected static interface ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException;
    }

    private Iface iface_;
    protected final HashMap<String,ProcessFunction> processMap_ = new HashMap<String,ProcessFunction>();

    public boolean process(TProtocol iprot, TProtocol oprot) throws TException
    {
      TMessage msg = iprot.readMessageBegin();
      ProcessFunction fn = processMap_.get(msg.name);
      if (fn == null) {
        TProtocolUtil.skip(iprot, TType.STRUCT);
        iprot.readMessageEnd();
        TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
        oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
        x.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
        return true;
      }
      fn.process(msg.seqid, iprot, oprot);
      return true;
    }

    private class hello implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        hello_args args = new hello_args();
        try {
          args.read(iprot);
        } catch (TProtocolException e) {
          iprot.readMessageEnd();
          TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
          oprot.writeMessageBegin(new TMessage("hello", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        iprot.readMessageEnd();
        hello_result result = new hello_result();
        result.success = iface_.hello(args.req);
        
        oprot.writeMessageBegin(new TMessage("hello", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }
    }
  }

  public static class Service extends com.twitter.finagle.Service<byte[], byte[]> {
    private final ServiceIface iface;
    private final TProtocolFactory protocolFactory;
    protected HashMap<String, Function2<TProtocol, Integer, Future<byte[]>>> functionMap = new HashMap<String, Function2<TProtocol, Integer, Future<byte[]>>>();
    public Service(final ServiceIface iface, final TProtocolFactory protocolFactory) {
      this.iface = iface;
      this.protocolFactory = protocolFactory;
      functionMap.put("hello", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(final TProtocol iprot, final Integer seqid) {
          hello_args args = new hello_args();
          try {
            args.read(iprot);
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
              TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
              TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);

              oprot.writeMessageBegin(new TMessage("hello", TMessageType.EXCEPTION, seqid));
              x.write(oprot);
              oprot.writeMessageEnd();
              oprot.getTransport().flush();
              byte[] buffer = Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length());
              return Future.value(buffer);
            } catch (Exception e1) {
              return Future.exception(e1);
            }
          } catch (Exception e) {
            return Future.exception(e);
          }

          try {
            iprot.readMessageEnd();
          } catch (Exception e) {
            return Future.exception(e);
          }
          Future<io.edr.finagle.demo.inout.HelloResponse> future;
          try {
            future = iface.hello(args.req);
          } catch (Exception e) {
            future = Future.exception(e);
          }

          try {
            return future.flatMap(new Function<io.edr.finagle.demo.inout.HelloResponse, Future<byte[]>>() {
              public Future<byte[]> apply(io.edr.finagle.demo.inout.HelloResponse value) {
                hello_result result = new hello_result();
                result.success = value;
                result.setSuccessIsSet(true);

                try {
                  TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
                  TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);

                  oprot.writeMessageBegin(new TMessage("hello", TMessageType.REPLY, seqid));
                  result.write(oprot);
                  oprot.writeMessageEnd();

                  return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
                } catch (Exception e) {
                  return Future.exception(e);
                }
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                return Future.exception(t);
              }
            });
          } catch (Exception e) {
            return Future.exception(e);
          }
        }
      });
    }

    public Future<byte[]> apply(byte[] request) {
      TTransport inputTransport = new TMemoryInputTransport(request);
      TProtocol iprot = protocolFactory.getProtocol(inputTransport);

      TMessage msg;
      try {
        msg = iprot.readMessageBegin();
      } catch (Exception e) {
        return Future.exception(e);
      }

      Function2<TProtocol, Integer, Future<byte[]>> fn = functionMap.get(msg.name);
      if (fn == null) {
        try {
          TProtocolUtil.skip(iprot, TType.STRUCT);
          iprot.readMessageEnd();
          TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
          TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
          TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
          oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
        } catch (Exception e) {
          return Future.exception(e);
        }
      }

      return fn.apply(iprot, msg.seqid);
    }
  }

  public static class hello_args implements TBase<hello_args, hello_args._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("hello_args");

  private static final TField REQ_FIELD_DESC = new TField("req", TType.STRUCT, (short)1);


  public io.edr.finagle.demo.inout.HelloRequest req;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    REQ((short)1, "req");
  
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
        case 1: // REQ
  	return REQ;
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
    tmpMap.put(_Fields.REQ, new FieldMetaData("req", TFieldRequirementType.DEFAULT,
      new StructMetaData(TType.STRUCT, io.edr.finagle.demo.inout.HelloRequest.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(hello_args.class, metaDataMap);
  }


  public hello_args() {
  }

  public hello_args(
    io.edr.finagle.demo.inout.HelloRequest req)
  {
    this();
    this.req = req;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public hello_args(hello_args other) {
    if (other.isSetReq()) {
      this.req = new io.edr.finagle.demo.inout.HelloRequest(other.req);
    }
  }

  public hello_args deepCopy() {
    return new hello_args(this);
  }

  @Override
  public void clear() {
    this.req = null;
  }

  public io.edr.finagle.demo.inout.HelloRequest getReq() {
    return this.req;
  }

  public hello_args setReq(io.edr.finagle.demo.inout.HelloRequest req) {
    this.req = req;
    
    return this;
  }

  public void unsetReq() {
    this.req = null;
  }

  /** Returns true if field req is set (has been asigned a value) and false otherwise */
  public boolean isSetReq() {
    return this.req != null;
  }

  public void setReqIsSet(boolean value) {
    if (!value) {
      this.req = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case REQ:
      if (value == null) {
        unsetReq();
      } else {
        setReq((io.edr.finagle.demo.inout.HelloRequest)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case REQ:
      return getReq();
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case REQ:
      return isSetReq();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof hello_args)
      return this.equals((hello_args)that);
    return false;
  }

  public boolean equals(hello_args that) {
    if (that == null)
      return false;
    boolean this_present_req = true && this.isSetReq();
    boolean that_present_req = true && that.isSetReq();
    if (this_present_req || that_present_req) {
      if (!(this_present_req && that_present_req))
        return false;
      if (!this.req.equals(that.req))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    boolean present_req = true && (isSetReq());
    builder.append(present_req);
    if (present_req)
      builder.append(req);
    return builder.toHashCode();
  }

  public int compareTo(hello_args other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    hello_args typedOther = (hello_args)other;

    lastComparison = Boolean.valueOf(isSetReq()).compareTo(typedOther.isSetReq());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReq()) {
      lastComparison = TBaseHelper.compareTo(this.req, typedOther.req);
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
        case 1: // REQ
          if (field.type == TType.STRUCT) {
            this.req = new io.edr.finagle.demo.inout.HelloRequest();
            this.req.read(iprot);
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
    if (this.req != null) {
      oprot.writeFieldBegin(REQ_FIELD_DESC);
      this.req.write(oprot);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("hello_args(");
    boolean first = true;
    sb.append("req:");
    if (this.req == null) {
      sb.append("null");
    } else {
      sb.append(this.req);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }
}


  public static class hello_result implements TBase<hello_result, hello_result._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("hello_result");

  private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.STRUCT, (short)0);


  public io.edr.finagle.demo.inout.HelloResponse success;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    SUCCESS((short)0, "success");
  
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
        case 0: // SUCCESS
  	return SUCCESS;
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
    tmpMap.put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT,
      new StructMetaData(TType.STRUCT, io.edr.finagle.demo.inout.HelloResponse.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(hello_result.class, metaDataMap);
  }


  public hello_result() {
  }

  public hello_result(
    io.edr.finagle.demo.inout.HelloResponse success)
  {
    this();
    this.success = success;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public hello_result(hello_result other) {
    if (other.isSetSuccess()) {
      this.success = new io.edr.finagle.demo.inout.HelloResponse(other.success);
    }
  }

  public hello_result deepCopy() {
    return new hello_result(this);
  }

  @Override
  public void clear() {
    this.success = null;
  }

  public io.edr.finagle.demo.inout.HelloResponse getSuccess() {
    return this.success;
  }

  public hello_result setSuccess(io.edr.finagle.demo.inout.HelloResponse success) {
    this.success = success;
    
    return this;
  }

  public void unsetSuccess() {
    this.success = null;
  }

  /** Returns true if field success is set (has been asigned a value) and false otherwise */
  public boolean isSetSuccess() {
    return this.success != null;
  }

  public void setSuccessIsSet(boolean value) {
    if (!value) {
      this.success = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SUCCESS:
      if (value == null) {
        unsetSuccess();
      } else {
        setSuccess((io.edr.finagle.demo.inout.HelloResponse)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SUCCESS:
      return getSuccess();
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SUCCESS:
      return isSetSuccess();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof hello_result)
      return this.equals((hello_result)that);
    return false;
  }

  public boolean equals(hello_result that) {
    if (that == null)
      return false;
    boolean this_present_success = true && this.isSetSuccess();
    boolean that_present_success = true && that.isSetSuccess();
    if (this_present_success || that_present_success) {
      if (!(this_present_success && that_present_success))
        return false;
      if (!this.success.equals(that.success))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    boolean present_success = true && (isSetSuccess());
    builder.append(present_success);
    if (present_success)
      builder.append(success);
    return builder.toHashCode();
  }

  public int compareTo(hello_result other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    hello_result typedOther = (hello_result)other;

    lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSuccess()) {
      lastComparison = TBaseHelper.compareTo(this.success, typedOther.success);
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
        case 0: // SUCCESS
          if (field.type == TType.STRUCT) {
            this.success = new io.edr.finagle.demo.inout.HelloResponse();
            this.success.read(iprot);
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
    oprot.writeStructBegin(STRUCT_DESC);
    if (this.isSetSuccess()) {
      oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
      this.success.write(oprot);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("hello_result(");
    boolean first = true;
    sb.append("success:");
    if (this.success == null) {
      sb.append("null");
    } else {
      sb.append(this.success);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }
}



}
