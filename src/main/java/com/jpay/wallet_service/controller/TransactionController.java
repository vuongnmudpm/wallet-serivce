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

@Controller // Đổi từ @RestController sang @Controller
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit")
    @ResponseBody // Thêm cái này để hàm này vẫn trả về JSON/String thay vì tìm View
    public ResponseEntity<String> deposit(@Valid @RequestBody DepositRequest request, Principal principal) {
        // Sau khi test xong, hãy bỏ comment phần lấy username từ principal
        String username = "vuongnm04";
        transactionService.deposit(username, request);
        return ResponseEntity.ok("Nạp tiền thành công! Số dư mới đã được cập nhật!");
    }

    @GetMapping("/top-up")
    public String showTopUpPage(Model model) {
        model.addAttribute("topUpData", new TopUpDto());
        return "pages/top-up"; // Bây giờ Thymeleaf sẽ hiểu và mở file HTML
    }
}