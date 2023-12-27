package com.phong.blog.Blog.DTO;

import lombok.Data;

import java.util.UUID;

enum SortBy {
    updated_at,
    like_count,
    view_count,
    share_count

}


@Data
public class AllPostReqDTO {
    private SortBy sortBy;
    private String topicId;
    private String readingListId;
    private String authorId;
    private int page;
    private int limit;
}
