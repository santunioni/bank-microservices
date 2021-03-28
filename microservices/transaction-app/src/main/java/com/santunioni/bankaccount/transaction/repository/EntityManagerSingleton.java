package com.santunioni.bankaccount.transaction.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private static EntityManager entityManagerInstance = null;

    private EntityManagerSingleton() {
    }

    public static EntityManager getInstance() {
        if (entityManagerInstance == null) {
            var entityManagerFactory = Persistence
                    .createEntityManagerFactory("Hibernate");
            entityManagerInstance = entityManagerFactory.createEntityManager();
        }
        return entityManagerInstance;
    }

}
