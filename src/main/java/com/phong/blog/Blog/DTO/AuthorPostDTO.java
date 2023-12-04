package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.util.UUID;


@Data
public class AuthorPostDTO {
    private Integer page;
    private Integer limit;
    private UUID authorId;
}
