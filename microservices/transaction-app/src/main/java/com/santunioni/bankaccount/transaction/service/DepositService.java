package com.santunioni.bankaccount.transaction.service;

import com.santunioni.bankaccount.transaction.domain.Deposit;
import com.santunioni.bankaccount.transaction.producer.DepositProducer;
import com.santunioni.bankaccount.transaction.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class DepositService {

    private final Logger log = LoggerFactory
            .getLogger(DepositService.class);
    private final TransactionRepository transactionRepository =
            TransactionRepository.getInstance();
    private final DepositProducer depositProducer = DepositProducer
            .getInstance();

    private static DepositService instance;

    private DepositService() {
    }

    public static DepositService getInstance() {
        if (instance == null) {
            instance = new DepositService();
        }
        return instance;
    }

    public Response create(Deposit deposit) {
        log.debug("Creating the deposit " + deposit);

        deposit.generateUUID();
        transactionRepository.save(deposit);
        depositProducer.stream(deposit);

        return Response
                .created(URI.create(
                        deposit.getUuid().toString()))
                .build();
    }

    public Response findAll() {
        log.debug("Getting all the deposits");
        return Response.ok(transactionRepository
                .findAll(Deposit.class))
                .build();
    }

    public Response findById(String id) {
        log.debug("Getting the deposit with id: " + id);
        return Optional
                .ofNullable(transactionRepository.findById(
                        Deposit.class, id))
                .map(Response::ok)
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

}
