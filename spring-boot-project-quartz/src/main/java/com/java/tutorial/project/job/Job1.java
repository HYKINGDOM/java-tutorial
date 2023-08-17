package com.java.tutorial.project.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hy
 */
@DisallowConcurrentExecution
public class Job1 extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final AtomicInteger count = new AtomicInteger();

    private String k1;

    public void setK1(String k1) {
        this.k1 = k1;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("[job1的执行了，时间: {}, k1={}, count={}]", fullDateFormat.format(new Date()), k1,
            count.incrementAndGet());
    }

}
