package com.santunioni.bankaccount.transaction.domain;


public interface ITransaction {

    void generateUUID();

    String getUuid();

    String getAccountId();

    double getValue();
}
