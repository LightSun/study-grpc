@rem https://blog.csdn.net/icyfox_bupt/article/details/108826873 grpc-java
@rem from https://blog.csdn.net/xiaoyafang123/article/details/76529917

SET ENV=E:\study\cpp\grpc_libs\MSVC142_32\Debug\bin
@rem jump to current dir
cd /d %~dp0
@rem cpp
%ENV%/protoc.exe -I=. --cpp_out=. helloworld.proto
%ENV%/protoc.exe -I=. --grpc_out=. --plugin=protoc-gen-grpc="%ENV%/grpc_cpp_plugin.exe" helloworld.proto
@rem java
%ENV%/protoc.exe -I=. --java_out=java helloworld.proto
%ENV%/protoc.exe -I=. --grpc_out=java --plugin=protoc-gen-grpc="protoc-gen-grpc-java-1.22.0-x86.exe" helloworld.proto
@PAUSE