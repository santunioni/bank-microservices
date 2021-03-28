package com.santunioni.bankaccount.balance.broker;

import com.santunioni.bankaccount.balance.domain.Withdraw;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class WithdrawConsumer {

    private static WithdrawConsumer instance = null;
    private final AccountService accountService = AccountService.getInstance();
    private String topic;
    private KafkaConsumer<String, Withdraw> consumer = loadSettings();

    private WithdrawConsumer() {
        this.consumer = loadSettings();
    }

    public static WithdrawConsumer getInstance() {
        if (instance == null) {
            instance = new WithdrawConsumer();
        }
        return instance;
    }

    public KafkaConsumer<String, Withdraw> loadSettings() {
        Properties settings = new Properties();
        settings.put("group.id", "com.santunioni.bankaccount.balance"); // Put here this app identification
        settings.put("bootstrap.servers", "http://localhost:9092"); // Put here the Kafka cluster addresses
        settings.put("auto-commit.interval.ms", 5000);
        settings.put("auto.offset.reset", "earliest");
        settings.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        settings.put("value.deserializer", "com.santunioni.bankaccount.balance.serializers.WithdrawDeserializer");
        topic = "transactions-withdraws";
        final var consumer = new KafkaConsumer<String, Withdraw>(settings);
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
                System.out.println("I am withdrawing "
                        + record.value().getValue()
                        + " from "
                        + record.value().getAccountId());
                accountService.perform(record.value());
            });
        }
    }
}
