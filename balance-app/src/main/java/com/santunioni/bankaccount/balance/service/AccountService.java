package com.santunioni.bankaccount.balance.service;

import com.santunioni.bankaccount.balance.domain.Account;
import com.santunioni.bankaccount.balance.domain.Deposit;
import com.santunioni.bankaccount.balance.domain.Withdraw;
import com.santunioni.bankaccount.balance.exception.AccountNotFoundException;
import com.santunioni.bankaccount.balance.repository.AccountRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AccountService {

    private final AccountRepository accountRepository;

    @Inject
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    public Account findById(String id) {
        var account = accountRepository.findById(id);
        if(account.isEmpty()) {
            throw new AccountNotFoundException(id);
        }
        return account.get();
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public void delete (Account account) {
        accountRepository.deleteById(account.getAccountID());
    }

    public void perform(Withdraw withdraw) {
        try {
            var account = findById(withdraw.getAccountId());
            accountRepository.save(account);
        } catch (AccountNotFoundException e) {
            var account = new Account(
                    withdraw.getAccountId(),
                    -withdraw.getValue());
            accountRepository.save(account);
        }
    }

    public void perform(Deposit deposit) {
        try {
            var account = findById(deposit.getAccountId());
            accountRepository.save(account);
        } catch (AccountNotFoundException e) {
            var account = new Account(
                    deposit.getAccountId(),
                    +deposit.getValue());
            accountRepository.save(account);
        }
    }
}
