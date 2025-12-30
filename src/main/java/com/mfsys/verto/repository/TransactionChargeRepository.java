package com.mfsys.verto.repository;

import com.mfsys.verto.model.TransactionChargeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionChargeRepository
        extends JpaRepository<TransactionChargeModel, Long> {

    // Find charges by transaction ID
    List<TransactionChargeModel> findByTransactionId(Long transactionId);
}
