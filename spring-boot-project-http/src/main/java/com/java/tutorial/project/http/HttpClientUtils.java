package com.java.tutorial.project.http;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

/**
 * @author hy
 */
@Slf4j
public class HttpClientUtils {

    private final static String THREAD_NAME = "thread-execute-Http-task-%d";

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat(THREAD_NAME).build();

    private static final RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.DiscardPolicy();
    private final HttpClient httpClient =
        HttpClient.newBuilder().executor(threadExecutor()).version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofMillis(5000)).build();

    private static ThreadPoolTaskExecutor threadExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(3);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix(THREAD_NAME);
        executor.setThreadFactory(THREAD_FACTORY);
        executor.setRejectedExecutionHandler(HANDLER);
        executor.initialize();
        return executor;
    }

    public String sendHttpAsync(String url) {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        CompletableFuture<String> result =

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).whenComplete((resp, throwable) -> {

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

        List<HttpRequest> requests =
            urls.stream().map(url -> HttpRequest.newBuilder(URI.create(url))).map(HttpRequest.Builder::build)
                .collect(Collectors.toList());

        List<CompletableFuture<HttpResponse<String>>> futures =
            requests.stream().map(request -> httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
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

    /**
     * 视频下载
     *
     * @param url
     * @return
     */
    public HttpResponse<InputStream> downLoadVideo(String url) {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() != 200) {
                String errorMessage = new BufferedReader(new InputStreamReader(response.body())).lines()
                    .collect(Collectors.joining("\n"));
                throw new IOException("Failed to download video: " + response.statusCode() + "\n" + errorMessage);
            }

            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
