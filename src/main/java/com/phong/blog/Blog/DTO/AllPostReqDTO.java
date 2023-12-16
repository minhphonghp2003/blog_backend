package com.phong.blog.Blog.DTO;

import lombok.Data;

enum SortBy{
    updatedAt,
    likeCount,
    viewCount,
    shareCount

}
@Data
public class AllPostReqDTO {
    private SortBy sortBy;
    private int page;
    private int limit;
}
