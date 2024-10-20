package com.java.tutorial.project.common.util;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.slf4j.MDC;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.UUID;

/**
 * @author meta
 */
@Slf4j
public class TraceIDUtil {

    public static final String DEFAULT_TRACE_ID = "traceId";

    /**
     * 根据入参设置traceId到MDC
     *
     * @param traceId
     */
    private static void setTraceIdToMdc(String traceId) {
        MDC.put(DEFAULT_TRACE_ID, traceId);
    }

    /**
     * 获取traceId
     * @return traceId
     */
    public static String getTraceId() {
        String traceId = MDC.get(DEFAULT_TRACE_ID);
        if (StrUtil.isBlank(traceId)) {
            traceId = createTraceId();
            setTraceIdToMdc(traceId);
        }
        return traceId;
    }

    /**
     * 清除MDC和TTL中的traceID
     */
    public static void clearTraceId() {
        MDC.remove(DEFAULT_TRACE_ID);
    }

    /**
     * 创建traceId
     * @return
     */
    public static String createTraceId() {
        return UUID.randomUUID().toString() + System.currentTimeMillis() + getRandomString() + getProcessId();
    }

    /**
     * 获取进程号
     *
     * @return
     */
    private static String getProcessId() {
        RuntimeMXBean runtimeProcessId = ManagementFactory.getRuntimeMXBean();
        return runtimeProcessId.getName().split("@")[0];
    }

    /**
     * 随机生成 4 位字符串
     *
     * @return
     */
    private static String getRandomString() {
        return UUID.randomUUID().toString().substring(0, 4);
    }

}