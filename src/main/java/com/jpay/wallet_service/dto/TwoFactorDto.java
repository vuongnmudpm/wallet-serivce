package com.jpay.wallet_service.dto;

import lombok.Data;

@Data
public class TwoFactorDto {
    private String verificationCode;
    private String secretKey; // Dùng để hiển thị mã thủ công nếu không quét được QR
}