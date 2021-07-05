

import logging
import asyncio
import grpc

import  CameraAI_pb2
import  CameraAI_pb2_grpc

class Greeter(CameraAI_pb2_grpc.GreeterServicer):

    async def SayHello(
            self, request: CameraAI_pb2.AIReq,
            context: grpc.aio.ServicerContext) -> CameraAI_pb2.AIRes:
        # (CameraAI_pb2.AIKeyInfo) keyInfo = request.keyInfo;
        # (int) type = request.type;
        # (string) imageObjectName = request.imageObjectName;
        # type = CameraAI_pb2.AIType.RECOGNIZE 识别
        # type = CameraAI_pb2.AIType.SUCKER_DETECTION 吸盘检测

        return CameraAI_pb2.AIRes(type=request.type, msg="ok", keyInfo = request.keyInfo, data="{<json-data>}")
    """
    data json数据格式：
    1)识别: 
    2)吸盘检测: {sucker_detected=true}
    """

async def serve() -> None:
    server = grpc.aio.server()
    CameraAI_pb2_grpc.add_GreeterServicer_to_server(Greeter(), server)
    listen_addr = '[::]:50051'
    server.add_insecure_port(listen_addr)
    logging.info("Starting server on %s", listen_addr)
    await server.start()
    try:
        await server.wait_for_termination()
    except KeyboardInterrupt:
        # Shuts down the server with 0 seconds of grace period. During the
        # grace period, the server won't accept new connections and allow
        # existing RPCs to continue within the grace period.
        await server.stop(0)


if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)
    asyncio.run(serve())