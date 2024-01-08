package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Tag;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Set;
@Data
class TopicNameDTO{
    private String name;
}

@Data
public class NewPostDTO {
    private TopicNameDTO topic;
    private int id;
    private AuthorDTO author;
    private Instant updatedAt;
    private String title;
    private String foreword;
    private String imageLink;
    private Set<Integer> tagIds;
    private int readingListId;
    private int topicId;
    private String postLink;
}
