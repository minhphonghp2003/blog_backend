package com.phong.blog.Authentication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    private String phone;
    private String bio;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    private String avatar;

    @OneToOne(mappedBy = "user")
    private UserCredential credential;
}
