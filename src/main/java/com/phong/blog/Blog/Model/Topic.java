package com.phong.blog.Blog.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "topic")


public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String name;
    private String icon;
    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.PENDING;
    private String description;

    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<Post> posts;
}
