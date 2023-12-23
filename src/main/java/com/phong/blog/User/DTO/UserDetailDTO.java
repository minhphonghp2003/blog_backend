package com.phong.blog.User.DTO;

import com.phong.blog.Blog.Model.Reader;
import com.phong.blog.Blog.Model.Tag;
import com.phong.blog.User.Model.Status;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
class SocialDTO{
    private int id;
    private String name ;
    private String link;
}

@Data
class PostDTO{
    private int id;
    private List<Reader> likeReader;
    private Integer shareCount;
    private Integer viewCount;
    private Set<Tag> tags;
}

@Data
public class UserDetailDTO {
    private UUID id;
    private String fullName;
    private String phone;
    private String bio;
    private Status status;
    private String avatar;
    private Set<String> roles;
    private List<SocialDTO> socials;
    private String username;
    private String email;
    private List<PostDTO>posts;
}
