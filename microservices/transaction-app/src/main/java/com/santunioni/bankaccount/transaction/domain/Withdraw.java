package com.santunioni.bankaccount.transaction.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Withdraw implements ITransaction {

    @Id
    private String uuid;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "value")
    private double value;

    public Withdraw(String accountId, double value) {
        this.accountId = accountId;
        this.value = value;
        this.uuid = UUID.randomUUID().toString();
    }

    public Withdraw( String uuid, String accountId, double value) {
        this.accountId = accountId;
        this.value = value;
        this.uuid = uuid;
    }

    public Withdraw() {

    }

    public void generateUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getValue() {
        return value;
    }
}
