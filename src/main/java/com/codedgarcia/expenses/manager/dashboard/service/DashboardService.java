package com.codedgarcia.expenses.manager.dashboard.service;

import com.codedgarcia.expenses.manager.category.entity.Type;
import com.codedgarcia.expenses.manager.dashboard.dto.SummaryResponse;
import com.codedgarcia.expenses.manager.transaction.dto.TransactionResponse;
import com.codedgarcia.expenses.manager.transaction.mapper.TransactionMapper;
import com.codedgarcia.expenses.manager.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public SummaryResponse getSummary(Long userId) {
        List<TransactionResponse> allTransactions = transactionRepository.findByUserId(userId)
                .stream()
                .map(transactionMapper::toResponse)
                .toList();

        BigDecimal totalIncome = allTransactions.stream()
                .filter(t -> t.categoryType() == Type.INCOME && t.amount() != null)
                .map(TransactionResponse::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = allTransactions.stream()
                .filter(t -> t.categoryType() == Type.EXPENSE && t.amount() != null)
                .map(TransactionResponse::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpenses);

        List<TransactionResponse> recentTransactions = allTransactions.stream()
                .filter(t -> t.transactionDate() != null)
                .sorted((t1, t2) -> t2.transactionDate().compareTo(t1.transactionDate()))
                .limit(5)
                .toList();

        return new SummaryResponse(
                totalIncome,
                totalExpenses,
                balance,
                recentTransactions
        );
    }
}