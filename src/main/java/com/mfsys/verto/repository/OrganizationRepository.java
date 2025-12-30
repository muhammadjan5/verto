package com.mfsys.verto.repository;

import com.mfsys.verto.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel, String> {

    boolean existsByPorOrgacode(String porOrgacode);
}
