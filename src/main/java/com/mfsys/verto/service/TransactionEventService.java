package com.mfsys.verto.service;

import com.mfsys.verto.model.TransactionEventModel;
import com.mfsys.verto.repository.TransactionEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionEventService {

    private final TransactionEventRepository repository;

    // CREATE or UPDATE
    public TransactionEventModel save(TransactionEventModel event) {
        return repository.save(event);
    }

    // READ ALL
    public List<TransactionEventModel> findAll() {
        return repository.findAll();
    }

    // READ BY ID
    public TransactionEventModel findById(Long id) {
        Optional<TransactionEventModel> event = repository.findById(id);
        return event.orElse(null);
    }

    // READ BY TRANSACTION ID
    public List<TransactionEventModel> findByTransactionId(Long transactionId) {
        return repository.findByTransactionId(transactionId);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
