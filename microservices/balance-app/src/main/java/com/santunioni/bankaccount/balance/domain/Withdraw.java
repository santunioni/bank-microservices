package com.santunioni.bankaccount.balance.domain;

import lombok.Data;

@Data
public class Withdraw {

    private String uuid;
    private String accountId;
    private double value;

    public String getAccountId() {
        return accountId;
    }

    public double getValue() {
        return value;
    }
}
