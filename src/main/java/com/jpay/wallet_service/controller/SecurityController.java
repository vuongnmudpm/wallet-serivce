package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.PasswordChangeDto;
import com.jpay.wallet_service.dto.TwoFactorDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/setup-2fa")
    public String showSetup2fa(Model model) {
        TwoFactorDto dto = new TwoFactorDto();
        dto.setSecretKey("JBSWY3DPEHPK3PXP"); // Mã giả lập để hiển thị
        model.addAttribute("twoFactorData", dto);
        return "pages/setup-2fa";
    }

    @PostMapping("/setup-2fa")
    public String processSetup2fa(TwoFactorDto dto) {
        System.out.println("Đang xác thực mã 2FA: " + dto.getVerificationCode());
        // Sau này sẽ thêm logic kiểm tra mã 6 số ở đây
        return "redirect:/"; // Thành công thì về dashboard
    }

    @GetMapping("/change-password")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("passwordData", new PasswordChangeDto());
        return "pages/change-password";
    }

    @PostMapping("/change-password")
    public String processChangePassword(PasswordChangeDto dto) {
        // Logic: Kiểm tra oldPassword đúng không -> Kiểm tra newPassword == confirmPassword
        System.out.println("Yêu cầu đổi mật khẩu cho User hiện tại...");
        return "redirect:/profile?success=password_changed";
    }
}