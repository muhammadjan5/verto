package com.mfsys.verto.controller;

import com.mfsys.verto.model.OrganizationModel;
import com.mfsys.verto.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<OrganizationModel> createOrganization(@RequestBody OrganizationModel organization) {
        OrganizationModel saved = organizationService.save(organization);
        return ResponseEntity.ok(saved);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<OrganizationModel>> getAllOrganizations() {
        List<OrganizationModel> organizations = organizationService.findAll();
        return ResponseEntity.ok(organizations);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationModel> getOrganizationById(@PathVariable Long id) {
        OrganizationModel organization = organizationService.findById(id);
        return ResponseEntity.ok(organization);
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationModel> updateOrganization(
            @PathVariable Long id,
            @RequestBody OrganizationModel organization) {
        OrganizationModel updated = organizationService.update(id, organization);
        return ResponseEntity.ok(updated);
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //  NO EXISTING CODE CHANGED
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        if (ex.getMessage().equalsIgnoreCase("Data already exists")) {
            return ResponseEntity.status(409).body("Data already exists");
        }

        if (ex.getMessage().equalsIgnoreCase("Data not found")) {
            return ResponseEntity.status(404).body("Data not found");
        }

        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
