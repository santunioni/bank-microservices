package com.santunioni.bankaccount.balance.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private static EntityManager instance = null;

    private EntityManagerSingleton() {
    }

    public static EntityManager getInstance() {
        if (instance == null) {
            var entityManagerFactory = Persistence
                    .createEntityManagerFactory("Hibernate");
            instance = entityManagerFactory.createEntityManager();
        }
        return instance;
    }

}
