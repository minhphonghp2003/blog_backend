package com.phong.blog.User.Repository;

import com.phong.blog.User.model.ERole;
import com.phong.blog.User.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);
}
