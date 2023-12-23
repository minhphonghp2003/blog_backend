package com.phong.blog.Blog.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private UUID userId;
    private String comId;
    private String fullName;
    private String avatarUrl;
    private String userProfile;
    private String text;
    private List<Comment> replies;
}
