package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Comment;
import lombok.Data;

@Data
public class PostCommentDTO {
    private Comment comment;
    private Integer postId;

}
