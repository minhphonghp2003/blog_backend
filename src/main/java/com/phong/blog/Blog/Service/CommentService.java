package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.Commenter;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.PostCommentId;
import com.phong.blog.Blog.Repository.CommentRepository;
import com.phong.blog.Blog.Repository.CommenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommenterRepository commenterRepository;
    private final CommentRepository commentRepository;
    public Commenter createCommenter(Commenter commenter){
        return commenterRepository.save(commenter);
    }
    public Comment createComment(Post post, Commenter commenter,String content){
        PostCommentId postCommentId = new PostCommentId(post.getId(),commenter.getId());
        Comment comment = new Comment();
        comment.setCommenter(commenter);
        comment.setPost(post);
        comment.setId(postCommentId);
        comment.setContent(content);
        return commentRepository.save(comment);
    }
}
