package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.AdminStatsDto;
import com.jpay.wallet_service.dto.FraudDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin") // Tất cả đường dẫn sẽ bắt đầu bằng /admin/...
public class AdminController {

    @GetMapping("/fraud")
    public String showFraudList(Model model) {
        // Data giả lập để giao diện lên hình đẹp
        List<FraudDto> list = List.of(
                new FraudDto("TXN-8821", "vuong_dev", "merchant_xyz", 55000000.0, "Số tiền vượt ngưỡng (>50M)", LocalDateTime.now()),
                new FraudDto("TXN-8825", "user_test_01", "unknown_acc", 10000000.0, "Giao dịch từ địa chỉ IP lạ", LocalDateTime.now().minusMinutes(30))
        );
        model.addAttribute("frauds", list);
        return "pages/admin/fraud-list";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // 1. Dữ liệu các thẻ thống kê
        AdminStatsDto stats = AdminStatsDto.builder()
                .totalUsers(1250)
                .totalSystemBalance(8500000000.0) // 8.5 tỷ
                .pendingFrauds(5)
                .totalTransactionsToday(142)
                .revenueToday(1250000.0)
                .build();

        model.addAttribute("stats", stats);

        // 2. Dữ liệu biểu đồ (Thường sẽ lấy từ SQL Server theo group by ngày)
        model.addAttribute("chartLabels", List.of("T2", "T3", "T4", "T5", "T6", "T7", "CN"));
        model.addAttribute("chartData", List.of(12, 19, 3, 5, 2, 3, 15)); // Số lượng giao dịch nghi vấn

        return "pages/admin/dashboard";
    }
}