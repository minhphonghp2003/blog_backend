package com.phong.blog.User.DTO;

import com.phong.blog.User.model.Role;
import com.phong.blog.User.model.Status;
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
}
