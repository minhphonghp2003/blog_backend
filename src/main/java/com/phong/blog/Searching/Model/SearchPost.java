package com.phong.blog.Searching.Model;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.Tag;
import com.phong.blog.Blog.Model.Topic;
import com.phong.blog.User.Model.User;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

@Data
@Document(indexName = "post")
public class SearchPost {
    @Id
    private Integer id;
    private String content;
    private User author;
    private Topic topic;
//    private List<Tag> tags;
    private String imageLink;
    private String title;
    private String foreword;
    @Field(type=FieldType.Date, pattern="dd.MM.uuuu")
    private Date updatedAt;
}
