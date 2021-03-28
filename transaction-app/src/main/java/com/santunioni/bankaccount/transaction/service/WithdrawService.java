package com.santunioni.bankaccount.transaction.service;

import com.santunioni.bankaccount.transaction.domain.Withdraw;
import com.santunioni.bankaccount.transaction.producer.WithdrawProducer;
import com.santunioni.bankaccount.transaction.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class WithdrawService {

    private final Logger log = LoggerFactory
            .getLogger(WithdrawService.class);
    private final TransactionRepository transactionRepository =
            TransactionRepository.getInstance();
    private final WithdrawProducer withdrawProducer = WithdrawProducer
            .getInstance();

    private static WithdrawService instance;

    private WithdrawService() {
    }

    public static WithdrawService getInstance() {
        if (instance == null) {
            instance = new WithdrawService();
        }
        return instance;
    }

    public Response create(Withdraw withdraw) {
        log.debug("Creating the withdraw " + withdraw);

        withdraw.generateUUID();
        transactionRepository.save(withdraw);
        withdrawProducer.stream(withdraw);

        return Response
                .created(URI.create(
                        withdraw.getUuid().toString()))
                .build();
    }

    public Response findAll() {
        log.debug("Getting all the withdraws");
        return Response.ok(transactionRepository
                .findAll(Withdraw.class))
                .build();
    }

    public Response findById(String id) {
        log.debug("Getting the withdraw with id: " + id);
        return Optional
                .ofNullable(transactionRepository.findById(
                        Withdraw.class, id))
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

}
