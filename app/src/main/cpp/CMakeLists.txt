cmake_minimum_required(VERSION 3.22.1)

project(Winlator)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O2 -Wno-unused-function -Wimplicit-function-declaration")

# Vortek и Gladio — обычные папки в корне проекта (не субмодули)
add_subdirectory(${CMAKE_SOURCE_DIR}/../../../../../Vortek
                 ${CMAKE_BINARY_DIR}/Vortek)

add_subdirectory(${CMAKE_SOURCE_DIR}/../../../../../gladio
                 ${CMAKE_BINARY_DIR}/gladio)

add_subdirectory(proot)
add_subdirectory(virglrenderer)
add_subdirectory(midihandler)

add_library(winlator SHARED
            winlator/drawable.c
            winlator/gpu_image.c
            winlator/sysvshared_memory.c
            winlator/xconnector_epoll.c
            winlator/alsa_client.c)

target_link_libraries(winlator
                      vulkan_vortek
                      GL
                      log
                      android
                      jnigraphics
                      aaudio
                      EGL
                      GLESv2
                      GLESv3)