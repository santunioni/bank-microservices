package com.santunioni.bankaccount.balance.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class AccountNotFoundException extends ClientErrorException {

    public AccountNotFoundException(String id) {
        super(String.format("Account with id %s not found.", id),
                Response.Status.NOT_FOUND);
    }

}