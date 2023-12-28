package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {

    @Query("{'postId':?0}")
   List<Comment> findByPost(Integer postId);

    @Query("{'parentCommentId':?0}")
    List<Comment> findReplyByComment(String commentId);

}
