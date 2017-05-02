package com.jim.multipos;

/**
 * Created by Developer on 5/1/17.
 */

public class NdkTest {
    static {
        System.loadLibrary("NdkTest");//加载要使用的so文件
    }

    public static native String getString();
    public static native int doAdd(int param1,int param2);
}
