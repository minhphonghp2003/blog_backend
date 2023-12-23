package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.*;
import com.phong.blog.Blog.Model.BlogStatistic;
import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Service.PostService;
import com.phong.blog.Utils.Helper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    private final ModelMapper modelMapper;
    private final PostService postService;
    private final Helper helper;

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

//    TODO: fetch statistic first then fetch post
    @GetMapping("/all")
    public Page<PostDTO> getAllPost( AllPostReqDTO allPostReqDTO){
        Page<Post> postPage = postService.getAllPost(allPostReqDTO);

       Page<PostDTO> postDTOS =   postPage.map(post -> {
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            modelMapper.map(postService.getPostStatistic(post.getId()),postDTO);
            return postDTO;
        });
       helper.sortPostsBy( postDTOS.getContent(),String.valueOf(allPostReqDTO.getSortBy()));
       return postDTOS;
    }
    @PutMapping("/statistic")
    public void updatePostStatistic(@RequestBody BlogStatistic postStatisticDTO){
        postService.updatePostStatistic(postStatisticDTO);
    }
    @PutMapping("/statistic/like")
    public void updatePostLike(@RequestBody PostLikeDTO postLikeDTO){
        postService.updatePostLike(postLikeDTO);
    }

    @PutMapping("/statistic/comment")
    public void updatePostLike(@RequestBody Comment comment){
        postService.updatePostComment(comment);
    }

    @GetMapping("/statistic")
    public BlogStatistic getPostStatistic(Integer id){
        return postService.getPostStatistic(id);
    }

    @PostMapping("/statistic")
    public BlogStatistic createPostStatistic(@RequestBody BlogStatistic postStatisticDTO){
        return postService.createPostStatistic(postStatisticDTO);
    }
}
