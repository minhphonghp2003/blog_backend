package com.phong.blog.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionDTO {
    private String actionName;
    private String roleName;
    private Integer actionId;
    private Integer roleId;

}
