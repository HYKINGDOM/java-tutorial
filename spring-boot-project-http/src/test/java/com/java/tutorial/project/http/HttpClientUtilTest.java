package com.java.tutorial.project.http;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jdk11--HttpClient实现
 */
class HttpClientUtilTest {

    @Test
    public void testTimeout() throws IOException, InterruptedException {
        // 1.set connect timeout
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000))
            .followRedirects(HttpClient.Redirect.NORMAL).build();

        // 2.set read timeout
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://openjdk.java.net/"))
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

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/json/info"))
            .timeout(Duration.ofMillis(5009)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
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

        System.out.println(response.body());
    }

    @Test
    public void testAsyncGet() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).build();

        CompletableFuture<String> result =
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
        System.out.println(result.get());
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

        CompletableFuture<StockDto> result = HttpClient.newHttpClient()
            .sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(body -> {
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
        HttpEntity multipartEntity = MultipartEntityBuilder.create()
            .addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY)).setBoundary(multipartFormDataBoundary) // 要设置，否则阻塞
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
        HttpClient client = HttpClient.newHttpClient();
        List<String> urls = List.of("http://www.baidu.com", "http://www.alibaba.com/", "http://www.tencent.com");
        List<HttpRequest> requests = urls.stream().map(url -> HttpRequest.newBuilder(URI.create(url)))
            .map(reqBuilder -> reqBuilder.build()).collect(Collectors.toList());

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
                HttpResponse.BodyHandlers.ofString())
            .whenComplete((resp, t) -> {
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