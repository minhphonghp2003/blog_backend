package com.phong.blog.Blog.Service;

import com.phong.blog.Blog.DTO.PostCommentDTO;
import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    public List<PostCommentDTO> getPostComment(Integer postId) {
        List<Comment> comments = commentRepository.findByPost(postId);
        List<PostCommentDTO> result = new ArrayList<>();
        for (Comment c:
             comments) {
            if(c.getParentCommentId()!=null){
                continue;
            }
           List<Comment> replies =  commentRepository.findReplyByComment(c.getId());
           PostCommentDTO postCommentDTO = modelMapper.map(c,PostCommentDTO.class);
           postCommentDTO.setReplies(replies);
           result.add(postCommentDTO);
        }
        return result;
    }

    public Comment createPostComment(Comment comment) {
        return commentRepository.save(comment);
    }
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    public void updateComment(Comment comment) {
        Comment existComment = commentRepository.findById(comment.getId()).orElse(null);
        existComment.setText(comment.getText());
        commentRepository.save(existComment);
    }
}
