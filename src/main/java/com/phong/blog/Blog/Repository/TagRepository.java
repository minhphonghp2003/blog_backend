package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    public Tag findById(int id);
    public List<Tag> findAllByStatus(EStatus status);
}
