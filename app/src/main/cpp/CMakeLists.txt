cmake_minimum_required(VERSION 3.22.1)

Project(Winlator)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O2 -Wno-unused-function -Wimplicit-function-declaration")

# Оставляем только живые модули
add_subdirectory(proot)
add_subdirectory(virglrenderer)
add_subdirectory(midihandler)

# ВАЖНО: Мы НЕ добавляем OpenXR-SDK, так как он сломан

add_library(winlator SHARED
            # Мы закомментировали все файлы из папки xr/, 
            # так как они зависят от библиотек OpenXR
            # xr/engine.c
            # xr/framebuffer.c
            # xr/input.c
            # xr/main.c
            # xr/math.c
            # xr/renderer.c
            winlator/drawable.c
            winlator/gpu_image.c
            winlator/sysvshared_memory.c
            winlator/xconnector_epoll.c
            winlator/alsa_client.c)

target_link_libraries(winlator
                      log
                      android
                      jnigraphics
                      # openxr_loader — вырезаем, этого файла у нас нет
                      aaudio
                      EGL
                      GLESv2
                      GLESv3)
