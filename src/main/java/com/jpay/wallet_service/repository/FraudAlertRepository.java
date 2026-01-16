package com.jpay.wallet_service.repository;

import com.jpay.wallet_service.entity.FraudAlert;
import com.jpay.wallet_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FraudAlertRepository extends JpaRepository<FraudAlert, UUID> {
}
