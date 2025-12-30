package com.mfsys.verto.controller;

import com.mfsys.verto.model.TransactionChargeModel;
import com.mfsys.verto.service.TransactionChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-charges")
@RequiredArgsConstructor
public class TransactionChargeController {

    private final TransactionChargeService transactionChargeService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<TransactionChargeModel> create(
            @RequestBody TransactionChargeModel charge) {
        return ResponseEntity.ok(transactionChargeService.save(charge));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<TransactionChargeModel>> getAll() {
        return ResponseEntity.ok(transactionChargeService.findAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionChargeModel> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(transactionChargeService.findById(id));
    }

    // READ BY TRANSACTION ID
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<List<TransactionChargeModel>> getByTransactionId(
            @PathVariable Long transactionId) {
        return ResponseEntity.ok(
                transactionChargeService.findByTransactionId(transactionId)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TransactionChargeModel> update(
            @PathVariable Long id,
            @RequestBody TransactionChargeModel charge) {
        return ResponseEntity.ok(
                transactionChargeService.update(id, charge)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionChargeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
