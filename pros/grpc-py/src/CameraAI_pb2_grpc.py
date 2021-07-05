# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import CameraAI_pb2 as CameraAI__pb2


class CameraAIStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.GetResult = channel.unary_unary(
                '/fgsb.CameraAI/GetResult',
                request_serializer=CameraAI__pb2.AIReq.SerializeToString,
                response_deserializer=CameraAI__pb2.AIRes.FromString,
                )


class CameraAIServicer(object):
    """Missing associated documentation comment in .proto file."""

    def GetResult(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_CameraAIServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'GetResult': grpc.unary_unary_rpc_method_handler(
                    servicer.GetResult,
                    request_deserializer=CameraAI__pb2.AIReq.FromString,
                    response_serializer=CameraAI__pb2.AIRes.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'fgsb.CameraAI', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class CameraAI(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def GetResult(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/fgsb.CameraAI/GetResult',
            CameraAI__pb2.AIReq.SerializeToString,
            CameraAI__pb2.AIRes.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
