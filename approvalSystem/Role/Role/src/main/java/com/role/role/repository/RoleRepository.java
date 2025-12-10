package com.role.role.repository;

import com.role.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Rol ismine göre rol arama
    Optional<Role> findByName(String name);

    // Belirli izinleri içeren rolleri bulma
    List<Role> findByPermissionIdsContaining(Long permissionId);

    // Birden fazla izin ID'sine sahip rolleri bulma
    List<Role> findByPermissionIdsIn(List<Long> permissionIds);
}
