package com.java.tutorial.project.controller;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.junit.jupiter.api.Test;

class TestControllerTest {

    @Test
    public void test_demo_01() throws IOException, InterruptedException {
        HttpClient client =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(20))
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("admin", "password".toCharArray());
                    }
                }).proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80))).build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1/user/1?userName=admin"))
            .header("userHeader", "myHeader").header("cookie", "JSESSIONID=111").timeout(Duration.ofSeconds(10)).GET()
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        response.headers().map().forEach((k, v) -> {
            System.out.println(k + "-->" + v);
        });
        System.out.println("同步请求:" + response.body());

        CompletableFuture<Void> voidCompletableFuture =
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
                .exceptionally(err -> {
                    err.printStackTrace();
                    return "error" + err.getMessage();
                }).thenAccept(x -> System.out.println("异步结果:" + x));
        voidCompletableFuture.join();

    }

    @Test
    public void test_demo_02() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =
            HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1/user")).timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString("id=1&userName=赵云")).build();
        String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println("阻塞请求结果:" + body);

    }

    /**
     * { "id": 1, "userName": "赵云" }
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void test_demo_03() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =
            HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1/user/update")).timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json;charset=utf-8").POST(HttpRequest.BodyPublishers.ofString(" "))
                .build();
        String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println("阻塞请求结果:" + body);
    }

    @Test
    public void test_demo_04() throws IOException, InterruptedException {

        // 要上传的图片
        Path sourcePath = Path.of("D:\\timg.jpg");
        String multipartFormDataBoundary = "Java11HttpClientFormBoundary";
        // 构建MultipartEntity
        HttpEntity httpEntity =
            MultipartEntityBuilder.create().addBinaryBody("img", sourcePath.toFile(), ContentType.IMAGE_JPEG, "1.png")
                .setBoundary(multipartFormDataBoundary).build();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =
            HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1/upload?userId=1")).timeout(Duration.ofMinutes(2))
                .header("Content-Type", "multipart/form-data; boundary=" + multipartFormDataBoundary)
                .POST(HttpRequest.BodyPublishers.ofInputStream(() -> {
                    try {

                        return httpEntity.getContent();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })).build();
        String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println("阻塞请求结果:" + body);

    }

    @Test
    public void test_demo_05() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        List<HttpRequest> requests = IntStream.range(1, 10).mapToObj(x -> String.format("http://127.0.0.1/user/%s", x))
            .map(url -> HttpRequest.newBuilder(URI.create(url))).map(HttpRequest.Builder::build)
            .collect(Collectors.toList());

        List<CompletableFuture<HttpResponse<String>>> futures =
            requests.stream().map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());
        futures.forEach(e -> e.whenComplete((resp, err) -> {
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
    public void test_demo_06() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        WebSocket webSocket =
            client.newWebSocketBuilder().buildAsync(URI.create("ws://localhost:8080/hello"), new WebSocket.Listener() {
                @Override
                public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                    webSocket.request(1);
                    return CompletableFuture.completedFuture(data).thenAccept(System.out::println);
                }
            }).join();
        webSocket.sendText("hello ", false);
        webSocket.sendText("world ", true);
        TimeUnit.SECONDS.sleep(10);
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").join();
    }

}