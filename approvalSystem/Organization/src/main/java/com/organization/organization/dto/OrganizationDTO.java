package com.organization.organization.dto;

import java.util.Set;

public class OrganizationDTO {

    private Long id;
    private String name;
    private Long parentId;


    // Default constructor
    public OrganizationDTO() {
    }

    // Parameterized constructor
    public OrganizationDTO(Long id, String name, Long parentId, Set<Long> childrenIds) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;

    }

    // Getters and Setters
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    // toString method
    @Override
    public String toString() {
        return "OrganizationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +

                '}';
    }
}
