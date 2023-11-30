package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Comment;
import com.phong.blog.Blog.Model.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, PostCommentId> {
    @Query(value="SELECT * FROM Comment c WHERE c.post_id = :postId ",nativeQuery = true)
    public List<Comment> findAllByPostId(@Param("postId") int postId);
}
