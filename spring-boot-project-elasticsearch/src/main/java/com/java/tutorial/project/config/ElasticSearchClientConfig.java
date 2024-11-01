package com.java.tutorial.project.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author meta
 */
@Configuration
public class ElasticSearchClientConfig {


    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // 创建 HttpHost 实例
        HttpHost host = new HttpHost("10.0.201.34", 9200, "http");

        // 创建 RestClientBuilder 实例
        RestClientBuilder builder = RestClient.builder(host);

        // 如果需要身份验证，可以配置 CredentialsProvider
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("myuser", "myuser_password"));
        builder.setHttpClientConfigCallback(
            httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        // 创建 RestClient 实例
        RestClient restClient = builder.build();

        // 创建 ElasticsearchTransport 实例
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // 创建 ElasticsearchClient 实例
        ElasticsearchClient client = new ElasticsearchClient(transport);

        return client;
    }
}
