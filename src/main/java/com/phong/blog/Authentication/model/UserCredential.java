package com.phong.blog.Authentication.model;

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
    private String userName;
    private String email;
    private String passRecvToken;

}
