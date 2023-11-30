package com.phong.blog.Blog.DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private String name;
    private String image;
    private String content;
    private int postId;
}
