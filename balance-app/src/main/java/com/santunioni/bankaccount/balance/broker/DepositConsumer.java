package com.santunioni.bankaccount.balance.broker;

import com.santunioni.bankaccount.balance.domain.Deposit;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DepositConsumer {

    private final AccountService accountService;
    @Inject
    public DepositConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Incoming("transactions/deposits")
    public void deposit(Deposit deposit){
        accountService.perform(deposit);
    }

}
