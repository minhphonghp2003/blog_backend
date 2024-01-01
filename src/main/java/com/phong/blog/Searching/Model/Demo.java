package com.phong.blog.Searching.Model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "demo")
@Data
public class Demo {
    @Id
    String id;
    String firstname;
    String lastname;
}
