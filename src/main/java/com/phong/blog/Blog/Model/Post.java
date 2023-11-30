package com.phong.blog.Blog.Model;

import com.phong.blog.User.Model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "post")
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "reading_list_id", nullable = true)
    private ReadingList readingList;
    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    private String image;
    private String title;
    private Integer likeCount;
    private Integer shareCount;
    private Integer viewCount;
    private String foreword;
}
