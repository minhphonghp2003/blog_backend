package com.phong.blog.User.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_login_data")
public class UserCredential {
    @Id
    private int id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String hashedPassword;
    @Column(name = "user_name")
    private String username;
    private String email;
    private String passRecvToken;

}
