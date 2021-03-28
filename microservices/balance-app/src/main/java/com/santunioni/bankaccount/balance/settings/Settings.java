package com.santunioni.bankaccount.balance.settings;

import java.util.Properties;

public class Settings {

    public static Properties brokerSettings() {
        Properties settings = new Properties();
        settings.put("group.id", "com.santunioni.bankaccount.balance"); // Put here this app identification
        settings.put("bootstrap.servers", "http://localhost:9092"); // Put here the Kafka cluster addresses
        settings.put("auto-commit.interval.ms", 5000);
        settings.put("auto.offset.reset", "earliest");
        settings.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return settings;
    }

}
