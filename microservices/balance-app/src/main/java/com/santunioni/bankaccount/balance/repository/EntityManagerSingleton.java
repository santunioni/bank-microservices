package com.santunioni.bankaccount.balance.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static com.santunioni.bankaccount.balance.settings.Settings.databaseSettings;

public class EntityManagerSingleton {

    private static EntityManager instance = null;

    private EntityManagerSingleton() {
    }

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = Persistence
                    .createEntityManagerFactory(
                            "Hibernate",
                            databaseSettings())
                    .createEntityManager();
        }
        return instance;
    }
}
