package com.jpay.wallet_service.service.impl;

import com.jpay.wallet_service.entity.User;
import com.jpay.wallet_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm user trong DB của bạn
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng: " + username);
        }

        // Chuyển đổi User của bạn thành UserDetails mà Spring Security hiểu được
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash()) // Mật khẩu đã mã hóa
                .authorities("USER") // Phân quyền mặc định
                .build();
    }
}
