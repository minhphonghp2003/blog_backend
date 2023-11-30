package com.phong.blog.User.Repository;

import com.phong.blog.User.Model.Social;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialRepository extends JpaRepository<Social,Integer> {
    public List<Social> findAll();
    public Social findById(int id);
}
