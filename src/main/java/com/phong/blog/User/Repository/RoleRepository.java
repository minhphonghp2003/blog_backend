package com.phong.blog.User.Repository;

import com.phong.blog.User.DTO.ActionDTO;
import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import com.phong.blog.namedQueries.RoleNamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);

    @Query(nativeQuery = true,value = RoleNamedQueries.getRoleHaveAction )
    List<Role> findByAction( @Param("actionName") String actionName);

}
