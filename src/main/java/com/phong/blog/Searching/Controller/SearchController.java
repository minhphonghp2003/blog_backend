package com.phong.blog.Searching.Controller;

import com.phong.blog.Searching.DTO.PostSearchDTO;
import com.phong.blog.Searching.Model.Demo;
import com.phong.blog.Searching.Model.SearchPost;
import com.phong.blog.Searching.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {
    private final SearchService searchService;

    @DeleteMapping("/post")
    public void delete(@RequestBody Integer id){
        searchService.deleteSearchPostById(id);
    }
    @PostMapping("/post")
    public SearchPost createDemo(@RequestBody SearchPost searchPost){
        return searchService.createSearchPost(searchPost);
    }
    @GetMapping("/post/all")
    public Page<SearchPost> getAllDemo(){
        return searchService.getAllSearchPost();
    }

    @GetMapping("/post")
    public Page<SearchPost> searchPosts(@RequestBody PostSearchDTO postSearchDTO){
        return searchService.searchPosts(postSearchDTO);
    }
}
