package com.banking.service;

import com.banking.model.*;
import com.banking.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction deposit(int account_number, int amount) {
        Account account = accountRepository.findById(account_number).orElse(null);
        Transaction t = new Transaction("deposit", amount, account);
        t.process();
        return transactionRepository.save(t);
    }

    public Transaction withdraw(int account_number, int amount) {
        Account account = accountRepository.findById(account_number).orElse(null);
        Transaction t = new Transaction("withdraw", amount, account);
        t.process();
        return transactionRepository.save(t);
    }

    public List<Transaction> getAllTransactions(int account_number) {
        Account account = accountRepository.findById(account_number).orElse(null);
        return transactionRepository.findByAccount(account);
    }
}
