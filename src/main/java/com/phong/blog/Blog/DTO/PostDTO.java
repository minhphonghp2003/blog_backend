package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Comment;
import lombok.Data;

import javax.naming.Name;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
class NameDTO {
    private String name;
}

@Data
class IdDTO {
    private Integer postId;
    private Integer commenterId;
}



@Data
public class PostDTO {
    private Integer id;
    private String imageLink;
    private String title;
    private Integer likeCount;
    private Integer shareCount;
    private Integer viewCount;
    private String foreword;
    private NameDTO topic;
    private Set<NameDTO> tags;
    private NameDTO readingList;
    private List<IdDTO> comments;
    private Instant createdAt;
    private Instant updatedAt;
}
