package com.santunioni.bankaccount.transaction.recorder;

import com.santunioni.bankaccount.transaction.domain.Deposit;

import com.santunioni.bankaccount.transaction.domain.Withdraw;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class TransactionRecorder {

    private final Logger log = Logger.getLogger(TransactionRecorder.class.getName());

    @Incoming("transactions/deposits")
    public KafkaRecord<String, Deposit> recordDeposit(Deposit deposit) {
        log.info("Sending transaction: " + deposit.toString());
        return KafkaRecord.of(UUID.randomUUID().toString(), deposit);
    }

    @Incoming("transactions/withdraws")
    public KafkaRecord<String, Withdraw> recordWithdraw(Withdraw withdraw) {
        log.info("Sending transaction: " + withdraw.toString());
        return KafkaRecord.of(UUID.randomUUID().toString(), withdraw);
    }

}