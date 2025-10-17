package com.banking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int transactionId;
    String type;
    int amount;
    @ManyToOne
    @JoinColumn(name = "account_number")
    @JsonBackReference
    private Account account;

    public Transaction() {}

    public Transaction(String type, int amount, Account account) {
        this.type = type;
        this.amount = amount;
        this.account = account;
    }

    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public void process(){
        if(type.equals("deposit")) {
            account.balance = account.balance + amount;
            System.out.println("Deposit successfull, new balance = ₹"+account.balance);
        }
        else if(type.equals("withdraw")) {
            if(account.balance >= amount){
                account.balance = account.balance - amount;
            }
            System.out.println("Withdraw successfull, new balance = ₹"+account.balance);
        }
        account.history.add(this);
    }

}
