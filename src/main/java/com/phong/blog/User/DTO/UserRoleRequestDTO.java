package com.phong.blog.User.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleRequestDTO {
    private String userId;
    private List<Integer> roleId;
}
