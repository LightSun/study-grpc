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

#grpc
INCLUDEPATH += E:\study\cpp\msys2_64\mingw32\include
#LIBPATH += E:\study\cpp\grpc_dll2lib\grpc_libs_32
#LIB_DIR = E:\study\cpp\grpc_dll2lib\grpc_libs_32
LIB_DIR = E:\study\cpp\msys2_64\mingw32\lib

win32: LIBS += $${LIB_DIR}/libgpr.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc_plugin_support.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc_unsecure.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc++.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc++_alts.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc++_error_details.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc++_reflection.dll.a
win32: LIBS += $${LIB_DIR}/libgrpc++_unsecure.dll.a
win32: LIBS += $${LIB_DIR}/libprotobuf.dll.a
win32: LIBS += $${LIB_DIR}/libprotobuf-lite.dll.a
win32: LIBS += $${LIB_DIR}/libprotoc.dll.a
#win32: LIBS += -L$${LIB_DIR} -llibgrpc_plugin_support


HEADERS += \
    helloworld.grpc.pb.h \
    helloworld.pb.h
