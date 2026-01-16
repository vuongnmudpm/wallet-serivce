package com.jpay.wallet_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "Transactions") // Chữ T viết hoa khớp với SQL
@Getter @Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false) // Khớp với UserId trong script
    private User user;

    @Column(name = "Amount", precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "Currency")
    private String currency = "VND";

    @Column(name = "MerchantInfo")
    private String merchantInfo;

    @Column(name = "TrxStatus")
    private String trxStatus = "PENDING";

    @Column(name = "FraudScore")
    private Integer fraudScore = 0;

    @Column(name = "IpAddress")
    private String ipAddress;

    @Column(name = "CreatedAt")
    private OffsetDateTime createdAt = OffsetDateTime.now();
}