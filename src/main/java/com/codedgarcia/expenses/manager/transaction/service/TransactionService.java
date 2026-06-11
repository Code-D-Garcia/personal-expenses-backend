package com.codedgarcia.expenses.manager.transaction.service;

import com.codedgarcia.expenses.manager.category.entity.Type;
import com.codedgarcia.expenses.manager.transaction.dto.CreateTransactionRequest;
import com.codedgarcia.expenses.manager.transaction.dto.TransactionResponse;
import com.codedgarcia.expenses.manager.transaction.dto.UpdateTransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponse createTransaction(
            Long userId,
            CreateTransactionRequest request);

    TransactionResponse getTransaction(
            Long userId,
            Long transactionId);

    List<TransactionResponse> getTransactions(
            Long userId);

    Page<TransactionResponse> getTransactions(Long userId, Pageable pageable);

    List<TransactionResponse> getTransactionsByUserIdAndType(
            Long userId,
            Type type
    );

    Page<TransactionResponse> getTransactionsByUserIdAndType(Long userId, Type type, Pageable pageable);

    Page<TransactionResponse> getTransactionsByDateRange(Long userId, LocalDate start, LocalDate end, Pageable pageable);

    TransactionResponse updateTransaction(
            Long userId,
            Long transactionId,
            UpdateTransactionRequest request);

    void deleteTransaction(
            Long userId,
            Long transactionId);

}
