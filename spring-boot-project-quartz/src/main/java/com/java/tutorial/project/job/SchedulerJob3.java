package com.java.tutorial.project.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static com.java.tutorial.project.common.Constant.FULL_DATE_FORMAT;

@Slf4j
public class SchedulerJob3 implements Job {


    private final AtomicInteger count = new AtomicInteger();

    private String k1;

    public void setK1(String k1) {
        this.k1 = k1;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("[SchedulerJob3的执行了，时间: {}, k1={}, count={}]", FULL_DATE_FORMAT.format(new Date()), k1,
            count.incrementAndGet());
    }
}