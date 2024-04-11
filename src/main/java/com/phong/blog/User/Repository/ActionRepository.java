package com.phong.blog.User.Repository;

import com.phong.blog.User.DTO.ActionDTO;
import com.phong.blog.User.Model.Action;
import com.phong.blog.namedQueries.RoleNamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {

    Action findByName(String actionName);
}
