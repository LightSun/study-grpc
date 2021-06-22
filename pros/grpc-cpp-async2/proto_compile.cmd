@rem https://blog.csdn.net/icyfox_bupt/article/details/108826873 grpc-java
@rem from https://blog.csdn.net/xiaoyafang123/article/details/76529917

SET ENV=F:\cpp_builds\grpc_1.38.0_x86\Debug
SET PROTOC_DIR=F:\cpp_builds\protoc\bin
SET JAVA_PROTOC_DIR=F:\cpp_builds\grpc_1.38.0_x86
@rem jump to current dir
cd /d %~dp0
@rem cpp
%PROTOC_DIR%/protoc.exe -I=. --cpp_out=. helloworld.proto
%PROTOC_DIR%/protoc.exe -I=. --grpc_out=. --plugin=protoc-gen-grpc="%ENV%/grpc_cpp_plugin.exe" helloworld.proto
@rem java
%PROTOC_DIR%/protoc.exe -I=. --java_out=java helloworld.proto
%PROTOC_DIR%/protoc.exe -I=. --grpc_out=java --plugin=protoc-gen-grpc="%JAVA_PROTOC_DIR%/protoc-gen-grpc-java-1.38.1-x86.exe" helloworld.proto
@rem python
%PROTOC_DIR%/protoc.exe -I=. --python_out=python helloworld.proto
%PROTOC_DIR%/protoc.exe -I=. --grpc_out=python --plugin=protoc-gen-grpc="%ENV%/grpc_python_plugin.exe" helloworld.proto
@PAUSE