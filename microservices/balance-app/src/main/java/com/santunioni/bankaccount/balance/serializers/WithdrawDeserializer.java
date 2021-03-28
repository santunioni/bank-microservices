package com.santunioni.bankaccount.balance.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santunioni.bankaccount.balance.domain.Withdraw;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class WithdrawDeserializer implements Deserializer<Withdraw> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public Withdraw deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Withdraw object = null;
        try {
            object = mapper.readValue(arg1, Withdraw.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return object;
    }
}