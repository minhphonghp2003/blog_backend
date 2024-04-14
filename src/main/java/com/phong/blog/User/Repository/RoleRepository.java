package com.phong.blog.User.Repository;

import com.phong.blog.User.DTO.ActionDTO;
import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import com.phong.blog.namedQueries.RoleNamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);

    @Query(nativeQuery = true,value = RoleNamedQueries.getRoleHaveAction )
    List<Role> findByAction( @Param("actionName") String actionName);
    @Modifying
    @Query(nativeQuery = true,value = RoleNamedQueries.assignRoleToUer)
    void assignRoleToUser(@Param("userId") UUID userId,@Param("roleId") Integer roleId);

}
