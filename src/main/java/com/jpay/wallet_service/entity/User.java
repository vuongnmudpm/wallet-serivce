package com.jpay.wallet_service.entity;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "UserStatus")
    private String userStatus = "ACTIVE";

    @Column(name = "CreatedAt")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "Balance")
    private BigDecimal balance;

}