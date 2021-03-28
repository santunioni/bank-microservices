package com.santunioni.bankaccount.transaction.settings;

import java.util.Properties;

public class Settings {
    public static Properties brokerSettings(String value) {
        Properties settings = new Properties();

        settings.put("client.id", "com.santunioni.bankaccount.transactions." + value); // Put here this app identification
        settings.put("bootstrap.servers", "http://localhost:9092"); // Put here the Kafka cluster addresses
        settings.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        settings.put("value.serializer", "com.santunioni.bankaccount.transaction.serializers." + value + "Serializer");
        return settings;
    }
}
