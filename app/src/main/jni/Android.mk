LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog

LOCAL_MODULE := sqlite

LOCAL_C_INCLUDES = ./sqlite

LOCAL_SRC_FILES :=  sqlite/sqlite3.c

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_CPPFLAGS := -Wall -std=c++11 -DANDROID -finline-functions -ffast-math -Os -fno-strict-aliasing
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
LOCAL_MODULE 	:= sqlitewrapper
LOCAL_STATIC_LIBRARIES := sqlite

LOCAL_C_INCLUDES = ./

LOCAL_SRC_FILES     := SQLLite_wrapper.cpp

include $(BUILD_SHARED_LIBRARY)

$(call import-module,android/cpufeatures)