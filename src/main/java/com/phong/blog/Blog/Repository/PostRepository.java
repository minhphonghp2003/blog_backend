package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Model.Topic;
import com.phong.blog.User.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public Post findById(int id);

    public Optional<List<Post>> findByIdGreaterThan(int id);

    @Query(nativeQuery = true, value = "select * from post p  where case WHEN :readingListId ~ '^[0-9\\.]+$' THEN reading_list_id = cast(:readingListId as int) WHEN :authorId ~ '^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$' THEN user_id = uuid(:authorId) WHEN :topicId ~ '^[0-9\\.]+$' THEN topic_id = cast( :topicId as int )ELSE true END  ")
    public Page<Post> findBySomething(Pageable pageable, @Param("readingListId") String readingListId, @Param("authorId") String authorId, @Param("topicId") String topicId);

    public void deleteById(Integer id);

    public Page<Post> findAll(Pageable pageable);

}
