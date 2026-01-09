package com.jpay.wallet_service.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String avatarUrl;
    private boolean kycVerified; // Trạng thái định danh
}