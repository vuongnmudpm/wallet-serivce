package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.TopUpDto;
import com.jpay.wallet_service.dto.TransactionDto;
import com.jpay.wallet_service.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/") // Khi bạn truy cập localhost:8080/
    public String index() {
        return "pages/dashboard"; // Nó sẽ tìm file index.html trong thư mục templates
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "pages/register";
    }

    @GetMapping("/history")
    public String fullHistory(Model model) {
        List<TransactionDto> list = List.of(
                new TransactionDto("TXN001", LocalDateTime.now(), "Nạp tiền", "Nạp từ ngân hàng", 2000000.0, "SUCCESS"),
                new TransactionDto("TXN002", LocalDateTime.now().minusDays(1), "Chuyển tiền", "Gửi cho Nguyen Van A", -500000.0, "SUCCESS"),
                new TransactionDto("TXN003", LocalDateTime.now().minusDays(2), "Chuyển tiền", "Thanh toán hóa đơn", -150000.0, "PENDING")
        );
        model.addAttribute("transactions", list);
        return "pages/full-history";
    }

    @GetMapping("/test-404")
    public String test404() {
        return "pages/error/404";
    }

    @GetMapping("/test-500")
    public String test500(Model model) {
        model.addAttribute("message", "Mô phỏng lỗi: java.lang.NullPointerException: Cannot invoke 'UserEntity.getBalance()' because 'user' is null");
        return "pages/error/500";
    }
}