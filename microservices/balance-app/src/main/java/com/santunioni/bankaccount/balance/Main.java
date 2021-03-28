package com.santunioni.bankaccount.balance;

import com.santunioni.bankaccount.balance.broker.DepositConsumer;
import com.santunioni.bankaccount.balance.broker.WithdrawConsumer;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.concurrent.Executors;

@QuarkusMain
public class Main {

    public static void main(String ... args) {
        System.out.println("Running main method");

        var executors = Executors.newFixedThreadPool(2);
        executors.submit(DepositConsumer.getInstance()::run);
        executors.submit(WithdrawConsumer.getInstance()::run);

        Quarkus.run(args);
    }
}
