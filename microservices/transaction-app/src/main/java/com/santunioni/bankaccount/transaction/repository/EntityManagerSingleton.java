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

//        private static EntityManager entityManagerInstance = null;
//
//        private EntityManager entityManager;
//        @Inject
//        public EntityManagerSingleton(EntityManager entityManager) {
//            this.entityManager = entityManager;
//        }
//
//        private EntityManager getEntityManager() {
//            return this.entityManager;
//        }
//
//        private EntityManagerSingleton() {
//        }
//
//        public static EntityManager getInstance() {
//            if (entityManagerInstance == null) {
//                entityManagerInstance = new com.santunioni.bankaccount.transaction.repository.EntityManagerSingleton().getEntityManager();
//            }
//            return entityManagerInstance;
//        }
}