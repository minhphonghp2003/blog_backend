package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.PostStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticRepository extends JpaRepository<PostStatistic,Integer> {
//    Optional<BlogStatistic> findOne(Integer id);

}
