package com.phong.blog.Logging.Repository;

import com.phong.blog.Logging.Model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Integer> {
    @Query(nativeQuery = true, value = "select * from user_log where user_id = ?1 order by created_at desc ")
    List<UserLog> getUserLog(UUID userId);
}
