package com.permission.permission.dto;

import jakarta.validation.constraints.NotBlank;

public class PermissionDTO {

    private Long id;

    @NotBlank(message = "Permission name is required")
    private String name;

    private String description;

    // Getter ve Setter metodları

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
