package com.java.demo.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * 此类的主要母的是打印 在 AssemblyReadCsvPathListener 类中获取到需要导入的文件路径 需要从 ExecutionContext 中获取值
 *
 * @author meta
 */
@Slf4j
@Component
@StepScope
public class PrintImportFilePathTaskLet implements Tasklet {

    @Value("#{jobExecutionContext['importPath']}")
    private String importFilePath;

    @Value("#{jobParameters['importDate']}")
    private String importDate;

    @Override
    public RepeatStatus execute(@Nonnull StepContribution contribution, @Nonnull ChunkContext chunkContext) {

        log.info("从job parameter 中获取到的 importDate:[{}],从 jobExecutionContext 中获取的 importPath:[{}]",
            importDate, importFilePath);

        return RepeatStatus.FINISHED;
    }
}
