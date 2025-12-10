package com.organization.organization.controller;

import com.organization.organization.model.Organization;
import com.organization.organization.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    // RuntimeException hatalarını yakalamak için @ExceptionHandler kullanıyoruz
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404 Not Found döner
    public String handleNotFound(RuntimeException ex) {
        return ex.getMessage();  // Hata mesajını döndürür
    }
}
