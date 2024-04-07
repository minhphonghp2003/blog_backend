package com.phong.blog.Searching.Service;

import com.phong.blog.Searching.DTO.PostSearchDTO;
import com.phong.blog.Searching.Model.SearchPost;
import com.phong.blog.Searching.Repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;

    public SearchPost createSearchPost(SearchPost post) {
        return searchRepository.save(post);
    }
    public void deleteSearchPostById(Integer id) {
        searchRepository.deleteById(id);
    }
    public Page<SearchPost> getAllSearchPost() {
        return searchRepository.findAll(PageRequest.of(0,10));
    }

    public Page<SearchPost> searchPosts(PostSearchDTO postSearchDTO){
        Pageable pageable = PageRequest.of(postSearchDTO.getPage(),postSearchDTO.getLimit());
        return searchRepository.searchPost(pageable,postSearchDTO.getQuery());
    }
}
