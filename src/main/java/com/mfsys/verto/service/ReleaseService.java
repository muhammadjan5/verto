package com.mfsys.verto.service;

import com.mfsys.verto.model.ReleaseModel;
import com.mfsys.verto.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    // CREATE with duplicate check
    public ReleaseModel createRelease(ReleaseModel payload) {
        if (existsByClientAndEnvironmentAndBranchAndBuild(
                payload.getClient(),
                payload.getEnvironment(),
                payload.getBranch(),
                payload.getBuild()
        )) {
            throw new RuntimeException("The given data already exists");
        }

        return releaseRepository.save(payload);
    }

    // Check if duplicate release exists
    public boolean existsByClientAndEnvironmentAndBranchAndBuild(
            String client, String environment, String branch, Integer build) {
        return releaseRepository.existsByClientAndEnvironmentAndBranchAndBuild(client, environment, branch, build);
    }

    // READ ALL
    public List<ReleaseModel> getAllReleases() {
        return releaseRepository.findAll();
    }

    // READ BY ID
    public ReleaseModel getReleaseById(Long id) {
        return releaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
    }

    // UPDATE BY ID
    public ReleaseModel updateById(Long id, ReleaseModel payload) {
        ReleaseModel existing = releaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        // If client/env/branch/build changed, check for duplicate
        if (!existing.getClient().equals(payload.getClient()) ||
                !existing.getEnvironment().equals(payload.getEnvironment()) ||
                !existing.getBranch().equals(payload.getBranch()) ||
                !existing.getBuild().equals(payload.getBuild())) {

            if (existsByClientAndEnvironmentAndBranchAndBuild(
                    payload.getClient(),
                    payload.getEnvironment(),
                    payload.getBranch(),
                    payload.getBuild()
            )) {
                throw new RuntimeException("The given data already exists");
            }
        }

        existing.setClient(payload.getClient());
        existing.setEnvironment(payload.getEnvironment());
        existing.setBranch(payload.getBranch());
        existing.setBuild(payload.getBuild());
        existing.setCommitMessage(payload.getCommitMessage());
        existing.setDate(payload.getDate());
        existing.setVersion(payload.getVersion());

        return releaseRepository.save(existing);
    }

    // DELETE BY ID
    public void deleteRelease(Long id) {
        if (!releaseRepository.existsById(id)) {
            throw new RuntimeException("Data not found");
        }
        releaseRepository.deleteById(id);
    }
}
