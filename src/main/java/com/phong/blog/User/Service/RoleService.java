package com.phong.blog.User.Service;

import com.phong.blog.User.DTO.ActionDTO;
import com.phong.blog.User.DTO.UserRoleRequestDTO;
import com.phong.blog.User.Model.Action;
import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Model.User;
import com.phong.blog.User.Repository.ActionRepository;
import com.phong.blog.User.Repository.RoleRepository;
import com.phong.blog.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final ActionRepository actionRepository;
    private final RequestMappingHandlerMapping handlerMapping;
    private final UserRepository userRepository;

    public Role getRoleByName(ERole roleName) {
        return roleRepository.findByName(roleName);
    }

    public List<Role> getRoleHaveAction(String actionName) {
        return roleRepository.findByAction(actionName);
    }

    @Transactional
    public Action addActionToRole(Integer roleId, String actionName) {
        Role role = roleRepository.findById(roleId).orElse(null);
        Action action = actionRepository.findByName(actionName);
        if (action == null) {
            action = addAction(actionName);
        }
        if (role == null || action == null) {
            return null;
        }
        role.getActions().add(action);
        roleRepository.save(role);
        return action;
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

    public void detachActionFromRole(Integer actionId, Integer roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        Action action = actionRepository.findById(actionId).orElse(null);
        if (role == null || action == null) {
            return;
        }
        role.getActions().remove(action);
        roleRepository.save(role);
    }

    public Role createRole(Role role) {
        ArrayList<Role> allRoles = (ArrayList<Role>) getAllRoles();
        for (Role r : allRoles) {
            if (r.getName().equals(role.getName())) {
                return null;
            }
        }
        role.setActions(new HashSet<>());
        return roleRepository.save(role);
    }

    @Transactional
    public void assignRoleToUser(UserRoleRequestDTO userRoleRequestDTO) {
        User user = userRepository.findById(UUID.fromString(userRoleRequestDTO.getUserId())).orElse(null);
        if (user == null) {
            return;
        }
        Set<Role> roles = new HashSet<>();
        userRoleRequestDTO.getRoleId().forEach(role -> {
            Role r = roleRepository.findById(role).orElse(null);
            if (r != null) {
                roles.add(r);
            }
        });
        user.setRoles((Set<Role>) roles);
        userRepository.save(user);
//        roleRepository.assignRoleToUser(user.getId(), role.getId());
    }

    private boolean checkUserHaveRole(final Set<Role> list, final Integer id) {
        return list.stream().filter(o -> o.getId() == id).findFirst().isPresent();
    }
}
