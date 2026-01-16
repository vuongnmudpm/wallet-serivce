package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.DepositRequest;
import com.jpay.wallet_service.dto.TopUpDto;
import com.jpay.wallet_service.entity.Transaction;
import com.jpay.wallet_service.service.impl.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit")
    // Lưu ý: Chỉ dùng Principal khi bạn chắc chắn API này đã được xác thực (authenticated)
    public ResponseEntity<String> deposit(@Valid @RequestBody DepositRequest request, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Bạn cần đăng nhập để thực hiện giao dịch này!");
        }

        String username = principal.getName();
        transactionService.deposit(username, request);
        return ResponseEntity.ok("Nạp tiền thành công! Số dư mới đã được cập nhật!");
    }

    @GetMapping("/top-up")
    public String showTopUpPage(Model model) {
        model.addAttribute("topUpData", new TopUpDto()); // Dùng TopUpDto thay vì UserRegistrationDto
        return "pages/top-up";
    }
}