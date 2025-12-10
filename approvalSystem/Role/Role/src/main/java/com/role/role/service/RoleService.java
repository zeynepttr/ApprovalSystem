package com.role.role.service;

import com.role.role.dto.RoleDTO;
import com.role.role.exception.PermissionNotFoundException;
import com.role.role.exception.RoleNotFoundException;
import com.role.role.model.Role;
import com.role.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public RoleService(RoleRepository roleRepository, WebClient.Builder webClientBuilder) {
        this.roleRepository = roleRepository;
        this.webClientBuilder = webClientBuilder;
    }

    // Permission mikroservisine erişim için bu metodu ekleyeceğiz
    private boolean isPermissionValid(Long permissionId) {
        String permissionServiceUrl = "http://localhost:8083/permissions/" + permissionId;

        // WebClient kullanarak permission mikroservisine GET isteği atıyoruz
        ResponseEntity<String> response = webClientBuilder.baseUrl(permissionServiceUrl)
                .build()
                .get()
                .retrieve()
                .toEntity(String.class)
                .block();

        // Eğer 200 OK dönerse, geçerli bir permission'dır
        return response != null && response.getStatusCode().is2xxSuccessful();
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(role -> {
            RoleDTO dto = new RoleDTO();
            dto.setId(role.getId());
            dto.setName(role.getName());
            dto.setPermissionIds(role.getPermissionIds());
            return dto;
        }).collect(Collectors.toList());
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setPermissionIds(roleDTO.getPermissionIds());
        Role savedRole = roleRepository.save(role);
        roleDTO.setId(savedRole.getId());
        return roleDTO;
    }

    public RoleDTO getRoleById(Long id) {
        return roleRepository.findById(id).map(role -> {
            RoleDTO dto = new RoleDTO();
            dto.setId(role.getId());
            dto.setName(role.getName());
            dto.setPermissionIds(role.getPermissionIds());
            return dto;
        }).orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    public RoleDTO removePermissionsFromRole(Long roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found"));

        List<Long> currentPermissions = role.getPermissionIds();
        currentPermissions.removeAll(permissionIds);

        List<Long> missingPermissions = permissionIds.stream()
                .filter(permissionId -> !currentPermissions.contains(permissionId))
                .collect(Collectors.toList());

        if (!missingPermissions.isEmpty()) {
            throw new PermissionNotFoundException("Permission not found");
        }

        role.setPermissionIds(currentPermissions);
        roleRepository.save(role);

        RoleDTO updatedRole = new RoleDTO();
        updatedRole.setId(role.getId());
        updatedRole.setName(role.getName());
        updatedRole.setPermissionIds(role.getPermissionIds());
        return updatedRole;

    }

    // PUT işlemi için de benzer bir yaklaşım kullanılabilir
    public RoleDTO updateRolePermissions(Long roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        permissionIds.forEach(permissionId -> {
            if (!isPermissionValid(permissionId)) {
                throw new PermissionNotFoundException("Permission not found");
            }
        });

        role.setPermissionIds(permissionIds);
        roleRepository.save(role);

        RoleDTO updatedRole = new RoleDTO();
        updatedRole.setId(role.getId());
        updatedRole.setName(role.getName());
        updatedRole.setPermissionIds(role.getPermissionIds());
        return updatedRole;
    }
}
