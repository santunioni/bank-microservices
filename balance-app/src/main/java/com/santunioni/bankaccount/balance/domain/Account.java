package com.santunioni.bankaccount.balance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "balance")
    private double balance;

    public String getAccountId() {
        return accountId;
    }

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
