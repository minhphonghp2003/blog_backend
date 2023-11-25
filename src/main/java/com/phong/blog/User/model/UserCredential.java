package com.phong.blog.User.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "user_login_data")
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
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
