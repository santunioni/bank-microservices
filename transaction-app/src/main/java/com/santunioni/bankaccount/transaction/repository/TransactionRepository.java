package com.santunioni.bankaccount.transaction.repository;

import com.santunioni.bankaccount.transaction.domain.ITransaction;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

public class TransactionRepository {

    private static TransactionRepository instance;
    private final EntityManager entityManager = EntityManagerSingleton.getInstance();

    private TransactionRepository() {
    }

    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    @Transactional(REQUIRED)
    public void save(final ITransaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }

    //TODO: fix findAll() method to enable queries of all transactions types
    @Transactional(SUPPORTS)
    public <Type> List<Type> findAll(Type entityClass) {
        return (List<Type>) entityManager
                .createQuery("SELECT m FROM "
                        +((Class) entityClass).getSimpleName()
                        +" m", (Class) entityClass)
                .getResultList();
    }

    //TODO: implement findById() method
    @Transactional(SUPPORTS)
    public <Type> Optional<Type> findById(Type entityClass, String id) {
        var optionalTransaction = Optional.ofNullable(
                entityManager.find(
                        (Class) entityClass,
                        id));
        return (Optional<Type>) optionalTransaction;
    }


    // TODO: implement findByAccount() method, which requires
    //  redesign the arch for Accounts database
//    @Transactional(SUPPORTS)
//    public List<Transfer> findByAccount() {
//        return new ArrayList<Transfer>();
//    }

    //TODO: implement update() method
//    @Transactional(REQUIRED)
//    public Transfer update(final Transfer transfer) {
//        return entityManager.merge(transfer);
//    }

    //TODO: implement deleteById() method
//    @Transactional(REQUIRED)
//    public void deleteById(final UUID id) throws TransactionNotFoundException {
//        var transactionFound = Optional.ofNullable(
//                findById(id)
//        );
//        if(transactionFound.isPresent()) {
//            entityManager.remove(transactionFound);
//        }
//    }
}
