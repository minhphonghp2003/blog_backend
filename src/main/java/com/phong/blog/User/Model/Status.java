package com.phong.blog.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Enumerated(EnumType.STRING)
    private EStatus name;
    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<User> user;
}
