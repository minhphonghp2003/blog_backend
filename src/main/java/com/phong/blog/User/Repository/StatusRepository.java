package com.phong.blog.User.Repository;

import com.phong.blog.User.model.ERole;
import com.phong.blog.User.model.EStatus;
import com.phong.blog.User.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    public List<Status> findAll();
    public Status findByName(EStatus name);
}
