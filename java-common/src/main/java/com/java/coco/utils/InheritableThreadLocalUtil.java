package com.java.coco.utils;

public class InheritableThreadLocalUtil {
    public static final InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    //获取线程中保存的值
    public static String getValue() {
        return THREAD_LOCAL.get();
    }

    //设置线程需要保存的值
    public static void setValue(String str) {
        THREAD_LOCAL.set(str);
    }

    //移除线程中保存的值
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
