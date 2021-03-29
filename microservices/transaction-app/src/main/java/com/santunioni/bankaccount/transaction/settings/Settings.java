package com.santunioni.bankaccount.transaction.settings;

//import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
//import io.confluent.kafka.serializers.KafkaAvroSerializer;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

//import org.apache.kafka.common.serialization.StringSerializer;

public class Settings {
    public static Properties brokerSettings(String value) {
        Properties props = new Properties();

//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "com.santunioni.bankaccount.transactions." + value); // Put here this app identification
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092"); // Put here the Kafka cluster addresses
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.santunioni.bankaccount.transaction.serializers." + value + "Serializer");
        return props;
    }

    public static Properties databaseSettings() {
        var props = new Properties();
        props.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        props.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost/transactions");
        props.put("javax.persistence.jdbc.user", "postgres");
        props.put("javax.persistence.jdbc.password", "postgresql");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        return props;
    }
}