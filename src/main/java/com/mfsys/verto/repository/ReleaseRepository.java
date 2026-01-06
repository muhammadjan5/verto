package com.mfsys.verto.repository;

import com.mfsys.verto.model.ReleaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseModel, Long> {

    // Check if a release with the same client, environment, branch, and build already exists
    boolean existsByClientAndEnvironmentAndBranchAndBuild(
            String client, String environment, String branch, Integer build
    );

    // Optional: find release by client & environment
    Optional<ReleaseModel> findByClientAndEnvironment(String client, String environment);
}
