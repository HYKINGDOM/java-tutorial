package com.java.demo.listener;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).start(step1()).next(step2()).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<User, User>chunk(10000).reader(reader(null)).processor(processor())
            .writer(writer(null)).taskExecutor(taskExecutor()).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").<User, User>chunk(10000).reader(reader2(null)).processor(processor())
            .writer(writer2(null)).taskExecutor(taskExecutor()).build();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    @StepScope
    public JdbcCursorItemReader<User> reader(@Value("#{stepExecutionContext['fromId']}") Long fromId) {
        JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM user WHERE id > ? AND id <= ?");
        reader.setPreparedStatementSetter(new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, fromId);
                ps.setLong(2, fromId + 10000);
            }
        });
        reader.setRowMapper(new BeanPropertyRowMapper<>(User.class));
        return reader;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @StepScope
    public JdbcCursorItemReader<User> reader2(@Value("#{stepExecutionContext['fromId']}") Long fromId) {
        JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM user WHERE id > ?");
        reader.setPreparedStatementSetter(new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, fromId + 10000);
            }
        });
        reader.setRowMapper(new BeanPropertyRowMapper<>(User.class));
        return reader;
    }

    @Bean
    public ItemProcessor<User, User> processor() {
        return new ItemProcessor() {
            @Override
            public Object process(Object o) throws Exception {
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<User> writer(DataSource dataSource) {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO user(name, age) VALUES(?, ?)");
        //writer.setItemPreparedStatementSetter(new UserPreparedStatementSetter());
        return writer;
    }

    @Bean
    public ItemWriter<User> writer2(DataSource dataSource) {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("UPDATE user SET age = ? WHERE name = ?");
        //writer.setItemPreparedStatementSetter(new UserUpdatePreparedStatementSetter());
        return writer;
    }

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(30);
        executor.initialize();
        return executor;
    }

    @Bean
    public StepExecutionListener stepExecutionListener() {
        return new StepExecutionListenerSupport() {
            @Override
            public ExitStatus afterStep(StepExecution stepExecution) {
                if (stepExecution.getSkipCount() > 0) {
                    return new ExitStatus("COMPLETED_WITH_SKIPS");
                } else {
                    return ExitStatus.COMPLETED;
                }
            }
        };
    }
}