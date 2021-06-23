//#include <QCoreApplication>

/*
 * Additional Include Directories
C:\extlibs\grpc\examples\cpp\helloworld\cmake\build C:\extlibs\grpc\testinstall\include

Preprocessor Definition
    _WIN32_WINNT=0x600

      Additional Dependencies
          C:\extlibs\grpc\testinstall\lib\grpc++_unsecure.lib
C:\extlibs\grpc\testinstall\lib\libprotobuf.lib
C:\extlibs\grpc\testinstall\lib\grpc_unsecure.lib
C:\extlibs\grpc\testinstall\lib\gpr.lib
C:\extlibs\grpc\testinstall\lib\zlib.lib
C:\extlibs\grpc\testinstall\lib\cares.lib
C:\extlibs\grpc\testinstall\lib\address_sorting.lib
wsock32.lib ws2_32.lib kernel32.lib user32.lib gdi32.lib
 winspool.lib shell32.lib ole32.lib oleaut32.lib
uuid.lib comdlg32.lib advapi32.lib
*/
/**
Packages (3) mingw-w64-x86_64-c-ares-1.17.1-1
             mingw-w64-x86_64-protobuf-3.12.4-1  mingw-w64-x86_64-grpc-1.35.0-1
*/
#define GRPC_TEST_SERVER 1
#ifdef GRPC_TEST_SERVER
extern "C" int main_server(int argc, char *argv[]);
#define main0 main_server
#else
extern "C" int main_client(int argc, char *argv[]);
#define main0 main_client
#endif

int main(int argc, char *argv[])
{
    //QCoreApplication a(argc, argv);

    //return a.exec();
    return main0(argc, argv);
}
