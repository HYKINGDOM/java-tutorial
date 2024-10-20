package com.java.tutorial.project.util;

import cn.hutool.core.lang.UUID;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @author meta
 */
public class ThreadMdcUtils {

    public static final String TRACE_ID = "traceId";

    public static Runnable wrapAsync(Runnable task, Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            if (MDC.get(TRACE_ID) == null) {
                MDC.put(TRACE_ID, UUID.fastUUID().toString(true));
            }
            try {
                task.run();
            } finally {
                MDC.clear();
            }
        };
    }
}

