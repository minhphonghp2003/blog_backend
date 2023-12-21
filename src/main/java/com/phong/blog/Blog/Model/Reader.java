package com.phong.blog.Blog.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String browserName;
    private String os;
    private String device;
    private String location;
    private String ipAddress;
}
