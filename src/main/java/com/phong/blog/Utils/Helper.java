package com.phong.blog.Utils;

import com.phong.blog.Blog.DTO.PostDTO;
import com.phong.blog.Blog.Model.Post;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class Helper {
    public void sortPostsBy(List<PostDTO> posts, String by){
        switch (by){
            case "view_count":
                Collections.sort(posts,((o1, o2) -> o2.getViewCount() - o1.getViewCount()));
            case "like_count":
                Collections.sort(posts, (Comparator.comparingInt(o -> o.getLikeReader().size())));
            case "share_count":
                Collections.sort(posts, Comparator.comparing(PostDTO::getShareCount));
        }
    }
}
