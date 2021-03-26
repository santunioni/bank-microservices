package com.santunioni.bankaccount.transaction.repository;

import com.santunioni.bankaccount.transaction.domain.Deposit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
public class DepositRepository {

    private final EntityManager entityManager;
    @Inject
    public DepositRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(REQUIRED)
    public Deposit save(final Deposit deposit) {
        entityManager.persist(deposit);
        return deposit;
    }

    @Transactional(SUPPORTS)
    public List<Deposit> findAll() {
        return entityManager
                .createQuery("SELECT m FROM Deposit m", Deposit.class)
                .getResultList();
    }

    //TODO: implement findById() method
//    @Transactional(SUPPORTS)
//    public Transfer findById(final UUID id) throws TransactionNotFoundException {
//        var optionalTransaction = Optional.ofNullable(
//                entityManager.find(Transfer.class, id));
//        if (optionalTransaction.isEmpty()) {
//            throw new TransactionNotFoundException(id);
//        }
//        return optionalTransaction.get();

//    }


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
