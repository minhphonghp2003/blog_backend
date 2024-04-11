package com.phong.blog.User.Service;

import com.phong.blog.User.DTO.ActionDTO;
import com.phong.blog.User.Model.Action;
import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Repository.ActionRepository;
import com.phong.blog.User.Repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final ActionRepository actionRepository;
    private final RequestMappingHandlerMapping handlerMapping;

    public Role getRoleByName(ERole roleName) {
        return roleRepository.findByName(roleName);
    }

    public List<Role> getRoleHaveAction(String actionName) {
        return roleRepository.findByAction(actionName);
    }

    @Transactional
    public void addActionToRole(Integer roleId, String actionName) {
        Role role = roleRepository.findById(roleId).orElse(null);
        Action action = actionRepository.findByName(actionName);
        if (action == null) {
            action = addAction(actionName);
        }
        if (role == null || action == null) {
            return;
        }
        role.getActions().add(action);
        roleRepository.save(role);
    }

    private Action addAction(String actionName) {
        Action newAction = new Action();
        newAction.setName(actionName);
        Action result = actionRepository.save(newAction);
        return result;

    }

    public List<String> getAllApis() {
        List<String> actions = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> allMethods = handlerMapping.getHandlerMethods();
        allMethods.forEach((requestMappingInfo, handlerMethod) -> {
            if (!requestMappingInfo.getDirectPaths().isEmpty()) {
                String actionName = (String) requestMappingInfo.getDirectPaths().toArray()[0];
                actions.add(actionName);
            }
        });
        return actions;
    }

    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteAction(Integer id) {
        actionRepository.deleteById(id);
    }
    public void detachActionFromRole(Integer actionId, Integer roleId){
        Role role = roleRepository.findById(roleId).orElse(null);
        Action action = actionRepository.findById(actionId).orElse(null);
        if(role ==null || action == null){
            return;
        }
        role.getActions().remove(action);
        roleRepository.save(role);
    }
}
