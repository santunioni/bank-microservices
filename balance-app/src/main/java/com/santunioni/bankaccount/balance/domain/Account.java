package com.santunioni.bankaccount.balance.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Getter
    @Column(name = "account_id")
    private String accountID;

    @Getter
    @Setter
    @Column(name = "balance")
    private double balance;

}
