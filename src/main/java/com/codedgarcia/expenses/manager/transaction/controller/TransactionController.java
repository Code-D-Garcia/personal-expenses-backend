package com.codedgarcia.expenses.manager.transaction.controller;

import com.codedgarcia.expenses.manager.category.entity.Type;
import com.codedgarcia.expenses.manager.transaction.dto.CreateTransactionRequest;
import com.codedgarcia.expenses.manager.transaction.dto.TransactionResponse;
import com.codedgarcia.expenses.manager.transaction.dto.UpdateTransactionRequest;
import com.codedgarcia.expenses.manager.transaction.service.TransactionService;
import com.codedgarcia.expenses.manager.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @AuthenticationPrincipal User user) {

        List<TransactionResponse> trans = transactionService.getTransactions(user.getId());
        return ResponseEntity.ok(trans);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<TransactionResponse>> getPaginatedTransactions(
            @AuthenticationPrincipal User user,
            Pageable pageable) {
        return ResponseEntity.ok(transactionService.getTransactions(user.getId(), pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TransactionResponse>> getTransactionByType(
            @AuthenticationPrincipal User user,
            @RequestParam Type type) {

        List<TransactionResponse> response = transactionService.getTransactionsByUserIdAndType(
                user.getId(),
                type
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-paginated")
    public ResponseEntity<Page<TransactionResponse>> getPaginatedTransactionByType(
            @AuthenticationPrincipal User user,
            @RequestParam Type type,
            Pageable pageable) {
        return ResponseEntity.ok(transactionService.getTransactionsByUserIdAndType(user.getId(), type, pageable));
    }

    @GetMapping("/date-range")
    public ResponseEntity<Page<TransactionResponse>> getTransactionsByDateRange(
            @AuthenticationPrincipal User user,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Pageable pageable) {
        return ResponseEntity.ok(transactionService.getTransactionsByDateRange(user.getId(), start, end, pageable));
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @AuthenticationPrincipal User user,
            @RequestBody CreateTransactionRequest request){

        TransactionResponse response = transactionService.createTransaction(user.getId(),  request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<TransactionResponse> getTransactionById(
            @AuthenticationPrincipal User user,
            @RequestParam Long id){
        TransactionResponse response = transactionService.getTransaction(user.getId(), id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<TransactionResponse> updateTransaction(
            @AuthenticationPrincipal User user,
            @RequestParam Long id,
            @RequestBody UpdateTransactionRequest request
    ){
        TransactionResponse updateResponse = transactionService.updateTransaction(user.getId(), id, request );
        return ResponseEntity.ok(updateResponse);
    }

    @DeleteMapping
    public void deleteTransactionById(
            @AuthenticationPrincipal User user,
            @RequestParam Long id
    ){
        transactionService.deleteTransaction(user.getId(), id);
    }
}
