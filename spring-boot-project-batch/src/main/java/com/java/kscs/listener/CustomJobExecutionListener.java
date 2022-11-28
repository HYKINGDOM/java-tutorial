package com.java.kscs.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.item.ExecutionContext;

/**
 * 当任务执行完毕或开始执行时，需要执行一些处理工作。这个时候可以使用JobExecutionListener
 * <p>
 * 通过 ExecutionContext executionContext = jobExecution.getExecutionContext(); 设置的值，在job的一整个生命周期中都可以获取到
 *
 * @author hy
 */
@Slf4j
public class CustomJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        JobInstance jobInstance = jobExecution.getJobInstance();
        String jobName = jobInstance.getJobName();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        executionContext.put("global-key", "job的一整个生命周期都可以获取到.");
        log.info("job:[{}]开始执行了", jobName);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // afterJob方法无论批处理任务成功还是失败都会被执行
        JobInstance jobInstance = jobExecution.getJobInstance();
        String jobName = jobInstance.getJobName();
        BatchStatus status = jobExecution.getStatus();
        String globalValue = jobExecution.getExecutionContext().getString("global-key");
        log.info("job:[{}]结束执行了,执行状态:[{}], global-key:对应都值为:[{}]", jobName, status, globalValue);
    }
}
