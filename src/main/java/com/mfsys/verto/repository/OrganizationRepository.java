package com.mfsys.verto.repository;

import com.mfsys.verto.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel, Long> {

    // Check if code already exists
    boolean existsByCode(String code);

    // Find organization by code
    Optional<OrganizationModel> findByCode(String code);
}
