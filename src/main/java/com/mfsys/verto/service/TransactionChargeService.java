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
                .orElseThrow(() -> new RuntimeException("Transaction charge not found"));
    }

    // UPDATE
    public TransactionChargeModel update(Long id, TransactionChargeModel charge) {
        TransactionChargeModel existing = findById(id);

        existing.setPch_chrgcode(charge.getPch_chrgcode());
        existing.setPch_chrgdesc(charge.getPch_chrgdesc());
        existing.setPch_chrgprofit(charge.getPch_chrgprofit());
        existing.setPch_chrgshort(charge.getPch_chrgshort());
        existing.setPel_elmtcode(charge.getPel_elmtcode());
        existing.setPtr_trancode(charge.getPtr_trancode());
        existing.setSoc_charges(charge.getSoc_charges());

        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
