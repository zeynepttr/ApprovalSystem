package com.groupservice.groupservice.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "group_permissions", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "permission_id")
    private List<Long> permissionIds;

    @Column(name = "organization_id")
    private Long OrganizationId;

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public Long getOrganizationId() {return OrganizationId;}
    public void setOrganizationId(Long OrganizationId) {this.OrganizationId = OrganizationId;}

}
