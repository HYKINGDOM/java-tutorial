package com.java.tutorial.project.http;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kscs
 */
@Slf4j
public class HttpClientUtils {


    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-dspAdvertiserCost-%d").build();


    private RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
    private HttpClient HTTP_CLIENT =
        HttpClient.newBuilder().executor(rulerExecutor()).version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofMillis(5000)).build();

    private static ThreadPoolTaskExecutor rulerExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(3);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix("test_execute_Http2");

        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }

    public String sendHttpAsync(String url) {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        CompletableFuture<String> result =

            HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString()).whenComplete((resp, throwable) -> {

                if (ObjectUtil.isNotEmpty(throwable)) {

                    log.error("sendHttpAsync: {}", (Object)throwable.getStackTrace());

                } else {
                    log.info("http status code: {}", resp.statusCode());
                    log.info("http body: {}", resp.body());
                }

            }).thenApply(HttpResponse::body).exceptionally(err -> {

                log.error("sendHttpAsync: {}", (Object)err.getStackTrace());

                return ExceptionUtil.getRootCauseMessage(err);
            });

        return result.join();
    }

}
