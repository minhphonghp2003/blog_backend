package com.phong.blog.Blog.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "reading_list")


public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String image;
    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.ACTIVE;

    @Override
    public String toString() {
        return "ReadingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String description;
    @OneToMany(mappedBy = "readingList", fetch = FetchType.EAGER)
    private List<Post> posts;
}
