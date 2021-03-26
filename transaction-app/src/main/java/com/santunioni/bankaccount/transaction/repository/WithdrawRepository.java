package com.santunioni.bankaccount.transaction.repository;

import com.santunioni.bankaccount.transaction.domain.Withdraw;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
public class WithdrawRepository {

    @Inject
    EntityManager entityManager;

    @Transactional(REQUIRED)
    public Withdraw save(final Withdraw withdraw) {
        entityManager.persist(withdraw);
        return withdraw;
    }

    @Transactional(SUPPORTS)
    public List<Withdraw> findAll() {
        return entityManager
                .createQuery("SELECT m FROM Withdraw m", Withdraw.class)
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
