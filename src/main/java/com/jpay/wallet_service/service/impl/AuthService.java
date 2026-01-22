package com.jpay.wallet_service.service.impl;

import com.jpay.wallet_service.config.JwtProvider;
import com.jpay.wallet_service.dto.LoginRequest;
import com.jpay.wallet_service.dto.LoginResponse;
import com.jpay.wallet_service.dto.RegisterRequest;
import com.jpay.wallet_service.entity.User;
import com.jpay.wallet_service.exception.DuplicateResourceException;
import com.jpay.wallet_service.exception.LoginFailedException;
import com.jpay.wallet_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Tên đăng nhập này đã có người dùng rồi bạn ơi!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setUserStatus("ACTIVE");

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        try {
            // Khi lệnh này chạy, nó sẽ tự gọi sang CustomUserDetailsService ở Bước 1
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Xác thực thành công mới lấy user ra check status
            Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
            User user = null;
            if (userOptional.isPresent()) {
                user = userOptional.get();
            }
            if ("LOCKED".equals(user.getUserStatus())) {
                throw new RuntimeException("Tài khoản của bạn hiện đang bị khóa!");
            }

            String token = jwtProvider.generateToken(auth);
            return new LoginResponse(token, "Đăng nhập thành công!");

        } catch (BadCredentialsException e) {
            throw new LoginFailedException("Tên đăng nhập hoặc mật khẩu không chính xác nha bạn ơi!");        }
    }
}
