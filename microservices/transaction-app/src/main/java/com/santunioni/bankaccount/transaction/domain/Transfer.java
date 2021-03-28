package com.santunioni.bankaccount.transaction.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Transfer implements ITransaction, ITransfer {

    @Id
    private String uuid = UUID.randomUUID().toString();
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "destination_account_id")
    private String toAccountID;
    @Column(name = "value")
    private double value;

    public Transfer() {

    }

    public void generateUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }

    public Transfer(String uuid, String accountId, String toAccountID, double value) {
        this.uuid = uuid;
        this.accountId = accountId;
        this.toAccountID = toAccountID;
        this.value = value;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getToAccountID() {
        return toAccountID;
    }

    public double getValue() {
        return value;
    }
}
