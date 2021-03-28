package com.santunioni.bankaccount.transaction.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santunioni.bankaccount.transaction.domain.Deposit;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DepositSerializer implements Serializer<Deposit> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String arg0, Deposit arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}