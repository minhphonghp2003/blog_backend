package com.phong.blog.Searching.Repository;

import com.phong.blog.Searching.Model.Demo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepository extends ElasticsearchRepository<Demo,Integer> {
}
