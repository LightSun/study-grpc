// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CameraControl.proto

package com.bdfint.base.sys.util.asuync;

public interface LoginInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:fgsb.LoginInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string username = 1;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string password = 2;</code>
   * @return The password.
   */
  java.lang.String getPassword();
  /**
   * <code>string password = 2;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <pre>
   *摄像头地址
   * </pre>
   *
   * <code>string cameraAddr = 3;</code>
   * @return The cameraAddr.
   */
  java.lang.String getCameraAddr();
  /**
   * <pre>
   *摄像头地址
   * </pre>
   *
   * <code>string cameraAddr = 3;</code>
   * @return The bytes for cameraAddr.
   */
  com.google.protobuf.ByteString
      getCameraAddrBytes();
}