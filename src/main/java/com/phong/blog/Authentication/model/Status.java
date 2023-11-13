package com.phong.blog.Authentication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
//    @OneToMany(mappedBy = "status")
//    private Set<User> user;
}
