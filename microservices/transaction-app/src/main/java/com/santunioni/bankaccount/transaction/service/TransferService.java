package com.santunioni.bankaccount.transaction.service;

import com.santunioni.bankaccount.transaction.domain.Deposit;
import com.santunioni.bankaccount.transaction.domain.Transfer;
import com.santunioni.bankaccount.transaction.domain.Withdraw;
import com.santunioni.bankaccount.transaction.repository.TransactionRepository;
import com.santunioni.bankaccount.transaction.resource.TransferResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class TransferService {

    private final Logger log = LoggerFactory.getLogger(TransferResource.class);
    private final TransactionRepository transactionRepository =
            TransactionRepository.getInstance();
    private final DepositService depositService = DepositService.getInstance();
    private final WithdrawService withdrawService = WithdrawService.getInstance();

    private static TransferService instance;

    private TransferService() {
    }

    public static TransferService getInstance() {
        if (instance == null) {
            instance = new TransferService();
        }
        return instance;
    }

    public Response create(Transfer transfer) {
        transfer.generateUUID();
        log.debug("Creating the transaction " + transfer);

        withdrawService.create(new Withdraw(
                transfer.getUuid(),
                transfer.getAccountId(),
                transfer.getValue()));

        depositService.create(new Deposit(
                transfer.getUuid(),
                transfer.getToAccountID(),
                transfer.getValue()));

        transactionRepository.save(transfer);

        return Response
                .created(URI.create(transfer.getUuid().toString()))
                .build();
    }

    public Response findAll() {
        log.debug("Getting all the transfers");
        return Response.ok(transactionRepository
                .findAll(Transfer.class))
                .build();
    }

    public Response findById(String id) {
        log.debug("Getting the transfer with id: " + id);
        return Optional
                .ofNullable(transactionRepository.findById(
                        Transfer.class, id))
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }
}
