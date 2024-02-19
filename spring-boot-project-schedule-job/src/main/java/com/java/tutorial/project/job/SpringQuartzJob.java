package com.java.tutorial.project.job;

import cn.hutool.core.util.RandomUtil;
import com.java.tutorial.project.schedule.ScheduleRunnable;
import com.java.tutorial.project.schedule.SysJobLog;
import com.java.tutorial.project.util.TraceIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.Executor;

/**
 * @author HY
 * @DisallowConcurrentExecution
 */
@Slf4j
@Component
public class SpringQuartzJob extends QuartzJobBean {

    @Autowired
    @Qualifier(value = "ttlThreadPoolTaskExecutor")
    private Executor threadPoolTaskExecutor;

    private static SysJobLog getSysJobLog() {
        SysJobLog jobLog = new SysJobLog();
        jobLog.setJobName("JobName: " + RandomUtil.randomString(10));
        jobLog.setMethodName("MethodName: " + RandomUtil.randomString(10));
        jobLog.setMethodParams("MethodParams: " + RandomUtil.randomString(10));
        return jobLog;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("准备循环开始执行定时任务,线程名:{}, traceID: {}", Thread.currentThread().getName(),
            TraceIDUtil.getTraceId());
        context.getJobDetail().getJobDataMap().forEach((k, v) -> {
            for (int i = 0; i < 3; i++) {
                extracted(getSysJobLog(), k, v);
            }
        });
        stopWatch.stop();
        log.info("循环定时任务执行结束，执行时间: " + stopWatch.getLastTaskTimeMillis());
        TraceIDUtil.clearTraceId();
    }

    private void extracted(SysJobLog jobLog, String k, Object v) {
        try {
            Thread.sleep(1000L);
            log.info("单个定时任务参数 - 线程{} 名称：{} 方法：{}", Thread.currentThread().getName(), jobLog.getJobName(),
                jobLog.getMethodName());
            ScheduleRunnable task =
                new ScheduleRunnable(jobLog.getJobName(), jobLog.getMethodName(), jobLog.getMethodParams());
            threadPoolTaskExecutor.execute(task);
            log.info("单个定时任务执行结束 param, key:{}, value:{}", k, v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
