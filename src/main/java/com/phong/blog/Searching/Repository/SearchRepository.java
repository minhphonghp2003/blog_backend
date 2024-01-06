package com.phong.blog.Searching.Repository;

import com.phong.blog.Searching.Model.Demo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends ElasticsearchRepository<Demo,Integer> {
    Demo save(Demo demo);
    Demo findById(String id);
}
