package com.jpay.wallet_service.controller;

import com.jpay.wallet_service.dto.TransactionDto;
import com.jpay.wallet_service.dto.UserRegistrationDto;
import com.jpay.wallet_service.entity.User;
import com.jpay.wallet_service.repository.UserRepository;
import com.jpay.wallet_service.service.impl.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    @GetMapping("/dashboard")
    public String index(Model model, Principal principal) {
        // 1. Kiểm tra đăng nhập
        if (principal == null) {
            return "redirect:/login";
        }

        // 2. Lấy thông tin user từ Database dựa trên username
        String username = principal.getName();
        // Bây giờ findByUsername trả về Optional, nên hàm orElseThrow sẽ được nhận diện
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        // 3. Đẩy dữ liệu User ra Model
        model.addAttribute("user", user);

        // 4. Lấy và đẩy danh sách giao dịch gần đây ra Model
        // Đảm bảo bạn đã sửa getRecentTransactions nhận vào UUID như bước trước

        return "pages/dashboard";
    }
}