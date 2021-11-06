cmake_minimum_required(VERSION 3.10.2)
project(helloworld)
add_executable(helloworld helloworld.cpp)
project(usespthread)
set(CMAKE_THREAD_PREFER_PTHREAD TRUE)
set(THREADS_PREFER_PTHREAD_FLAG TRUE)
find_package(Threads REQUIRED)
add_executable(usespthread usespthread.cpp)
target_link_libraries(usespthread Threads::Threads)
project(MyEngineDynamic)
include(GNUInstallDirs)
add_library(MyEngineDynamic SHARED 
    myengine/myengine.cpp)
add_library(MyEngineStatic STATIC 
    myengine/myengine.cpp)
set_target_properties(MyEngineDynamic PROPERTIES
    PUBLIC_HEADER myengine/myengine.hpp)
set_target_properties(MyEngineStatic PROPERTIES
    PUBLIC_HEADER myengine/myengine.hpp)
install(TARGETS MyEngineDynamic MyEngineStatic
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    PUBLIC_HEADER DESTINATION ${CMAKE_INSTALL_INCLUDEDIR})


