package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.ReadingList;
import com.phong.blog.Blog.Model.Topic;
import com.phong.blog.User.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public Post findById(int id);

    public Page<Post> findByAuthor(User author, Pageable pageable);
    public Page<Post> findByTopic(Topic topic, Pageable pageable);
    public Page<Post> findByReadingList(ReadingList readingList, Pageable pageable);

    public void deleteById(Integer id);

    public Page<Post> findAll(Pageable pageable);
}
