package com.groupservice.groupservice.controller;

import com.groupservice.groupservice.dto.GroupDto;
import com.groupservice.groupservice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    public GroupDto createGroup(@RequestBody GroupDto groupDTO) {
        return groupService.createGroup(groupDTO);
    }

    @GetMapping("/{id}")
    public GroupDto getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @PostMapping("/{groupId}/permissions")
    public GroupDto addPermissionsToGroup(@PathVariable Long groupId, @RequestBody List<Long> permissionIds) {
        return groupService.updateGroupPermissions(groupId, permissionIds);
    }
    @PostMapping("/{groupId}/organizations")
    public GroupDto addOrganizationToGroup(@PathVariable Long groupId, @RequestBody Long organizationId) {
        return groupService.updateGroupOrganizations(groupId, organizationId);
    }

}
