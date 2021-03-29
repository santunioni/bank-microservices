package com.santunioni.bankaccount.balance.settings;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class Settings {

    public static Properties brokerSettings() {
        Properties props = new Properties();
        props.put("group.id", "com.santunioni.bankaccount.balance"); // Put here this app identification
        props.put("auto-commit.interval.ms", 5000);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092"); // Put here the Kafka cluster addresses

        return props;
    }

    public static Properties databaseSettings() {
        var props = new Properties();
        props.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        props.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost/balances");
        props.put("javax.persistence.jdbc.user", "postgres");
        props.put("javax.persistence.jdbc.password", "postgresql");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        return props;
    }

}
