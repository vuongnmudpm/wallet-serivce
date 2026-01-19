package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.LoginRequest;
import com.jpay.wallet_service.dto.RegisterRequest;
import com.jpay.wallet_service.service.impl.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("request") RegisterRequest request,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        try {
            // Gọi hàm register mà bạn đã viết
            authService.register(request);

            // Nếu thành công: Dùng RedirectAttributes để giữ thông báo sau khi chuyển trang
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công! Bạn có thể đăng nhập ngay.");
            return "redirect:/register";

        } catch (Exception e) {
            // Nếu thất bại (trùng tên đăng nhập...): Trả về trang cũ và hiển thị lỗi
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("request", request); // Giữ lại dữ liệu người dùng đã nhập
            return "pages/register";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest  request) {
        authService.login(request);
        return ResponseEntity.ok("Đăng nhập thành công!");
    }
}
