package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.TransactionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HistoryController {
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
}
