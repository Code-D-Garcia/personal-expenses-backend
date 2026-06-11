package com.codedgarcia.expenses.manager.transaction.repository;

import com.codedgarcia.expenses.manager.category.entity.Type;
import com.codedgarcia.expenses.manager.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdAndUserId(
            Long transactionId,
            Long userId);

    List<Transaction> findByUserIdAndCategoryId(
            Long userId,
            Long categoryId);

    List<Transaction> findByUserIdAndTransactionDateBetween(
            Long userId,
            LocalDate start,
            LocalDate end);

    Page<Transaction> findByUserIdAndTransactionDateBetween(
            Long userId,
            LocalDate start,
            LocalDate end,
            Pageable pageable);

    List<Transaction> findByUserId(Long userId);

    Page<Transaction> findByUserId(Long userId, Pageable pageable);

    List<Transaction> findByUserIdAndCategoryType(
            Long userId,
            Type type);

    Page<Transaction> findByUserIdAndCategoryType(
            Long userId,
            Type type,
            Pageable pageable);
}
