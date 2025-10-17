package com.banking.service;

import com.banking.model.Account;
import com.banking.repository.AccountRepository;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountByNumber(int account_number){
        for(Account a: accountRepository.findAll()){
            int accountNumber = a.getAccountNumber();
            if(accountNumber == account_number){
                return a;
            }
        }
        return null;
    }

    public Account createAccount(Account account){
        Account a = new Account();
        a.setHolderName(account.getHolderName());
        a.setBalance(account.getAccountBalance());
        return accountRepository.save(a);
    }

    public boolean deleteAccountByNumber(int accountNumber) {
        if (accountRepository.existsById(accountNumber)) {
            accountRepository.deleteById(accountNumber);
            return true;
        }
        return false;
    }
}
