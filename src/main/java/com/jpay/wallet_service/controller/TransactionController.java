package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.TopUpDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    @GetMapping("/top-up")
    public String showTopUpPage(Model model) {
        model.addAttribute("topUpData", new TopUpDto()); // Dùng TopUpDto thay vì UserRegistrationDto
        return "pages/top-up";
    }
}