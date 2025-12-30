package com.mfsys.verto.service;

import com.mfsys.verto.model.TransactionTypeModel;
import com.mfsys.verto.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    // CREATE
    public TransactionTypeModel create(TransactionTypeModel transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    // READ ALL
    public List<TransactionTypeModel> findAll() {
        return transactionTypeRepository.findAll();
    }

    // READ BY ID
    public Optional<TransactionTypeModel> findById(Long id) {
        return transactionTypeRepository.findById(id);
    }

    // UPDATE
    public TransactionTypeModel update(Long id, TransactionTypeModel updatedType) {
        TransactionTypeModel existing = transactionTypeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("TransactionType not found with id: " + id)
                );

        existing.setCode(updatedType.getCode());
        existing.setName(updatedType.getName());
        existing.setDescription(updatedType.getDescription());
        existing.setActive(updatedType.getActive());

        return transactionTypeRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        if (!transactionTypeRepository.existsById(id)) {
            throw new RuntimeException("TransactionType not found with id: " + id);
        }
        transactionTypeRepository.deleteById(id);
    }
}
