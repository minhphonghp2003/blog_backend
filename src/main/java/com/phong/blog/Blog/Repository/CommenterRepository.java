package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.Commenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommenterRepository extends JpaRepository<Commenter,Integer> {
}
