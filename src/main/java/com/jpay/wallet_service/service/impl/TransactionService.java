package com.jpay.wallet_service.service.impl;

import com.jpay.wallet_service.dto.DepositRequest;
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

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final FraudAlertRepository fraudAlertRepository;
    private final UserRepository userRepository;

    @Transactional
    public Transaction processTransaction(Transaction trx) {
        // Logic kiểm tra gian lận giả lập
        if (trx.getAmount().compareTo(new BigDecimal("20000000")) > 0) {
            trx.setTrxStatus("REVIEW");
            trx.setFraudScore(65);
            transactionRepository.save(trx);

            // Tạo cảnh báo
            FraudAlert alert = new FraudAlert();
            alert.setTransaction(trx);
            alert.setReason("Giao dịch vượt ngưỡng 20M");
            alert.setSeverity("MEDIUM");
            fraudAlertRepository.save(alert);
        } else {
            trx.setTrxStatus("APPROVED");
        }
        return transactionRepository.save(trx);
    }

    @Transactional
    public void deposit(String username, DepositRequest request) {
        User user = userRepository.findByUsername(username);
        BigDecimal newBalance = user.getBalance().add(request.getAmount());
        user.setBalance(newBalance);
        userRepository.save(user);

        // 3. Tạo bản ghi giao dịch (Lịch sử)
        Transaction transaction = new Transaction();
        transaction.setUser(user); // Gắn với UserId (UUID)
        transaction.setAmount(request.getAmount());
        transaction.setCurrency("VND");
        transaction.setMerchantInfo("Nạp tiền vào ví SentinelPay");
        transaction.setTrxStatus("APPROVED"); // Thường nạp tiền mặc định là thành công
        transaction.setFraudScore(0); // Nạp tiền ít rủi ro

        transactionRepository.save(transaction);
    }
}