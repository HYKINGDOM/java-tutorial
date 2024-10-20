package com.java.coco.utils;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author meta
 */
public class TransmittableThreadLocalUtil {
    public static final TransmittableThreadLocal<String> THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 获取线程中保存的值
     *
     * @return
     */
    public static String getValue() {
        return THREAD_LOCAL.get();
    }

    /**
     * 设置线程需要保存的值
     *
     * @param str
     */
    public static void setValue(String str) {
        THREAD_LOCAL.set(str);
    }

    /**
     * 移除线程中保存的值
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
