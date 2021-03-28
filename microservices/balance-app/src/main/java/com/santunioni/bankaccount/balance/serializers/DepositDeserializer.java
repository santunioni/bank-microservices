package com.santunioni.bankaccount.balance.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santunioni.bankaccount.balance.domain.Deposit;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class DepositDeserializer implements Deserializer<Deposit> {

    @Override public void close() {

    }

    @Override public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public Deposit deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Deposit object = null;
        try {
            object = mapper.readValue(arg1, Deposit.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return object;
    }

}