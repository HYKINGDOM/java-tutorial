package com.java.tutorial.project.util;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author HY
 */
public class TransmittableThreadLocalUtil {
    public static final TransmittableThreadLocal<String> THREAD_LOCAL = new TransmittableThreadLocal<>();


    /**
     * 设置线程需要保存的值
     *
     * @param str
     */
    public static void setValue(String str) {
        THREAD_LOCAL.set(str);
    }

    /**
     * 获取线程中保存的值
     *
     * @return
     */
    public static String getValue() {
        return THREAD_LOCAL.get();
    }

    /**
     * 移除线程中保存的值
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}