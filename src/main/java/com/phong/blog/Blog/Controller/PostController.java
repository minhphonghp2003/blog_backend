package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.*;
import com.phong.blog.Blog.Model.PostStatistic;
import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    private final ModelMapper modelMapper;
    private final PostService postService;

    @Secured({"ADMIN", "AUTHOR"})
    @PostMapping("/")
    public NewPostDTO createPost(@RequestBody NewPostDTO newPostDTO) {
        return modelMapper.map(postService.createPost(newPostDTO), newPostDTO.getClass());
    }

    @Secured({"ADMIN", "AUTHOR"})
    @PutMapping("/")
    public UpdatePostDTO updatePost(@RequestBody UpdatePostDTO updatePostDTO) {
        return modelMapper.map(postService.updatePost(updatePostDTO), updatePostDTO.getClass());
    }

    @GetMapping("/")
    public PostDTO getPost(int id) {
        return postService.getPost(id);
    }


    @DeleteMapping("/")
    @Secured({"ADMIN", "AUTHOR"})
    public void deletePost(@RequestBody Integer id) {
        postService.deletePost(id);
    }

    @GetMapping("/all")
    public Page<PostDTO> getAllPost(AllPostReqDTO allPostReqDTO) {
        Page<Post> postPage = postService.getAllPost(allPostReqDTO);

        return postPage.map(post -> {
            return modelMapper.map(post, PostDTO.class);
        });
    }

    @PutMapping("/statistic")
    public void updatePostStatistic(@RequestBody PostStatistic postStatisticDTO) {
        postService.updatePostStatistic(postStatisticDTO);
    }

    @PutMapping("/statistic/like")
    public void updatePostLike(@RequestBody PostLikeDTO postLikeDTO) {
        postService.updatePostLike(postLikeDTO);
    }

    @PutMapping("/statistic/comment")
    public void updatePostLike(@RequestBody Comment comment) {
        postService.updatePostComment(comment);
    }
}
