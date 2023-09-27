package com.java.tutorial.project.http;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class HttpClientTest {

    @Test
    public void test_http_client() throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();

        HttpResponse.BodySubscriber<String> subscriber =
            HttpResponse.BodySubscribers.fromSubscriber(new StringSubscriber(), StringSubscriber::getBody);

        client.sendAsync(httpRequest, responseInfo -> subscriber).thenApply(HttpResponse::body)
            .thenAccept(System.out::println).join();
    }

    static class StringSubscriber implements Flow.Subscriber<List<ByteBuffer>> {
        Flow.Subscription subscription;
        List<ByteBuffer> response = new ArrayList<>();

        @Getter
        String body;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(List<ByteBuffer> item) {
            response.addAll(item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println(throwable);
        }

        @Override
        public void onComplete() {
            byte[] data = new byte[response.stream().mapToInt(ByteBuffer::remaining).sum()];
            int offset = 0;
            for (ByteBuffer buffer : response) {
                int remain = buffer.remaining();
                buffer.get(data, offset, remain);
                offset += remain;
            }
            body = new String(data);
        }
    }
}
