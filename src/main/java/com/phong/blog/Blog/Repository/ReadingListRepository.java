package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList,Integer> {
    public List<ReadingList>  findAll();
}
