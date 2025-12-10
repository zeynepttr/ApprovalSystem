package com.permission.permission.service;

import com.permission.permission.model.Permission;
import com.permission.permission.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    // Tüm izinleri getir
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    // ID ile izin getir
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
    }

    // Yeni bir izin oluştur
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    // İzni güncelle
    public Permission updatePermission(Long id, Permission permissionDetails) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));

        permission.setName(permissionDetails.getName());
        permission.setDescription(permissionDetails.getDescription());
        return permissionRepository.save(permission);
    }

    // İzni sil
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new RuntimeException("Permission not found with id: " + id);
        }
        permissionRepository.deleteById(id);
    }
}
