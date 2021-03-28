package com.santunioni.bankaccount.balance.service;

import com.santunioni.bankaccount.balance.domain.Account;
import com.santunioni.bankaccount.balance.domain.Deposit;
import com.santunioni.bankaccount.balance.domain.Withdraw;
import com.santunioni.bankaccount.balance.exception.AccountNotFoundException;
import com.santunioni.bankaccount.balance.repository.AccountRepository;

import java.util.List;

public class AccountService {

    private static AccountService instance = null;
    private final AccountRepository accountRepository= AccountRepository.getInstance();

    private AccountService() {
    }

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
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
        accountRepository.deleteById(account.getAccountId());
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
