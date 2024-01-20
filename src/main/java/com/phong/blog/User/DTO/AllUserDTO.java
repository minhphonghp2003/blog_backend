package com.phong.blog.User.DTO;

import com.phong.blog.User.Model.EStatus;
import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Model.Status;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
class CredDTO{
    private String username;
    private String email;
}
@Data
public class AllUserDTO {
    private UUID id;
    private String name;
    private List<Role> roles;
    private String phone;
    private Status status;
    private String email;
    private CredDTO credential;
}
