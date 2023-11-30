package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam Integer postId) {
        return postService.getComments(postId);
    }


}
