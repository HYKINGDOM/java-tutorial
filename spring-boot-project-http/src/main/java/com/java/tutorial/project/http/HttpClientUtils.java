package com.java.tutorial.project.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kscs
 */
@Slf4j
public class HttpClientUtils {

    private ThreadFactory threadFactory =
        new ThreadFactoryBuilder().setNameFormat("thread-dspAdvertiserCost-%d").build();

    private RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
    private HttpClient HTTP_CLIENT = HttpClient.newBuilder().executor(rulerExecutor())
        .version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofMillis(5000)).build();

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

                }
            }).thenApply(HttpResponse::body).exceptionally(err -> {

                log.error("sendHttpAsync: {}", (Object)err.getStackTrace());

                return ExceptionUtil.getRootCauseMessage(err);
            });

        return result.join();
    }

    public List<String> sendBatchHttpAsync(List<String> urls) {

        List<HttpRequest> requests = urls.stream().map(url -> HttpRequest.newBuilder(URI.create(url)))
            .map(HttpRequest.Builder::build).collect(Collectors.toList());

        List<CompletableFuture<HttpResponse<String>>> futures =
            requests.stream().map(request -> HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        futures.forEach(e -> e.whenComplete((resp, throwable) -> {
            if (ObjectUtil.isNotEmpty(throwable)) {
                log.error("sendHttpAsync: {}", (Object)throwable.getStackTrace());
            }
        }).thenApply(HttpResponse::body).exceptionally(err -> {
            log.error("sendHttpAsync: {}", (Object)err.getStackTrace());
            return ExceptionUtil.getRootCauseMessage(err);
        }));
        // CompletableFuture.allOf(futures.toArray(CompletableFuture<?>[]::new)).join();

        return futures.stream().map(CompletableFuture::join).map(HttpResponse::body).collect(Collectors.toList());

    }
}
