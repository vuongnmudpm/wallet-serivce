package com.jpay.wallet_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error-404")
    public String test404() {
        return "pages/error/404";
    }

    @GetMapping("/error-500")
    public String test500(Model model) {
        model.addAttribute("message", "Mô phỏng lỗi: java.lang.NullPointerException: Cannot invoke 'UserEntity.getBalance()' because 'user' is null");
        return "pages/error/500";
    }
}
