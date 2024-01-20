package com.phong.blog.Logging.DTO;

import lombok.Data;

import java.time.Instant;

@Data
public class UserActivityDTO {
    private Integer id;
    private String action;
    private Instant createdAt;
}
