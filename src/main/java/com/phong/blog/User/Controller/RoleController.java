package com.phong.blog.User.Controller;

import com.phong.blog.User.DTO.ActionAssignDTO;
import com.phong.blog.User.Model.Action;
import com.phong.blog.User.Model.ERole;
import com.phong.blog.User.Model.Role;
import com.phong.blog.User.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/")
    public Role getRole(ERole roleName) {
        return roleService.getRoleByName(roleName);
    }

    @GetMapping("/allRoles")
    public List<Role> allRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/assignAction")
    public String addActionToRole(@RequestBody ActionAssignDTO actionAssignDTO) {
        roleService.addActionToRole(actionAssignDTO.getRoleId(),actionAssignDTO.getActionName());
        return "Done";
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
