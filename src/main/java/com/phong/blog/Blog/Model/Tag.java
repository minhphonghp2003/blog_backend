package com.phong.blog.Blog.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.PENDING;
//    @ManyToMany(mappedBy = "tags")
//    private Set<Post> posts;
}
