package com.mfsys.verto.repository;

import com.mfsys.verto.model.TransactionTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository
        extends JpaRepository<TransactionTypeModel, Long> {
}
