package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.TwoFactorDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @GetMapping("/security/setup-2fa")
    public String showSetup2fa(Model model) {
        TwoFactorDto dto = new TwoFactorDto();
        dto.setSecretKey("JBSWY3DPEHPK3PXP"); // Mã giả lập để hiển thị
        model.addAttribute("twoFactorData", dto);
        return "pages/setup-2fa";
    }

    @PostMapping("/security/setup-2fa")
    public String processSetup2fa(TwoFactorDto dto) {
        System.out.println("Đang xác thực mã 2FA: " + dto.getVerificationCode());
        // Sau này sẽ thêm logic kiểm tra mã 6 số ở đây
        return "redirect:/"; // Thành công thì về dashboard
    }
}