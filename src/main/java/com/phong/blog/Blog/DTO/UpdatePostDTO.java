package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class UpdatePostDTO {
    private int id;
    private String title;
    private String foreword;
    private Set<Integer> tagIds;
    private int readingListId;
    private int topicId;
}
