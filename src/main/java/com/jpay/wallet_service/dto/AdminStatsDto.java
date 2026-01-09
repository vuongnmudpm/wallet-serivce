package com.jpay.wallet_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminStatsDto {
    private long totalUsers;
    private double totalSystemBalance;
    private long pendingFrauds;
    private long totalTransactionsToday;
    private double revenueToday; // Phí giao dịch thu được
}