package com.jpay.wallet_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    @NotNull(message = "Số tiền không được để trống")
    @DecimalMin(value = "1000.0", message = "Số tiền nạp tối thiểu là 1,000 VND")
    private BigDecimal amount;
}
