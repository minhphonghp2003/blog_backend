package com.phong.blog.Blog.DTO;

import lombok.Data;

enum  GetBy{
    topic,
    readinglist
}
@Data
public class AllPostByReq {
    private int id;
    private GetBy getBy;
    private SortBy sortBy;
    private int page;
    private int limit;
}
