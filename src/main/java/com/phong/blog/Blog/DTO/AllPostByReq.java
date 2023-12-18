package com.phong.blog.Blog.DTO;

import lombok.Data;


@Data
public class AllPostByReq {
    private int id;
    private GetBy getBy;
    private SortBy sortBy;
    private int page;
    private int limit;
}
