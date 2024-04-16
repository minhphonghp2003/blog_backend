package com.phong.blog.Blog.DTO;

import com.phong.blog.Blog.Model.EStatus;
import lombok.Data;

@Data
public class StatusChangeDTO {
    private int targetId;
    private EStatus status;
}
