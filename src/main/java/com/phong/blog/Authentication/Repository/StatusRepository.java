package com.phong.blog.Authentication.Repository;

import com.phong.blog.Authentication.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    public List<Status> findAll();
}
