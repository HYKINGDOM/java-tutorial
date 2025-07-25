package com.java.tutorial.project.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * jdk11--HttpClient实现
 *
 * @author meta
 */
class HttpClientUtilTest {

    /**
     * cpu 核心数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数量大小
     */
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    /**
     * 线程池最大容纳线程数
     */
    private static final int MAX_POOL_SIZE = CPU_COUNT * 2 + 1;
    /**
     * 阻塞队列
     */
    private static final int WORK_QUEUE = 20;
    /**
     * 线程空闲后的存活时长
     */
    private static final int KEEP_ALIVE_TIME = 30;

    @Test
    public void testTimeout() throws IOException, InterruptedException {
        // 1.set connect timeout
        HttpClient client =
            HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        // 2.set read timeout
        HttpRequest request =
            HttpRequest.newBuilder().version(HttpClient.Version.HTTP_2).uri(URI.create("http://openjdk" + ".java.net/"))
                .timeout(Duration.ofMillis(5009)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertThat(response.body()).isNotEmpty();
        System.out.println(response.body());
    }

    @Test
    public void testBasicAuth() throws IOException, InterruptedException {
        HttpClient client =
            HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("admin", "password".toCharArray());
                }
            }).build();

        HttpRequest request =
            HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/json/info")).timeout(Duration.ofMillis(5009))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

        Document document = Jsoup.parseBodyFragment(response.body());

    }

    @Test
    public void testCookies() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/json/cookie"))
            .header("Cookie", "JSESSIONID=4f994730-32d7-4e22-a18b-25667ddeb636; userId=java11")
            .timeout(Duration.ofMillis(5009)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    @Test
    public void testSyncGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());

        Document document = Jsoup.parseBodyFragment(response.body());

        System.out.println(document.body());

    }

    @Test
    public void testAsyncGet() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).build();

        CompletableFuture<String> result =
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
        System.out.println(result.get());
    }

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {

        //此处使用的是spring封装ThreadPoolExecutor之后的线程池
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //等待队列
        threadPoolTaskExecutor.setQueueCapacity(WORK_QUEUE);
        //线程前缀
        threadPoolTaskExecutor.setThreadNamePrefix("taskExecutor-");
        //线程池维护线程所允许的空闲时间,单位为秒
        threadPoolTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        // 线程池对拒绝任务(无线程可用)的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Test
    public void testAsyncGetParam() throws ExecutionException, InterruptedException, URISyntaxException {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = getThreadPoolTaskExecutor();

        List<CompletableFuture<String>> futures = new ArrayList<>(200);
        Map<String, String> params = new HashMap<>(1);

        try (HttpClient client =
                 HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).version(HttpClient.Version.HTTP_2)
                     .executor(threadPoolTaskExecutor).build()) {
            int length = 200;
            String path = "https://";

            for (int i = 0; i < length; i++) {

                params.put("customerId", String.valueOf(i));
                URI uri = buildUriWithParams(path, params);
                HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
                CompletableFuture<String> result =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
                futures.add(result);
            }

            CompletableFuture<Void> voidCompletableFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            voidCompletableFuture.join();
            for (CompletableFuture<String> future : futures) {
                System.out.println(future.get());
            }
        }
    }

    private URI buildUriWithParams(String baseUrl, Map<String, String> params) throws URISyntaxException {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (query.length() > 0) {
                query.append("&");
            }
            query.append(param.getKey()).append("=").append(param.getValue());
        }
        if (query.length() > 0) {
            return new URI(baseUrl + "?" + query);
        }
        return new URI(baseUrl);
    }

    @Test
    public void testPostForm() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.w3school.com.cn/demo/demo_form.asp"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString("name1=value1&name2=value2")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    @Test
    public void testPostJsonGetJson() throws ExecutionException, InterruptedException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        StockDto dto = new StockDto();
        dto.setName("hj");
        dto.setSymbol("hj");
        String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/json/demo"))
            .header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        CompletableFuture<StockDto> result =
            HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(body -> {
                    try {
                        return objectMapper.readValue(body, StockDto.class);
                    } catch (IOException e) {
                        return new StockDto();
                    }
                });
        System.out.println(result.get());
    }

    @Test
    public void testUploadFile() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        Path path = Path.of(getClass().getClassLoader().getResource("body.txt").toURI());
        File file = path.toFile();

        String multipartFormDataBoundary = "Java11HttpClientFormBoundary";
        HttpEntity multipartEntity =
            MultipartEntityBuilder.create().addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY))
                .setBoundary(multipartFormDataBoundary) // 要设置，否则阻塞
                .build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/file/upload"))
            .header("Content-Type", "multipart/form-data; boundary=" + multipartFormDataBoundary)
            .POST(HttpRequest.BodyPublishers.ofInputStream(() -> {
                try {
                    return multipartEntity.getContent();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            })).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    @Test
    public void testAsyncDownload() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/file/download")).build();

        CompletableFuture<Path> result =
            client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Paths.get("/tmp/body.txt")))
                .thenApply(HttpResponse::body);
        System.out.println(result.get());
    }

    @Test
    public void testConcurrentRequests() {
        HttpClient client =
            HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).executor(rulerExecutor()).build();
        List<String> urls = List.of("http://www.baidu.com", "https://juejin.cn/post/7181713105490018341",
            "https://juejin.cn/post/7160554181156306958");
        List<HttpRequest> requests =
            urls.stream().map(url -> HttpRequest.newBuilder(URI.create(url))).map(HttpRequest.Builder::build)
                .collect(Collectors.toList());

        List<CompletableFuture<HttpResponse<String>>> futures =
            requests.stream().map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());
        futures.stream().forEach(e -> e.whenComplete((resp, err) -> {
            if (err != null) {
                err.printStackTrace();
            } else {
                System.out.println(resp.body());
                System.out.println(resp.statusCode());
            }
        }));
        CompletableFuture.allOf(futures.toArray(CompletableFuture<?>[]::new)).join();
    }

    @Test
    public void testHandleException() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://twitter.com")).build();

        CompletableFuture<String> result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            // .whenComplete((resp,err) -> {
            // if(err != null){
            // err.printStackTrace();
            // }else{
            // System.out.println(resp.body());
            // System.out.println(resp.statusCode());
            // }
            // })
            .thenApply(HttpResponse::body).exceptionally(err -> {
                err.printStackTrace();
                return "fallback";
            });
        System.out.println(result.get());
    }

    @Test
    public void testHttp2() throws URISyntaxException {
        HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NEVER).version(HttpClient.Version.HTTP_2).build()
            .sendAsync(HttpRequest.newBuilder().uri(new URI("https://http2.akamai.com/demo")).GET().build(),
                HttpResponse.BodyHandlers.ofString()).whenComplete((resp, t) -> {
                if (t != null) {
                    t.printStackTrace();
                } else {
                    System.out.println(resp.version());
                    System.out.println(resp.statusCode());
                }
            }).join();
    }

    @Test
    public void test_execute_Http2() throws URISyntaxException {
        HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NEVER).executor(rulerExecutor())
            .version(HttpClient.Version.HTTP_2).build()
            .sendAsync(HttpRequest.newBuilder().uri(new URI("https://http2.akamai.com/demo")).GET().build(),
                HttpResponse.BodyHandlers.ofString()).whenComplete((resp, t) -> {
                if (t != null) {
                    t.printStackTrace();
                } else {
                    System.out.println(resp.version());
                    System.out.println(resp.statusCode());
                }
            }).join();
    }

    public ThreadPoolTaskExecutor rulerExecutor() {
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

    @Test
    public void test_execute_Http2_proxy_01() throws URISyntaxException {

        ProxySelector myProxySelector = new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                // return a list of proxies to use for the given URI
                return List.of(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www.google.com", 7890)));
            }

            /**
             * Called to indicate that a connection could not be established to a proxy/socks server. An
             * implementation of this method can temporarily remove the proxies or reorder the sequence
             * of proxies returned by {@link #select(URI)}, using the address and the IOException caught
             * when trying to connect.
             *
             * @param uri The URI that the proxy at sa failed to serve.
             * @param sa  The socket address of the proxy/SOCKS server
             * @param ioe The I/O exception thrown when the connect failed.
             * @throws IllegalArgumentException if either argument is null
             */
            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {

            }

        };

        // set the custom ProxySelector as the default for HTTP requests
        ProxySelector.setDefault(myProxySelector);

        HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NEVER).executor(rulerExecutor())
            .proxy(myProxySelector).version(HttpClient.Version.HTTP_2).build()
            .sendAsync(HttpRequest.newBuilder().uri(new URI("https://http2.akamai.com/demo")).GET().build(),
                HttpResponse.BodyHandlers.ofString()).whenComplete((resp, t) -> {
                if (t != null) {
                    t.printStackTrace();
                } else {
                    System.out.println(resp.version());
                    System.out.println(resp.statusCode());
                }
            }).join();
    }

    @Test
    public void testWebSocket() throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        WebSocket webSocket =
            client.newWebSocketBuilder().buildAsync(URI.create("ws://localhost:8080/echo"), new WebSocket.Listener() {

                @Override
                public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                    // request one more
                    webSocket.request(1);

                    // Print the message when it's available
                    return CompletableFuture.completedFuture(data).thenAccept(System.out::println);
                }
            }).join();
        webSocket.sendText("hello ", false);
        webSocket.sendText("world ", true);

        TimeUnit.SECONDS.sleep(10);
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").join();
    }
}