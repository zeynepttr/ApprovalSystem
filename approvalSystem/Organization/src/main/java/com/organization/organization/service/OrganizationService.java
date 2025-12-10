package com.organization.organization.service;

import com.organization.organization.model.Organization;
import com.organization.organization.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    // Tüm organizasyonları döndüren metod
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    // ID'ye göre bir organizasyonu döndüren metod
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElse(null);  // Hata fırlatmak yerine null dönebiliriz
    }
}
