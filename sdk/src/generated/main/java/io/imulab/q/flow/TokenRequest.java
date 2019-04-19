// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CodeHybridFlow.proto

package io.imulab.q.flow;

/**
 * Protobuf type {@code flow.TokenRequest}
 */
public  final class TokenRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:flow.TokenRequest)
    TokenRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TokenRequest.newBuilder() to construct.
  private TokenRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TokenRequest() {
    id_ = "";
    requestAt_ = 0L;
    grantTypes_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    code_ = "";
    redirectUri_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TokenRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            id_ = s;
            break;
          }
          case 16: {

            requestAt_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              grantTypes_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000004;
            }
            grantTypes_.add(s);
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            code_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            redirectUri_ = s;
            break;
          }
          case 50: {
            io.imulab.q.flow.Client.Builder subBuilder = null;
            if (client_ != null) {
              subBuilder = client_.toBuilder();
            }
            client_ = input.readMessage(io.imulab.q.flow.Client.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(client_);
              client_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        grantTypes_ = grantTypes_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.imulab.q.flow.CodeHybridFlowProto.internal_static_flow_TokenRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.imulab.q.flow.CodeHybridFlowProto.internal_static_flow_TokenRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.imulab.q.flow.TokenRequest.class, io.imulab.q.flow.TokenRequest.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 1;</code>
   */
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REQUEST_AT_FIELD_NUMBER = 2;
  private long requestAt_;
  /**
   * <code>int64 request_at = 2;</code>
   */
  public long getRequestAt() {
    return requestAt_;
  }

  public static final int GRANT_TYPES_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList grantTypes_;
  /**
   * <code>repeated string grant_types = 3;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getGrantTypesList() {
    return grantTypes_;
  }
  /**
   * <code>repeated string grant_types = 3;</code>
   */
  public int getGrantTypesCount() {
    return grantTypes_.size();
  }
  /**
   * <code>repeated string grant_types = 3;</code>
   */
  public java.lang.String getGrantTypes(int index) {
    return grantTypes_.get(index);
  }
  /**
   * <code>repeated string grant_types = 3;</code>
   */
  public com.google.protobuf.ByteString
      getGrantTypesBytes(int index) {
    return grantTypes_.getByteString(index);
  }

  public static final int CODE_FIELD_NUMBER = 4;
  private volatile java.lang.Object code_;
  /**
   * <code>string code = 4;</code>
   */
  public java.lang.String getCode() {
    java.lang.Object ref = code_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      code_ = s;
      return s;
    }
  }
  /**
   * <code>string code = 4;</code>
   */
  public com.google.protobuf.ByteString
      getCodeBytes() {
    java.lang.Object ref = code_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      code_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REDIRECTURI_FIELD_NUMBER = 5;
  private volatile java.lang.Object redirectUri_;
  /**
   * <code>string redirectUri = 5;</code>
   */
  public java.lang.String getRedirectUri() {
    java.lang.Object ref = redirectUri_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      redirectUri_ = s;
      return s;
    }
  }
  /**
   * <code>string redirectUri = 5;</code>
   */
  public com.google.protobuf.ByteString
      getRedirectUriBytes() {
    java.lang.Object ref = redirectUri_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      redirectUri_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CLIENT_FIELD_NUMBER = 6;
  private io.imulab.q.flow.Client client_;
  /**
   * <code>.flow.Client client = 6;</code>
   */
  public boolean hasClient() {
    return client_ != null;
  }
  /**
   * <code>.flow.Client client = 6;</code>
   */
  public io.imulab.q.flow.Client getClient() {
    return client_ == null ? io.imulab.q.flow.Client.getDefaultInstance() : client_;
  }
  /**
   * <code>.flow.Client client = 6;</code>
   */
  public io.imulab.q.flow.ClientOrBuilder getClientOrBuilder() {
    return getClient();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (requestAt_ != 0L) {
      output.writeInt64(2, requestAt_);
    }
    for (int i = 0; i < grantTypes_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, grantTypes_.getRaw(i));
    }
    if (!getCodeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, code_);
    }
    if (!getRedirectUriBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, redirectUri_);
    }
    if (client_ != null) {
      output.writeMessage(6, getClient());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (requestAt_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, requestAt_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < grantTypes_.size(); i++) {
        dataSize += computeStringSizeNoTag(grantTypes_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getGrantTypesList().size();
    }
    if (!getCodeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, code_);
    }
    if (!getRedirectUriBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, redirectUri_);
    }
    if (client_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(6, getClient());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.imulab.q.flow.TokenRequest)) {
      return super.equals(obj);
    }
    io.imulab.q.flow.TokenRequest other = (io.imulab.q.flow.TokenRequest) obj;

    boolean result = true;
    result = result && getId()
        .equals(other.getId());
    result = result && (getRequestAt()
        == other.getRequestAt());
    result = result && getGrantTypesList()
        .equals(other.getGrantTypesList());
    result = result && getCode()
        .equals(other.getCode());
    result = result && getRedirectUri()
        .equals(other.getRedirectUri());
    result = result && (hasClient() == other.hasClient());
    if (hasClient()) {
      result = result && getClient()
          .equals(other.getClient());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + REQUEST_AT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRequestAt());
    if (getGrantTypesCount() > 0) {
      hash = (37 * hash) + GRANT_TYPES_FIELD_NUMBER;
      hash = (53 * hash) + getGrantTypesList().hashCode();
    }
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + getCode().hashCode();
    hash = (37 * hash) + REDIRECTURI_FIELD_NUMBER;
    hash = (53 * hash) + getRedirectUri().hashCode();
    if (hasClient()) {
      hash = (37 * hash) + CLIENT_FIELD_NUMBER;
      hash = (53 * hash) + getClient().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.imulab.q.flow.TokenRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.imulab.q.flow.TokenRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.imulab.q.flow.TokenRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.imulab.q.flow.TokenRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.imulab.q.flow.TokenRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code flow.TokenRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:flow.TokenRequest)
      io.imulab.q.flow.TokenRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.imulab.q.flow.CodeHybridFlowProto.internal_static_flow_TokenRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.imulab.q.flow.CodeHybridFlowProto.internal_static_flow_TokenRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.imulab.q.flow.TokenRequest.class, io.imulab.q.flow.TokenRequest.Builder.class);
    }

    // Construct using io.imulab.q.flow.TokenRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = "";

      requestAt_ = 0L;

      grantTypes_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      code_ = "";

      redirectUri_ = "";

      if (clientBuilder_ == null) {
        client_ = null;
      } else {
        client_ = null;
        clientBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.imulab.q.flow.CodeHybridFlowProto.internal_static_flow_TokenRequest_descriptor;
    }

    @java.lang.Override
    public io.imulab.q.flow.TokenRequest getDefaultInstanceForType() {
      return io.imulab.q.flow.TokenRequest.getDefaultInstance();
    }

    @java.lang.Override
    public io.imulab.q.flow.TokenRequest build() {
      io.imulab.q.flow.TokenRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.imulab.q.flow.TokenRequest buildPartial() {
      io.imulab.q.flow.TokenRequest result = new io.imulab.q.flow.TokenRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.id_ = id_;
      result.requestAt_ = requestAt_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        grantTypes_ = grantTypes_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.grantTypes_ = grantTypes_;
      result.code_ = code_;
      result.redirectUri_ = redirectUri_;
      if (clientBuilder_ == null) {
        result.client_ = client_;
      } else {
        result.client_ = clientBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.imulab.q.flow.TokenRequest) {
        return mergeFrom((io.imulab.q.flow.TokenRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.imulab.q.flow.TokenRequest other) {
      if (other == io.imulab.q.flow.TokenRequest.getDefaultInstance()) return this;
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        onChanged();
      }
      if (other.getRequestAt() != 0L) {
        setRequestAt(other.getRequestAt());
      }
      if (!other.grantTypes_.isEmpty()) {
        if (grantTypes_.isEmpty()) {
          grantTypes_ = other.grantTypes_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureGrantTypesIsMutable();
          grantTypes_.addAll(other.grantTypes_);
        }
        onChanged();
      }
      if (!other.getCode().isEmpty()) {
        code_ = other.code_;
        onChanged();
      }
      if (!other.getRedirectUri().isEmpty()) {
        redirectUri_ = other.redirectUri_;
        onChanged();
      }
      if (other.hasClient()) {
        mergeClient(other.getClient());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.imulab.q.flow.TokenRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.imulab.q.flow.TokenRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      id_ = value;
      onChanged();
      return this;
    }

    private long requestAt_ ;
    /**
     * <code>int64 request_at = 2;</code>
     */
    public long getRequestAt() {
      return requestAt_;
    }
    /**
     * <code>int64 request_at = 2;</code>
     */
    public Builder setRequestAt(long value) {
      
      requestAt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 request_at = 2;</code>
     */
    public Builder clearRequestAt() {
      
      requestAt_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList grantTypes_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureGrantTypesIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        grantTypes_ = new com.google.protobuf.LazyStringArrayList(grantTypes_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getGrantTypesList() {
      return grantTypes_.getUnmodifiableView();
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public int getGrantTypesCount() {
      return grantTypes_.size();
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public java.lang.String getGrantTypes(int index) {
      return grantTypes_.get(index);
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public com.google.protobuf.ByteString
        getGrantTypesBytes(int index) {
      return grantTypes_.getByteString(index);
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public Builder setGrantTypes(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureGrantTypesIsMutable();
      grantTypes_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public Builder addGrantTypes(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureGrantTypesIsMutable();
      grantTypes_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public Builder addAllGrantTypes(
        java.lang.Iterable<java.lang.String> values) {
      ensureGrantTypesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, grantTypes_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public Builder clearGrantTypes() {
      grantTypes_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string grant_types = 3;</code>
     */
    public Builder addGrantTypesBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureGrantTypesIsMutable();
      grantTypes_.add(value);
      onChanged();
      return this;
    }

    private java.lang.Object code_ = "";
    /**
     * <code>string code = 4;</code>
     */
    public java.lang.String getCode() {
      java.lang.Object ref = code_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        code_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string code = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCodeBytes() {
      java.lang.Object ref = code_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        code_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string code = 4;</code>
     */
    public Builder setCode(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      code_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string code = 4;</code>
     */
    public Builder clearCode() {
      
      code_ = getDefaultInstance().getCode();
      onChanged();
      return this;
    }
    /**
     * <code>string code = 4;</code>
     */
    public Builder setCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      code_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object redirectUri_ = "";
    /**
     * <code>string redirectUri = 5;</code>
     */
    public java.lang.String getRedirectUri() {
      java.lang.Object ref = redirectUri_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        redirectUri_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string redirectUri = 5;</code>
     */
    public com.google.protobuf.ByteString
        getRedirectUriBytes() {
      java.lang.Object ref = redirectUri_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        redirectUri_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string redirectUri = 5;</code>
     */
    public Builder setRedirectUri(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      redirectUri_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string redirectUri = 5;</code>
     */
    public Builder clearRedirectUri() {
      
      redirectUri_ = getDefaultInstance().getRedirectUri();
      onChanged();
      return this;
    }
    /**
     * <code>string redirectUri = 5;</code>
     */
    public Builder setRedirectUriBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      redirectUri_ = value;
      onChanged();
      return this;
    }

    private io.imulab.q.flow.Client client_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.imulab.q.flow.Client, io.imulab.q.flow.Client.Builder, io.imulab.q.flow.ClientOrBuilder> clientBuilder_;
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public boolean hasClient() {
      return clientBuilder_ != null || client_ != null;
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public io.imulab.q.flow.Client getClient() {
      if (clientBuilder_ == null) {
        return client_ == null ? io.imulab.q.flow.Client.getDefaultInstance() : client_;
      } else {
        return clientBuilder_.getMessage();
      }
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public Builder setClient(io.imulab.q.flow.Client value) {
      if (clientBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        client_ = value;
        onChanged();
      } else {
        clientBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public Builder setClient(
        io.imulab.q.flow.Client.Builder builderForValue) {
      if (clientBuilder_ == null) {
        client_ = builderForValue.build();
        onChanged();
      } else {
        clientBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public Builder mergeClient(io.imulab.q.flow.Client value) {
      if (clientBuilder_ == null) {
        if (client_ != null) {
          client_ =
            io.imulab.q.flow.Client.newBuilder(client_).mergeFrom(value).buildPartial();
        } else {
          client_ = value;
        }
        onChanged();
      } else {
        clientBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public Builder clearClient() {
      if (clientBuilder_ == null) {
        client_ = null;
        onChanged();
      } else {
        client_ = null;
        clientBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public io.imulab.q.flow.Client.Builder getClientBuilder() {
      
      onChanged();
      return getClientFieldBuilder().getBuilder();
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    public io.imulab.q.flow.ClientOrBuilder getClientOrBuilder() {
      if (clientBuilder_ != null) {
        return clientBuilder_.getMessageOrBuilder();
      } else {
        return client_ == null ?
            io.imulab.q.flow.Client.getDefaultInstance() : client_;
      }
    }
    /**
     * <code>.flow.Client client = 6;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.imulab.q.flow.Client, io.imulab.q.flow.Client.Builder, io.imulab.q.flow.ClientOrBuilder> 
        getClientFieldBuilder() {
      if (clientBuilder_ == null) {
        clientBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.imulab.q.flow.Client, io.imulab.q.flow.Client.Builder, io.imulab.q.flow.ClientOrBuilder>(
                getClient(),
                getParentForChildren(),
                isClean());
        client_ = null;
      }
      return clientBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:flow.TokenRequest)
  }

  // @@protoc_insertion_point(class_scope:flow.TokenRequest)
  private static final io.imulab.q.flow.TokenRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.imulab.q.flow.TokenRequest();
  }

  public static io.imulab.q.flow.TokenRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TokenRequest>
      PARSER = new com.google.protobuf.AbstractParser<TokenRequest>() {
    @java.lang.Override
    public TokenRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TokenRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TokenRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TokenRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.imulab.q.flow.TokenRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

