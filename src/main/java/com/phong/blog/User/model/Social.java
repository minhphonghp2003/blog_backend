package com.phong.blog.User.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "user_social")
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String link;
    private String name;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", link:'" + link + '\'' +
                ", name:'" + name + '\'' +
                '}';
    }
}
