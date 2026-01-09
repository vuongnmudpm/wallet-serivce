package com.jpay.wallet_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // "content" là tên biến chúng ta dùng trong th:replace
        // "pages/dashboard" là đường dẫn tới file dashboard.html
        model.addAttribute("content", "pages/dashboard");

        // Trả về file layout chính
        return "layout/main-layout";
    }
}