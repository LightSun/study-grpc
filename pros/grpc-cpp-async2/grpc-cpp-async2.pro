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
GRPC_DIR=F:\cpp_builds\grpc_1.38.0_x86_install
INCLUDEPATH += $${GRPC_DIR}/include
LIBPATH += $${GRPC_DIR}/lib
#grpc need '_WIN32_WINNT'
DEFINES += _WIN32_WINNT=0x0A00
#DEFINES += _ITERATOR_DEBUG_LEVEL=2

# the first release means build release mode. debug means debug
CONFIG(debug, debug|release){
    LIBS += address_sorting.lib
    LIBS += cares.lib
    LIBS += crypto.lib

    LIBS += gpr.lib
    LIBS += grpc.lib
    #LIBS += grpc_csharp_ext.lib
    LIBS += grpc_plugin_support.lib
    LIBS += grpc_unsecure.lib

    LIBS += grpc++.lib
    LIBS += grpc++_alts.lib
    LIBS += grpc++_error_details.lib
    LIBS += grpc++_reflection.lib
    LIBS += grpc++_unsecure.lib
    LIBS += grpcpp_channelz.lib

    LIBS += libprotobufd.lib
    LIBS += libprotobuf-lited.lib
    LIBS += libprotocd.lib
    LIBS += zlibstaticd.lib

    LIBS += re2.lib
    LIBS += ssl.lib
    LIBS += upb.lib

    LIBS += absl_wyhash.lib
    LIBS += absl_bad_any_cast_impl.lib
    LIBS += absl_bad_optional_access.lib
    LIBS += absl_bad_variant_access.lib
    LIBS += absl_base.lib
    LIBS += absl_city.lib
    LIBS += absl_civil_time.lib
    LIBS += absl_cord.lib
    LIBS += absl_debugging_internal.lib
    LIBS += absl_demangle_internal.lib
    LIBS += absl_examine_stack.lib
    LIBS += absl_exponential_biased.lib
    LIBS += absl_failure_signal_handler.lib
    LIBS += absl_flags.lib
    LIBS += absl_flags_commandlineflag.lib
    LIBS += absl_flags_commandlineflag_internal.lib
    LIBS += absl_flags_config.lib
    LIBS += absl_flags_internal.lib
    LIBS += absl_flags_marshalling.lib
    LIBS += absl_flags_parse.lib
    LIBS += absl_flags_private_handle_accessor.lib
    LIBS += absl_flags_program_name.lib
    LIBS += absl_flags_reflection.lib
    LIBS += absl_flags_usage.lib
    LIBS += absl_flags_usage_internal.lib
    LIBS += absl_graphcycles_internal.lib
    LIBS += absl_hash.lib
    LIBS += absl_hashtablez_sampler.lib
    LIBS += absl_int128.lib
    LIBS += absl_leak_check.lib
    LIBS += absl_leak_check_disable.lib
    LIBS += absl_log_severity.lib
    LIBS += absl_malloc_internal.lib
    LIBS += absl_periodic_sampler.lib
    LIBS += absl_random_distributions.lib
    LIBS += absl_random_internal_distribution_test_util.lib
    LIBS += absl_random_internal_platform.lib
    LIBS += absl_random_internal_pool_urbg.lib
    LIBS += absl_random_internal_randen.lib
    LIBS += absl_random_internal_randen_hwaes.lib
    LIBS += absl_random_internal_randen_hwaes_impl.lib
    LIBS += absl_random_internal_randen_slow.lib
    LIBS += absl_random_internal_seed_material.lib
    LIBS += absl_random_seed_gen_exception.lib
    LIBS += absl_random_seed_sequences.lib
    LIBS += absl_raw_hash_set.lib
    LIBS += absl_raw_logging_internal.lib
    LIBS += absl_scoped_set_env.lib
    LIBS += absl_spinlock_wait.lib
    LIBS += absl_stacktrace.lib
    LIBS += absl_status.lib
    LIBS += absl_statusor.lib
    LIBS += absl_str_format_internal.lib
    LIBS += absl_strerror.lib
    LIBS += absl_strings.lib
    LIBS += absl_strings_internal.lib
    LIBS += absl_symbolize.lib
    LIBS += absl_synchronization.lib
    LIBS += absl_throw_delegate.lib
    LIBS += absl_time.lib
    LIBS += absl_time_zone.lib

    #grpc depend on 'ws2_32,advapi32'
    LIBS += -lWs2_32
    LIBS += -ladvapi32
}

HEADERS += \
    helloworld.grpc.pb.h \
    helloworld.pb.h
