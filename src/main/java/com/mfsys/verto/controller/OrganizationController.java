package com.mfsys.verto.controller;

import com.mfsys.verto.model.OrganizationModel;
import com.mfsys.verto.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    // CREATE
    @PostMapping("/create")
    public OrganizationModel create(@RequestBody OrganizationModel organization) {
        return organizationService.save(organization);
    }

    // READ ALL
    @GetMapping
    public List<OrganizationModel> getAll() {
        return organizationService.findAll();
    }

    // READ BY CODE
    @GetMapping("/{code}")
    public OrganizationModel getByCode(@PathVariable("code") String code) {
        return organizationService.findByCode(code);
    }

    // UPDATE
    @PutMapping("/{code}")
    public OrganizationModel update(
            @PathVariable("code") String code,
            @RequestBody OrganizationModel organization) {
        return organizationService.update(code, organization);
    }

    // DELETE
    @DeleteMapping("/{code}")
    public void delete(@PathVariable("code") String code) {
        organizationService.delete(code);
    }
}
