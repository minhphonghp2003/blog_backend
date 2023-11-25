package com.phong.blog.User.DTO;

import lombok.Data;

@Data
public class UserDetailUpdateDTO {
    private String fullName;
    private String phone;
    private String bio;
    private String avatar;
    private String email;
}
