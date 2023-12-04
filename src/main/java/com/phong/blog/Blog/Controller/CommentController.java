package com.phong.blog.Blog.Controller;

import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.DTO.CommentDTO;
import com.phong.blog.Blog.Model.Commenter;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Service.CommentService;
import com.phong.blog.Blog.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final ModelMapper modelMapper;

    @PostMapping("/")
    public void createComment(@RequestBody CommentDTO commentDTO){
        Commenter commenter = commentService.createCommenter(modelMapper.map(commentDTO,Commenter.class));
        Post post = postService.findById(commentDTO.getPostId());
        Comment comment = commentService.createComment(post,commenter,commentDTO.getContent());
    }
}
