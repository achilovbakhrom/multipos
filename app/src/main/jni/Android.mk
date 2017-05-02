LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := NdkTest
LOCAL_C_INCLUDES = ./sqlite ./
LOCAL_SRC_FILES := NdkTest.cpp sqlite/sqlite3.c sqlite_helper.c
include $(BUILD_SHARED_LIBRARY)