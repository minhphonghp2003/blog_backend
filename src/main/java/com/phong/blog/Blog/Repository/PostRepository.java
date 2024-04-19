package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.namedQueries.PostNamedQueries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public Post findById(int id);

    public Optional<List<Post>> findByIdGreaterThan(int id);

    @Query(nativeQuery = true, value = "select  p.*, count(reader_id) as like_count,max(s.view_count) as view_count,max( s.share_count) as share_count from post p left join post_statistic s on p.statistic_id = s.id left join post_like l on p.id = l.post_id  where  (:readingListId is null or reading_list_id = :readingListId) and  (:topicId is null or topic_id = :topicId) and  (:authorId is null or user_id = uuid(:authorId)) group by p.id ")
    public Page<Post> findBySomething(Pageable pageable, @Param("readingListId") Integer readingListId, @Param("authorId") String authorId, @Param("topicId") Integer topicId);

    public void deleteById(Integer id);

    public Page<Post> findAll(Pageable pageable);

    @Query(value = PostNamedQueries.allPost, nativeQuery = true)
    public Page<Post> findBySomethingV2(Pageable pageable, @Param("readingListId") Integer readingListId, @Param("authorId") String authorId, @Param("topicId") Integer topicId, @Param("status") String status);

    @Query(value = PostNamedQueries.QUERY, nativeQuery = true)

    public ArrayList<Post> findTestPost();
}
