package com.role.role.controller;

import com.role.role.dto.RoleDTO;
import com.role.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Tüm rollerin listelenmesi
    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    // Yeni bir rol oluşturulması
    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    // Belirli bir rolün detaylarının alınması
    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }


    // Permission ekleme işlemi
    @PostMapping("/{roleId}/permissions")
    public RoleDTO addPermissionsToRole(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        return roleService.updateRolePermissions(roleId, permissionIds);
    }

    // Permission çıkarma işlemi
    @DeleteMapping("/{roleId}/permissions")
    public RoleDTO removePermissionsFromRole(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        return roleService.removePermissionsFromRole(roleId, permissionIds);
    }
}
