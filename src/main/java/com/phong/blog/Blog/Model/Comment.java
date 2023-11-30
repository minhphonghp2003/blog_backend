package com.phong.blog.Blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @EmbeddedId
    @JsonIgnore
    private PostCommentId id;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    @MapsId("postId")
    @JsonIgnore
    private Post post;
    @OneToOne
    @JoinColumn(name = "commenter_id")
    @MapsId("commenterId")
    private Commenter commenter;
    private String content;
}
