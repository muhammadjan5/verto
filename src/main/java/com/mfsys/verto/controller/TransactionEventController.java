package com.mfsys.verto.controller;

import com.mfsys.verto.model.TransactionEventModel;
import com.mfsys.verto.service.TransactionEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-events")
@RequiredArgsConstructor
public class TransactionEventController {

    private final TransactionEventService transactionEventService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<TransactionEventModel> create(
            @RequestBody TransactionEventModel event) {
        return ResponseEntity.ok(transactionEventService.save(event));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionEventModel> update(
            @PathVariable Long id,
            @RequestBody TransactionEventModel updatedEvent) {

        TransactionEventModel existingEvent = transactionEventService.findById(id);
        if (existingEvent == null) {
            return ResponseEntity.notFound().build();
        }

        existingEvent.setTransactionId(updatedEvent.getTransactionId());
        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setEventCode(updatedEvent.getEventCode());
        existingEvent.setDescription(updatedEvent.getDescription());

        TransactionEventModel savedEvent = transactionEventService.save(existingEvent);
        return ResponseEntity.ok(savedEvent);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<TransactionEventModel>> getAll() {
        return ResponseEntity.ok(transactionEventService.findAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionEventModel> getById(@PathVariable Long id) {
        TransactionEventModel event = transactionEventService.findById(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    // READ BY TRANSACTION ID
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<List<TransactionEventModel>> getByTransactionId(
            @PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionEventService.findByTransactionId(transactionId));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionEventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
