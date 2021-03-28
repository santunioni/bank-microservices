package com.santunioni.bankaccount.balance.broker;

import com.santunioni.bankaccount.balance.domain.Withdraw;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

import static com.santunioni.bankaccount.balance.settings.Settings.brokerSettings;

public class WithdrawConsumer {

    private static WithdrawConsumer instance = null;
    private final AccountService accountService = AccountService.getInstance();
    private final String topic = "transactions-withdraws";
    private final KafkaConsumer<String, Withdraw> consumer = loadSettings();

    private WithdrawConsumer() {
    }

    public static WithdrawConsumer getInstance() {
        if (instance == null) {
            instance = new WithdrawConsumer();
        }
        return instance;
    }

    public KafkaConsumer<String, Withdraw> loadSettings() {
        var settings = brokerSettings();
        settings.put("value.deserializer", "com.santunioni.bankaccount.balance.serializers.WithdrawDeserializer");
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
