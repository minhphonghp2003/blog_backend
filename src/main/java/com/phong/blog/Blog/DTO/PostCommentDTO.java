package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDTO {
    private String id;
    private Integer postId;
    private String userId;
    private String fullName;
    private String text;
    private List<Comment> replies = new ArrayList<>();

}
