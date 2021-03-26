package com.santunioni.bankaccount.balance.broker;

import com.santunioni.bankaccount.balance.domain.Withdraw;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class WithdrawConsumer {

    private final AccountService accountService;
    @Inject
    public WithdrawConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Incoming("transactions/deposits")
    public void withdraw(Withdraw withdraw){
        accountService.perform(withdraw);
    }

}
