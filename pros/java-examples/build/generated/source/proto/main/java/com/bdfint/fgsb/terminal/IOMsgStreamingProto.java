// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: io_streaming.proto

package com.bdfint.fgsb.terminal;

public final class IOMsgStreamingProto {
  private IOMsgStreamingProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_bdfint_fgsb_terminal_ReqMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_bdfint_fgsb_terminal_ReqMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_bdfint_fgsb_terminal_ResMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_bdfint_fgsb_terminal_ResMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022io_streaming.proto\022\030com.bdfint.fgsb.te" +
      "rminal\"(\n\nReqMessage\022\014\n\004type\030\001 \001(\005\022\014\n\004da" +
      "ta\030\002 \001(\t\"C\n\nResMessage\022\014\n\004type\030\001 \001(\005\022\014\n\004" +
      "code\030\002 \001(\005\022\013\n\003msg\030\003 \001(\t\022\014\n\004data\030\004 \001(\t2o\n" +
      "\013IOStreaming\022`\n\014reqStreaming\022$.com.bdfin" +
      "t.fgsb.terminal.ReqMessage\032$.com.bdfint." +
      "fgsb.terminal.ResMessage\"\000(\0010\001B8\n\030com.bd" +
      "fint.fgsb.terminalB\023IOMsgStreamingProtoP" +
      "\001\242\002\004Fgsbb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_bdfint_fgsb_terminal_ReqMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_bdfint_fgsb_terminal_ReqMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_bdfint_fgsb_terminal_ReqMessage_descriptor,
        new java.lang.String[] { "Type", "Data", });
    internal_static_com_bdfint_fgsb_terminal_ResMessage_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_bdfint_fgsb_terminal_ResMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_bdfint_fgsb_terminal_ResMessage_descriptor,
        new java.lang.String[] { "Type", "Code", "Msg", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
