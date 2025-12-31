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

  // Tüm organizasyonları döndürür
  public List<Organization> getAllOrganizations() {
    return organizationRepository.findAll();
  }

  // ID'ye göre organizasyon getirir
  public Organization getOrganizationById(Long id) {
    return organizationRepository.findById(id)
      .orElseThrow(() ->
        new RuntimeException("Organization not found with id: " + id)
      );
  }

  // ✅ Yeni organization oluşturur
  public Organization createOrganization(Organization organization) {

    // Parent varsa gerçekten var mı kontrol et
    if (organization.getParentId() != null) {
      organizationRepository.findById(organization.getParentId())
        .orElseThrow(() ->
          new RuntimeException(
            "Parent organization not found with id: " + organization.getParentId()
          )
        );
    }

    return organizationRepository.save(organization);
  }
}
