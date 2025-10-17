package com.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int AccountNumber;
    String holderName;
    int balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    public List<Transaction> history = new ArrayList<>();

    public Account() {}

    public Account(String holderName, int balance) {
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return this.AccountNumber;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public int getAccountBalance() {
        return this.balance;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void displayInfo(){
        System.out.println("Hi, "+holderName+"! your a/c no. "+AccountNumber+" has balance ₹"+balance);
    }

    public void showHistory() {
        System.out.println("Transaction history for " + holderName + ":");
        for (Transaction t : history) {
            System.out.println("ID " + t.transactionId + " | Type: " + t.type + " | Amount: ₹" + t.amount);
        }
    }

}