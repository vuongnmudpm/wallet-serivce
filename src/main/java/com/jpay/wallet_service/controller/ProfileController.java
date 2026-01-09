package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.PasswordChangeDto;
import com.jpay.wallet_service.dto.UserProfileDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String showProfile(Model model) {
        // Giả lập dữ liệu người dùng hiện tại
        UserProfileDto user = new UserProfileDto();
        user.setFullName("Vương Developer");
        user.setEmail("vuong.dev@jpay.com");
        user.setPhoneNumber("0901234567");
        user.setUsername("vuong_developer");
        user.setKycVerified(true);

        model.addAttribute("user", user);
        return "pages/profile";
    }

    @GetMapping("/security/change-password")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("passwordData", new PasswordChangeDto());
        return "pages/change-password";
    }

    @PostMapping("/security/change-password")
    public String processChangePassword(PasswordChangeDto dto) {
        // Logic: Kiểm tra oldPassword đúng không -> Kiểm tra newPassword == confirmPassword
        System.out.println("Yêu cầu đổi mật khẩu cho User hiện tại...");
        return "redirect:/profile?success=password_changed";
    }
}