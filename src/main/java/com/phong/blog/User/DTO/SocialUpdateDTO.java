package com.phong.blog.User.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class SocialUpdateDTO {
    private UUID userId;
    private String link;
    private String name;

}
