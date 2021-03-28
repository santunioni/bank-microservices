package com.santunioni.bankaccount.balance.broker;

import com.santunioni.bankaccount.balance.domain.Deposit;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class DepositConsumer {

    private static DepositConsumer instance = null;
    private final AccountService accountService = AccountService.getInstance();
    private String topic;
    private KafkaConsumer<String, Deposit> consumer = loadSettings();

    private DepositConsumer() {
        this.consumer = loadSettings();
    }

    public static DepositConsumer getInstance() {
        if (instance == null) {
            instance = new DepositConsumer();
        }
        return instance;
    }

    public KafkaConsumer<String, Deposit> loadSettings() {
        Properties settings = new Properties();
        settings.put("group.id", "com.santunioni.bankaccount.balance"); // Put here this app identification
        settings.put("bootstrap.servers", "http://localhost:9092"); // Put here the Kafka cluster addresses
        settings.put("auto-commit.interval.ms", 5000);
        settings.put("auto.offset.reset", "earliest");
        settings.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        settings.put("value.deserializer", "com.santunioni.bankaccount.balance.serializers.DepositDeserializer");
        topic = "transactions-deposits";
        final var consumer = new KafkaConsumer<String, Deposit>(settings);
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }

    public void run() {
        System.out.println("*** Starting Basic Consumer ***");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("### Stopping Basic Consumer ###");
            consumer.close();
        }));

        while (true) {
            var records = consumer
                    .poll(Duration.ofMillis(500))
                    .records(topic);

            records.forEach(record -> {
                System.out.println("I am depositing "
                        + record.value().getValue()
                        + " to "
                        + record.value().getAccountId());
                accountService.perform(record.value());
            });
        }
    }
}
