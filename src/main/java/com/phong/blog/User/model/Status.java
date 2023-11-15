package com.phong.blog.User.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Enumerated(EnumType.STRING)
    private EStatus name;
//    @OneToMany(mappedBy = "status")
//    private Set<User> user;
}
