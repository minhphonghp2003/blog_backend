package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Tag;
import lombok.Data;

import java.util.List;
import java.util.Set;



@Data
public class NewPostDTO {
    private String title;
    private String foreword;
    private String imageLink;
    private Set<Integer> tagIds;
    private int readingListId;
    private int topicId;
    private String postLink;
}
