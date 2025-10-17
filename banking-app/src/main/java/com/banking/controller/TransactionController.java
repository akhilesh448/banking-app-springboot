package com.banking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.service.TransactionService;
import com.banking.model.Transaction;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("http://localhost:4200")
public class TransactionController {
    
    public static class TransactionRequest {
        public int account_number;
        public int amount;
    }

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody TransactionRequest req) {
        Transaction t = transactionService.deposit(req.account_number, req.amount);
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody TransactionRequest req) {
        Transaction t = transactionService.withdraw(req.account_number, req.amount);
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }

    @GetMapping("/{account_number}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccountNumber(@PathVariable int account_number) {
        List<Transaction> transactions = transactionService.getAllTransactions(account_number);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
