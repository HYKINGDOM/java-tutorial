package com.java.demo.config;

import static cn.hutool.db.ds.pooled.PooledDataSource.getDataSource;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchLoggingConfiguration {

    @Bean
    public BatchConfigurer configurer(DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource) {
            @Override
            public PlatformTransactionManager getTransactionManager() {
                return new ResourcelessTransactionManager();
            }

            @Override
            public JobLauncher getJobLauncher() {
                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(getJobRepository());
                try {
                    jobLauncher.afterPropertiesSet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return jobLauncher;
            }

            @Override
            public JobRepository getJobRepository() {
                JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
                factory.setDataSource(getDataSource());
                factory.setTransactionManager(getTransactionManager());
                factory.setIsolationLevelForCreate("ISOLATION_DEFAULT");
                try {
                    factory.afterPropertiesSet();
                    return factory.getObject();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
