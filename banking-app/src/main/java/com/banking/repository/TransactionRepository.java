package com.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Account;
import com.banking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    List<Transaction> findByAccount(Account account_number);
}
