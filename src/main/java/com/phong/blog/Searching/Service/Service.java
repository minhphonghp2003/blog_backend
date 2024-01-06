package com.phong.blog.Searching.Service;

import com.phong.blog.Searching.Model.Demo;
import com.phong.blog.Searching.Repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final SearchRepository searchRepository;
    private ElasticsearchOperations elasticsearchOperations;


    public Demo createDemo(Demo demo) {
        return searchRepository.save(demo);
    }

    public Page<Demo> getAllDemo() {
        return searchRepository.findAll(PageRequest.of(0,10));
    }
}
