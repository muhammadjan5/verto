package com.mfsys.verto.controller;

import com.mfsys.verto.model.TransactionTypeModel;
import com.mfsys.verto.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-types")
@RequiredArgsConstructor
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    // CREATE
    @PostMapping ("/create")
    public ResponseEntity<TransactionTypeModel> create(
            @RequestBody TransactionTypeModel transactionType) {
        return ResponseEntity.ok(transactionTypeService.create(transactionType));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<TransactionTypeModel>> findAll() {
        return ResponseEntity.ok(transactionTypeService.findAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionTypeModel> findById(@PathVariable Long id) {
        return transactionTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TransactionTypeModel> update(
            @PathVariable Long id,
            @RequestBody TransactionTypeModel transactionType) {
        return ResponseEntity.ok(
                transactionTypeService.update(id, transactionType)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
