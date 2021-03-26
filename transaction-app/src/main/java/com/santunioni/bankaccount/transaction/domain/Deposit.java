package com.santunioni.bankaccount.transaction.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Deposit {

    @Id
    private String uuid;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "value")
    private double value;

    public Deposit(String accountId, double value) {
        this.accountId = accountId;
        this.value = value;
        this.uuid = UUID.randomUUID().toString();
    }

}
