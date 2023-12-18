package com.phong.blog.User.DTO;

import com.phong.blog.User.Model.Social;
import com.phong.blog.User.Model.Status;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AuthorDTO {
    private UUID id;
    private String fullName;
    private String phone;
    private String bio;
    private String avatar;
    private List<SocialDTO> socials;
}
