package com.java.tutorial.project.schedule;

import com.java.tutorial.project.util.TraceIDUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ScheduleRunnable implements Runnable {


    private Object target;
    private Object method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params)
            throws SecurityException {
        this.target = beanName;
        this.params = params;
        this.method = methodName;
    }


    @Override
    public void run() {
        try {
            String traceId = TraceIDUtil.getTraceId();
            Thread.sleep(5000L);
            log.info("任务线程： {}, 任务执行结束,TraceId: {}, beanName： {}, params： {}, methodName： {}",
                    Thread.currentThread().getName(),
                    traceId,
                    target,
                    params,
                    method);
        } catch (Exception e) {
            log.error("执行定时任务异常  - ：", e);
        }
    }

}
