package com.mfsys.verto.service;

import com.mfsys.verto.model.TransactionChargeModel;
import com.mfsys.verto.repository.TransactionChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionChargeService {

    private final TransactionChargeRepository repository;

    // CREATE
    public TransactionChargeModel save(TransactionChargeModel charge) {
        return repository.save(charge);
    }

    // READ ALL
    public List<TransactionChargeModel> findAll() {
        return repository.findAll();
    }

    // READ BY ID
    public TransactionChargeModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Charge not found"));
    }

    // READ BY TRANSACTION ID
    public List<TransactionChargeModel> findByTransactionId(Long transactionId) {
        return repository.findByTransactionId(transactionId);
    }

    // UPDATE
    public TransactionChargeModel update(Long id, TransactionChargeModel charge) {
        // Fetch existing entity
        TransactionChargeModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Charge not found"));

        // Update fields
        existing.setTransactionId(charge.getTransactionId());
        existing.setAmount(charge.getAmount());
        existing.setChargeType(charge.getChargeType());
        existing.setCurrency(charge.getCurrency());
        existing.setDescription(charge.getDescription());
        existing.setStatus(charge.getStatus());

        // Save back to DB
        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
