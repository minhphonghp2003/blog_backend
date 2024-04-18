package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Draft;
import com.phong.blog.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DraftRepository extends JpaRepository<Draft,Integer> {
    List<Draft> findAllByUser(User user);
}
