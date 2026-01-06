package com.mfsys.verto.service;

import com.mfsys.verto.model.OrganizationModel;
import com.mfsys.verto.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    // CREATE
    public OrganizationModel save(OrganizationModel organization) {
        if (organizationRepository.existsByCode(organization.getCode())) {
            throw new RuntimeException("Data already exists");
        }
        return organizationRepository.save(organization);
    }

    // READ ALL
    public List<OrganizationModel> findAll() {
        return organizationRepository.findAll();
    }

    // READ BY ID
    public OrganizationModel findById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
    }

    // READ BY CODE
    public OrganizationModel findByCode(String code) {
        return organizationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Data not found"));
    }

    // UPDATE BY ID
    public OrganizationModel update(Long id, OrganizationModel updatedOrg) {
        OrganizationModel existing = findById(id);

        if (updatedOrg.getCode() != null) {
            // Check if new code is duplicate
            if (!existing.getCode().equals(updatedOrg.getCode()) &&
                    organizationRepository.existsByCode(updatedOrg.getCode())) {
                throw new RuntimeException("Data already exists");
            }
            existing.setCode(updatedOrg.getCode());
        }

        if (updatedOrg.getName() != null) {
            existing.setName(updatedOrg.getName());
        }

        return organizationRepository.save(existing);
    }

    // DELETE BY ID
    public void delete(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new RuntimeException("Data not found");
        }
        organizationRepository.deleteById(id);
    }
}
