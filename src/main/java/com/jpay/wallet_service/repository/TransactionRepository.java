package com.jpay.wallet_service.repository;

import com.jpay.wallet_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserIdOrderByCreatedAtDesc(UUID userId);

    @Query("SELECT t FROM Transaction t WHERE t.trxStatus = 'REVIEW'")
    List<Transaction> findSuspiciousTransactions();
}