package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Comment;
import lombok.Data;

import javax.naming.Name;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
class NameDTO {
    private int id;
    private String name;
}

@Data
class IdDTO {
    private Integer postId;
    private Integer commenterId;
}

@Data
class AuthorDTO{
    private UUID id;
    private String fullName;
    private String avatar;
}


@Data
public class PostDTO {
    private Integer id;
    private String imageLink;
    private String postLink;
    private String title;
    private Integer likeCount;
    private Integer shareCount;
    private Integer viewCount;
    private String foreword;
    private NameDTO topic;
    private Set<NameDTO> tags;
    private NameDTO readingList;
    private List<IdDTO> comments;
    private AuthorDTO author;
    private Instant createdAt;
    private Instant updatedAt;
}
