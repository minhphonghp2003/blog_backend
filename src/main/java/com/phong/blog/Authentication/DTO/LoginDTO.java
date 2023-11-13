package com.phong.blog.Authentication.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private String userName;
    private String email;
    private String fullName;
}
