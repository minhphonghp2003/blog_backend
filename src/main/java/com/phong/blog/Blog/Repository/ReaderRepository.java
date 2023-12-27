package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Post;
import com.phong.blog.Blog.Model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,UUID> {
    Optional<Reader> findById(UUID id);
//    Optional<Reader> findByIdAndPost(UUID id, Post post);
}
