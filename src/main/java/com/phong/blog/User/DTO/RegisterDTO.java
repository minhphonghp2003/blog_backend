package com.phong.blog.User.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterDTO {
    private String fullName;
    private Set<String> roles;
    private String status;
    private String phone;
    private String username;
    private String password;
    private String email;
}
