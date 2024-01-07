package com.phong.blog.Searching.Model;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.Topic;
import com.phong.blog.User.Model.User;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;

@Data
@Document(indexName = "post")
public class SearchPost {
    @Id
    private Integer id;
    private String content;
    private User author;
    private Topic topic;
    private String imageLink;
    private String title;
    private String foreword;
    private Instant updatedAt;
}
