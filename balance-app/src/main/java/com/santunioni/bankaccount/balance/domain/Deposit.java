package com.santunioni.bankaccount.balance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Deposit {

    private UUID uuid;
    private String accountId;
    private double value;

}
