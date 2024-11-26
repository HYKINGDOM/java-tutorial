package com.java.tutorial.project.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TextProperty;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.PutMappingRequest;
import com.java.tutorial.project.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author meta
 */
@Slf4j
@Repository
public class BookRepository {

    public static final String BOOKS = "books";

    private final ElasticsearchClient elasticsearchClient;

    @Autowired
    public BookRepository(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public void createIndexAndMapping() throws IOException {
        // 检查索引是否存在
        try {
            GetIndexResponse getIndexResponse =
                elasticsearchClient.indices().get(GetIndexRequest.of(g -> g.index(BOOKS)));

            if (!ObjectUtils.isEmpty(getIndexResponse)) {
                log.info("索引 {} 已经存在，无需创建。", BOOKS);
                return;
            }
        } catch (Exception e) {
            // 索引不存在，继续创建
            log.info("索引 {} 不存在，准备创建。", BOOKS);
        }

        // 创建索引
        elasticsearchClient.indices().create(CreateIndexRequest.of(c -> c.index(BOOKS)));

        // 定义映射
        Map<String, Property> propertyMap = new HashMap<>(3);
        propertyMap.put("title", Property.of(p -> p.text(TextProperty.of(t -> t))));
        propertyMap.put("author", Property.of(p -> p.text(TextProperty.of(t -> t))));
        propertyMap.put("description", Property.of(p -> p.text(TextProperty.of(t -> t))));

        PutMappingRequest putMappingRequest = PutMappingRequest.of(pm -> pm.index(BOOKS).properties(propertyMap));

        // 应用映射
        elasticsearchClient.indices().putMapping(putMappingRequest);

        log.info("索引 {} 和映射已创建。", BOOKS);
    }

    public Book createBook(Book book) throws IOException {
        IndexResponse indexResponse = elasticsearchClient.index(i -> i.index(BOOKS).document(book).id(book.getId()));
        return book;
    }

    public Book getBook(String id) throws IOException {
        GetResponse<Book> getResponse = elasticsearchClient.get(g -> g.index(BOOKS).id(id), Book.class);
        return getResponse.source();
    }

    public List<Book> searchBooks(String query) throws IOException {
        SearchResponse<Book> searchResponse = elasticsearchClient.search(
            s -> s.index(BOOKS).query(q -> q.multiMatch(m -> m.query(query).fields("title", "author", "description"))),
            Book.class);
        return searchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    public Book updateBook(String id, Book book) throws IOException {
        UpdateResponse<Book> updateResponse =
            elasticsearchClient.update(u -> u.index(BOOKS).id(id).doc(book), Book.class);
        log.info("Book updated updateResponse: {}", updateResponse);
        return book;
    }

    public void deleteBook(String id) throws IOException {
        elasticsearchClient.delete(d -> d.index(BOOKS).id(id));
    }
}


