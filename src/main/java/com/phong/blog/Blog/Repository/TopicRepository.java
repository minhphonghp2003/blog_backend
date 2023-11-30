package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    public List<Topic> findByStatus(EStatus status);
    public Optional<Topic> findById(Integer id);
}
