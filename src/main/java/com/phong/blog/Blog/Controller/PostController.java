package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.DTO.*;
import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    private final ModelMapper modelMapper;
    private final PostService postService;

    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam Integer postId) {
        return postService.getComments(postId);
    }

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

    @GetMapping("/author")
    public Page<PostDTO> authorPost(AuthorPostDTO authorPostDTO) {
        Page<Post> postPage = postService.getPostOfAuthor(authorPostDTO);
        return postPage.map(post -> {
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            return postDTO;
        });
    }

    @GetMapping("/")
    public PostDTO getPost(int id) {
        Post post = postService.getPost(id);
        return modelMapper.map(post, PostDTO.class);
    }


    @DeleteMapping("/")
    @Secured({"ADMIN", "AUTHOR"})
    public void deletePost(@RequestBody Integer id) {
        postService.deletePost(id);
    }

    @GetMapping("/all")
    public Page<PostDTO> getAllPost( AllPostReqDTO allPostReqDTO){
        Page<Post> postPage = postService.getAllPost(allPostReqDTO);
        return  postPage.map(post -> {
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            return postDTO;
        });
    }
}
