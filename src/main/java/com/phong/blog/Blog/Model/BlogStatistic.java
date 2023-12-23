package com.phong.blog.Blog.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document("statistic")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogStatistic {
    @Id
    private Integer id;
    private Integer shareCount = 0;
    private Integer viewCount = 0;
    private List<Comment> comments = new ArrayList<>();
    private Set<Reader> likeReader = new HashSet<>();
}
