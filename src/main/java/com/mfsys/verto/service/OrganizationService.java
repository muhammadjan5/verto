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

    // CREATE / SAVE
    public OrganizationModel save(OrganizationModel organization) {
        return organizationRepository.save(organization);
    }

    // READ ALL
    public List<OrganizationModel> findAll() {
        return organizationRepository.findAll();
    }

    // READ BY CODE
    public OrganizationModel findByCode(String porOrgacode) {
        return organizationRepository.findById(porOrgacode)
                .orElseThrow(() ->
                        new RuntimeException("Organization not found with code: " + porOrgacode)
                );
    }

    // UPDATE
    public OrganizationModel update(String porOrgacode, OrganizationModel updatedOrg) {
        OrganizationModel existing = findByCode(porOrgacode);
        existing.setPorOrgaDesc(updatedOrg.getPorOrgaDesc());
        return organizationRepository.save(existing);
    }

    // DELETE
    public void delete(String porOrgacode) {
        if (!organizationRepository.existsById(porOrgacode)) {
            throw new RuntimeException("Organization not found with code: " + porOrgacode);
        }
        organizationRepository.deleteById(porOrgacode);
    }
}
