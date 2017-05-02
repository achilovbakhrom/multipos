#include "com_jim_multipos_NdkTest.h"
#include "sqlite_helper.h"
JNIEXPORT jstring JNICALL Java_com_jim_multipos_NdkTest_getString
			(JNIEnv *env, jclass type) {//具体实现
		create_db();
		insert_data();
		return env->NewStringUTF(get_data());
//		return env->NewStringUTF("hello world!!!");
	}

/*
 * Class:     com_jeanboy_demo_jnitest_NdkTest
 * Method:    doAdd
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_jim_multipos_NdkTest_doAdd
		(JNIEnv *env, jclass type, jint param1, jint param2) {//具体实现

	return param1 + param1;
}
