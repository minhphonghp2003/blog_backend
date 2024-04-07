package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.EStatus;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class TopicDTO {
   private int id;
   private String name;
   private String icon;
   private String description;
   private EStatus status = EStatus.PENDING;
}
