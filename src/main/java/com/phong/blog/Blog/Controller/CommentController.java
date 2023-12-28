package com.phong.blog.Blog.Controller;


import com.phong.blog.Blog.DTO.PostCommentDTO;
import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/post")
    public List<PostCommentDTO> getPostComment(Integer postId) {
        return commentService.getPostComment(postId);
    }

    @PutMapping("/")
    public void updateComment(@RequestBody Comment comment){
        commentService.updateComment(comment);
    }
    @PostMapping("/")
    public Comment createPostComment(@RequestBody Comment comment) {
        return commentService.createPostComment(comment);
    }

    @DeleteMapping("/")
    public void deleteComment(String commentId) {
        commentService.deleteComment(commentId);
    }
}
