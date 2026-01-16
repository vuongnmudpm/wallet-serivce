package com.jpay.wallet_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String message;
    private String username;

    public LoginResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
