package com.phong.blog.Authentication.DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    private String fullName;
    private String phone;
    private String bio;
    private String userName;
    private String password;
    private String email;
}
