package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.EStatus;
import com.phong.blog.Blog.Model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList,Integer> {
    public List<ReadingList>  findAll();
    public Optional<ReadingList> findByIdAndStatus(Integer id, EStatus status);

    List<ReadingList> findAllByStatus(EStatus eStatus);
}
