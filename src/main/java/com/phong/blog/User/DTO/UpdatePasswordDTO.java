package com.phong.blog.User.DTO;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String token;
    private String password;

}
