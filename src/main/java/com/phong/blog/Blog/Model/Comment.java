package com.phong.blog.Blog.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("comment")
public class Comment {
    @Id
    private String id;
    private Integer postId;
    private String userId;
    private String fullName;
    private String text;
    private String parentCommentId;
}
