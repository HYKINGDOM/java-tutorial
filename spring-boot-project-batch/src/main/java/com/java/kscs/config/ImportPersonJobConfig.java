package com.java.kscs.config;

import cn.hutool.core.util.StrUtil;
import com.java.kscs.dto.Address;
import com.java.kscs.listener.AssemblyReadCsvPathListener;
import com.java.kscs.listener.CustomStepExecutionListener;
import com.java.kscs.mapper.ReoportFieldSetMapper;
import com.java.kscs.process.AddressItemProcessor;
import com.java.kscs.repository.AddressRepository;
import com.java.kscs.tasklet.PrintImportFilePathTaskLet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Optional;

/**
 * @author hy
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class ImportPersonJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PrintImportFilePathTaskLet printImportFilePathTaskLet;
    private final ItemReader<Address> readCsvItemReader;


    private final AddressRepository addressRepository;

    @Bean
    public Job importPersonJob() {
        JobBuilder jobBuilder = jobBuilderFactory.get("import-address-job");
        log.info("importPersonJob start");
        // 获取一个job builder, jobName可以是不存在的
        return jobBuilder
                // 添加job execution 监听器
                .listener(new AssemblyReadCsvPathListener())
                // 打印 job parameters 和 ExecutionContext 中的值
                .start(printParametersAndContextVariables())
                // 读取csv的数据并处理
                .next(handleCsvFileStep()).build();
    }

    /**
     * 读取数据 注意：此处需要返回 FlatFileItemReader类型，而不要返回ItemReader 否则可能报如下异常 Reader must be open before it can be read
     *
     * @param importPath 文件路径
     * @return reader
     */
    @Bean
    @StepScope
    public FlatFileItemReader<Address> readCsvItemReader(
            @Value("#{jobExecutionContext['importPath']}") String importPath) {

        // 读取数据
        return new FlatFileItemReaderBuilder<Address>()
                .name("read-csv-file")
                .resource(new ClassPathResource(importPath))
                .delimited()
                .delimiter(",")
                .names("address_id", "address", "address2", "district", "city_id", "postal_code", "phone", "last_update")
                .linesToSkip(1)
                .fieldSetMapper(new ReoportFieldSetMapper())
                .build();
    }

    @Bean
    public Step handleCsvFileStep() {
        // 每读取一条数据，交给这个处理
        ItemProcessor<Address, Address> processor = item -> {
            if (StrUtil.isEmpty(item.getCityId())) {
                item.setCityId("0");
            }
            log.info("Address Item Processor :{}", item);
            return item;
        };

        // 读取到了 chunk 大小的数据后，开始执行写入
        ItemWriter<Address> itemWriter = items -> {
            for (Address item : items) {
                log.info("开始写入数据 : {}", item);
                addressRepository.save(item);
            }
        };


        return stepBuilderFactory.get("handle-csv-file")
                // 每读取2条数据，执行一次write，当每read一条数据后，都会执行process
                .<Address, Address>chunk(2)
                // 读取数据
                .reader(readCsvItemReader)
                // 读取一条数据就开始处理
                .processor(processor)
                // 当读取的数据的数量到达 chunk 时，调用该方法进行处理
                .writer(itemWriter)
                .build();
    }

    /**
     * 打印 job parameters 和 ExecutionContext 中的值
     * <p>
     * TaskletStep是一个非常简单的接口，仅有一个方法——execute。 TaskletStep会反复的调用这个方法直到获取一个RepeatStatus.FINISHED返回或者抛出一个异常。
     * 所有的Tasklet调用都会包装在一个事物中。
     *
     * @return Step
     */
    private Step printParametersAndContextVariables() {
        return stepBuilderFactory.get("print-context-params")
                .tasklet(printImportFilePathTaskLet)
                // 当job重启时，如果达到了3此，则该step不在执行
                .startLimit(3)
                // 当job重启时，如果该step的是已经处理完成即COMPLETED状态时，下方给false表示该step不在重启，即不在执行
                .allowStartIfComplete(false)
                // 添加 step 监听
                .listener(new CustomStepExecutionListener()).build();
    }
}

