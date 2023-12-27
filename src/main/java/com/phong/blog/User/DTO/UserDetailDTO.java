package com.phong.blog.User.DTO;

import com.phong.blog.Blog.DTO.PostDTO;
import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.PostStatistic;
import com.phong.blog.Blog.Model.Reader;
import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Model.Status;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
class SocialDTO {
    private int id;
    private String name;
    private String link;
}
@Data
public class UserDetailDTO {
    private Status status;
    private Set<Role> roles;
    private String username;
    private String email;
    private AuthorDTO userInformation = new AuthorDTO();
}
