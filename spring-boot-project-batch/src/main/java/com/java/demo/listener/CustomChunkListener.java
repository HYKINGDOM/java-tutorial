package com.java.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * ChunkListener是在数据事物发生的两端被触发。chunk的配置决定了处理多少项记录才进行一次事物提交，ChunkListener的作用就是对一次事物开始之后或事物提交之后进行拦截
 *
 * @author meta
 */
@Slf4j
public class CustomChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        // 事物开始之后，ItemReader调用之前
    }

    @Override
    public void afterChunk(ChunkContext context) {
        // 事物提交之后
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        // 事物回滚之后
    }
}
