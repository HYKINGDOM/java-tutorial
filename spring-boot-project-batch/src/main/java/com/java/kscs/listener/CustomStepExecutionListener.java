package com.java.kscs.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * 监听step执行
 *
 * @author hy
 */
@Slf4j
public class CustomStepExecutionListener implements StepExecutionListener {
    /**
     * step 执行之前调用
     *
     * @param execution instance of {@link StepExecution}.
     */
    @Override
    public void beforeStep(StepExecution execution) {
        String stepName = execution.getStepName();
        log.info("step:[{}]开始执行了", stepName);
    }

    /**
     * step 执行之后调用
     *
     * @param execution {@link StepExecution} instance.
     * @return
     */
    @Override
    public ExitStatus afterStep(StepExecution execution) {
        log.info("step:[{}]结束执行了", execution.getStepName());
        return execution.getExitStatus();
    }
}
