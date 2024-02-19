package com.java.demo.config;

import com.java.demo.dto.BlogInfo;
import com.java.demo.validate.MyBeanValidator;
import com.java.demo.process.MyItemProcessor;
import com.java.demo.listener.MyJobListener;
import com.java.demo.listener.MyReadListener;
import com.java.demo.listener.MyWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MyBatchConfig {

    /**
     * JobRepository定义：Job的注册容器以及和数据库打交道（事务管理等）
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository myJobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
        throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * jobLauncher定义：job的启动器,绑定相关的jobRepository
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher myJobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
        throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        // 设置jobRepository
        jobLauncher.setJobRepository(myJobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    /**
     * 定义job
     *
     * @param jobs
     * @param myStep
     * @return
     */
    @Bean
    public Job myJob(JobBuilderFactory jobs, Step myStep) {
        return jobs.get("myJob").incrementer(new RunIdIncrementer()).flow(myStep).end().listener(myJobListener())
            .build();
    }

    /**
     * 注册job监听器
     *
     * @return
     */
    @Bean
    public MyJobListener myJobListener() {
        return new MyJobListener();
    }

    /**
     * ItemReader定义：读取文件数据+entirty实体类映射
     *
     * @return
     */
    @Bean
    public ItemReader<BlogInfo> reader() {
        // 使用FlatFileItemReader去读cvs文件，一行即一条数据
        FlatFileItemReader<BlogInfo> reader = new FlatFileItemReader<>();
        // 设置文件处在路径
        reader.setResource(new ClassPathResource("static/bloginfo.csv"));
        // entity与csv数据做映射
        reader.setLineMapper(new DefaultLineMapper<BlogInfo>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("blogAuthor", "blogUrl", "blogTitle", "blogItem");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<BlogInfo>() {
                    {
                        setTargetType(BlogInfo.class);
                    }
                });
            }
        });
        return reader;
    }

    /**
     * 注册ItemProcessor: 处理数据+校验数据
     *
     * @return
     */
    @Bean
    public ItemProcessor<BlogInfo, BlogInfo> processor() {
        MyItemProcessor myItemProcessor = new MyItemProcessor();
        // 设置校验器
        myItemProcessor.setValidator(myBeanValidator());
        return myItemProcessor;
    }

    /**
     * 注册校验器
     *
     * @return
     */
    @Bean
    public MyBeanValidator<BlogInfo> myBeanValidator() {
        return new MyBeanValidator<>();
    }

    /**
     * ItemWriter定义：指定datasource，设置批量插入sql语句，写入数据库
     *
     * @param dataSource
     * @return
     */
    @Bean
    public ItemWriter<BlogInfo> writer(DataSource dataSource) {
        // 使用jdbcBcatchItemWrite写数据到数据库中
        JdbcBatchItemWriter<BlogInfo> writer = new JdbcBatchItemWriter<>();
        // 设置有参数的sql语句
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BlogInfo>());
        String sql =
            "insert into bloginfo " + " (blogAuthor,blogUrl,blogTitle,blogItem) " + " values(:blogAuthor,:blogUrl,:blogTitle,:blogItem)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    /**
     * step定义： 包括 ItemReader 读取 ItemProcessor  处理 ItemWriter 输出
     *
     * @param stepBuilderFactory
     * @param reader
     * @param writer
     * @param processor
     * @return
     */

    @Bean
    public Step myStep(StepBuilderFactory stepBuilderFactory, ItemReader<BlogInfo> reader, ItemWriter<BlogInfo> writer,
        ItemProcessor<BlogInfo, BlogInfo> processor) {
        return stepBuilderFactory.get("myStep")
            // Chunk的机制(即每次读取一条数据，再处理一条数据，累积到一定数量后再一次性交给writer进行写入操作)
            .<BlogInfo, BlogInfo>chunk(65000).reader(reader).faultTolerant().retryLimit(3).retry(Exception.class)
            .skip(Exception.class).skipLimit(2).listener(new MyReadListener()).processor(processor).writer(writer)
            .faultTolerant().skip(Exception.class).skipLimit(2).listener(new MyWriteListener()).build();
    }
}
