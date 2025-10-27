package com.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    Integer accountNumber;

    String holderName;
    int balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Transaction> history = new ArrayList<>();

    public Account() {}

    public Account(String holderName, int balance) {
        this.holderName = holderName;
        this.balance = balance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getAccountBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void setHistory(List<Transaction> history) {
        this.history = history;
    }

    public void displayInfo(){
        System.out.println("Hi, "+holderName+"! your a/c no. "+accountNumber+" has balance ₹"+balance);
    }

    public void showHistory() {
        System.out.println("Transaction history for " + holderName + ":");
        for (Transaction t : history) {
            System.out.println("ID " + t.transactionId + " | Type: " + t.type + " | Amount: ₹" + t.amount);
        }
    }

}