package com.jpay.wallet_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "FraudAlerts")
@Getter
@Setter
public class FraudAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "TransactionId", nullable = false)
    private Transaction transaction;

    @Column(name = "Reason")
    private String reason;
    @Column(name = "Severity")
    private String severity;

    private OffsetDateTime createdAt = OffsetDateTime.now();
}