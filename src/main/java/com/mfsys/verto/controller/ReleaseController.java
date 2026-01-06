package com.mfsys.verto.controller;

import com.mfsys.verto.model.ReleaseModel;
import com.mfsys.verto.service.ReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releases")
@RequiredArgsConstructor
public class ReleaseController {

    private final ReleaseService releaseService;

    // CREATE with duplicate check
    @PostMapping("/create")
    public ResponseEntity<?> createRelease(@RequestBody ReleaseModel payload) {
        try {
            // Check if the same release already exists
            boolean exists = releaseService.existsByClientAndEnvironmentAndBranchAndBuild(
                    payload.getClient(),
                    payload.getEnvironment(),
                    payload.getBranch(),
                    payload.getBuild()
            );

            if (exists) {
                return ResponseEntity.badRequest().body("The given data already exists");
            }

            ReleaseModel saved = releaseService.createRelease(payload);
            return ResponseEntity.ok(saved);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ReleaseModel>> getAllReleases() {
        return ResponseEntity.ok(releaseService.getAllReleases());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReleaseById(@PathVariable Long id) {
        try {
            ReleaseModel release = releaseService.getReleaseById(id);
            return ResponseEntity.ok(release);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Data not found");
        }
    }

    // UPDATE by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRelease(
            @PathVariable Long id,
            @RequestBody ReleaseModel payload) {
        try {
            ReleaseModel updated = releaseService.updateById(id, payload);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRelease(@PathVariable Long id) {
        try {
            releaseService.deleteRelease(id);
            return ResponseEntity.ok("Release deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Data not found");
        }
    }
}
