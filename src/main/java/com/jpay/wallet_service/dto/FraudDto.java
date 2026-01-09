package com.jpay.wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FraudDto {
    private String txnId;
    private String sender;
    private String receiver;
    private Double amount;
    private String riskReason; // Lý do nghi vấn: Số tiền lớn, Giao dịch liên tục, Vị trí lạ...
    private LocalDateTime createdAt;
}
