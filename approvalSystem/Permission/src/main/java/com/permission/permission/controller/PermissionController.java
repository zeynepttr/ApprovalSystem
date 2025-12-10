package com.permission.permission.controller;

import com.permission.permission.model.Permission;
import com.permission.permission.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Tüm izinleri getir
    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    // ID ile izin getir
    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    // Yeni bir izin oluştur
    @PostMapping
    public Permission createPermission(@RequestBody @Valid Permission permission) {
        return permissionService.createPermission(permission);
    }

    // İzni güncelle
    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id, @RequestBody @Valid Permission permissionDetails) {
        return permissionService.updatePermission(id, permissionDetails);
    }

    // İzni sil
    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}
