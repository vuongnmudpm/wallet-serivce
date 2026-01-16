package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.LoginRequest;
import com.jpay.wallet_service.dto.RegisterRequest;
import com.jpay.wallet_service.service.impl.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Đăng ký tài khoản thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest  request) {
        authService.login(request);
        return ResponseEntity.ok("Đăng nhập thành công!");
    }
}
