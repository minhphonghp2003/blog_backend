package com.phong.blog.Searching.Repository;

import com.phong.blog.Searching.Model.SearchPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends ElasticsearchRepository<SearchPost,Integer> {
    SearchPost save(SearchPost demo);
    SearchPost findById(String id);

    void deleteById(Integer id);

    @Query("{\"multi_match\":{ \"query\": \"?0\",\"fields\": [ \"*\"]} }")
    Page<SearchPost> searchPost(Pageable pageable, String query);
}
