package com.java.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.TimeUnit;

/**
 * 测试并行steps
 *
 * @author hy
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class ParallelStepsJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        JobBuilder jobBuilder = jobBuilderFactory.get("job");
        log.info(jobBuilder.toString());
        return jobBuilder
                .start(splitFlow())
                .next(step4())
                .build()        // builds FlowJobBuilder instance
                .build();       // builds Job instance
    }

    @Bean
    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                // flow1 和 flow2 并行执行
                .add(flow1(), flow2())
                .build();
    }

    @Bean
    public Flow flow1() {
        FlowBuilder<SimpleFlow> simpleFlowFlowBuilder = new FlowBuilder<>("flow1");
        log.info("flow1 start");
        return simpleFlowFlowBuilder.start(step1()).next(step2()).build();
    }

    @Bean
    public Flow flow2() {
        FlowBuilder<SimpleFlow> simpleFlowFlowBuilder = new FlowBuilder<>("flow2");
        log.info("flow2 start");
        return simpleFlowFlowBuilder.start(step3()).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
            log.info("step1 start");
            TimeUnit.SECONDS.sleep(1);
            log.info("step1 end");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get("step2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step2 start");
                    TimeUnit.SECONDS.sleep(2);
                    log.info("step2 end");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet((contribution, chunkContext) -> {
            log.info("step3 start");
            TimeUnit.SECONDS.sleep(3);
            log.info("step3 end");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4").tasklet((contribution, chunkContext) -> {

            log.info("step4 start:{}, {}", contribution.toString(), chunkContext.toString());
            TimeUnit.SECONDS.sleep(4);
            log.info("step4 end");
            return RepeatStatus.FINISHED;
        }).build();
    }

    /**
     * 线程池
     * @return
     */
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor springBatch = new SimpleAsyncTaskExecutor("spring_batch");
        TraceIDUtil.getTraceId();
        return springBatch;
    }
}
