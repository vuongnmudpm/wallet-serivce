package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        if (!model.containsAttribute("request")) {
            model.addAttribute("request", new RegisterRequest());
        }
        return "pages/register";
    }
}
