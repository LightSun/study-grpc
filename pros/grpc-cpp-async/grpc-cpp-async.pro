QT -= gui

CONFIG += c++11 console
CONFIG -= app_bundle

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
        greeter_async_client.cc \
        greeter_async_server.cc \
        helloworld.grpc.pb.cc \
        helloworld.pb.cc \
        main.cpp

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

#grpc 1.22. download: https://github.com/LightSun/gRPC_windows
#grpc-java plugin: https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.22.0/
GRPC_DIR=E:\study\cpp\grpc_libs\MSVC142_32\Debug
INCLUDEPATH += $${GRPC_DIR}/include
LIBPATH += $${GRPC_DIR}/lib

CONFIG(debug, debug|release){
    LIBS +=  address_sorting.lib
    LIBS +=  benchmark.lib
    LIBS +=  benchmark_main.lib
    LIBS +=  cares.lib
    LIBS +=  crypto.lib
    LIBS +=  decrepit.lib
    LIBS +=  gflags_nothreads_static_debug.lib
    LIBS +=  gpr.lib
    LIBS +=  grpc.lib
    LIBS +=  grpc_cronet.lib
    LIBS +=  grpc_csharp_ext.lib
    LIBS +=  grpc_plugin_support.lib
    LIBS +=  grpc_unsecure.lib

    LIBS +=  grpc++.lib
    LIBS +=  grpc++_cronet.lib
    LIBS +=  grpc++_error_details.lib
    LIBS +=  grpc++_reflection.lib
    LIBS +=  grpcpp_channelz.lib
    LIBS +=  libprotobufd.lib
    LIBS +=  libprotobuf-lited.lib
    LIBS +=  libprotocd.lib
    LIBS +=  ssl.lib
    LIBS +=  zlibstaticd.lib
}
#grpc need '_WIN32_WINNT'
DEFINES += _WIN32_WINNT=0x0A00
#grpc depend on 'ws2_32,advapi32'
LIBS += -lWs2_32
LIBS += -ladvapi32

HEADERS += \
    helloworld.grpc.pb.h \
    helloworld.pb.h
