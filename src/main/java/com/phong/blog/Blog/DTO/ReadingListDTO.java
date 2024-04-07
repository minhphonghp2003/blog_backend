package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.EStatus;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ReadingListDTO {
    private int id;
    private String image;
    private String name;
    private String description;

    private EStatus status = EStatus.PENDING;
}
