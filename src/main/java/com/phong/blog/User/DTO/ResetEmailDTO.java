package com.phong.blog.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetEmailDTO {
    private String to;
    private String token;
    private String server;
}
