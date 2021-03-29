package com.santunioni.bankaccount.balance.domain;

import lombok.Data;

@Data
public class Deposit {

    private String uuid;
    private String accountId;
    private double value;

    public String getAccountId() {
        return accountId;
    }

    public double getValue() {
        return value;
    }

    public double getUuid() {
        return value;
    }

}
