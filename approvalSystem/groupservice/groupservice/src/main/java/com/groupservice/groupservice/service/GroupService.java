package com.groupservice.groupservice.service;

import com.groupservice.groupservice.dto.GroupDto;
import com.groupservice.groupservice.model.Group;
import com.groupservice.groupservice.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public GroupService(GroupRepository groupRepository, WebClient.Builder webClientBuilder) {
        this.groupRepository = groupRepository;
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

    private boolean isOrganizationValid(Long organizationsId) {
        String organizationServiceUrl = "http://localhost:8085/organizations/" + organizationsId;

        // WebClient kullanarak group mikroservisine GET isteği atıyoruz
        ResponseEntity<String> response = webClientBuilder.baseUrl(organizationServiceUrl)
                .build()
                .get()
                .retrieve()
                .toEntity(String.class)
                .block();

        // Eğer 200 OK dönerse, geçerli bir permission'dır
        return response != null && response.getStatusCode().is2xxSuccessful();
    }


    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream().map(group -> {
            GroupDto dto = new GroupDto();
            dto.setId(group.getId());
            dto.setName(group.getName());
            dto.setPermissionIds(group.getPermissionIds());
            dto.setOrganizationId(group.getOrganizationId());
            return dto;
        }).collect(Collectors.toList());
    }

    public GroupDto createGroup(GroupDto groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setPermissionIds(groupDTO.getPermissionIds());
        group.setOrganizationId(group.getOrganizationId());
        Group savedGroup = groupRepository.save(group);
        groupDTO.setId(savedGroup.getId());
        return groupDTO;
    }

    public GroupDto getGroupById(Long id) {
        return groupRepository.findById(id).map(group -> {
            GroupDto dto = new GroupDto();
            dto.setId(group.getId());
            dto.setName(group.getName());
            dto.setPermissionIds(group.getPermissionIds());
            dto.setOrganizationId(group.getOrganizationId());
            return dto;
        }).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    // PUT işlemi için de benzer bir yaklaşım kullanılabilir
    public GroupDto updateGroupPermissions(Long groupId, List<Long> permissionIds) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        permissionIds.forEach(permissionId -> {
            if (!isPermissionValid(permissionId)) {
                throw new RuntimeException("Invalid permission ID: " + permissionId);
            }
        });

        group.setPermissionIds(permissionIds);
        groupRepository.save(group);

        GroupDto updatedGroup = new GroupDto();
        updatedGroup.setId(group.getId());
        updatedGroup.setName(group.getName());
        updatedGroup.setPermissionIds(group.getPermissionIds());
        updatedGroup.setOrganizationId(group.getOrganizationId());
        return updatedGroup;
    }

    public GroupDto updateGroupOrganizations(Long groupId, Long OrganizationId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        if (!isOrganizationValid(OrganizationId)) {
            throw new RuntimeException("Invalid permission ID: " + OrganizationId);
        }

        group.setOrganizationId(OrganizationId);
        groupRepository.save(group);

        GroupDto updatedGroup = new GroupDto();
        updatedGroup.setId(group.getId());
        updatedGroup.setName(group.getName());
        updatedGroup.setPermissionIds(group.getPermissionIds());
        updatedGroup.setOrganizationId(group.getOrganizationId());
        return updatedGroup;
    }
}
