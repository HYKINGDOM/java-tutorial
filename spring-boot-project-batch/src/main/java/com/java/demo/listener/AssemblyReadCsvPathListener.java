package com.java.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ExecutionContext;

/**
 * 在此监听器中，获取到具体的需要读取的文件路径，并保存到 ExecutionContext
 *
 * @author hy
 */
@Slf4j
public class AssemblyReadCsvPathListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        JobParameters jobParameters = jobExecution.getJobParameters();
        String importDate = jobParameters.getString("importDate");
        log.info("从 job parameter 中获取的 importDate 参数的值为:[{}]", importDate);
        String readCsvPath = "data/address.csv";
        log.info("根据日期组装需要读取的csv路径为:[{}],此处排除日期，直接写一个死的路径", readCsvPath);
        executionContext.putString("importPath", readCsvPath);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(jobExecution.getJobConfigurationName());
    }
}
