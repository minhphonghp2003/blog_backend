package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class PostLikeDTO {
    private Integer postId;
    private String readerId;
}
