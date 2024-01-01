package com.phong.blog.Searching.Service;

import com.phong.blog.Searching.Model.Demo;
import com.phong.blog.Searching.Repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final ElasticsearchOperations elasticsearchOperations;


    public String createDemo(Demo demo) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(demo.getId().toString())
                .withObject(demo).build();

        String documentId = elasticsearchOperations
                .index(indexQuery, IndexCoordinates.of("demo"));

        return documentId;

    }
}
