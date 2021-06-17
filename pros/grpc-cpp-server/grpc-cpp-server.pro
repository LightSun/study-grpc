QT -= gui

CONFIG += c++11 console
CONFIG -= app_bundle

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS
DEFINES += _WIN32_WINNT=0x0600

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
        greeter_client.cc \
        greeter_server.cc \
        helloworld.grpc.pb.cc \
        helloworld.pb.cc \
        main.cpp

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

INCLUDE_DIR = E:\study\cpp\msys2_64\mingw64\include
LIB_DIR = E:\study\cpp\msys2_64\mingw64\lib
INCLUDEPATH += $${INCLUDE_DIR}
DEPENDPATH += $${INCLUDE_DIR}

win32: LIBS += -L$${LIB_DIR} -llibgpr.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc.dll
#win32: LIBS += -L$${LIB_DIR} -llibgrpc_cronet.dll
#win32: LIBS += -L$${LIB_DIR} -llibgrpc_csharp_ext.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc_plugin_support.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc_unsecure.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc++.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc++_alts.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc++_error_details.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc++_reflection.dll
win32: LIBS += -L$${LIB_DIR} -llibgrpc++_unsecure.dll
#win32: LIBS += -L$${LIB_DIR} -llibgrpcpp_channelz.dll
win32: LIBS += -L$${LIB_DIR} -llibprotobuf.dll
win32: LIBS += -L$${LIB_DIR} -llibprotobuf-lite.dll
win32: LIBS += -L$${LIB_DIR} -llibprotoc.dll

#INCLUDEPATH += $$PWD/../../../../../cpp/msys2_64/mingw64/include
#DEPENDPATH += $$PWD/../../../../../cpp/msys2_64/mingw64/include

HEADERS += \
    helloworld.grpc.pb.h \
    helloworld.pb.h
