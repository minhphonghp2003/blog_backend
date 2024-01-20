package com.phong.blog.Logging.Repository;

import com.phong.blog.Logging.Model.UserActivity;
import com.phong.blog.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity,Integer> {

    List<UserActivity> findAllByUser(User user);
}
