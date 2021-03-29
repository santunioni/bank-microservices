package com.santunioni.bankaccount.transaction.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static com.santunioni.bankaccount.transaction.settings.Settings.databaseSettings;

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