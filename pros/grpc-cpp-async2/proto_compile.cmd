@rem https://blog.csdn.net/icyfox_bupt/article/details/108826873 grpc-java
@rem from https://blog.csdn.net/xiaoyafang123/article/details/76529917

SET ENV=F:\cpp_builds\grpc_1.38.0_x86_cmake\Debug
SET JAVA_PROTOC_EXE=F:\cpp_builds\grpc_1.38.0_x86/protoc-gen-grpc-java-1.38.1-x86.exe
@rem jump to current dir
cd /d %~dp0
@rem cpp
%ENV%/protoc.exe -I=. --cpp_out=. helloworld.proto
%ENV%/protoc.exe -I=. --grpc_out=. --plugin=protoc-gen-grpc="%ENV%/grpc_cpp_plugin.exe" helloworld.proto
@rem java
%ENV%/protoc.exe -I=. --java_out=java helloworld.proto
%ENV%/protoc.exe -I=. --grpc_out=java --plugin=protoc-gen-grpc="%JAVA_PROTOC_EXE%" helloworld.proto
@rem python
%ENV%/protoc.exe -I=. --python_out=python helloworld.proto
%ENV%/protoc.exe -I=. --grpc_out=python --plugin=protoc-gen-grpc="%ENV%/grpc_python_plugin.exe" helloworld.proto
@PAUSE