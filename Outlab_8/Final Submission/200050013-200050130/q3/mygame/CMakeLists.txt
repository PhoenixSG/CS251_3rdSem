cmake_minimum_required (VERSION 3.10.2)
project (mygame)
add_executable(mygamedynamic mygame.cpp)
target_link_libraries(mygamedynamic MyEngineDynamic)

project (mygame)
add_executable(mygamestatic mygame.cpp)
target_link_libraries(mygamestatic MyEngineStatic)


