package com.phong.blog.Searching.DTO;

import lombok.Data;

@Data
public class PostSearchDTO {
    private String query;
    private Integer page;
    private Integer limit;
}
