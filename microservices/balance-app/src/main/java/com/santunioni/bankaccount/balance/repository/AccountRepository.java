package com.santunioni.bankaccount.balance.repository;

import com.santunioni.bankaccount.balance.domain.Account;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

public class AccountRepository {

    private static AccountRepository instance;
    private final EntityManager entityManager = EntityManagerSingleton.getInstance();

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    @Transactional(REQUIRED)
    public Account save(final Account account) {
        entityManager.persist(account);
        return account;
    }

    //    @Transactional(REQUIRED)
    public void deleteById(final String id) {
//        entityManager.remove(
//                this.findById(id)
//        );
    }

    @Transactional(SUPPORTS)
    public List<Account> findAll() {
        var criteriaQuery =
                entityManager
                        .getCriteriaBuilder()
                        .createQuery(Account.class);

        var accountRoot =
                criteriaQuery
                        .from(Account.class);

//        var inClause =
//                entityManager
//                .getCriteriaBuilder()
//                .in(accountRoot.get(Account_.nome))
//                .value(nomeAluno);

        criteriaQuery
                .select(accountRoot);
//                .where(inClause);

        try {
            return entityManager
                    .createQuery(criteriaQuery)
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    @Transactional(SUPPORTS)
    public Optional<Account> findById(String id) {
//        return Optional.ofNullable(
//                entityManager
//                .createQuery("SELECT id="+id+" FROM Account"
//                        , Account.class)
//                .getSingleResult());
//    }
        return Optional.ofNullable(null);
    }
}
