package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.Reader;
import lombok.Data;

import java.time.Instant;
import java.util.*;


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
class SocialDTO{
    private int id;
    private String name ;
    private String link;
}


@Data
class AuthorDTO{
    private List<SocialDTO> socials;
    private UUID id;
    private String fullName;
    private String bio;
    private String avatar;
}
@Data
class LikeReaderDTO{
    private String name;
    private UUID id;
}

@Data
public class PostDTO {
    private Integer id;
    private String imageLink;
    private String postLink;
    private String title;
    private List<LikeReaderDTO> likeReader;
    private Integer shareCount;
    private Integer viewCount;
    private String foreword;
    private List<Comment> comments;
    private NameDTO topic;
    private Set<NameDTO> tags;
    private NameDTO readingList;
    private AuthorDTO author;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer nextId;
    private String nextImageLink;
    private String nextTitle;
}
