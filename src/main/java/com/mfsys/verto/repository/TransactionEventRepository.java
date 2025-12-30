package com.mfsys.verto.repository;

import com.mfsys.verto.model.TransactionEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionEventRepository extends JpaRepository<TransactionEventModel, Long> {

    // Find all events by transaction ID
    List<TransactionEventModel> findByTransactionId(Long transactionId);
}
