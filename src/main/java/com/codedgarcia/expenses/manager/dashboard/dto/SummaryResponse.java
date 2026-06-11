package com.codedgarcia.expenses.manager.dashboard.dto;

import com.codedgarcia.expenses.manager.transaction.dto.TransactionResponse;

import java.math.BigDecimal;
import java.util.List;

public record SummaryResponse(
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal balance,
        List<TransactionResponse> recentTransactions
) {
}
