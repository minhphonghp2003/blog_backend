package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Repository.CommentRepository;
import com.phong.blog.Blog.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Post findById(int id){
        return postRepository.findById(id);
    }
    public List<Comment> getComments(int postId){
       return postRepository.findById(postId).getComments() ;
    }

}
