package com.phong.blog.User.Repository;

import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);
}
