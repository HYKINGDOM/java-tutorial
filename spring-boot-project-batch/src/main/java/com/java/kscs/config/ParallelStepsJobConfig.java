package com.java.kscs.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
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
@AllArgsConstructor
@Slf4j
public class ParallelStepsJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job").start(splitFlow()).next(step4())
                .build()        // builds FlowJobBuilder instance
                .build();       // builds Job instance
    }

    @Bean
    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("splitFlow").split(taskExecutor())
                // flow1 和 flow2 并行执行
                .add(flow1(), flow2()).build();
    }

    @Bean
    public Flow flow1() {
        return new FlowBuilder<SimpleFlow>("flow1").start(step1()).next(step2()).build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<SimpleFlow>("flow2").start(step3()).build();
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
        return stepBuilderFactory.get("step2").tasklet((contribution, chunkContext) -> {
            log.info("step2 start");
            TimeUnit.SECONDS.sleep(2);
            log.info("step2 end");
            return RepeatStatus.FINISHED;
        }).build();
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

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }
}
