@rem https://blog.csdn.net/icyfox_bupt/article/details/108826873 grpc-java
@rem from https://blog.csdn.net/xiaoyafang123/article/details/76529917

SET ENV=E:\study\cpp\grpc_libs\MSVC142_32\Debug\bin
@rem jump to current dir
cd /d %~dp0
%ENV%/protoc.exe -I=. --cpp_out=. helloworld.proto
%ENV%/protoc.exe -I=. --grpc_out=. --plugin=protoc-gen-grpc="%ENV%/grpc_cpp_plugin.exe" helloworld.proto
@PAUSE