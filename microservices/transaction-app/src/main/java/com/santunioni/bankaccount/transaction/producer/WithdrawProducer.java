package com.santunioni.bankaccount.transaction.producer;

import com.santunioni.bankaccount.transaction.domain.Withdraw;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.logging.Logger;

import static com.santunioni.bankaccount.transaction.settings.Settings.brokerSettings;

public class WithdrawProducer {

    private static WithdrawProducer instance;
    private final Logger log = Logger.getLogger(WithdrawProducer.class.getName());
    private final KafkaProducer<String, Withdraw> producerWithdraw =
            new KafkaProducer<>(brokerSettings("Withdraw"));

    private WithdrawProducer() {
    }

    public static WithdrawProducer getInstance() {
        if (instance == null) {
            instance = new WithdrawProducer();
        }
        return instance;
    }


    public void stream(Withdraw withdraw) {
        final String topic = "transactions-withdraws";
        final var record = new ProducerRecord<String, Withdraw>(topic, withdraw);
        producerWithdraw.send(record);
    }
}
