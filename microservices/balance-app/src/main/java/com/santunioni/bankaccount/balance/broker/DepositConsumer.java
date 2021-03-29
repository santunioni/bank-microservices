package com.santunioni.bankaccount.balance.broker;

import com.santunioni.bankaccount.balance.domain.Deposit;
import com.santunioni.bankaccount.balance.service.AccountService;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

import static com.santunioni.bankaccount.balance.settings.Settings.brokerSettings;

public class DepositConsumer {

    private static DepositConsumer instance = null;
//    private final AccountService accountService = AccountService.getInstance();
    private final String topic = "transactions-deposits";
    private final KafkaConsumer<String, Deposit> consumer = loadSettings();

    private DepositConsumer() {
    }

    public static DepositConsumer getInstance() {
        if (instance == null) {
            instance = new DepositConsumer();
        }
        return instance;
    }

    public KafkaConsumer<String, Deposit> loadSettings() {
        var settings = brokerSettings();
        settings.put("value.deserializer", "com.santunioni.bankaccount.balance.serializers.DepositDeserializer");
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
//                accountService.perform(record.value());
            });
        }
    }
}
