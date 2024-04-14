package com.phong.blog.User.Controller;

import com.phong.blog.User.DTO.ActionAssignDTO;
import com.phong.blog.User.DTO.UserRoleRequestDTO;
import com.phong.blog.User.Model.Action;
import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/validRoles")
    public List<ERole> getAllValidRoles() {
        return new ArrayList<ERole>(Arrays.asList(ERole.values()));

    }

    @PostMapping("/")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
    @GetMapping("/allRoles")
    public List<Role> allRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/assignAction")
    public Action addActionToRole(@RequestBody ActionAssignDTO actionAssignDTO) {
        return roleService.addActionToRole(actionAssignDTO.getRoleId(), actionAssignDTO.getActionName());
    }

    @PostMapping("/assignRole")
    public void addRoleToUser(@RequestBody UserRoleRequestDTO userRoleRequestDTO) {
        roleService.assignRoleToUser(userRoleRequestDTO);
    }

    @GetMapping("/allApis")
    public List<String> getAllApis() {
        return roleService.getAllApis();
    }

    @GetMapping("/allActions")
    public List<Action> getAllActions() {
        return roleService.getAllActions();
    }

    @DeleteMapping("/action")
    public String deleteAction(Integer id) {
        roleService.deleteAction(id);
        return "Done";
    }

    @DeleteMapping("/detachAction")
    public ResponseEntity<String> detachAction(Integer roleId, Integer actionId) {
        roleService.detachActionFromRole(actionId, roleId);
        return ResponseEntity.ok("Done");
    }
}
