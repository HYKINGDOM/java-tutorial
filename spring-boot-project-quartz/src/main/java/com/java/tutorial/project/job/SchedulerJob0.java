package com.java.tutorial.project.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static com.java.tutorial.project.common.Constant.FULL_DATE_FORMAT;

/**
 * @author hy
 */
@Slf4j
@DisallowConcurrentExecution
public class SchedulerJob0 extends QuartzJobBean {
    private static final AtomicInteger count = new AtomicInteger(0);

    private String k1;

    public void setK1(String k1) {
        this.k1 = k1;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("[SchedulerJob0的执行了，时间: {}, k1={}, count={}]", FULL_DATE_FORMAT.format(new Date()), k1,
            count.incrementAndGet());
    }

}
