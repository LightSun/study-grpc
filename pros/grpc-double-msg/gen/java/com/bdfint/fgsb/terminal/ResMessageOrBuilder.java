// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: io_streaming.proto

package com.bdfint.fgsb.terminal;

public interface ResMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.bdfint.fgsb.terminal.ResMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 code = 1;</code>
   * @return The code.
   */
  int getCode();

  /**
   * <code>string msg = 2;</code>
   * @return The msg.
   */
  java.lang.String getMsg();
  /**
   * <code>string msg = 2;</code>
   * @return The bytes for msg.
   */
  com.google.protobuf.ByteString
      getMsgBytes();

  /**
   * <code>string data = 3;</code>
   * @return The data.
   */
  java.lang.String getData();
  /**
   * <code>string data = 3;</code>
   * @return The bytes for data.
   */
  com.google.protobuf.ByteString
      getDataBytes();
}