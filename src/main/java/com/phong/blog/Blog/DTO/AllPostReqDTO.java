package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.util.UUID;

enum SortBy{
    updatedAt,
    likeCount,
    viewCount,
    shareCount

}

enum  GetBy{
    topic,
    readinglist,
    author
}
@Data
public class AllPostReqDTO {
    private Integer id;
    private UUID uuId;
    private GetBy getBy;
    private SortBy sortBy;
    private int page;
    private int limit;
}
