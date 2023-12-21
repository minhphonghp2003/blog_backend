package com.phong.blog.Blog.DTO;

import lombok.Data;

@Data
public class PostStatisticDTO {
    private Integer id;
    private Integer likeCount;
    private Integer viewCount;
    private Integer shareCount;
}
