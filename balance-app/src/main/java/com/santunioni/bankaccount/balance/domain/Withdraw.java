package com.santunioni.bankaccount.balance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Withdraw {

    private final UUID uuid;
    private final String accountId;
    private final double value;

}
