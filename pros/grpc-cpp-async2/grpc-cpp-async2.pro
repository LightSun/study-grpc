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

#grpc
GRPC_DIR=F:\cpp_builds\grpc_1.38.0_x86\Debug
INCLUDEPATH += $${GRPC_DIR}/include
LIBS += $${GRPC_DIR}/lib address_sorting.lib
#LIBS += $${GRPC_DIR}/lib benchmark.lib
#LIBS += $${GRPC_DIR}/lib benchmark_main.lib
#LIBS += $${GRPC_DIR}/lib cares.lib
#LIBS += $${GRPC_DIR}/lib crypto.lib
#LIBS += $${GRPC_DIR}/lib decrepit.lib
#LIBS += $${GRPC_DIR}/lib gflags_nothreads_static_debug.lib
LIBS += $${GRPC_DIR}/lib gpr.lib
LIBS += $${GRPC_DIR}/lib grpc.lib
#LIBS += $${GRPC_DIR}/lib grpc_cronet.lib
LIBS += $${GRPC_DIR}/lib grpc_csharp_ext.lib
LIBS += $${GRPC_DIR}/lib grpc_plugin_support.lib
LIBS += $${GRPC_DIR}/lib grpc_unsecure.lib

LIBS += $${GRPC_DIR}/lib grpc++.lib
#LIBS += $${GRPC_DIR}/lib grpc++_cronet.lib
LIBS += $${GRPC_DIR}/lib grpc++_alts.lib
LIBS += $${GRPC_DIR}/lib grpc++_error_details.lib
LIBS += $${GRPC_DIR}/lib grpc++_reflection.lib
LIBS += $${GRPC_DIR}/lib grpc++_unsecure.lib
LIBS += $${GRPC_DIR}/lib grpcpp_channelz.lib
LIBS += $${GRPC_DIR}/lib upd.lib
#LIBS += $${GRPC_DIR}/lib libprotobufd.lib
#LIBS += $${GRPC_DIR}/lib libprotobuf-lited.lib
#LIBS += $${GRPC_DIR}/lib libprotocd.lib
#LIBS += $${GRPC_DIR}/lib ssl.lib
#LIBS += $${GRPC_DIR}/lib zlibstaticd.lib
#grpc need '_WIN32_WINNT'
DEFINES += _WIN32_WINNT=0x0A00
#grpc depend on 'ws2_32,advapi32'
LIBS += -lWs2_32
LIBS += -ladvapi32

HEADERS += \
    helloworld.grpc.pb.h \
    helloworld.pb.h
