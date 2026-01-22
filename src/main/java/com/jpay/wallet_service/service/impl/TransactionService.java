package com.jpay.wallet_service.service.impl;

import com.jpay.wallet_service.dto.DepositRequest;
import com.jpay.wallet_service.dto.TransactionDto;
import com.jpay.wallet_service.entity.FraudAlert;
import com.jpay.wallet_service.entity.Transaction;
import com.jpay.wallet_service.entity.User;
import com.jpay.wallet_service.repository.FraudAlertRepository;
import com.jpay.wallet_service.repository.TransactionRepository;
import com.jpay.wallet_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final FraudAlertRepository fraudAlertRepository;

    @Transactional
    public void deposit(String username, DepositRequest request) {
        // 1. Lấy User (Sử dụng Optional để an toàn)
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }


        // 2. Xử lý số dư (Chống lỗi Null)
        BigDecimal currentBalance = (user.getBalance() != null) ? user.getBalance() : BigDecimal.ZERO;
        BigDecimal newBalance = currentBalance.add(request.getAmount());

        user.setBalance(newBalance);
        userRepository.save(user);

        // 3. Tạo bản ghi giao dịch (History)
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(request.getAmount());
        transaction.setCurrency("VND");
        transaction.setMerchantInfo("Nạp tiền vào ví SentinelPay");
        transaction.setTrxStatus("APPROVED");
        transaction.setCreatedAt(java.time.OffsetDateTime.now());
        transactionRepository.save(transaction);
    }
}