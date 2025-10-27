package com.banking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.service.AccountService;
import com.banking.model.Account;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("http://localhost:3000")
public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/new")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountInfo(@PathVariable int account_number) {
        Account accountDetails = accountService.getAccountByNumber(account_number);
        if (accountDetails != null) {
            return new ResponseEntity<>(accountDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> allAccounts = accountService.getAllAccounts();
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}/delete")
    public ResponseEntity<Void> deleteAccountByNumber(@PathVariable int account_number) {
        boolean isDeleted = accountService.deleteAccountByNumber(account_number);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
