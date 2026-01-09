package com.jpay.wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {
    private String id;
    private LocalDateTime date;
    private String type; // Nạp tiền, Chuyển tiền, Nhận tiền
    private String description;
    private Double amount;
    private String status; // SUCCESS, PENDING, FAILED
}