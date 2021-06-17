//#include <QCoreApplication>

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
