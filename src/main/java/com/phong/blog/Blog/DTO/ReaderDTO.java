package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ReaderDTO {
    private String name;
    private String browserName;
    private String device;
    private String ipAddress;
    private String location;
    private String os;
}
