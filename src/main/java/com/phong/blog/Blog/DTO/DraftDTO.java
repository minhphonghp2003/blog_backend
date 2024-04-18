package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.time.Instant;

@Data
public class DraftDTO {
    private Integer id;
    private String path;
    private Instant createdAt;
}
