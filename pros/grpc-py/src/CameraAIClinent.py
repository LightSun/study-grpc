
from __future__ import print_function
import logging

import grpc

import CameraAI_pb2
import CameraAI_pb2_grpc

def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = CameraAI_pb2_grpc.CameraAIStub(channel)
        keyInfo = CameraAI_pb2.AIKeyInfo(unloadingPointId="p_123",recognizeSheetId="reg_123",presetId=1,isLastPreset=True,cameraAddr="21")
        response = stub.GetResult(CameraAI_pb2.AIReq(keyInfo=keyInfo,type=CameraAI_pb2.AIType.RECOGNIZE,imageObjectName="adb/d.jpg"))
    print("Greeter client received msg: " + response.msg)
    print("Greeter client received data: " + response.data)


if __name__ == '__main__':
    logging.basicConfig()
    run()
