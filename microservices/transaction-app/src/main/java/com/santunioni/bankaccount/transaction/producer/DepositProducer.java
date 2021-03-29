package com.santunioni.bankaccount.transaction.producer;

import com.santunioni.bankaccount.transaction.domain.Deposit;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.logging.Logger;

import static com.santunioni.bankaccount.transaction.settings.Settings.brokerSettings;

public class DepositProducer {

    private static DepositProducer instance;
    private final Logger log = Logger.getLogger(DepositProducer.class.getName());
    private final KafkaProducer<String, Deposit> producerDeposit =
            new KafkaProducer<>(brokerSettings("Deposit"));

    private DepositProducer() {
    }

    public static DepositProducer getInstance() {
        if (instance == null) {
            instance = new DepositProducer();
        }
        return instance;
    }

    public void stream(Deposit deposit) {
        final String topic = "transactions-deposits";
        final var record = new ProducerRecord<String, Deposit>(topic, deposit);
        producerDeposit.send(record);
    }

}
