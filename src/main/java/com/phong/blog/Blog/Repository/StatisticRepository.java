package com.phong.blog.Blog.Repository;

import com.phong.blog.Blog.Model.BlogStatistic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StatisticRepository extends MongoRepository<BlogStatistic,Integer> {
//    Optional<BlogStatistic> findOne(Integer id);

}
